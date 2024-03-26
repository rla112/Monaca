import styled from "styled-components";
import { NavBar } from "../NavBar";
import { PFRBar } from "../Professors/PFRBar";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

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
  grid-template-rows: 1fr 1fr 1fr;
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

export function StudentMgmt() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const navigate = useNavigate();

  const navigateToExamPaper = () => {
    navigate("/exam-paper");
  };

  return (
    <>
      <NavBar />
      <Container>
        <Wrapper>
          <PFRBar />
          <Section1>
            <Folder1>
              <Text1>
                <h2>최근 등록한 시험</h2>
                <h4>전체보기 &#62;</h4>
              </Text1>
              <Text2>등록된 시험이 없습니다.</Text2>
              <Text3></Text3>
              <Button>
                <button onClick={navigateToExamPaper}>시험지 바로보기</button>
              </Button>
            </Folder1>
            <Folder2>
              <Inner1>
                <Text4>
                  <h2>학생 시험지 목록</h2>
                  <h4>전체보기 &#62;</h4>
                </Text4>
              </Inner1>
              <Inner2>
                <Text4>
                  <h2>학생 출결관리</h2>
                  <h4>전체보기 &#62;</h4>
                </Text4>
              </Inner2>
              <Inner2>
                <Text4>
                  <h2>학생 진도율관리</h2>
                  <h4>전체보기 &#62;</h4>
                </Text4>
              </Inner2>
            </Folder2>
          </Section1>
        </Wrapper>
      </Container>
    </>
  );
}
