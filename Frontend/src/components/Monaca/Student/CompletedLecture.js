import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import Kiosk1 from "../image/Kiosk/Kiosk1.jpg";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { addLectureToClassRoom, getAllLectureHistory } from "../api";

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
    &:nth-child(2) {
      background-color: white;
      border: 1px solid silver;
    }
    &:nth-child(1) {
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

const Image = styled.div`
  width: 100%;
  height: 100%;
  background-image: url(${Kiosk1});
  background-size: cover;
  background-repeat: no-repeat;
  &:hover {
    cursor: pointer;
  }
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

export function CompletedLecture() {
  const navigate = useNavigate();

  const [lectures, setLectures] = useState([]);

  const [addClassRoom, setAddClassRoom] = useState([]);

  useEffect(() => {
    getAllLectureHistory()
      .then((response) => {
        setLectures(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("수료한 강의 불러오기 오류 : ", error);
      });
  }, []);

  const handleaddLectureToClassRoom = (lectureHistoryId) => {
    addLectureToClassRoom(lectureHistoryId)
      .then((response) => {
        setAddClassRoom([...addClassRoom, response.data]);
        navigateToClassRoom();
      })
      .catch((error) => {
        console.error("재수강 처리 오류 : ", error);
      });
  };

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
              <h3>수료한 강의</h3>
            </Title>
            <LectureWrapper>
              {lectures?.map((lecture, index) => (
                <Lectures key={index}>
                  <Lecture>
                    <Image
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                    />
                    <Info>
                      <h5>강의 명 : {lecture.lectureName}</h5>
                      <h5>강의 소개 : {lecture.subTitle}</h5>
                    </Info>
                    <Button>
                      <button
                        onClick={() => handleaddLectureToClassRoom(lecture.id)}
                      >
                        재수강
                      </button>
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
