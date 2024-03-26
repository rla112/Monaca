import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { NavLink, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

// error image import
import CTextLogo from "../image/CTextLogo.jpg";
import ErrorBack from "../image/ErrorBack.jpg";

// api import
import {
  createQandA,
  getLectureById,
  getPartOfUserInfo,
  getParticipantCountByLectureId,
} from "../api";

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
  height: 50%;
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
  &:nth-child(2) {
    background-color: #0b4434;
    color: white;
  }
  &:hover {
    cursor: pointer;
  }
`;

const Info = styled.div`
  width: 85%;
  height: 90%;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
`;

const Header = styled.div`
  width: 75%;
  height: 10vh;
  text-align: left;
  margin: 5vh auto;
  & p {
    font-size: 1.5rem;
    line-height: 2.5rem;
  }
`;

const QnAWrap = styled.div`
  width: 60%;
  height: 90%;
  background-color: #1c493d;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 20px;
  & h3 {
    font-size: 1.3re;
    margin-bottom: 1rem;
    color: white;
  }
`;

const Title = styled.div`
  width: 80%;
  height: 8%;
  & input {
    width: 100%;
    height: 100%;
    border-radius: 10px;
    border: 1px solid black;
    padding: 10px;
    font-size: 1rem;
    font-family: "GmarketSansMedium";
    letter-spacing: 2px;
    outline: none;
    &:focus {
      border: 2px solid black;
    }
  }
`;

const Text = styled.div`
  width: 80%;
  height: 60%;
  margin-top: 4rem;
  & textarea {
    border: 1px solid black;
    border-radius: 10px;
    width: 100%;
    height: 90%;
    resize: none;
    padding: 10px;
    font-size: 1rem;
    line-height: 2rem;
    font-family: "GmarketSansMedium";
    letter-spacing: 2px;
    outline: none;
    &:focus {
      border: 2px solid black;
    }
  }
`;

const Qbtn = styled.div`
  width: 80%;
  height: 8%;
  display: flex;
  align-items: end;
  justify-content: end;
  gap: 10px;
`;

const QchBtn = styled(NavLink)`
  width: 20%;
  height: 70%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  border: 1px solid black;
  font-family: "GmarketSansMedium";
  text-decoration: none;
  color: black;
  &:hover {
    cursor: pointer;
  }
  &:active {
    background-color: #6666;
  }
`;

// 강의 상세 페이지
export function DoQnA() {
  // const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  const [loginState, setLoginState] = useState(null);
  console.log("loginState from sessionStorage:", loginState);
  const [classRoomData, setClassRoomData] = useState([]);
  const [lectureData, setLectureData] = useState([]);

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

  const navigate = useNavigate();

  const [loggingIn, setLoggingIn] = useState(false);

  useEffect(() => {
    const storedLoginState = JSON.parse(sessionStorage.getItem("loginState"));
    if (storedLoginState && storedLoginState.loginId) {
      setLoginState(storedLoginState);
      setLoggingIn(true);
    }
  }, []);

  // qna 등록하기

  const [title, setTitle] = useState([]);

  const [text, setText] = useState([]);

  const [lecture, setLecture] = useState([]);

  useEffect(() => {
    getLectureById(lectureId)
      .then((response) => {
        setLecture(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("강의 정보 불러오기 오류 : ", error);
      });
  }, [lectureId]);

  const qnaSubmit = () => {
    if (
      !title ||
      !String(title).trim() ||
      !text ||
      !String(text).trim() ||
      !lecture
    ) {
      alert("모든 항목을 공백 없이 입력주세요.");
      return;
    }

    const qnaData = { title: title, text: text, lecture: lecture.lectureName };

    createQandA(lecture, title, text)
      .then(() => {
        navigate(`/lecture/${lectureId}/qna`);
      })
      .catch((err) => console.log("질문 등록 실패", err));
  };

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
                  <button onClick={() => navigate(`/lecture/${lectureId}/qna`)}>
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
                    <Button>장바구니 담기</Button>
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
              <Info>
                <Header>
                  <h2>강의 내용에 대해 궁금한 점을 질문해 보세요!</h2>
                </Header>
                <QnAWrap>
                  <Title>
                    <h3>제목</h3>
                    <input
                      placeholder="질문의 핵심을 50자 내로 적어주세요."
                      maxLength="50"
                      value={title}
                      onChange={(e) => setTitle(e.target.value)}
                    />
                  </Title>
                  <Text>
                    <h3>내용</h3>
                    <textarea
                      placeholder="묻고싶은 내용을 1000자 이내로 적어주세요."
                      maxLength="1000"
                      value={text}
                      onChange={(e) => setText(e.target.value)}
                    />
                  </Text>
                  <Qbtn>
                    <QchBtn to={`/lecture/${lectureId}/qna`}>
                      목록으로 돌아가기
                    </QchBtn>
                    <QchBtn onClick={qnaSubmit}>등록하기</QchBtn>
                  </Qbtn>
                </QnAWrap>
              </Info>
            </Section2>
          </>
        )}
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
