import styled from "styled-components";
import CandyRoom from "../image/CandyRoom.jpg";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Back = styled.div`
  background-image: url(${CandyRoom});
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "GmarketSansMedium";
`;

const Section1 = styled.div`
  width: 80%;
  height: 80%;
  align-items: center;
  display: flex;
  justify-content: center;
  margin-left: 7rem;
`;
const Folder = styled.div`
  border: 30px solid #fcc757;
  border-radius: 20px;
  width: 70%;
  height: 90%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: white;
`;

const FInner = styled.div`
  width: 95%;
  height: 90%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Receiver = styled.div`
  width: 95%;
  height: 10%;
  display: flex;
  align-items: center;
  margin: 1rem;
  & label {
    margin-right: 0.5rem;
    font-size: 1.5rem;
    width: 15%;
  }
  & select {
    height: 60%;
    width: 15%;
    border: 1px solid black;
    background-color: white;
    border-radius: 5px;
  }
  & h5 {
    margin-left: 1rem;
  }
`;
const Title = styled.div`
  width: 95%;
  height: 10%;
  display: flex;
  align-items: center;
  margin: 1rem;
  & label {
    margin-right: 0.5rem;
    font-size: 1.5rem;
    width: 15%;
  }
  & input {
    outline: 1px solid black;
    height: 60%;
    width: 25%;
    border: 0;
    border-radius: 5px;
  }
  & h5 {
    margin-left: 1rem;
  }
`;
const Text = styled.div`
  width: 95%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 1rem;
  & label {
    outline: none;
    margin-right: 0.5rem;
    font-size: 1.5rem;
    width: 18%;
  }
`;
const TMain = styled.div`
  display: flex;
  width: 100%;
  height: 80%;
  & textarea {
    height: 100%;
    width: 100%;
    border: 1px solid black;

    padding-top: 10px;
    text-align: left;
    resize: none;
    border-radius: 5px;
  }
  & textarea:focus {
    outline: none;
  }
  & h5 {
    margin-top: 5px;
  }
`;
const TWrap = styled.div`
  width: 100%;
  height: 20%;
  display: flex;
  justify-content: center;
  align-items: center;
  & h5 {
    margin-left: 6rem;
    width: 50%;
    text-align: end;
  }
  & button {
    margin: 1rem;
    width: 15%;
    height: 25px;
    font-size: 1rem;
    background-color: white;
    border: 1px solid black;
    &:hover {
      cursor: pointer;
    }
    &:active {
      background-color: #6666;
    }
  }
`;
const Button = styled.button`
  border: 0;
  display: flex;
  flex-direction: column;
  width: 7%;
  height: 80%;
  background-color: #ffffff;
  & :nth-child(1) {
    background-image: linear-gradient(to right, white 70%, #ed6674 30%);
  }
  & :nth-child(2) {
    background-image: linear-gradient(to right, #6666 70%, #527d98 30%);
  }
  & :nth-child(3) {
    background-image: linear-gradient(to right, #6666 70%, #a8b860 30%);
  }
  & button {
    border: 0;
    height: 40px;
    width: 100%;
    margin-bottom: 1rem;
    font-size: 1rem;
    text-align: start;
    border-top: 1px solid black;
    border-bottom: 1px solid black;
    border-right: 1px solid black;
    font-family: "GmarketSansMedium";
    padding-left: 1px;
    letter-spacing: 3px;
    &:hover {
      cursor: pointer;
    }
  }
`;
export function Message() {
  const navigate = useNavigate();

  const [selectedOption, setSelectedOption] = useState("");

  const handleSelectChange = (event) => {
    setSelectedOption(event.target.value);
  };

  const navigateToMessage = () => {
    navigate("/message");
  };

  const navigateToOutBox = () => {
    navigate("/out-box");
  };

  const navigateToInBox = () => {
    navigate("/in-box");
  };

  return (
    <>
      <Back>
        <Section1>
          <Folder>
            <FInner>
              <Receiver>
                <label>받는 사람</label>
                <select
                  id="receiver"
                  value={selectedOption}
                  onChange={handleSelectChange}
                >
                  <option value="">수신자 선택</option>
                  <option value="모나카 관리자">모나카 관리자</option>
                  <option value="강태영 교수님">강태영 교수님</option>
                  <option value="김지영 교수님">김지영 교수님</option>
                  <option value="손미경 교수님">손미경 교수님</option>
                  <option value="이준호 교수님">이준호 교수님</option>
                  <option value="이현주 교수님">이현주 교수님</option>
                  <option value="정수빈 교수님">정수빈 교수님</option>
                  <option value="정현우 교수님">정현우 교수님</option>
                  <option value="최윤서 교수님">최윤서 교수님</option>
                  <option value="한태석 교수님">한태석 교수님</option>
                </select>
                <h5>받는 사람을 선택 해주세요.</h5>
              </Receiver>
              <Title>
                <label>제목</label>
                <input type="text"></input>
                <h5>제목은 최대 20자 까지 입력 가능합니다.</h5>
              </Title>
              <Text>
                <TMain>
                  <label>내용</label>
                  <textarea></textarea>
                </TMain>
                <TWrap>
                  <h5>내용은 최대 150자 까지 입력 가능합니다.</h5>
                  <button>발송</button>
                </TWrap>
              </Text>
            </FInner>
          </Folder>
          <Button>
            <button onClick={navigateToMessage}>쪽지발신</button>
            <button onClick={navigateToOutBox}>발신함</button>
            <button onClick={navigateToInBox}>수신함</button>
          </Button>
        </Section1>
      </Back>
      <MonacaInfo />
    </>
  );
}
