import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import { PFRBar } from "../Professors/PFRBar";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Wrapper = styled.div`
   width: 100vw;
   height: 100vh;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   margin-top: 3rem;
   font-family: "GmarketSansMedium";
`;

const SubWrapper = styled.div`
   width: 65.5%;
   height: 100%;
   display: flex;
   align-items: center;
   justify-content: center;
`;
const Section1 = styled.div`
   display: flex;
   margin-left: 2rem;
   width: 90%;
   height: 100%;
   align-items: center;
   justify-content: center;
`;
const Grade = styled.div`
   width: 65%;
   height: 10%;
   display: flex;
   flex-direction: column;
   justify-content: center;
`;

const User = styled.div`
   height: 100%;
`;

const Folder = styled.div`
   border: 20px solid #fcc757;
   border-radius: 20px;
   width: 90%;
   height: 65%;
   display: flex;
   flex-direction: column;
   font-size: 10rem;
`;

const Button = styled.button`
   border: 0;
   display: flex;
   flex-direction: column;
   width: 10%;
   height: 60%;
   background-color: #ffffff;
   & :nth-child(1) {
      background-image: linear-gradient(to right, #6666 70%, #c95561 30%);
   }
   & :nth-child(2) {
      background-image: linear-gradient(to right, white 70%, #68a0c2 30%);
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
      padding-left: 3px;
      &:hover {
         cursor: pointer;
      }
   }
`;

export function PfsProfile() {
   useEffect(() => {
      window.scrollTo(0, 0);
   }, []);

   const navigate = useNavigate();

   const navigateToProfessorRoom = () => {
      navigate("/pf-room");
   };

   const navigateToPfsProfile = () => {
      navigate("/pf-profile");
   };

   return (
      <>
         <NavBar />
         <Wrapper>
            <Grade>
               <User></User>
            </Grade>
            <SubWrapper>
               <PFRBar />
               <Section1>
                  <Folder>교수 프로필</Folder>
                  <Button>
                     <button onClick={navigateToProfessorRoom}>내캐릭터</button>
                     <button onClick={navigateToPfsProfile}>프로필</button>
                  </Button>
               </Section1>
            </SubWrapper>
         </Wrapper>
         <MonacaInfo />
      </>
   );
}
