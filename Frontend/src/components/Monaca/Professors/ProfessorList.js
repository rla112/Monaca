// 강의진 한 눈에 모아보기
import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";

//emoji import
import WebEmo from "../image/webEmoji.jpg";
import MobEmo from "../image/MobEmoji.jpg";
import WelEmo from "../image/WelEmoji.jpg";

import Candylog from "../image/CandyLogo.png";
import { getProfessorByLectureCategoryName } from "../api";

const Wrapper = styled.div`
  width: 100vw;
  height: 230vh;
  margin-bottom: 15rem;
  font-family: "TmonMonsori";
`;

const SubWrap = styled.div`
  width: 80%;
  height: 90%;
  margin: 4rem auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Container = styled.div`
  height: 200vh;
  width: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
  font-family: "GmarketSansMedium";
`;

const Header = styled.div`
  width: 100%;
  height: 12vh;
  background-color: #0b4434;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "TmonMonsori";
  & h1 {
    color: white;
    font-size: 2rem;
    letter-spacing: 5px;
  }
`;
const Kiosk = styled.div`
  width: 90%;
  height: 600px;
  margin: 1rem auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const KioskHeader = styled.div`
  display: flex;
  flex-direction: column;
  height: 20%;
  width: 98%;
  margin: 0 auto;
`;

const KioskTitle = styled.div`
  height: 50%;
  font-size: 2rem;
  display: flex;
`;

const Info = styled.div`
  height: 50%;
  font-size: 2rem;
  display: flex;
  align-items: center;
`;

const KioskWrap = styled.div`
  display: flex;
  height: 100%;
  width: 100%;
  align-items: center;
  margin: 0 auto;
`;

const KioskIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 30%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const KioskPic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: top;
  border-radius: 1rem;
  height: 100%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

const LectureInfo = styled.div`
  height: 50%;
  background-color: lightgrey;
  width: 100%;
  margin-top: 1.5rem;
  border-radius: 1rem;
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
  text-align: center;
  line-height: 2.5rem;
  & p {
    font-size: 18px;
  }
`;

//웹사이트
const Web = styled.div`
  width: 90%;
  height: 600px;
  margin: 1rem auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const WebEmoji = styled.div`
  width: 2.5%;
  height: 100%;
  background-image: url(${WebEmo});
  background-position: center;
  background-repeat: no-repeat;
  background-size: contain;
  margin-left: 0.5rem;
`;

const WebHeader = styled.div`
  display: flex;
  flex-direction: column;
  height: 20%;
  width: 98%;
  margin: 0 auto;
`;

const WebTitle = styled.div`
  height: 50%;
  font-size: 2rem;
  display: flex;
`;

const WebWrap = styled.div`
  display: flex;
  height: 100%;
  width: 100%;
  align-items: center;
  margin: 0 auto;
`;

const WebIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 30%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const WebPic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  border-radius: 1rem;
  height: 100%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

// 모바일
const Mobile = styled.div`
  width: 90%;
  height: 600px;
  margin: 1rem auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const MobileEmoji = styled.div`
  width: 2%;
  height: 100%;
  background-image: url(${MobEmo});
  background-position: top;
  background-repeat: no-repeat;
  background-size: contain;
  margin-left: 0.5rem;
`;

const MobileHeader = styled.div`
  display: flex;
  flex-direction: column;
  height: 20%;
  width: 98%;
  margin: 0 auto;
`;

const MobileTitle = styled.div`
  height: 50%;
  font-size: 2rem;
  display: flex;
`;

const MobileWrap = styled.div`
  display: flex;
  height: 100%;
  width: 100%;
  align-items: center;
  margin: 0 auto;
`;

const MobileIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 30%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const MobilePic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  border-radius: 1rem;
  height: 100%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

//복지
const Welfare = styled.div`
  width: 90%;
  height: 600px;
  margin: 1rem auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const WelfareEmoji = styled.div`
  width: 3%;
  height: 100%;
  background-image: url(${WelEmo});
  background-position: center;
  background-repeat: no-repeat;
  background-size: contain;
  margin-left: 0.5rem;
`;

const WelfareHeader = styled.div`
  display: flex;
  flex-direction: column;
  height: 20%;
  width: 98%;
  margin: 0 auto;
`;

const WelfareTitle = styled.div`
  height: 50%;
  font-size: 2rem;
  display: flex;
`;

const WelfareWrap = styled.div`
  display: flex;
  height: 100%;
  width: 100%;
  align-items: center;
  margin: 0 auto;
`;

const WelfareIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 30%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const WelfarePic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  border-radius: 1rem;
  height: 100%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

const Candy = styled.div`
  width: 30%;
  height: 90%;
  margin: 1rem;
  background-image: url(${Candylog});
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
  opacity: 0.5;
`;

export function ProfessorList() {
  const [kioskPf, setKioskPf] = useState([]);
  const [webPf, setWebPf] = useState([]);
  const [mobPf, setMobPf] = useState([]);
  const [welPf, setWelPf] = useState([]);

  useEffect(() => {
    getProfessorByLectureCategoryName("키오스크 사용법")
      .then((response) => {
        setKioskPf(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("키오스크 교수 정보 불러오기 오류 : ", error);
      });
  }, []);

  useEffect(() => {
    getProfessorByLectureCategoryName("웹사이트 사용법")
      .then((response) => {
        setWebPf(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("웹사이트 교수 불러오기 오류 : ", error);
      });
  }, []);

  useEffect(() => {
    getProfessorByLectureCategoryName("모바일기기 사용법")
      .then((response) => {
        setMobPf(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("모바일 교수 불러오기 오류 : ", error);
      });
  }, []);

  useEffect(() => {
    getProfessorByLectureCategoryName("국가 복지 및 민원 신청법")
      .then((response) => {
        setWelPf(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("국가복지 교수 불러오기 오류 : ", error);
      });
  }, []);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const navigate = useNavigate();

  return (
    <>
      <Wrapper>
        <Header>
          <h1>모나카에 있는 교수진 한눈에 보기</h1>
        </Header>
        <SubWrap>
          <Container>
            <Kiosk>
              <KioskHeader>
                <KioskTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요."></KioskTitle>
                <Info>
                  <p>키오스크 🏧</p>
                </Info>
              </KioskHeader>
              <KioskWrap>
                {kioskPf.map((professor, index) => (
                  <KioskIn key={index} to={`/professor/${professor.name}`}>
                    <KioskPic
                      style={{
                        backgroundImage: `url(${professor.image})`,
                      }}
                    />
                    <LectureInfo>
                      <p>{professor.name}</p>
                      <p>{professor.professorResume}</p>
                      <p>{professor.email}</p>
                    </LectureInfo>
                  </KioskIn>
                ))}
              </KioskWrap>
            </Kiosk>
            <Web>
              <WebHeader>
                <WebTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요."></WebTitle>
                <Info>
                  <p>웹사이트</p>
                  <WebEmoji />
                </Info>
              </WebHeader>
              <WebWrap>
                {webPf.map((professor, index) => (
                  <WebIn key={index} to={`/professor/${professor.name}`}>
                    <WebPic
                      style={{
                        backgroundImage: `url(${professor.image})`,
                      }}
                    />
                    <LectureInfo>
                      <p>{professor.name}</p>
                      <p>{professor.professorResume}</p>
                      <p>{professor.email}</p>
                    </LectureInfo>
                  </WebIn>
                ))}
                <Candy />
              </WebWrap>
            </Web>
            <Mobile>
              <MobileHeader>
                <MobileTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요."></MobileTitle>
                <Info>
                  <p>모바일</p>
                  <MobileEmoji />
                </Info>
              </MobileHeader>
              <MobileWrap>
                {mobPf.map((professor, index) => (
                  <MobileIn key={index} to={`/professor/${professor.name}`}>
                    <MobilePic
                      style={{
                        backgroundImage: `url(${professor.image})`,
                      }}
                    />
                    <LectureInfo>
                      <p>{professor.name}</p>
                      <p>{professor.professorResume}</p>
                      <p>{professor.email}</p>
                    </LectureInfo>
                  </MobileIn>
                ))}
                <Candy />
              </MobileWrap>
            </Mobile>
            <Welfare>
              <WelfareHeader>
                <WelfareTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요."></WelfareTitle>
                <Info>
                  <p>국가복지 및 민원</p>
                  <WelfareEmoji />
                </Info>
              </WelfareHeader>
              <WelfareWrap>
                {welPf.map((professor, index) => (
                  <WelfareIn key={index} to={`/professor/${professor.name}`}>
                    <WelfarePic
                      style={{
                        backgroundImage: `url(${professor.image})`,
                      }}
                    />
                    <LectureInfo>
                      <p>{professor.name}</p>
                      <p>{professor.professorResume}</p>
                      <p>{professor.email}</p>
                    </LectureInfo>
                  </WelfareIn>
                ))}
                <Candy />
              </WelfareWrap>
            </Welfare>
          </Container>
        </SubWrap>
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
