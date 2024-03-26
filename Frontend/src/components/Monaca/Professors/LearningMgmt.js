import styled from "styled-components";
import { NavBar } from "../NavBar";
import { PFRBar } from "../Professors/PFRBar";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { MonacaInfo } from "../MonacaInfo";

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
  display: grid;
  grid-template-columns: 1fr 1fr;
  align-items: center;
  justify-content: center;
`;

const Folder1 = styled.div`
  border: 1px solid silver;
  border-radius: 20px;
  width: 90%;
  height: 65%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Folder2 = styled.div`
  width: 90%;
  height: 65%;
  display: grid;
  grid-gap: 1rem;
  grid-template-rows: 1fr 1fr;
`;

const Inner1 = styled.div`
  border: 1px solid silver;
  border-radius: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const Inner2 = styled.div`
  border: 1px solid silver;
  border-radius: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const Text1 = styled.div`
  display: flex;
  width: 90%;
  height: 10%;
  align-items: center;
  & h2 {
    text-align: left;
    width: 80%;
  }
  & h4 {
    text-align: right;
    width: 20%;
    color: silver;
    &:hover {
      cursor: pointer;
    }
  }
`;
const Text2 = styled.div`
  width: 80%;
  height: 40%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Text3 = styled.div`
  width: 80%;
  height: 40%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Button = styled.button`
  margin: 0.5rem;
  border: none;
  background-color: #ffffff;
  & button {
    border: 1px solid silver;
    width: 140px;
    margin-right: 1rem;
    padding: 5px;
    font-family: "GmarketSansMedium";
    font-size: 1rem;
    background-color: #ffffff;
    &:hover {
      cursor: pointer;
    }

    &:active {
      background-color: #6666;
    }
  }
`;

const Text4 = styled.div`
  margin-top: 3px;
  width: 90%;
  height: 12%;
  display: flex;
  align-items: center;
  justify-content: center;
  & h2 {
    text-align: left;
    width: 80%;
  }
  & h4 {
    text-align: right;
    width: 20%;
    color: silver;
    &:hover {
      cursor: pointer;
    }
  }
`;

const Text5 = styled.div`
  width: 100%;
  height: 40%;
`;
const Text6 = styled.div`
  width: 100%;
  height: 40%;
`;

const Text7 = styled.div`
  width: 100%;
  height: 40%;
`;

const Text8 = styled.div`
  width: 100%;
  height: 40%;
`;

const SubInner1 = styled.div`
  width: 90%;
  height: 70%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 1rem;
`;

const SubInner2 = styled.div`
  width: 90%;
  height: 70%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 1rem;
`;
export function LearningMgmt() {
  const navigate = useNavigate();

  const navigateToMaterial = () => {
    navigate("/material");
  };

  const navigateToRegisteredLecture = () => {
    navigate("/reged-lectures");
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <NavBar />
      <Container>
        <Wrapper>
          <PFRBar />
          <Section1>
            <Folder1>
              <Text1>
                <h2>최근 등록한 강의</h2>
                <h4 onClick={navigateToRegisteredLecture}>전체보기 &#62;</h4>
              </Text1>
              <Text2>키오스크 초급 1-1</Text2>
              <Text3>총 인원수 : 2명</Text3>
              <Button>
                <button>강의 바로보기</button>
                <button onClick={navigateToMaterial}>강의 자료</button>
              </Button>
            </Folder1>
            <Folder2>
              <Inner1>
                <Text4>
                  <h2>최근 등록한 강의자료</h2>
                  <h4>전체보기 &#62;</h4>
                </Text4>
                <SubInner1>
                  <Text5>등록한 강의자료가 없습니다.</Text5>
                  <Text6></Text6>
                </SubInner1>
              </Inner1>
              <Inner2>
                <Text4>
                  <h2>최근 달린 질의응답</h2>
                  <h4>전체보기 &#62;</h4>
                </Text4>
                <SubInner2>
                  <Text7>최근 달린 질문이 없습니다. </Text7>
                  <Text8></Text8>
                </SubInner2>
              </Inner2>
            </Folder2>
          </Section1>
        </Wrapper>
      </Container>
      <MonacaInfo />
    </>
  );
}
