import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import { useEffect, useState } from "react";
import { getUserByProfessorIntro } from "../api";
import { NavLink, useParams } from "react-router-dom";

const Wrapper = styled.div`
  width: 100vw;
  height: 200vh;
  font-family: "GmarketSansMedium";
  display: flex;
  flex-direction: column;
  align-items: center;
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
  height: 100%;
  width: 25%;
  margin: 3rem;
  background-position: center;
  background-repeat: no-repeat;
  background-size: contain;
`;

const SecInner2 = styled.div`
  height: 100%;
  width: 40%;
  margin: 3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
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
  width: 70%;
  text-align: center;
  height: 11%;
`;

const ProfessorName = styled.div`
  font-size: 2.5rem;
  width: 50%;
  height: 30%;
  text-align: center;
`;

const SecWrap = styled.div`
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Section2 = styled.div`
  width: 70%;
  height: 55%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2rem;
`;

const Intro1 = styled.div`
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  & h3 {
    font-size: 2rem;
    margin: 1rem;
  }
`;

const Box1 = styled.div`
  width: 80%;
  height: 100%;
  display: flex;
  justify-content: center;
  line-height: 4rem;
`;

const BInner = styled.div`
  width: 20%;
  height: 100%;
  display: grid;
  grid-template-rows: 60px 60px 60px 60px 100px;
  align-items: center;
  justify-content: end;
  & h5 {
    width: 100%;
    height: 100%;
    text-align: end;
    border-bottom: 1px solid silver;
    &:nth-child(5) {
      border: none;
    }
  }
`;

const BInner2 = styled.div`
  display: grid;
  grid-template-rows: 60px 60px 60px 60px 359px;
  width: 85%;
  height: 100%;
`;

const Section3 = styled.div`
  width: 70%;
  height: 45%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 3rem;
`;

const Lecture = styled.div`
  width: 78%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  & h3 {
    font-size: 2rem;
    margin: 1.5rem;
  }
`;

const Box2 = styled.div`
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const Inner = styled.div`
  display: grid;
  grid-template-rows: 179px 179px 179px;
`;

const Name = styled.div`
  width: 100%;
  height: 100%;
  border-bottom: 1px solid silver;
  & h4 {
    margin-left: 1rem;
  }
`;

const Birth = styled.div`
  width: 100%;
  height: 100%;
  border-bottom: 1px solid silver;
  & h4 {
    margin-left: 1rem;
  }
`;

const Email = styled.div`
  width: 100%;
  height: 100%;
  border-bottom: 1px solid silver;
  & h4 {
    margin-left: 1rem;
  }
`;

const Resume = styled.div`
  width: 100%;
  height: 100%;
  border-bottom: 1px solid silver;
  font-size: 1.3rem;
  & h5 {
    margin-left: 1rem;
  }
`;

const Me = styled.div`
  width: 100%;
  height: 100%;
  & h5 {
    margin: 1rem;
    font-size: 1rem;
    line-height: 2rem;
  }
`;

const Lecture1 = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  border-bottom: 1px solid silver;
`;

const Pic = styled(NavLink)`
  width: 40%;
  height: 90%;
  text-align: center;
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
`;

const Info = styled.div`
  width: 80%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;
export function ProfessorInfo() {
  const { name } = useParams();
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  console.log("loginState from sessionStorage:", loginState);
  const [data, setData] = useState([]);
  const [professorData, setProfessorData] = useState([]);
  const [lectureData, setLectureData] = useState([]);

  useEffect(() => {
    getUserByProfessorIntro()
      .then((response) => {
        // API로부터 받은 데이터 중에서 해당하는 교수의 정보만 필터링
        const filteredData = response.data.filter((pro) => pro.name === name);
        setProfessorData(filteredData); // 필터링된 데이터를 상태에 설정
        console.log(filteredData);
      })
      .catch((error) => {
        console.error("교수 불러오기 오류 : ", error);
      });
  }, [name]);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <>
      <Wrapper>
        <Section1>
          <InnerWrap>
            {professorData.map((pro, index) => (
              <div
                key={index}
                style={{
                  height: "100%",
                  width: "100%",
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <SecInner1
                  style={{
                    backgroundImage: `url(${pro.image})`,
                  }}
                />
                <SecInner2>
                  <InfoWrap>
                    <LectureCategory>
                      &#60;{pro.lecture[0].lectureCategory}&#62;
                    </LectureCategory>
                    <br />
                    <ProfessorName>{pro.name} 교수님</ProfessorName>
                    <CurrentList>{pro.professorResume}</CurrentList>
                  </InfoWrap>
                </SecInner2>
              </div>
            ))}
          </InnerWrap>
        </Section1>
        <SecWrap>
          {professorData.map((pro, index) => (
            <div
              key={index}
              style={{
                width: "80%",
                height: "100%",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              <Section2>
                <Intro1>
                  <h3>교수님 정보</h3>
                  <Box1>
                    <BInner>
                      <h5>성함 : </h5>
                      <h5>생년월일 : </h5>
                      <h5>이메일 : </h5>
                      <h5>경력 : </h5>
                      <h5>한 마디 : </h5>
                    </BInner>
                    <BInner2>
                      <Name>
                        <h4>{pro.name}</h4>
                      </Name>
                      <Birth>
                        <h4>{pro.birthDate}</h4>
                      </Birth>
                      <Email>
                        <h4>{pro.email}</h4>
                      </Email>
                      <Resume>
                        <h5>{pro.professorResume}</h5>
                      </Resume>
                      <Me>
                        <h5>{pro.professorIntro}</h5>
                      </Me>
                    </BInner2>
                  </Box1>
                </Intro1>
              </Section2>
              <Section3>
                <Lecture>
                  <h3>{pro.name} 교수님 강의 목록</h3>{" "}
                  <Box2>
                    <Inner>
                      {pro.lecture.map((lec, index) => (
                        <Lecture1 key={index}>
                          <Pic
                            style={{
                              backgroundImage: `url(${lec.image})`,
                            }}
                            to={`/lecture/${lec.id}`}
                          />
                          <Info>
                            <h4>&#60;{lec.lectureName}&#62;</h4>
                          </Info>
                        </Lecture1>
                      ))}
                    </Inner>
                  </Box2>
                </Lecture>
              </Section3>
            </div>
          ))}
        </SecWrap>
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
