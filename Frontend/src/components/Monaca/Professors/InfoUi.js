import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import Kioks1 from "../image/Kiosk/Kiosk1.jpg";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

const Wrapper = styled.div`
   width: 100vw;
   height: 200vh;
   font-family: "GmarketSansMedium";
   display: flex;
   flex-direction: column;
   align-items: center;
`;

const Section1 = styled.div`
   width: 100%;
   height: 30%;
   background-color: #0b4434;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
`;

const InnerWrap = styled.div`
   height: 100%;
   width: 100%;
   display: flex;
   align-items: center;
   justify-content: center;
`;

const SecInner1 = styled.div`
   height: 60%;
   width: 25%;
   margin: 3rem;
   background-color: black;
   color: white;
   font-size: 2rem;
   text-align: center;
`;

const SecInner2 = styled.div`
   height: 100%;
   width: 40%;
   margin: 3rem;
   display: flex;
   flex-direction: column;
   justify-content: center;
`;

const InfoWrap = styled.div`
   height: 100%;
   line-height: 4rem;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   color: white;
`;
const LectureCategory = styled.div`
   font-size: 2.5rem;
`;
const LectureName = styled.div`
   font-size: 2.5rem;
`;

const CurrentList = styled.div`
   font-size: 2.5rem;
`;

const ProfessorName = styled.div`
   font-size: 2.5rem;
`;


const SecWrap = styled.div`
   width: 80%;
   height: 100%;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
`

const Section2 = styled.div`
   width: 70%;
   height: 55%;
   display: flex;
   flex-direction: column;
   align-items: center;
   /* background-color: aqua; */
   margin-top: 2rem;
`;

const Intro1 = styled.div`
   width: 80%;
   height: 100%;
   /* background-color: beige; */
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
   font-size: 1.5rem;
`;

const Box1 = styled.div`
   width: 80%;
   height: 100%;
   /* background-color: blue; */
   /* border: 1px solid black; */
   display: flex;
   justify-content: center;
   line-height: 4rem;
`;

const BInner = styled.div`
   width: 15%;
   height: 100%;
   display: grid;
   grid-template-rows: 60px 60px 60px 60px 100px;
   align-items: center;
   justify-content: end;
   & h5 {
      width: 100%;
      height: 100%;
      /* background-color: aqua; */
      /* border: 1px solid black; */
      text-align: end;
      border-bottom: 1px solid silver;
      &:nth-child(5) {
         border: none;
      }
   }
`;

const BInner2 = styled.div`
   display: grid;
   grid-template-rows: 60px 60px 60px 60px 359px;
   width: 85%;
   height: 100%;
   /* background-color: bisque; */
`;

const Section3 = styled.div`
   width: 70%;
   height: 45%;
   display: flex;
   flex-direction: column;
   align-items: center;
   padding-top: 3rem;
   /* background-color: brown; */
`;

const Lecture = styled.div`
   width: 78%;
   height: 100%;
   /* background-color: beige; */
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
   font-size: 1.5rem;
`;

const Box2 = styled.div`
   width: 80%;
   height: 100%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   /* background-color: purple */
`;

const Inner = styled.div`
   display: grid;
   grid-template-rows: 179px 179px 179px;
   
`;

const Name = styled.div`
   width: 100%;
   height: 100%;
   border-bottom: 1px solid silver;
`;

const Birth = styled.div`
   width: 100%;
   height: 100%;
   border-bottom: 1px solid silver;
`;

const Email = styled.div`
   width: 100%;
   height: 100%;
   border-bottom: 1px solid silver;
`;

const Resume = styled.div`
   width: 100%;
   height: 100%;
   border-bottom: 1px solid silver;
`;

const Me = styled.div`
   width: 100%;
   height: 100%;
   border-bottom: 1px solid silver;

`;

const Lecture1 = styled.div`
   width: 100%;
   height: 100%;
   /* background-color: red; */
   display: flex;
   align-items: center;
   border-bottom : 1px solid silver;

`;

const Pic = styled.div`
   width : 40%;
   height: 90%;
   text-align: center;
   border: 1px solid black;
`;

const Info = styled.div`
   width: 80%;
   height: 100%;
   display: flex;
   align-items: center;
   justify-content: center;

`;

 // 교수 상세 페이지
export function InfoUi() {
   return (
      <>
         <NavBar />
         <Wrapper>
            <Section1>
               <InnerWrap>
                  <SecInner1>Professor Image</SecInner1>
                  <SecInner2>
                     <InfoWrap>
                        <LectureCategory>ProfessorName</LectureCategory>
                        <br />
                        <CurrentList>LectureCategory</CurrentList>
                        <ProfessorName>전공 하드코딩</ProfessorName>
                     </InfoWrap>
                  </SecInner2>
               </InnerWrap>
            </Section1>
            <SecWrap>
            <Section2>
               <Intro1>
                  <h3>교수님 정보</h3>
                  <Box1>
                     <BInner>
                        <h5>성함 : </h5>
                        <h5>생년월일 : </h5>
                        <h5>이메일 : </h5>
                        <h5>경력 : </h5>
                        <h5>자기소개 : </h5>
                     </BInner>
                     <BInner2>
                        <Name></Name>
                        <Birth></Birth>
                        <Email></Email>
                        <Resume></Resume>
                        <Me></Me>
                     </BInner2>
                  </Box1>
               </Intro1>
            </Section2>
            <Section3>
               <Lecture>
                  <h3>교수님 강의 목록</h3>{" "}
                  <Box2>
                     <Inner>
                        <Lecture1>
                           <Pic>LecturePic</Pic>
                           <Info>
                              <h3>LectureTitle</h3>
                           </Info>
                        </Lecture1>
                        <Lecture1>
                           <Pic>LecturePic</Pic>
                           <Info></Info>
                        </Lecture1>
                        <Lecture1>
                           <Pic>LecturePic</Pic>
                           <Info></Info>
                        </Lecture1>
                     </Inner>
                  </Box2>
               </Lecture>
            </Section3>
            </SecWrap>
         </Wrapper>
         <MonacaInfo />
      </>
   );
}