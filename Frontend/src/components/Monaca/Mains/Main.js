import styled, { createGlobalStyle } from "styled-components";
import ClassMotto from "../image/ClassMotto.jpg";
import LoginBoard from "../image/MainBoard.jpg";
import CandyLogo from "../image/CandyLogo.png";
import Folder from "../image/Folder.jpg";
import { Opencourseinformation } from "./OpencourseInformation";
import { MainLecture } from "./MainLecture";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import { NavLink, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { login } from "../api";
import { MonacaContext } from "../MonacaLMS";

const BackGround = styled.div`
  &::before {
    content: "";
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
    background-image: url(${LoginBoard});
    background-repeat: no-repeat;
    background-size: contain;
  }
`;
const CrPar = styled.div`
  height: 100vh;
`;
const CrSect1 = styled.div`
  height: 100vh;
  width: 100vw;
  font-family: "TmonMonsori";
`;
const CrContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background-image: url(${LoginBoard});
  background-repeat: no-repeat;
  background-size: contain;
`;
const CrText1 = styled.div`
  width: 70%;
  margin: 10px;
  font-size: 3.5rem;
  color: white;
  text-align: center;
  letter-spacing: 5px;
`;
const CrText2 = styled.div`
  width: 50%;
  margin: 10px;
  font-size: 3.5rem;
  color: white;
  text-align: center;
  letter-spacing: 5px;
`;

const Welcome = styled.div`
  width: 55%;
  height: 8.3%;
  margin: 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const CrButton = styled.button`
  height: 50%;
  font-size: 1.8rem;
  width: 200px;
  background-color: white;
  text-decoration: none;
  color: black;
  box-shadow: 5px 5px 5px #04291f;
  border: 1px solid white;
  font-family: "TmonMonsori";
`;

const Par = styled.div`
  height: 100vh;
`;

const Sect1 = styled.div`
  height: 100vh;
  width: 100vw;
  font-family: "TmonMonsori";
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background-image: url(${LoginBoard});
  background-repeat: no-repeat;
  background-size: contain;
`;

const Text1 = styled.div`
  width: 70%;
  margin: 10px;
  font-size: 3.5rem;
  color: white;
  text-align: center;
  letter-spacing: 5px;
`;

const Logo = styled.div`
  background-image: url(${CandyLogo});
  background-repeat: no-repeat;
  background-size: contain;
  width: 400px;
  height: 400px;
`;

const Text2 = styled.div`
  width: 50%;
  margin: 10px;
  font-size: 3.5rem;
  color: white;
  text-align: center;
  letter-spacing: 5px;
`;

const Section = styled.div`
  align-items: center;
  justify-content: center;
  display: flex;
  width: 90%;
  margin: 1rem;
  & form {
    display: grid;
    grid-template-columns: 1fr;
    align-items: center;
    text-align: center;
    justify-content: center;
  }
  @media screen and (min-width: 768px) {
    & form {
      grid-template-columns: 1fr 1fr 1fr;
    }
  }
`;

const SecLog = styled.div`
  color: white;
  font-size: 3rem;
  width: 200px;
  text-align: center;
`;

const Id = styled.div`
  color: white;
  font-size: 1.8rem;
  letter-spacing: 5px;
  display: flex;
  padding: 1rem;
  margin: 0 auto;
  align-items: center;
  & label {
    margin-right: 1rem;
  }
  & input {
    outline: none;
    box-shadow: 5px 5px 5px #04291f;
    width: 100%;
    max-width: 280px;
    padding: 5px;
    font-size: 1.8rem;
    &:focus {
      outline: 2px solid black;
    }
  }
`;

const Pw = styled.div`
  color: white;
  font-size: 1.8rem;
  letter-spacing: 5px;
  display: flex;
  padding: 1rem;
  margin: 0 auto;
  align-items: center;
  & label {
    margin-right: 1rem;
  }
  & input {
    outline: none;
    box-shadow: 5px 5px 5px #04291f;
    width: 100%;
    max-width: 280px;
    padding: 5px;
    font-size: 1.8rem;
    &:focus {
      outline: 2px solid black;
    }
  }
`;

const Reg = styled.div`
  display: flex;
  color: white;
  padding: 1rem;
  margin: 0 auto;
  &a:visited {
  }
`;

const LgButton = styled.div`
  height: 100%;
  font-size: 1.8rem;
  margin-right: 1rem;
  width: 200px;
  background-color: white;
  text-decoration: none;
  color: black;
  box-shadow: 5px 5px 5px #04291f;
  & button {
    background-color: white;
    border: 1px solid white;
    width: 100%;
    height: 100%;
    font-family: "TmonMonsori";
    font-size: 1.8rem;
    &:active {
      color: #fe5665;
    }
  }
`;
const StyledNavLink = styled(NavLink)`
  border: 1px solid white;
  height: 100%;
  box-shadow: 5px 5px 5px #04291f;
  font-size: 1.8rem;
  margin-right: 1rem;
  width: 200px;
  font-family: "TmonMonsori";
  background-color: white;
  text-decoration: none;
  color: black;
  &:active {
    color: #fe5665;
  }
`;

const Sect2 = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200vh;
  width: 100vw;
  font-family: "GmarketSansMedium";
`;

const Sect3 = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  font-family: "TmonMonsori";
`;

const Container2 = styled.div`
  position: relative;
  width: 70%;
  max-width: 1280px;
  height: 80vh;
  background-image: url(${ClassMotto});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  letter-spacing: 4px;
`;

const Title2 = styled.div`
  font-size: 4rem;
  line-height: 5rem;
  margin: 20px;
  letter-spacing: 1rem;
`;

const Text3 = styled.div`
  text-align: center;
  line-height: 11vh;
  & h2 {
    font-size: 2.5rem;
  }
`;

const Sect4 = styled.div``;

const Sect5 = styled.div`
  width: 100vw;
  height: 40vh;
  background-color: #f6c383;
  display: flex;
  margin-top: 10rem;
`;

const FolderWrapper = styled.div`
  width: 65%;
  height: 60%;
  margin: auto;
  display: grid;
  grid-gap: 8rem;
  grid-template-columns: 1fr 1fr 1fr;
`;

const Folder1 = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-image: url(${Folder});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  font-family: "TmonMonsori";
  &:hover {
    cursor: pointer;
  }
  & h2 {
    font-size: 1.8rem;
    text-align: center;
    margin-top: 4rem;
  }
`;

const Folder2 = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-image: url(${Folder});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  font-family: "TmonMonsori";
  &:hover {
    cursor: pointer;
  }
  & h2 {
    font-size: 1.8rem;
    text-align: center;
    margin-top: 4rem;
  }
`;

const Folder3 = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-image: url(${Folder});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  font-family: "TmonMonsori";
  &:hover {
    cursor: pointer;
  }
  & h2 {
    font-size: 1.8rem;
    text-align: center;
    margin-top: 4rem;
  }
`;

export function Main() {
  const navigate = useNavigate();
  const navigateToLectureList = () => {
    navigate("/lecture-list");
  };
  const navigateToProfessorList = () => {
    navigate("/professor-list");
  };
  const navigateToGradeGuide = () => {
    navigate("/grade-guide");
  };
  const navigateToLogout = () => {
    sessionStorage.removeItem("loginState");
    navigate("/logout");
  };

  const Error = styled.strong`
    display: ${(props) => (props.display ? "block" : "none")};
    color: red;
    font-size: 1.7rem;
    letter-spacing: 3px;
  `;

  const [loginId, setLoginId] = useState("");
  const [password, setPassword] = useState("");
  const [loginState, setLoginState] = useState(null);
  const [error, setError] = useState(false);
  async function onSubmit(e) {
    e.preventDefault();
    try {
      const user = {
        loginId: loginId,
        password: password,
      };
      const response = await login(user);
      if (response.resultCode === "SUCCESS" && response.data) {
        console.log("로그인 성공", response);
        setLoginState({
          token: response.data.token,
          loginId: response.data.name,
        });

        sessionStorage.setItem(
          "loginState",
          JSON.stringify({
            token: response.data.token,
            loginId: response.data.name,
          })
        );
        setLoginId(response.data.name);
      } else if (response.resultCode === "ERROR") {
        console.log("로그인 실패");
      }
    } catch (err) {
      setError(true);
      console.log(err);
    }
  }

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  useEffect(() => {
    const storedLoginState = JSON.parse(sessionStorage.getItem("loginState"));
    if (storedLoginState && storedLoginState.loginId) {
      setLoginState(storedLoginState);
    }
  }, []);

  return (
    <>
      <BackGround>
        {loginState && loginState.token ? (
          <>
            <CrPar>
              <CrSect1>
                <CrContainer>
                  <CrText1>
                    <p>{loginState.loginId} 님</p>
                  </CrText1>
                  <Logo />
                  <CrText2>
                    <p>모나카가 함께 합니다.</p>
                  </CrText2>
                  <Welcome>
                    <CrButton onClick={navigateToLogout}>로그아웃</CrButton>
                  </Welcome>
                </CrContainer>
              </CrSect1>
            </CrPar>
          </>
        ) : (
          <>
            {/* 로그아웃 */}
            <Par>
              <Sect1>
                <Container>
                  <Text1>
                    <p>모든 시니어분들의 디지털 세상 첫 출발을</p>
                  </Text1>
                  <Logo />
                  <Text2>
                    <p>모나카가 함께 합니다.</p>
                  </Text2>{" "}
                  <Error display={error ? 1 : 0}>
                    * 아이디 또는 비밀번호가 일치하지 않습니다.
                  </Error>
                  <Section>
                    <SecLog>로그인</SecLog>
                    <form onSubmit={onSubmit}>
                      <Id>
                        <label>아이디</label>
                        <input
                          input
                          id="loginId"
                          pattern="[A-Za-z]+"
                          value={loginId}
                          onChange={(e) => setLoginId(e.target.value)}
                        />
                      </Id>
                      <Pw>
                        <label>비밀번호</label>
                        <input
                          input
                          id="password"
                          pattern="[A-Za-z]+"
                          value={password}
                          type="password"
                          onChange={(e) => setPassword(e.target.value)}
                        />
                      </Pw>
                      <Reg>
                        <LgButton>
                          <button onClick={onSubmit}>로그인</button>
                        </LgButton>
                        <StyledNavLink to="/student-register">
                          가입하기
                        </StyledNavLink>
                      </Reg>
                    </form>{" "}
                  </Section>
                </Container>
              </Sect1>
            </Par>
          </>
        )}
        <Sect2>
          <Opencourseinformation />
        </Sect2>
        <Sect3>
          <Container2 data-aos="flip-up" data-aos-duration="2000">
            <Title2>
              <p>&#60;급훈&#62;</p>
            </Title2>
            <Text3>
              <h2>왜 모나카를 선택해야 할까?</h2>
              <h2>첫 번째. 쉽고 간편하니까!</h2>
              <h2>두 번째. 재밌는 요소들로 학습욕구가 솟아나니까!</h2>
              <h2>세 번째. 필요한 양질의 강의가 여기 모였으니까!</h2>
            </Text3>
          </Container2>
        </Sect3>
        <Sect4>
          <MainLecture />
        </Sect4>
        <Sect5>
          <FolderWrapper>
            <Folder1 onClick={navigateToGradeGuide}>
              <h2>회원 등급 안내도</h2>
            </Folder1>
            <Folder2 onClick={navigateToLectureList}>
              <h2>강의목록</h2>
            </Folder2>
            <Folder3 onClick={navigateToProfessorList}>
              <h2>교수님 목록</h2>
            </Folder3>
          </FolderWrapper>
        </Sect5>
        <MonacaInfo />
      </BackGround>
    </>
  );
}
