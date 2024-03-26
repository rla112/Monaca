import styled from "styled-components";
import CandyRoom from "../image/CandyRoom.jpg";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
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
  width: 90%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow: auto;
`;

const Main = styled.div`
  width: 100%;
  height: 100%;
`;

const Header = styled.div`
  width: 100%;
  height: 15%;
  border-bottom: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
`;
const Input = styled.div`
  width: 10%;
  height: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
  & input {
    width: 30%;
    height: 30%;
  }
`;
const Msg = styled.div`
  width: 100%;
  height: 15%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid black;
`;

const MsgInner = styled.div`
  width: 90%;
  height: 100%;
  display: flex;
  align-items: center;
  cursor: pointer;
  & :nth-child(1) {
    height: 100%;
    width: 16.5%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(2) {
    height: 100%;
    width: 39%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(3) {
    height: 100%;
    width: 22.2%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(4) {
    height: 100%;
    width: 22.2%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

const Trash = styled.div`
  width: 90%;
  height: 4%;
  margin-top: 7px;
  display: flex;
  justify-content: end;
  & button {
    padding: 8px;
    display: flex;
    align-items: center;
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
    background-image: linear-gradient(to right, #6666 70%, #c95561 30%);
  }
  & :nth-child(2) {
    background-image: linear-gradient(to right, white 70%, #68a0c2 30%);
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
export function OutBox() {
  const navigate = useNavigate();

  const navigateToMessage = () => {
    navigate("/message");
  };

  const navigateToOutBox = () => {
    navigate("/out-box");
  };

  const navigateToInBox = () => {
    navigate("/in-box");
  };

  const navigateToOutBoxDetaile = () => {
    navigate("/out-box-detaile");
  };

  function selectAll(event) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach((checkbox) => {
      checkbox.checked = event.target.checked;
    });
  }

  return (
    <>
      <Back>
        <Section1>
          <Folder>
            <FInner>
              <Main>
                <Header>
                  <Input>
                    <input
                      type="checkbox"
                      name="out"
                      value={selectAll}
                      onClick={(e) => selectAll(e)}
                    />
                  </Input>
                  <MsgInner>
                    <h4>받는 사람</h4>
                    <h4>제목</h4>
                    <h4>날짜</h4>
                    <h4>읽음 상태</h4>
                  </MsgInner>
                </Header>
                <Msg>
                  <Input>
                    <input type="checkbox" name="out" value="message" />
                  </Input>
                  <MsgInner onClick={navigateToOutBoxDetaile}>
                    <h4>123</h4>
                    <h4>123</h4>
                    <h4>2024-01-30</h4>
                    <h4>안 읽음</h4>
                  </MsgInner>
                </Msg>
                <Msg>
                  <Input>
                    <input type="checkbox" name="out" value="message" />
                  </Input>
                  <MsgInner onClick={navigateToOutBoxDetaile}>
                    <h4>123</h4>
                    <h4>123</h4>
                    <h4>2024-01-30</h4>
                    <h4>안 읽음</h4>
                  </MsgInner>
                </Msg>
                <Msg>
                  <Input>
                    <input type="checkbox" name="out" value="message" />
                  </Input>
                  <MsgInner onClick={navigateToOutBoxDetaile}>
                    <h4>123</h4>
                    <h4>123</h4>
                    <h4>2024-01-30</h4>
                    <h4>안 읽음</h4>
                  </MsgInner>
                </Msg>
                <Msg>
                  <Input>
                    <input type="checkbox" name="out" value="message" />
                  </Input>
                  <MsgInner onClick={navigateToOutBoxDetaile}>
                    <h4>123</h4>
                    <h4>123</h4>
                    <h4>2024-01-30</h4>
                    <h4>안 읽음</h4>
                  </MsgInner>
                </Msg>
                <Msg>
                  <Input>
                    <input type="checkbox" name="out" value="message" />
                  </Input>
                  <MsgInner onClick={navigateToOutBoxDetaile}>
                    <h4>123</h4>
                    <h4>123</h4>
                    <h4>2024-01-30</h4>
                    <h4>안 읽음</h4>
                  </MsgInner>
                </Msg>
                <Msg>
                  <Input>
                    <input type="checkbox" name="out" value="message" />
                  </Input>
                  <MsgInner onClick={navigateToOutBoxDetaile}>
                    <h4>123</h4>
                    <h4>123</h4>
                    <h4>2024-01-30</h4>
                    <h4>안 읽음</h4>
                  </MsgInner>
                </Msg>
              </Main>
            </FInner>
            <Trash>
              <button>휴지통으로 보내기</button>
            </Trash>
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
