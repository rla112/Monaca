import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { NavLink, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  getAllClassRoom,
  updateClassRoomAndAddLectureToClassRoom,
} from "../api";

const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-family: "GmarketSansMedium";
`;

const Wrap = styled.div`
  width: 60%;
  height: 80%;
  border: 20px solid #fcc757;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 4%;
`;

const Buttons = styled.div`
  width: 58%;
  height: 5%;
  display: flex;
  gap: 1%;
  & button {
    width: 20%;
    height: 100%;
    border-top-right-radius: 15px;
    border-top-left-radius: 15px;
    font-family: "GmarketSansMedium";
    font-size: large;
    font-weight: bolder;
    &:hover {
      cursor: pointer;
    }
    &:active {
      background-color: #6666;
    }
    &:nth-child(1) {
      background-color: white;
      border: 1px solid silver;
    }
    &:nth-child(2) {
      background-color: #6666;
      border: 1px solid silver;
    }
  }
`;

const Title = styled.div`
  width: 100%;
  height: 8%;
  display: flex;
  align-items: center;
  border-bottom: 5px double black;
`;

const Watch = styled.div`
  width: 90%;
  height: 90%;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow: auto;
`;
const LectureWrapper = styled.div`
  width: 100%;
  height: 100%;
  overflow: auto;
`;

const Lectures = styled.div`
  width: 100%;
  height: 35%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-bottom: 1px solid silver;
`;

const Lecture = styled.div`
  width: 80%;
  height: 80%;
  display: grid;
  grid-template-columns: 25% 50% 25%;
`;

const Image = styled(NavLink)`
  width: 100%;
  height: 100%;
  background-size: cover;
  background-repeat: no-repeat;
`;

const Info = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 1.3rem;
`;

const Button = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  & button {
    width: 50%;
    height: 20%;
    background-color: white;
    border: 1px solid silver;
    font-size: 1rem;
    font-family: "GmarketSansMedium";
    &:hover {
      cursor: pointer;
    }
    &:active {
      background-color: #6666;
    }
  }
`;

export function ClassRoom() {
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));

  const [lectures, setLectures] = useState([]);

  const [complete, setComplete] = useState([]);

  useEffect(() => {
    getAllClassRoom()
      .then((response) => {
        setLectures(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("결제한 강의 불러오기 오류 : ", error);
      });
  }, []);

  const handleCompleteLecture = (id) => {
    updateClassRoomAndAddLectureToClassRoom(id)
      .then((response) => {
        setComplete([...complete, response.data]);
        navigateToCompletedLecture();
      })
      .catch((error) => {
        console.error("시청 완료 처리 오류 : ", error);
      });
  };

  const navigate = useNavigate();

  const navigateToClassRoom = () => {
    navigate("/stud-class-room");
  };

  const navigateToCompletedLecture = () => {
    navigate("/cp-lectures");
  };

  return (
    <>
      <Container>
        <Buttons>
          <button onClick={navigateToClassRoom}>강의실</button>
          <button onClick={navigateToCompletedLecture}>수료실</button>
        </Buttons>
        <Wrap>
          <Watch>
            <Title>
              <h3>수강 중인 강의</h3>
            </Title>
            <LectureWrapper>
              {lectures?.map((lecture, index) => (
                <Lectures key={index}>
                  <Lecture>
                    <Image
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                      to={`/watch-the-lecture/${lecture.lecture_id}`}
                    />
                    <Info>
                      <h5>강의 명 : {lecture.lectureName}</h5>
                      <h5>강의 소개 : {lecture.subTitle}</h5>
                      <h5>
                        수료 기준 : 사진을 클릭해 강의를 끝까지 시청 후<br />
                        ㅤㅤㅤㅤㅤ시청 완료 버튼을 꼭 눌러주세요.
                      </h5>
                    </Info>
                    <Button>
                      <button onClick={() => handleCompleteLecture(lecture.id)}>
                        시청 완료
                      </button>
                      {console.log(lecture.id)}
                    </Button>
                  </Lecture>
                </Lectures>
              ))}
            </LectureWrapper>
          </Watch>
        </Wrap>
      </Container>
      <MonacaInfo />
    </>
  );
}
