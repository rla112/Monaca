import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import YellowNote from "../image/YellowNote.png";
import { NavBar } from "../NavBar";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

const Wrapper = styled.div`
   height: 310vh;
   font-family: "GmarketSansMedium";
`;

const Section1 = styled.div`
   height: 10vh;
   width: 100vw;
   margin-bottom: 15vh;
`;

const Header = styled.div`
   width: 100%;
   height: 12vh;
   background-color: #0b4434;
   display: flex;
   align-items: center;
   justify-content: center;
   font-family: "GmarketSansMedium";
   & h1 {
      color: white;
      font-size: 2rem;
      letter-spacing: 5px;
   }
`;

const Section2 = styled.div`
   width: 100%;
   height: 290vh;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
`;

const NoticeInner = styled.div`
   width: 50%;
   height: 140vh;
   display: flex;
   flex-direction: column;
   margin: 1rem auto;
`;

const NewButton = styled.div`
   width: 100%;
   height: 5%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: end;
   & button {
      width: 15%;
      height: 50%;
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

const Title = styled.div`
   width: 100%;
   height: 10%;
   background-color: white;
   border-top: 5px solid silver;
   border-bottom: 5px solid silver;
   display: flex;
   align-items: center;
   justify-content: center;
   font-size: 2rem;
   letter-spacing: 3px;
`;

const Text = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   justify-content: center;
   border-bottom: 5px solid silver;
`;

const MemoP = styled.div`
   margin-top: 10vh;
   width: 50%;
   height: 100vh;
   background-image: url(${YellowNote});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
`;

const MemoTitle = styled.div`
   width: 100%;
   height: 10%;
   display: flex;
   justify-content: center;
   text-align: center;
   font-size: 3rem;
   padding-bottom: 2rem;
   margin-bottom: 1rem;
`;

const MemoInner = styled.div`
   height: 70%;
   width: 75%;
   display: flex;
   flex-direction: column;
   align-items: center;
`;

const SubHeader = styled.div`
   display: flex;
   height: 100%;
   width: 100%;
`;

const SubTitle = styled.div`
   height: 100%;
   width: 25%;
   display: flex;
   flex-direction: column;
   & ul {
      list-style: none;
      display: grid;
      grid-template-rows: repeat(7, 1fr);
      height: 100%;
      align-items: center;
      font-size: 1.5rem;
      text-align: center;
   }
`;

const SubText = styled.div`
   height: 100%;
   width: 75%;
   display: flex;
   flex-direction: column;
   & ul {
      list-style: none;
      display: grid;
      grid-template-rows: repeat(7, 1fr);
      height: 100%;
      align-items: center;
      font-size: 1rem;
      text-align: center;
   }
`;

const Inner = styled.div`
   width: 100%;
   height: 100%;

   overflow: auto;
   & ul {
      width: 100%;
      height: 40%;

      & li {
         width: 100%;
         height: 20%;
         display: flex;
         flex-direction: column;
         justify-content: center;
         border-bottom: 1px solid silver;
      }
   }
`;

const Bottom = styled.div`
   width: 35%;
   height: 10vh;
   margin-top: 2rem;
   display: flex;
   align-items: center;
   justify-content: center;
   & p {
      font-size: 2rem;
      margin-right: 2rem;
   }
`;

const BotText = styled.div`
   display: flex;
   align-items: center;
   justify-content: center;
   height: 100%;
   width: 45%;
   font-size: 1.5rem;
   margin: auto;
`;

const BotButton = styled.div`
   font-family: "GmarketSansMedium";
   display: flex;
   align-items: center;
   text-align: center;
   height: 50%;
   width: 40%;
`;

const Button = styled.button`
   font-family: "GmarketSansMedium";
   width: 90%;
   height: 100%;
   font-size: 1.5rem;
   border: none;
   background-color: #0b4434;
   color: white;
   &:hover {
      cursor: pointer;
   }
`;

const StyledLi = styled.div`
   width: 100%;
   display: flex;
   height: 200px;
   border-top: 1px solid silver;
   border-bottom: 1px solid silver;
   background-color: white;
   display: flex;
   & p {
      width: 90%;
      height: 90%;
      font-size: 1rem;
      padding: 10px;
   }
`;

const XButton = styled.div`
   width: 10%;
   height: 20%;
   display: flex;
   justify-content: end;
`;

const StyledButton = styled.button`
   width: 40px;
   height: 40px;
   font-size: 1rem;
   border: none;
   cursor: pointer;
`;

export function AdminNotice() {
   const [isModalOpen, setIsModalOpen] = useState(false);
   const [selectedContent, setSelectedContent] = useState(null);
   const [indexState, setIndexState] = useState(-1);

   const contents = [
      {
         title: "모나카 업데이트 일정 안내",
         text: "모나카를 이용해주시는 여러분 안녕하세요 관리자 입니다.ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ최근 부정행위를 남발하여 얻는 포인트제제에 대해 신고가 많이 들어오고있습니다. ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ금일부터 적극적인 조치가 이뤄져 해당 계정들은 모두 이용정지 조치를 취하였습니다.ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ앞으로 더더욱 모니터를 꾸준히 하여 깨끗한 모나카가 될 수있도록 노력하겠습니다.ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ읽어주셔서 감사합니다.",
      },
      {
         title: "공지사항2",
         text: "공지사항2 입니다.",
      },
      {
         title: "공지사항3",
         text: "공지사항3 입니다.",
      },
      {
         title: "공지사항4",
         text: "공지사항4 입니다.",
      },
      {
         title: "공지사항5",
         text: "공지사항5 입니다.",
      },
      {
         title: "공지사항6",
         text: "공지사항6 입니다.",
      },
      {
         title: "공지사항7",
         text: "공지사항7 입니다.",
      },
      {
         title: "공지사항8",
         text: "공지사항8 입니다.",
      },
      {
         title: "공지사항9",
         text: "공지사항9 입니다.",
      },
      {
         title: "공지사항10",
         text: "공지사항10 입니다.",
      },
      {
         title: "공지사항11",
         text: "공지사항11 입니다.",
      },
      {
         title: "공지사항12",
         text: "공지사항12 입니다.",
      },
      {
         title: "공지사항13",
         text: "공지사항13 입니다.",
      },
      {
         title: "공지사항14",
         text: "공지사항14 입니다.",
      },
   ];

   const openModal = (index) => {
      //  setSelectedContent(content);
      console.log("index", index);
      setIsModalOpen(true);
      setIndexState(index);
   };

   const closeModal = (index) => {
      setIsModalOpen(false);
      setIndexState(-1);
      console.log("test");
   };

   const navigate = useNavigate();

   const navigateToMessage = () => {
      navigate("/message");
   };

   const navigateToWriteaNotice = () => {
      navigate("/write-a-notice");
   };

   return (
      <>
         <Wrapper>
            <Section1>
               <NavBar />
               <Header>
                  <h1>모나카 공지사항</h1>
               </Header>
            </Section1>
            <Section2>
               <NoticeInner>
                  <NewButton>
                     <button onClick={navigateToWriteaNotice}>
                        새소식 업데이트
                     </button>
                  </NewButton>
                  <Title>모나카 새소식</Title>
                  <Text>
                     <Inner>
                        <ul>
                           {contents.map((c, i) => (
                              <>
                                 <li key={i} onClick={() => openModal(i)}>
                                    {c.title}
                                 </li>
                                 {indexState == i && (
                                    <StyledLi key={i}>
                                       <p>{c.text}</p>
                                       <XButton>
                                          <StyledButton onClick={closeModal}>
                                             X
                                          </StyledButton>
                                       </XButton>
                                    </StyledLi>
                                 )}
                              </>
                           ))}
                        </ul>
                     </Inner>
                  </Text>
               </NoticeInner>
               <MemoP>
                  <MemoTitle>자주 묻는 질문</MemoTitle>
                  <MemoInner>
                     <SubHeader>
                        <SubTitle>
                           <ul>
                              <li>회원등급</li>
                              <li>탈퇴조치</li>
                              <li>
                                 탈퇴조치 후<br />
                                 재가입
                              </li>
                              <li>교재</li>
                              <li>수업진행방법</li>
                              <li>강의환불 문의</li>
                              <li>아이템 환불</li>
                           </ul>
                        </SubTitle>
                        <SubText>
                           <ul>
                              <li>
                                 메인페이지 하단에 회원등급안내도를 참고해주시길
                                 바랍니다.
                              </li>
                              <li>
                                 부정행위로 얻은 사탕 적발시 강제탈퇴조치
                                 하겠습니다.
                              </li>
                              <li>
                                 탈퇴조치 후에 재가입을 원하신다면 다시
                                 회원가입을 하시면 됩니다.
                                 <br /> 단, 회원등급이나 이전 계정에서 들었던
                                 강의는 초기화 됩니다.
                              </li>
                              <li>
                                 교재는 따로 있지 않고, 교수님이 올려주신 <br />
                                 강의자료로 학습하시길 바랍니다.
                              </li>
                              <li>
                                 실시간 수업은 존재하지 않습니다.
                                 <br />
                                 등록되어있는 강의로 학습하시길 바랍니다
                              </li>
                              <li>죄송하지만 강의특성상 환불은 어렵습니다.</li>
                              <li>죄송하지만 아이템은 환불이 어렵습니다.</li>
                           </ul>
                        </SubText>
                     </SubHeader>
                  </MemoInner>
               </MemoP>
               <Bottom>
                  <BotText>다른 궁금한 점 질문 하기</BotText>
                  <p>&#10230;</p>
                  <BotButton>
                     <Button onClick={navigateToMessage}>
                        관리자에게 쪽지 쓰기
                     </Button>
                  </BotButton>
               </Bottom>
            </Section2>
         </Wrapper>
         <MonacaInfo />
      </>
   );
}
