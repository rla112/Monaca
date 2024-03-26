import styled from "styled-components";
import { NavBar } from "../NavBar";
import { UserBar } from "../Student/UserBar";
import { NavLink, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { MonacaInfo } from "../MonacaInfo";

import Tc from "../image/TrashCan.jpg";

const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "GmarketSansMedium";
`;

const Wrapper = styled.div`
  width: 64%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Folder = styled.div`
  border: 20px solid #f0b2b8;
  border-radius: 20px;
  width: 80%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow: auto;
`;

const FInner = styled.div`
  width: 100%;
  height: 96%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Main = styled.div`
  width: 90%;
  height: 90%;
`;

const Header = styled.div`
  width: 100%;
  height: 15%;
  border-top: 2px solid silver;
  border-bottom: 2px solid silver;
  display: flex;
  align-items: center;
  justify-content: center;
  & :nth-child(1) {
    height: 100%;
    width: 10%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(2) {
    height: 100%;
    width: 40%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(3) {
    height: 100%;
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(4) {
    height: 100%;
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

const Lecture = styled.div`
  margin-top: 2.5rem;
  width: 100%;
  height: 40%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-top: 2px dashed silver;
  border-bottom: 2px dashed silver;

  & :nth-child(2) {
    height: 100%;
    width: 40%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(3) {
    height: 100%;
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(4) {
    height: 100%;
    width: 25%;
    display: flex;

    align-items: center;
    justify-content: center;
  }
`;

const TrashWrap = styled.div`
  width: 10%;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const TrashPic = styled.div`
  background-image: url(${Tc});
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 40px;
`;

const TrashButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
  border: none;
  width: 30%;
  &:hover {
    cursor: pointer;
  }
`;

const LecturePic1 = styled(NavLink)`
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  border-right: 1px solid silver;
  border-left: 1px solid silver;
  width: 20%;
  height: 20%;
`;

export function UserCourseMgmt() {
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  console.log("loginState from sessionStorage:", loginState);
  const [wishLectureData, setWishLectureData] = useState([]);

  async function getWishLectures() {
    const loginState = JSON.parse(sessionStorage.getItem("loginState"));
    console.log("loginState from sessionStorage:", loginState);

    if (!loginState) {
      return;
    }
    try {
      const response = await fetch(
        `http://localhost:8080/api/wishLecture/list`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );

      const responseData = await response.json();

      console.log(responseData);

      if (response.ok) {
        setWishLectureData(responseData.data);
      } else {
        console.error(`Error: ${responseData.message}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }

  async function removeWishLecture(lectureId) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/removeWishLecture/${lectureId}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );
      const responseData = await response.json(); // 응답 본문 파싱
      if (response.ok) {
        getWishLectures(); // 찜 해제 후 찜 목록 다시 불러오기
      } else {
        console.error(`Error: ${responseData.message}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }

  useEffect(() => {
    getWishLectures();

    // getLectureById(id);
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <Container>
        <Wrapper>
          <UserBar />
          <Folder>
            <FInner>
              <Main>
                <Header>
                  <h4>찜 해제</h4>
                  <h4>강의사진</h4>
                  <h4>제목</h4>
                  <h4>가격</h4>
                </Header>
                {wishLectureData.map((lecture, index) => (
                  <Lecture key={index}>
                    <TrashWrap>
                      <TrashButton
                        onClick={() => removeWishLecture(lecture.id)}
                      >
                        <TrashPic />
                      </TrashButton>
                    </TrashWrap>
                    <LecturePic1
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                        to={`/lecture/${lecture.id}`}
                    />
                    <h4>{lecture.lectureName}</h4>
                    <h4>{lecture.price}</h4>
                  </Lecture>
                ))}
              </Main>
            </FInner>
          </Folder>
        </Wrapper>
      </Container>
      <MonacaInfo />
    </>
  );
}
