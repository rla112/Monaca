import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { NavLink, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

// api import
import {
  getLectureById,
  getPartOfUserInfo,
  getParticipantCountByLectureId,
} from "../api";

// error image import
import CTextLogo from "../image/CTextLogo.jpg";
import ErrorBack from "../image/ErrorBack.jpg";

const Wrapper = styled.div`
  width: 100vw;
  height: 200vh;
  font-family: "GmarketSansMedium";
`;

const ErrorWrapper = styled.div`
  width: 100%;
  height: 100vh;
`;
const ErrorLogo = styled.div`
  background-image: url(${ErrorBack});
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Box = styled.div`
  width: 30%;
  height: 40%;
  background-color: white;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-family: "GmarketSansMedium";
  & button {
    margin: 1rem;
    border-radius: 10px;
    width: 40%;
    height: 10%;
    background-color: #0b4434;
    border: none;
    color: white;
    font-family: "GmarketSansMedium";
    letter-spacing: 2px;
    font-size: 1.5rem;
    cursor: pointer;
  }
  & h1 {
    margin-bottom: 1rem;
  }
`;

const ErrorHead = styled.div`
  background-image: url(${CTextLogo});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  width: 40%;
  height: 40%;
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
    &:nth-child(3) {
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
  &:nth-child(3) {
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
  & button {
    background-color: white;
    border: 1px solid black;
    width: 200px;
    height: 40%;
    font-size: 1.5rem;
    &:hover {
      cursor: pointer;
    }

    &:active {
      background-color: #6666;
    }
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
    font-size: 1.5rem;
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
    font-size: 1.5rem;
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
export function LectureNotice() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [indexState, setIndexState] = useState(-1);
  const [loginState, setLoginState] = useState(null);
  // const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  console.log("loginState from sessionStorage:", loginState);
  const [classRoomData, setClassRoomData] = useState([]);
  const [lectureData, setLectureData] = useState([]);
  const navigate = useNavigate();

  // 강의 찜하기
  async function saveWishLecture(lectureId) {
    try {
      const loginState = JSON.parse(sessionStorage.getItem("loginState"));
      if (!loginState || !loginState.token) {
        console.error("로그인 상태가 유효하지 않습니다.");
        return;
      }

      const response = await fetch(
        `http://localhost:8080/api/saveWishLecture/${lectureId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );

      const responseData = await response.json();

      if (response.ok) {
        console.log("강의를 찜했습니다.", responseData);
        // 여기서 필요하다면 적절한 처리를 수행할 수 있습니다.
        navigate("/user-coursemgmt");
      } else {
        console.error(`에러: ${responseData.message}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }
  //   useEffect(() => {
  //     getLectureById(1);
  //     window.scrollTo(0, 0);
  //   }, []);

  const contents = [
    {
      title: "강의에 대한 질문",
      text: "강의에 대한 질문이 있습니다",
    },
    {
      title: "title2",
      text: "text2",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
    {
      title: "title3",
      text: "text3",
    },
  ];

  const openModal = (index) => {
    //  setSelectedContent(content);
    console.log("index", index);
    setIsModalOpen(true);
    setIndexState(index);
  };

  const closeModal = (index) => {
    setIsModalOpen(false);
    setIndexState(-1);
    console.log("test");
  };

  const { lectureId } = useParams();
  const [professorData, setProfessorData] = useState([]);

  useEffect(() => {
    console.log("useEffect 실행");

    getLecture()
      .then((response) => {
        console.log("getLecture response", response);
        setLectureData(response);
        // getLecture가 완료된 후에 getPartOfUserInfo 호출
        getPartOfUserInfo(response.professorLoginId)
          .then((professor) => {
            setProfessorData(professor);
          })
          .catch((error) => console.error(error));
      })
      .catch((error) => console.error(error));

    getParticipantCount().then((response) => {
      console.log("getParticipantCount response", response); // 추가
      setClassRoomData(response);
    });
  }, []);

  async function getLecture() {
    const lectureResponse = await getLectureById(lectureId);
    console.log(lectureResponse); // response의 값 확인
    return lectureResponse.data; // 수정된 부분
  }
  async function getParticipantCount() {
    const response = await getParticipantCountByLectureId(lectureId);
    console.log(response); // response의 값 확인
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
    window.scrollTo(0, 0);
  }, []);

  const [loggingIn, setLoggingIn] = useState(false);

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
        {!loggingIn ? (
          <>
            <ErrorWrapper>
              <ErrorLogo>
                <Box>
                  <ErrorHead />
                  <h1>로그인 후 이용해주세요.</h1>
                  <button onClick={() => navigate(`/lecture/${lectureId}`)}>
                    돌아가기
                  </button>
                  <button onClick={() => navigate("/")}>로그인 하기</button>
                </Box>
              </ErrorLogo>
            </ErrorWrapper>
          </>
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
                    <Price>가격 : {lectureData.price}원</Price>
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
                <h2>업데이트 되는 강의 새소식을 확인해 보세요!</h2>
              </Header>
            </Section2>
            <Section3>
              <List>
                <ListTitle>
                  {/* <button onClick={navigateToDoKioskQ1}>질문하러 가기</button> */}
                </ListTitle>
                <ListMain>
                  <ul>
                    {contents.map((c, i) => (
                      <>
                        <li key={i} onClick={() => openModal(i)}>
                          {c.title}
                        </li>
                        {indexState == i && (
                          <StyledLi key={i}>
                            <p>{c.text}</p>
                            <StyledButton onClick={closeModal}>X</StyledButton>
                          </StyledLi>
                        )}
                      </>
                    ))}
                  </ul>{" "}
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
