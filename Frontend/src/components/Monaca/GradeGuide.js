import styled from "styled-components";
import { NavBar } from "./NavBar";
import { MonacaInfo } from "./MonacaInfo";
import { useEffect } from "react";

// candyPic import
import Rd from "./image/Candy/RedC.png";
import Og from "./image/Candy/OrangeC.png";
import Yell from "./image/Candy/YellowC.png";
import Gr from "./image/Candy/GreenC.png";

// 칠판 틀 import
import LoginGrade from "./image/LoginGrade.jpg";

const Container = styled.div`
  width: 100vw;
  height: 100vh;
  margin-top: 3rem;
  margin-bottom: 5rem;
`;

const SecWrap = styled.div`
  width: 100%;
  height: 100%;
`;

const Section1 = styled.div`
  width: 100%;
  height: 100%;
  font-family: "GmarketSansMedium";
  background-image: url(${LoginGrade});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Grade = styled.div`
  font-size: 3rem;
  padding: 10px;
  color: white;
  letter-spacing: 2px;
`;

const Text = styled.div`
  display: grid;
  grid-template-rows: 1fr 1fr 1fr 1fr;
  align-items: center;
  & p {
    font-size: 1.2rem;
    padding: 10px;
    color: white;
    text-align: left;
    letter-spacing: 2px;
  }
`;
const Rc = styled.div`
  display: flex;
  align-items: center;
  margin: 20px auto;
  width: 100%;
`;
const Red = styled.div`
  width: 50px;
  height: 50px;
  background-image: url(${Rd});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

const Oc = styled.div`
  display: flex;
  align-items: center;
  margin: 20px auto;
  width: 100%;
`;

const Org = styled.div`
  width: 50px;
  height: 50px;
  background-image: url(${Og});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

const Yc = styled.div`
  display: flex;
  align-items: center;
  margin: 20px auto;
  width: 100%;
`;
const Yel = styled.div`
  width: 50px;
  height: 50px;
  background-image: url(${Yell});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

const Gc = styled.div`
  display: flex;
  align-items: center;
  margin: 20px auto;
  width: 100%;
`;
const Grn = styled.div`
  width: 50px;
  height: 50px;
  background-image: url(${Gr});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

export function GradeGuide() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <Container>
        <SecWrap>
          <Section1>
            <Grade>회원등급 안내</Grade>
            <Text>
              <Rc>
                <Red />
                <p>기본 네 가지 무료강의 / 기본 캐릭터 세트 제공</p>
              </Rc>
              <Oc>
                <Org />
                <p>
                  기본 네 가지 무료강의 + 유료강의 한 가지 / <br /> 출석 사탕
                  29개 모으면 하나 더 제공
                </p>
              </Oc>
              <Yc>
                <Yel />
                <p>
                  기본 네 가지 무료강의 + 유료강의 두 가지 / <br /> 유료 캐릭터
                  꾸미기 재료 한 가지 제공
                </p>
              </Yc>
              <Gc>
                <Grn />
                <p>
                  전 강의 무료 / <br />
                  유료 캐릭터 꾸미기 재료 두 가지 제공
                </p>
              </Gc>
            </Text>
          </Section1>
        </SecWrap>
        <MonacaInfo />
      </Container>
    </>
  );
}
