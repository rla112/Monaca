import styled from "styled-components";
import { NavBar } from "../NavBar";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { MonacaInfo } from "../MonacaInfo";
import { AdminBar } from "./AdminBar";

const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "GmarketSansMedium";
`;

const Wrapper = styled.div`
  width: 64%;
  height: 100%;
  display: flex;
`;

const Section1 = styled.div`
  margin-left: 2rem;
  width: 90%;
  height: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
`;

const InWrap = styled.div`
  width: 80%;
  height: 30%;
  display: grid;
  grid-template-columns: repeat(2, 400px);
  gap: 10px;
`;

const Inner1 = styled.div`
  width: 100%;
  border: 1px solid silver;
  border-radius: 20px;
  & h3 {
    width: 38%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.1rem;
  }
`;

const Header1 = styled.div`
  width: 100%;
  height: 15%;
  display: flex;
`;

const P = styled.div`
  width: 60%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: end;
  color: gray;
  & p {
    &:hover {
      cursor: pointer;
    }
  }
`;

const Main1 = styled.div`
  width: 100%;
  height: 85%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Lectures = styled.div`
  width: 90%;
  height: 90%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;
`;

const Name = styled.div`
  width: 95%;
  height: 20%;
  display: flex;
  align-items: center;
  justify-content: start;
  font-size: 1.2rem;
`;

const Count = styled.div`
  width: 95%;
  height: 20%;
  display: flex;
  align-items: center;
  justify-content: start;
  font-size: 1.2rem;
`;

const Inner2 = styled.div`
  width: 100%;
  border: 1px solid silver;
  border-radius: 20px;
  & h3 {
    width: 45%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.1rem;
  }
`;

const Header2 = styled.div`
  width: 100%;
  height: 15%;
  display: flex;
`;

const Main2 = styled.div`
  width: 100%;
  height: 85%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const P2 = styled.div`
  width: 53%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: end;
  color: silver;
  & p {
    &:hover {
      cursor: pointer;
    }
  }
`;

export function AdminLMgnt() {
  const navigate = useNavigate();

  const navigateToLectureList = () => {
    navigate("/lecture-list");
  };

  const navigateToAdminNotice = () => {
    navigate("/admin-notice");
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <NavBar />
      <Container>
        <Wrapper>
          <AdminBar />
          <Section1>
            <InWrap>
              <Inner1>
                <Header1>
                  <h3>최근 등록된 강의</h3>
                  <P>
                    <p onClick={navigateToLectureList}>전체보기 &#62;</p>
                  </P>
                </Header1>
                <Main1>
                  <Lectures>
                    <Name>
                      <p>강의 이름 : 시민 중심의 민원</p>
                    </Name>
                    <Count>
                      <p>현 수강 인구 수 : 3명</p>
                    </Count>
                  </Lectures>
                </Main1>
              </Inner1>
              <Inner2>
                <Header2>
                  <h3>최근 등록된 공지사항</h3>
                  <P2>
                    <p onClick={navigateToAdminNotice}>전체보기 &#62;</p>
                  </P2>
                </Header2>
                <Main2>
                  <Lectures>
                    <Name>
                      <p>공지 제목 : 모나카 업데이트 일정 안내</p>
                    </Name>
                  </Lectures>
                </Main2>
              </Inner2>
            </InWrap>
          </Section1>
        </Wrapper>
      </Container>
      <MonacaInfo />
    </>
  );
}
