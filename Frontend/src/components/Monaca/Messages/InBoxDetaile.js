import styled from "styled-components";
import logo from "../image/CTextLogo.jpg";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import { useNavigate } from "react-router-dom";
import inDe from "../image/InBoxDetaile.jpg";

const Back = styled.div`
  background-image: url(${inDe});
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  width: 100vw;
  height: 135vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "GmarketSansMedium";
`;

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: top;
  justify-content: center;
`;
const Window = styled.div`
  margin-top: 4rem;
  background-color: white;
  border: 1px solid black;
  width: 35%;
  height: 70%;
`;

const Headers = styled.div`
  width: 100%;
  height: 28%;
  display: grid;
  grid-template-rows: repeat(5, 50px);
`;

const Hd1 = styled.div`
  width: 100%;
  height: 100%;
  background-color: #0b4434;
  border-bottom: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Blank = styled.div`
  width: 30%;
  background-color: beige;
`;

const Logo = styled.div`
  width: 40%;
  height: 55%;
  background-image: url(${logo});
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
`;

const Close = styled.div`
  width: 27%;
  height: 55%;
  display: flex;
  justify-content: end;
  & button {
    background-color: white;
    border: 1px solid black;
    border-radius: 8px;
    width: 20%;
    height: 100%;
    font-weight: bolder;
    font-family: "GmarketSansMedium";
    &:hover {
      cursor: pointer;
    }
    &:active {
      background-color: #6666;
    }
  }
`;

const Hd2 = styled.div`
  width: 100%;
  height: 100%;
  background-color: #082f24;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid black;
`;

const BTitle = styled.div`
  width: 100%;
  height: 70%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.3rem;
  font-weight: bolder;
  color: white;
`;

const Hd3 = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  border-bottom: 1px solid black;
`;

const Buttons = styled.div`
  width: 39%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: end;
  gap: 1.5rem;
  & button {
    width: 30%;
    height: 80%;
    border-radius: 8px;
    border: 1px solid #686868;
    background-color: white;
    font-size: 1.2rem;
    font-weight: bolder;
    font-family: "GmarketSansMedium";
    color: #686868;
    &:hover {
      cursor: pointer;
    }
    &:active {
      background-color: #6666;
    }
  }
`;

const Blank2 = styled.div`
  width: 50%;
  height: 100%;
`;

const Blank3 = styled.div`
  width: 10%;
  height: 100%;
`;

const Hd4 = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid black;
`;

const Recipient = styled.div`
  width: 97%;
  height: 90%;
  display: flex;
  align-items: center;
  font-size: 1.2rem;
  color: #686868;
`;

const Hd5 = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid black;
`;

const SentTime = styled.div`
  width: 97%;
  height: 90%;
  display: flex;
  align-items: center;
  font-size: 1.2rem;
  color: #686868;
`;

const Main = styled.div`
  width: 100%;
  height: 71%;
  margin-top: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-bottom-left-radius: 19px;
  border-bottom-right-radius: 19px;
  overflow: auto;
`;

const Text = styled.div`
  width: 95%;
  height: 95%;
`;

const Msg = styled.div`
  width: 100%;
  height: 100%;
`;

export function InBoxDetaile() {
  const navigate = useNavigate();

  const navigateToInBox = () => {
    navigate("/in-box");
  };

  return (
    <>
      <Back>
        <Wrapper>
          <Window>
            <Headers>
              <Hd1>
                <Blank />
                <Logo />
                <Close>
                  <button onClick={navigateToInBox}>X</button>
                </Close>
              </Hd1>
              <Hd2>
                <BTitle>
                  <p>받은 쪽지</p>
                </BTitle>
              </Hd2>
              <Hd3>
                <Blank2 />
                <Blank3 />
                <Buttons>
                  <button>답장</button>
                  <button>삭제</button>
                </Buttons>
              </Hd3>
              <Hd4>
                <Recipient>
                  <p>보낸 사람 : </p>
                </Recipient>
              </Hd4>
              <Hd5>
                <SentTime>
                  <p>수신 시간 : </p>
                </SentTime>
              </Hd5>
            </Headers>
            <Main>
              <Text>
                <Msg>
                  <h3>안녕하세요 저는 user입니다.</h3>
                  <h5>
                    Lorem Ipsum is simply dummy text of the printing and
                    typesetting industry. Lorem Ipsum has been the industry's
                    standard dummy text ever since the 1500s, when an unknown
                    printer took a galley of type and scrambled it to make a
                    type specimen book. It has survived not only five centuries,
                    but also the leap into electronic typesetting, remaining
                    essentially unchanged. It was popularised in the 1960s with
                    the release of Letraset sheets containing Lorem Ipsum
                    passages, and more recently with desktop publishing software
                    like Aldus PageMaker including versions of Lorem Ipsum Lorem
                    Ipsum is simply dummy text of the printing and typesetting
                    industry. Lorem Ipsum has been the industry's standard dummy
                    text ever since the 1500s, when an unknown printer took a
                    galley of type and scrambled it to make a type specimen
                    book. It has survived not only five centuries, but also the
                    leap into electronic typesetting, remaining essentially
                    unchanged. It was popularised in the 1960s with the release
                    of Letraset sheets containing Lorem Ipsum passages, and more
                    recently with desktop publishing software like Aldus
                    PageMaker including versions of Lorem Ipsum Lorem Ipsum is
                    simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever
                    since the 1500s, when an unknown printer took a galley of
                    type and scrambled it to make a type specimen book. It has
                    survived not only five centuries, but also the leap into
                    electronic typesetting, remaining essentially unchanged. It
                    was popularised in the 1960s with the release of Letraset
                    sheets containing Lorem Ipsum passages, and more recently
                    with desktop publishing software like Aldus PageMaker
                    including versions of Lorem Ipsum Lorem Ipsum is simply
                    dummy text of the printing and typesetting industry. Lorem
                    Ipsum has been the industry's standard dummy text ever since
                    the 1500s, when an unknown printer took a galley of type and
                    scrambled it to make a type specimen book. It has survived
                    not only five centuries, but also the leap into electronic
                    typesetting, remaining essentially unchanged. It was
                    popularised in the 1960s with the release of Letraset sheets
                    containing Lorem Ipsum passages, and more recently with
                    desktop publishing software like Aldus PageMaker including
                    versions of Lorem Ipsum Lorem Ipsum is simply dummy text of
                    the printing and typesetting industry. Lorem Ipsum has been
                    the industry's standard dummy text ever since the 1500s,
                    when an unknown printer took a galley of type and scrambled
                    it to make a type specimen book. It has survived not only
                    five centuries, but also the leap into electronic
                    typesetting, remaining essentially unchanged. It was
                    popularised in the 1960s with the release of Letraset sheets
                    containing Lorem Ipsum passages, and more recently with
                    desktop publishing software like Aldus PageMaker including
                    versions of Lorem Ipsum Lorem Ipsum is simply dummy text of
                    the printing and typesetting industry. Lorem Ipsum has been
                    the industry's standard dummy text ever since the 1500s,
                    when an unknown printer took a galley of type and scrambled
                    it to make a type specimen book. It has survived not only
                    five centuries, but also the leap into electronic
                    typesetting, remaining essentially unchanged. It was
                    popularised in the 1960s with the release of Letraset sheets
                    containing Lorem Ipsum passages, and more recently with
                    desktop publishing software like Aldus PageMaker including
                    versions of Lorem Ipsum Lorem Ipsum is simply dummy text of
                    the printing and typesetting industry. Lorem Ipsum has been
                    the industry's standard dummy text ever since the 1500s,
                    when an unknown printer took a galley of type and scrambled
                    it to make a type specimen book. It has survived not only
                    five centuries, but also the leap into electronic
                    typesetting, remaining essentially unchanged. It was
                    popularised in the 1960s with the release of Letraset sheets
                    containing Lorem Ipsum passages, and more recently with
                    desktop publishing software like Aldus PageMaker including
                    versions of Lorem Ipsum
                  </h5>
                </Msg>
              </Text>
            </Main>
          </Window>
        </Wrapper>
      </Back>
    </>
  );
}
