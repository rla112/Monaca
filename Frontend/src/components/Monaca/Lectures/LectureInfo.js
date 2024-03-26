import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import CheckEmoji from "../image/Check.jpg";
import Man from "../image/Man.jpg";
import Woman from "../image/Woman.jpg";
import Cap from "../image/Cap.jpg";
import LecPaper from "../image/LecPaper.jpg";
import { NavLink, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

// api import
import {
  createLectureCart,
  getLectureById,
  getParticipantCountByLectureId,
  getPartOfUserInfo,
} from "../api";

const Wrapper = styled.div`
  width: 100vw;
  height: 300vh;
  font-family: "GmarketSansMedium";
`;

const Section1 = styled.div`
  width: 100%;
  height: 20%;
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
  /* background-image: ; */
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
`;

const Now = styled.div`
  display: flex;
  & h6 {
    color: #898989;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
  }
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
  width: 55%;
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

const Section2 = styled.div`
  width: 100%;
  height: 80%;
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

const ButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  width: 75%;
  margin: 5vh auto;
  height: 100%;
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
  &:nth-child(1) {
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
  align-items: center;
`;
const Header = styled.div`
  width: 75%;
  height: 30vh;
  text-align: left;
  margin: 5vh auto;
  & p {
    font-size: 1.5rem;
    line-height: 2.5rem;
  }
`;
const Inner1 = styled.div`
  width: 75%;
  height: 40vh;
  display: flex;
  flex-direction: column;
`;

const InTitle = styled.div`
  font-size: 1.5rem;
  display: flex;
  height: 5vh;
  align-items: center;
`;
const InWrap = styled.div`
  width: 100%;
  height: 10vh;
  display: flex;
  flex-direction: column;
  border: 2px solid black;
  justify-content: center;
  line-height: 3rem;
  padding-left: 1rem;
  margin-top: 10px;
  margin-bottom: 10px;
`;

const InText = styled.div`
  display: flex;
  font-size: 1.3rem;
  letter-spacing: 1px;
`;

const SubText = styled.div`
  display: flex;
  letter-spacing: 1px;
  font-size: 1rem;
`;

const Bar = styled.div`
  width: 75%;
  height: 50vh;
  display: flex;
  flex-direction: column;
  background-color: #0b4434;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  color: white;
  margin-top: 2rem;
`;

const Header2 = styled.div`
  width: 75%;
  height: 15vh;
  text-align: left;
  margin: 5vh auto;
  & p {
    font-size: 1.5rem;
    line-height: 2.5rem;
  }
`;
const Check = styled.div`
  display: flex;
  height: 70%;
  width: 3%;
  background-image: url(${CheckEmoji});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  margin-left: 1rem;
`;

const Inner2 = styled.div`
  width: 75%;
  height: 25vh;
  display: flex;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
  margin-bottom: 5rem;
`;

const SubIn1 = styled.div`
  width: 100%;
  height: 100%;
  margin-right: 2rem;
  display: flex;
  flex-direction: column;
  & h2 {
    margin-bottom: 1rem;
    font-size: 1.5rem;
    display: flex;
  }
`;

const SubIn2 = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  & h2 {
    margin-bottom: 1rem;
    font-size: 1.5rem;
    display: flex;
  }
`;

const SubIn3 = styled.div`
  width: 100%;
  height: 100%;
  margin-left: 2rem;
  display: flex;
  flex-direction: column;
  & h2 {
    margin-bottom: 1rem;
    font-size: 1.5rem;
    display: flex;
  }
`;

const SubInner = styled.div`
  width: 100%;
  height: 80%;
  line-height: 2rem;
  border: 2px solid black;
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 1.5rem;
  & p {
    margin: 10px;
    letter-spacing: 1.5px;
  }
`;

const SubInner2 = styled.div`
  width: 95%;
  height: 80%;
  line-height: 2rem;
  border: 2px solid black;
  display: flex;
  flex-direction: column;
  justify-content: center;
  & p {
    text-align: center;
    font-size: 1.5rem;
    margin: 10px;
    letter-spacing: 1.5px;
  }
`;

const ProInfo = styled.div`
  width: 75%;
  height: 80%;
  display: flex;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
  /* background-color: lightgrey; */
`;

const ManEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 10%;
  background-image: url(${Man});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: right;
  margin-left: 0.5rem;
`;

const WomanEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 10%;
  background-image: url(${Woman});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: left;
`;

const LecPaperEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 10%;
  background-image: url(${LecPaper});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: left;
  margin-left: 1rem;
`;

const CapEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 15%;
  background-image: url(${Cap});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
`;

const ProIntro = styled.div`
  margin-top: 1rem;
  height: 70%;
  width: 100%;
  background-color: white;
  & h4 {
    line-height: 3rem;
    color: #5b5b5b;
  }
`;

const ProProfile = styled.div`
  width: 100%;
  height: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const Professor = styled.div`
  width: 50%;
  height: 30%;
  display: flex;
  align-items: center;
  & h2 {
    margin: 1rem;
    &:hover {
      cursor: pointer;
    }
  }

  & h3 {
    margin: 0.5rem;
    color: #898989;
  }
`;

const ProPic1 = styled.div`
  width: 17%;
  height: 80%;
  border-radius: 50%;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  &:hover {
    cursor: pointer;
  }
`;

// 강의 상세 페이지
export function LectureInfo() {
  const { lectureId } = useParams();

  const [classRoomData, setClassRoomData] = useState([]);
  const [lectureData, setLectureData] = useState([]);
  const [professorData, setProfessorData] = useState([]);
  const navigate = useNavigate();
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));

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

  async function getPartOfUserInfoById() {
    const professor = await getPartOfUserInfo(lectureData.professorLoginId);
    console.log(professor);
    return professor.data;
  }

  // 강의 찜하기
  async function saveWishLecture(lectureId) {
    try {
      const loginState = JSON.parse(sessionStorage.getItem("loginState"));
      const response = await fetch(
        `http://localhost:8080/api/saveWishLecture/${lectureId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${loginState.token}`,
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
  }, [lectureData, classRoomData, professorData]); // lectureData와 classRoomData가 변할 때마다 실행

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);


  // 강의 장바구니에 담기
  const addCart = () => {
    createLectureCart({lectureId})
    .then(() => {
      navigate(`/my-cart`);
    })
    .catch((error) => console.log("장바구니에 담기 실패", error));
  };

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
                    <Button onClick={addCart}>
                      장바구니 담기
                    </Button>
                    <Button  onClick={async () => {
                        if (lectureData.id !== undefined) {
                          await saveWishLecture(lectureData.id);
                          navigate("/user-coursemgmt");
                        }
                      }}>찜하기</Button>
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
                  <h2>시작을 향해 출발한 초급자를 위한</h2>
                  <h2>무료 키오스크 강의입니다.</h2>
                  <br />
                  <p>
                    어딜가나 사람대신 <br />
                    키오스크와 함께 주문해야 하는 시대가 왔습니다.
                  </p>
                  <br />
                  <p>
                    주문 또는 신청에 앞서 난감하거나 어려움을 겪고 계시진
                    않나요?
                    <br />
                    더이상 키오스크 앞에서 망설이고 싶지 않다면 모나카와 함께
                    성장해요!
                  </p>
                </Header>
                <Inner1>
                  <InTitle>
                    학습내용 엿보기 <Check />
                  </InTitle>
                  <InWrap>
                    <InText> ✔ 키오스크 전반적인 시스템 이해하기</InText>
                    <InText> ✔ 종류별 키오스크 분류 알아보기</InText>
                  </InWrap>
                  <InTitle>
                    수강 전 확인하기 <Check />
                  </InTitle>
                  <InWrap>
                    <SubText>
                      ✔ 기초수업이기 때문에 키오스크에 대해 어느정도 이해도가
                      있으신 분은 중급이나 고급강의를 추천 드립니다.
                    </SubText>
                    <SubText> ✔ 종류별 키오스크 분류 알아보기</SubText>
                  </InWrap>
                </Inner1>
                <Bar>
                  <p>초보자를 위한 키오스크 강의</p>
                  <p>기초를 알아야 한 단계 성장 할 수 있다 &#128293;</p>
                </Bar>
                <Header2>
                  <h2>강의 목표</h2>
                  <br />
                  <p>키오스크라는 게 왜 보편화가 되었는지 이해하고</p>
                  <p>
                    우리와 같이 공존함에 있어 어려움이 없도록 기초를 잘 쌓을 수
                    있게!
                  </p>
                </Header2>
                <Inner2>
                  <SubIn1>
                    <h2>
                      키오스크 강의 구성 <LecPaperEmoji />
                    </h2>
                    <SubInner>
                      <Now>
                        <p>✔ 키오스크 초급 1-1</p>
                        <h6>현재 강의</h6>
                      </Now>
                      <p>✔ 키오스크 중급</p>
                      <p>✔ 키오스크 고급</p>
                    </SubInner>
                  </SubIn1>
                  <SubIn2>
                    <h2>
                      수강 대상 <ManEmoji /> <WomanEmoji />
                    </h2>
                    <SubInner>
                      <p>✔ 키오스크를 잘 모르는 분</p>
                      <p>✔ 기초부터 시작하고 싶은 분</p>
                    </SubInner>
                  </SubIn2>
                  <SubIn3>
                    <h2>
                      듣기 전 필요한 지식 <CapEmoji />
                    </h2>
                    <SubInner2>
                      <p>
                        초급반이기 때문에 <br />
                        마음가짐만 있다면
                        <br /> 잘 모르더라도 <br /> 누구든 수강 가능
                      </p>
                    </SubInner2>
                  </SubIn3>
                </Inner2>
                <ProInfo>
                  <ProProfile>
                    <Professor>
                      <ProPic1
                        style={{
                          backgroundImage: `url(${
                            professorData.data && professorData.data.image
                          })`,
                        }}
                      />
                      <h2>{lectureData.professor}</h2>
                      <h3>교수님</h3>
                    </Professor>
                    <ProIntro>
                      <h4>
                        {professorData.data &&
                          professorData.data.professorIntro}
                      </h4>
                    </ProIntro>
                  </ProProfile>
                </ProInfo>
              </Info>
            </Section2>
          </>
        )}
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
