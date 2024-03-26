import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import { Main } from "../Mains/Main";
import { useNavigate } from "react-router-dom";

const Container = styled.div`
   width: 100vw;
   height: 100vh;
   font-family: "GmarketSansMedium";
`;

const Sec1 = styled.div`
   width: 100%;
   height: 12%;
   background-color: #0b4434;
   display: flex;
   align-items: center;
   justify-content: center;
   color: white;
   font-size: 1rem;
   letter-spacing: 5px;
`;

const Sec2 = styled.div`
   width: 100%;
   height: 100%;

   display: flex;
   align-items: center;
   justify-content: center;
   flex-direction: column;
`;

const Inner1 = styled.div`
   width: 50%;
   height: 85%;
`;

const Chin1 = styled.div`
   width: 100%;
   height: 10%;
`;

const Header1 = styled.div`
   width: 100%;
   height: 90%;

   display: flex;
   align-items: center;
   border-bottom: 1px solid silver;
`;

const Chin2 = styled.div`
   width: 100%;
   height: 8%;
`;

const Header2 = styled.div`
   width: 100%;
   height: 90%;
   display: flex;
   align-items: center;
   & p {
      font-size: 1.2rem;
   }
`;

const Title = styled.div`
   width: 100%;
   height: 80%;
   display: flex;
   align-items: center;
   & input {
      width: 50%;
      height: 80%;
      padding: 5px;
      outline: none;
      font-family: "GmarketSansMedium";
      &:focus {
         border: 1px solid black;
      }
   }
   margin-bottom: 1rem;
   border-bottom: 1px solid silver;
`;

const Chin3 = styled.div`
   width: 100%;
   height: 65%;
   margin-top: 3rem;
`;

const Header3 = styled.div`
   width: 100%;
   height: 10%;
   display: flex;
   align-items: center;
   & p {
      font-size: 1.2rem;
   }
`;

const Text = styled.div`
   width: 100%;
   height: 100%;
   & textarea {
      resize: none;
      outline: none;
      width: 100%;
      height: 500px;
      padding: 5px;
      font-family: "GmarketSansMedium";
   }
`;

const Button = styled.button`
   width: 10%;
   height: 40px;
   background-color: white;
   border: 1px solid black;
   &:hover {
      cursor: pointer;
   }
   &:active {
      background-color: #6666;
   }
`;

export function WriteaNotice() {
   const navigate = useNavigate();

   const navigateToAdminNotice = () => {
      navigate("/admin-notice");
   };
   return (
      <>
         <NavBar />
         <Container>
            <Sec1>
               <h1>모나카 공지사항</h1>
            </Sec1>
            <Sec2>
               <Inner1>
                  <Chin1>
                     <Header1>
                        <h2>공지사항 작성하기</h2>
                     </Header1>
                  </Chin1>
                  <Chin2>
                     <Header2>
                        <p>제목</p>
                     </Header2>
                     <Title>
                        <input type="text" placeholder="제목을 입력해주세요." />
                     </Title>
                  </Chin2>
                  <Chin3>
                     <Header3>
                        <p>내용</p>
                     </Header3>
                     <Text>
                        <textarea placeholder="내용을 입력해주세요." />
                     </Text>
                  </Chin3>
               </Inner1>
               <Button onClick={navigateToAdminNotice}>등록하기</Button>
            </Sec2>
         </Container>
         <MonacaInfo />
      </>
   );
}
