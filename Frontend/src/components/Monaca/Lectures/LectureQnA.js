import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { NavLink, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

// api import
import {
  getAllQandAByLecture,
  getLectureById,
  getPartOfUserInfo,
  getParticipantCountByLectureId,
} from "../api";

const Wrapper = styled.div`
  width: 100vw;
  height: 200vh;
  font-family: "GmarketSansMedium";
`;

const Section1 = styled.div`
  width: 100%;
  height: 30%;
  background-color: #0b4434;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const InnerWrap = styled.div`
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const SecInner1 = styled.div`
  height: 75%;
  width: 35%;
  margin: 3rem;
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
`;

const SecInner2 = styled.div`
  height: 100%;
  width: 40%;
  margin: 3rem;
`;

const InfoWrap = styled.div`
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  line-height: 2.5rem;
  color: white;
`;

const LectureCategory = styled.div`
  font-size: 2.5rem;
  text-align: center;
  width: 60%;
  height: 12%;
`;
const LectureName = styled.div`
  font-size: 2.5rem;
  text-align: center;
  width: 50%;
  height: 20%;
`;

const CurrentList = styled.div`
  font-size: 2.5rem;
  width: 40%;
  text-align: center;
  height: 11%;
`;

const ProfessorName = styled.div`
  font-size: 2.5rem;
  width: 50%;
  height: 11%;
  text-align: center;
`;

const Price = styled.div`
  font-size: 2.5rem;
  width: 50%;
  height: 11%;
  text-align: center;
`;

const ButtonWrap = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 10%;
`;

const Button = styled.button`
  align-items: center;
  width: 30%;
  margin: 1rem;
  height: 40px;
  font-size: 1.5rem;
  letter-spacing: 3px;
  font-family: "GmarketSansMedium";
  background-color: white;
  border: 1px solid black;
  &:hover {
    cursor: pointer;
  }
  &:active {
    background-color: #6666;
  }
  &:nth-child(2) {
    width: 15%;
  }
`;

const Section2 = styled.div`
  width: 100%;
  height: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Select = styled.div`
  width: 85%;
  height: 10vh;
  display: flex;
  align-items: center;
  margin-top: 5vh;
`;

const ButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  width: 75%;
  margin: 5vh auto;
  height: 100%;
  & button {
    background-color: white;
    border: 1px solid black;
    &:nth-child(2) {
      background-color: #0b4434;
      color: white;
    }
  }
`;
const SelectBtn = styled(NavLink)`
  width: 150px;
  height: 40px;
  display: flex;
  font-size: 1rem;
  font-weight: bold;
  letter-spacing: 3px;
  background-color: white;
  border: 1px solid black;
  font-family: "GmarketSansMedium";
  text-decoration: none;
  color: black;
  justify-content: center;
  align-items: center;
  &:nth-child(2) {
    background-color: #0b4434;
    color: white;
  }
  &:hover {
    cursor: pointer;
  }
`;

const Header = styled.div`
  width: 64%;
  margin-top: 5vh;
  display: flex;
  align-items: center;
`;

const Section3 = styled.div`
  width: 100%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 3rem;
`;

const List = styled.div`
  position: relative;
  width: 60%;
  height: 80%;
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
`;
const ListTitle = styled.button`
  width: 90%;
  height: 10vh;
  display: flex;
  align-items: center;
  justify-content: end;
  border: none;
  background-color: transparent;
`;

const ListBtn = styled(NavLink)`
  background-color: white;
  border: 1px solid black;
  width: 200px;
  height: 40%;
  font-size: 1.5rem;
  text-decoration: none;
  color: black;
  font-family: "GmarketSansMedium";
  &:hover {
    cursor: pointer;
  }
  &:active {
    background-color: #6666;
  }
`;

const ListMain = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 90%;
  height: 80%;
  border: 1px solid silver;
  /* border-left: 2px solid silver;
   border-right: 2px solid silver;
   border-top: 7px double black;
   border-bottom: 7px double black; */
  overflow: auto;
  & ul {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  & li {
    font-size: 1.2rem;
    list-style: none;
    width: 90%;
    height: 10vh;
    margin: 1rem;
    display: flex;
    align-items: center;
    padding: 10px;
    border-top: 1px solid gray;
    border-bottom: 1px solid gray;
    cursor: pointer;
    & p {
      width: 30%;
      font-size: 0.8rem;
      color: darkgray;
      /* background-color: aqua; */
      text-align: right;
    }
  }
`;
const StyledLi = styled.div`
  width: 90%;
  display: flex;
  height: 200px;
  border-top: 1px solid silver;
  border-bottom: 1px solid silver;
  background-color: white;
  & p {
    width: 100%;
    height: 100%;
    font-size: 1rem;
    padding: 10px;
  }
`;
const StyledButton = styled.button`
  width: 40px;
  height: 40px;
  font-size: 1rem;
  border: none;
  cursor: pointer;
`;

// 강의 상세 페이지
export function LectureQnA() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [indexState, setIndexState] = useState(-1);
  const [lecture, setLecture] = useState([]);
  const [qnas, setQnas] = useState([]);
  const { lectureId } = useParams();
  const [classRoomData, setClassRoomData] = useState([]);
  const [lectureData, setLectureData] = useState([]);
  const [professorData, setProfessorData] = useState([]);
  const [loggingIn, setLoggingIn] = useState(false);
  const [loginState, setLoginState] = useState(null);

  useEffect(() => {
    getLectureById(lectureId)
      .then((response) => {
        const lectureData = response.data;
        setLecture(lectureData);
        console.log(lectureData);
        return getAllQandAByLecture(lectureData.lectureName);
      })
      .then((response) => {
        setQnas(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("강의 정보 불러오기 오류 : ", error);
      });
  }, [lectureId]);

  const openModal = (index) => {
    console.log("index", index);
    setIsModalOpen(true);
    setIndexState(index);
  };

  const closeModal = (index) => {
    setIsModalOpen(false);
    setIndexState(-1);
    console.log("test");
  };

  useEffect(() => {
    console.log("useEffect 실행");
    getLecture()
      .then((response) => {
        console.log("getLecture response", response);
        setLectureData(response);
        getPartOfUserInfo(response.professorLoginId)
          .then((professor) => {
            setProfessorData(professor);
          })
          .catch((error) => console.error(error));
      })
      .catch((error) => console.error(error));

    getParticipantCount().then((response) => {
      console.log("getParticipantCount response", response); 
      setClassRoomData(response);
    });
  }, []);

  async function getLecture() {
    const lectureResponse = await getLectureById(lectureId);
    console.log(lectureResponse);
    return lectureResponse.data;
  }
  async function getParticipantCount() {
    const response = await getParticipantCountByLectureId(lectureId);
    console.log(response);
    return response.data;
  }

  // 강의 찜하기
  async function saveWishLecture(lectureId) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/saveWishLecture/${lectureId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(lectureId),
        }
      );

      const responseData = await response.json();

      if (response.ok) {
        console.log("강의를 찜했습니다.", responseData);

        navigate("/user-coursemgmt");
      } else {
        console.error(`에러: ${responseData.message}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }

  useEffect(() => {
    console.log("lectureData", lectureData); // 상태 변화 확인
    console.log("classRoomData", classRoomData); // 상태 변화 확인
    console.log("professorData", professorData);
  }, [lectureData, classRoomData, professorData]); // lectureData와 classRoomData가 변할 때마다 실행

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const navigate = useNavigate();

  useEffect(() => {
    const storedLoginState = JSON.parse(sessionStorage.getItem("loginState"));
    if (storedLoginState && storedLoginState.loginId) {
      setLoginState(storedLoginState);
      setLoggingIn(true);
    }
  }, []);

  return (
    <>
      <Wrapper>
        {lectureData === undefined || classRoomData === undefined ? (
          <h1>Loading...</h1>
        ) : (
          <>
            <Section1>
              <InnerWrap>
                <SecInner1
                  style={{
                    backgroundImage: `url(${lectureData.image})`,
                  }}
                />
                <SecInner2>
                  <InfoWrap>
                    <LectureCategory>
                      {lectureData.lectureCategory}
                    </LectureCategory>
                    <LectureName>
                      &#60;{lectureData.lectureName}&#62;
                    </LectureName>
                    <CurrentList>수강 인구 수 : {classRoomData}</CurrentList>
                    <ProfessorName>
                      교수 명 : {lectureData.professor}
                    </ProfessorName>
                    <Price>가격 : {lectureData.price}원 </Price>
                  </InfoWrap>
                  <ButtonWrap>
                    <Button
                      onClick={async () => {
                        if (lectureData.id !== undefined) {
                          await saveWishLecture(lectureData.id);
                          navigate("/user-coursemgmt");
                        }
                      }}
                    >
                      장바구니 담기
                    </Button>
                    <Button>찜하기</Button>
                  </ButtonWrap>
                </SecInner2>
              </InnerWrap>
            </Section1>
            <Section2>
              <Select>
                <ButtonWrapper>
                  <SelectBtn to={`/lecture/${lectureId}`}>강의 소개</SelectBtn>
                  <SelectBtn to={`/lecture/${lectureId}/qna`}>QnA</SelectBtn>
                  <SelectBtn to={`/lecture/${lectureId}/notice`}>
                    강의 공지사항
                  </SelectBtn>
                </ButtonWrapper>
              </Select>
              <Header>
                <h2>강의에 올라온 질문들을 확인할 수 있습니다.</h2>
              </Header>
            </Section2>
            <Section3>
              <List>
                <ListTitle>
                  <ListBtn to={`/lecture/${lectureId}/qna/do-qna`}>
                    질문하러 가기
                  </ListBtn>
                </ListTitle>
                <ListMain>
                  <ul>
                    {qnas.length > 0 ? (
                      qnas.map((qna, i) => (
                        <div
                          style={{
                            width: "100%",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "center",
                            alignItems: "center",
                          }}
                          key={i}
                        >
                          <li onClick={() => openModal(i)}>
                            {qna.title} <p>작성자 아이디 : {qna.author}</p>{" "}
                            <p>작성 시간 : {qna.createAt}</p>
                          </li>
                          {indexState === i && (
                            <StyledLi>
                              <p>{qna.text}</p>
                              <StyledButton onClick={closeModal}>
                                X
                              </StyledButton>
                            </StyledLi>
                          )}
                        </div>
                      ))
                    ) : (
                      <p
                        style={{
                          width: "100%",
                          fontSize: "2rem",
                          color: "darkgray",
                          display: "flex",
                          flexDirection: "column",
                          justifyContent: "center",
                          alignItems: "center",
                        }}
                      >
                        아직 질문이 달리지 않았습니다. 자유롭게 질문해보세요!
                      </p>
                    )}
                  </ul>
                </ListMain>
              </List>
            </Section3>
          </>
        )}
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
