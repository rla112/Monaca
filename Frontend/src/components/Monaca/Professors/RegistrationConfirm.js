import styled from "styled-components";
import back from "../image/UploadBack.jpg";
import logo from "../image/CTextLogo.jpg";
import { useNavigate } from "react-router-dom";

const Back = styled.div`
   width: 100vw;
   height: 100vh;
   background-image: url(${back});
   background-position: center;
   background-size: contain;
   background-repeat: no-repeat;
   display: flex;
   align-items: center;
   justify-content: center;
   font-family: "GmarketSansMedium";
`;

const Window = styled.div`
   width: 20%;
   height: 40%;
   background-color: white;
   border: 1px solid black;
   border-radius: 20px;
`;

const Header = styled.div`
   width: 100%;
   height: 15%;
   display: flex;
   align-items: center;
   justify-content: center;
`;

const Logo = styled.div`
   width: 80%;
   height: 80%;
   background-image: url(${logo});
   background-size: contain;
   background-repeat: no-repeat;
   background-position: center;
`;

const Main = styled.div`
   width: 100%;
   height: 65%;
   display: flex;
   align-items: center;
   justify-content: center;
   text-align: center;
   line-height: 4rem;
`;

const Button = styled.div`
   width: 100%;
   height: 20%;
   display: flex;
   align-items: center;
   justify-content: center;
   & button {
      width: 45%;
      height: 60%;
      background-color: white;
      border: 1px solid black;
      font-size: 1rem;
      font-family: "GmarketSansMedium";
      font-weight: bold;
      &:hover {
         cursor: pointer;
      }
      &:active {
         background-color: #6666;
      }
   }
`;

export function RegistrationConfirm() {
   const navigate = useNavigate();

   const navigateToRegisteredLecture = () => {
      navigate("/reged-lectures");
   };

   return (
      <>
         <Back>
            <Window>
               <Header>
                  <Logo />
               </Header>
               <Main>
                  <h2>
                     강의가 <br />
                     성공적으로
                     <br />
                     업로드 되었습니다.
                  </h2>
               </Main>
               <Button>
                  <button onClick={navigateToRegisteredLecture}>
                     확인하기
                  </button>
               </Button>
            </Window>
         </Back>
      </>
   );
}
