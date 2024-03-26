import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import WebEmo from "../image/webEmoji.jpg";
import MobEmo from "../image/MobEmoji.jpg";
import WelEmo from "../image/WelEmoji.jpg";

import { getAllLectureByCategoryName } from "../api";

const Wrapper = styled.div`
  width: 100vw;
  height: 230vh;
  margin-bottom: 15rem;
  font-family: "TmonMonsori";
`;

const SubWrap = styled.div`
  width: 80%;
  height: 100%;
  margin: 4rem auto;
`;

const Container = styled.div`
  height: 230vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
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
  font-size: 1.5rem;
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
  width: 20%;
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
  background-position: center;
  border-radius: 1rem;
  border: 1px solid silver;
  height: 50%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

const LectureInfo = styled.div`
  height: 50%;
  width: 100%;
  border-radius: 1rem;
  border: 1px solid silver;
  margin: 1rem;
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
  line-height: 4rem;
  &:hover {
    cursor: pointer;
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
  background-position: bottom;
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
  width: 20%;
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
  border: 1px solid silver;
  height: 50%;
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
  background-position: bottom;
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
  width: 20%;
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
  border: 1px solid silver;
  border-radius: 1rem;
  height: 50%;
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
  background-position: bottom;
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
  width: 20%;
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
  border: 1px solid silver;
  height: 50%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

export function LectureList() {
  
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const [kiosks, setKiosks] = useState([]);

  useEffect(() => {
    getAllLectureByCategoryName("키오스크 사용법")
      .then((response) => {
        setKiosks(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("강의 정보 불러오기 오류 : ", error);
      });
  }, []);

  const [webs, setWebs] = useState([]);

  useEffect(() => {
    getAllLectureByCategoryName("웹사이트 사용법")
      .then((response) => {
        setWebs(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("강의 정보 불러오기 오류 : ", error);
      });
  }, []);

  const [mobiles, setMobiles] = useState([]);

  useEffect(() => {
    getAllLectureByCategoryName("모바일기기 사용법")
      .then((response) => {
        setMobiles(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("강의 정보 불러오기 오류 : ", error);
      });
  }, []);

  const [wels, setWels] = useState([]);

  useEffect(() => {
    getAllLectureByCategoryName("국가 복지 및 민원 신청법")
      .then((response) => {
        setWels(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("강의 정보 불러오기 오류 : ", error);
      });
  }, []);

  // const splitArr = String.split("✔");

  return (
    <>
      <Wrapper>
        <Header>
          <h1>모나카에 있는 강의 한눈에 보기</h1>
        </Header>
        <SubWrap>
          <Container>
            <Kiosk>
              <KioskHeader>
                <KioskTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.">
                  이제는 없는 곳이 없는 키오스크 🏧
                </KioskTitle>
                <Info>더 이상 키오스크 앞에서 힘들고 싶지 않을 때</Info>
                <p></p>
              </KioskHeader>
              <KioskWrap>
                {kiosks.map((lecture) => (
                  <KioskIn key={lecture.id} to={`/lecture/${lecture.id}`}>
                    <KioskPic
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                    ></KioskPic>
                    <LectureInfo>
                      <p>{lecture.lectureName}</p>
                      <p>{lecture.subTitle}</p>
                    </LectureInfo>
                  </KioskIn>
                ))}
              </KioskWrap>
            </Kiosk>
            <Web>
              <WebHeader>
                <WebTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.">
                  정보화시대에 발맞춤 할 수 있는 강의 <WebEmoji />
                </WebTitle>
                <Info>인터넷으로 누리고 싶은 혜택이 많지만 잘 모를 때</Info>
              </WebHeader>
              <WebWrap>
                {webs.map((lecture) => (
                  <WebIn key={lecture.id} to={`/lecture/${lecture.id}`}>
                    <WebPic
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                    ></WebPic>
                    <LectureInfo>
                      <p>{lecture.lectureName}</p>
                      <p>{lecture.subTitle}</p>
                    </LectureInfo>
                  </WebIn>
                ))}
              </WebWrap>
            </Web>
            <Mobile>
              <MobileHeader>
                <MobileTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.">
                  비싼 핸드폰 쓸 때 다 알고 쓰자 <MobileEmoji />
                </MobileTitle>
                <Info>핸드폰 사용하면서 내 자신이 답답해질 때</Info>
              </MobileHeader>
              <MobileWrap>
                {mobiles.map((lecture) => (
                  <MobileIn key={lecture.id} to={`/lecture/${lecture.id}`}>
                    <MobilePic
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                    ></MobilePic>
                    <LectureInfo>
                      <p>{lecture.lectureName}</p>
                      <p>{lecture.subTitle}</p>
                    </LectureInfo>
                  </MobileIn>
                ))}
              </MobileWrap>
            </Mobile>
            <Welfare>
              <WelfareHeader>
                <WelfareTitle title="아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.">
                  해당되는 국가복지를 놓치고 있다면 <WelfareEmoji />
                </WelfareTitle>
                <Info>받을 수 있는 복지를 모르거나 놓칠 때</Info>
              </WelfareHeader>
              <WelfareWrap>
                {wels.map((lecture) => (
                  <WelfareIn key={lecture.id} to={`/lecture/${lecture.id}`}>
                    <WelfarePic
                      style={{
                        backgroundImage: `url(${lecture.image})`,
                      }}
                    ></WelfarePic>
                    <LectureInfo>
                      <p>{lecture.lectureName}</p>
                      <p>{lecture.subTitle}</p>
                    </LectureInfo>
                  </WelfareIn>
                ))}
              </WelfareWrap>
            </Welfare>
          </Container>
        </SubWrap>
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
