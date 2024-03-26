// 개강과목 안내
import { useEffect } from "react";
import YellowNote from "../image/YellowNote.png";
import PurpleNote from "../image/PurpleNote.jpg";
import CrumpledNote from "../image/CrumpledNote.jpg";
import Kiosk from "../image/Kiosk.jpg";
import Web from "../image/Web.jpg";
import Moblie from "../image/Moblie.jpg";
import Welfare from "../image/Welfare.jpg";
import styled, { createGlobalStyle } from "styled-components";
import Aos from "aos";
import "aos/dist/aos.css";

const Container = styled.div`
   width: 100vw;
   height: 200vh;
   align-items: center;
   display: flex;
   flex-direction: column;
   justify-content: center;
   font-family: NEXON Lv1 Gothic OTF;
`;

const Course = styled.div`
   width: 60%;
   height: 90%;
   padding-top: 1.5rem;
   display: flex;
   flex-direction: column;
`;

const Title = styled.h1`
   font-size: 3rem;
   padding-top: 10rem;
   padding-bottom: 1.5rem;
   letter-spacing: 4px;
`;

const Opencourse1 = styled.div`
   width: 60%;
   height: 25%;
   display: flex;
   padding: 1.5rem;
   margin-bottom: 2rem;
`;

const Op2Wrap = styled.div`
   display: flex;
   width: 60%;
`;
const Op2Blank = styled.div`
   width: 40%;
`;

const Opencourse2 = styled.div`
   width: 100%;
   height: 25%;
   display: flex;
   padding: 1.5rem;
   justify-content: center;
   margin-bottom: 2rem;
`;

const Opencourse3 = styled.div`
   width: 60%;
   height: 25%;
   display: flex;
   padding: 1.5rem;
   margin-bottom: 2rem;
`;

const Op4Wrap = styled.div`
   display: flex;
   width: 60%;
`;

const Op4Blank = styled.div`
   width: 40%;
`;

const Opencourse4 = styled.div`
   width: 100%;
   height: 25%;
   display: flex;
   padding: 1.5rem;
   justify-content: center;
`;

const Lecture1 = styled.div`
   width: 100%;
   height: 100%;
   background-image: url(${Kiosk});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   margin: 0 auto;
   margin-top: 1.9rem;
`;
const Lecture2 = styled.div`
   width: 100%;
   height: 100%;
   background-image: url(${Web});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   display: flex;
   flex-direction: column;
   justify-content: center;
   margin-top: 1rem;
`;
const Lecture3 = styled.div`
   width: 100%;
   height: 100%;
   background-image: url(${Moblie});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   margin: 0 auto;
   margin-top: 1.9rem;
`;
const Lecture4 = styled.div`
   width: 100%;
   height: 100%;
   background-image: url(${Welfare});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   margin: 0 auto;
   margin-top: 1.9rem;
`;

const LectureInfo1 = styled.div`
   width: 130%;
   height: 110%;
   background-image: url(${YellowNote});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   /* border: 2px solid black;
   background-color: lightblue; */
   & h2 {
      padding-top: 2rem;
      font-size: 2rem;
      text-align: center;
   }
   & ul {
      font-size: 1.2rem;
      line-height: 5rem;
      text-align: center;
   }
`;
const LectureInfo2 = styled.div`
   width: 130%;
   height: 110%;
   background-image: url(${PurpleNote});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   /* border: 2px solid black;
   background-color: lightblue; */
   & h2 {
      padding-top: 2rem;
      font-size: 2rem;
      text-align: center;
   }
   & ul {
      font-size: 1.2rem;
      line-height: 5rem;
      text-align: center;
   }
`;
const LectureInfo3 = styled.div`
   width: 130%;
   height: 110%;
   background-image: url(${CrumpledNote});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   /* border: 2px solid black;
   background-color: lightblue; */
   & h2 {
      padding-top: 2rem;
      font-size: 2rem;
      text-align: center;
   }
   & ul {
      font-size: 1.2rem;
      line-height: 2.3rem;
      text-align: center;
   }
`;
const LectureInfo4 = styled.div`
   width: 130%;
   height: 110%;
   background-image: url(${YellowNote});
   background-repeat: no-repeat;
   background-size: contain;
   background-position: center;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   & h2 {
      padding-top: 2rem;
      font-size: 2rem;
      text-align: center;
   }
   & ul {
      font-size: 1.2rem;
      line-height: 5rem;
      text-align: center;
   }
`;

export function Opencourseinformation() {
   useEffect(() => {
      Aos.init();
   });

   return (
      <>
         <Container>
            <Title>개강과목 안내</Title>
            <Course>
               <Opencourse1 data-aos="fade-up" data-aos-duration="1000">
                  <Lecture1></Lecture1>
                  <LectureInfo1>
                     <h2>키오스크 사용법</h2>
                     <ul>
                        <li>음식점 등 키오스크 사용법</li>
                        <li>대중교통 키오스크 사용법</li>
                        <li>무인 서류 발급기 사용법</li>
                     </ul>
                  </LectureInfo1>
               </Opencourse1>
               <Opencourse2 data-aos="fade-up" data-aos-duration="1000">
                  <Op2Blank />
                  <Op2Wrap>
                     <LectureInfo2>
                        <h2>웹사이트 사용법</h2>
                        <ul>
                           <li>웹사이트 회원가입 방법</li>
                           <li>웹사이트 인터넷 쇼핑</li>
                           <li>영상 시청 및 자료 검색</li>
                        </ul>
                     </LectureInfo2>
                     <Lecture2></Lecture2>
                  </Op2Wrap>
               </Opencourse2>
               <Opencourse3 data-aos="fade-up" data-aos-duration="1000">
                  <Lecture3></Lecture3>
                  <LectureInfo3>
                     <h2>모바일 기기 사용법</h2>
                     <ul>
                        <li>
                           간단한 스마트폰 사용법
                           <br />및<br />
                           소셜 미디어 사용법
                        </li>
                        <li>
                           인터넷 뱅킹
                           <br />및<br />
                           모바일 결제 방법
                        </li>
                     </ul>
                  </LectureInfo3>
               </Opencourse3>
               <Opencourse4 data-aos="fade-up" data-aos-duration="1000">
                  <Op4Blank />
                  <Op4Wrap>
                     <LectureInfo4>
                        <h2>
                           국가 복지 및 민원
                           <br />
                           신청법
                        </h2>
                        <ul>
                           <li>기초수급자 수급 신청법</li>
                           <li>긴급 복지지원 신청법</li>
                           <li>고령자 고용지원금 신청법</li>
                        </ul>
                     </LectureInfo4>
                     <Lecture4></Lecture4>
                  </Op4Wrap>
               </Opencourse4>
            </Course>
         </Container>
      </>
   );
}
