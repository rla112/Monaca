import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";

import Wom5 from "../image/Professor/WP5.jpg";

const Container = styled.div`
   width: 100vw;
   height: 150vh;
   font-family: "GmarketSansMedium";
`;

const Header = styled.div`
   width: 100%;
   height: 12vh;
   background-color: #0b4434;
   display: flex;
   align-items: center;
   justify-content: center;
   font-family: "TmonMonsori";
   & h1 {
      color: white;
      font-size: 2rem;
      letter-spacing: 5px;
   }
`;

const PfsWrap = styled.div`
   width: 60%;
   height: 20%;
   border: 1px solid black;
   border-radius: 1.5rem;
   margin: 5rem auto;
   display: flex;
   align-items: center;
`;

const Professor = styled.div`
   width: 20%;
   height: 80%;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   /* background-color: aqua; */
`;

const Profile = styled.div`
   width: 70%;
   height: 80%;
   background-image: url(${Wom5});
   background-position: center;
   background-repeat: no-repeat;
   background-size: cover;
`;

const Info = styled.div`
   width: 70%;
   height: 80%;
   /* background-color: red; */
   margin: 0 auto;
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
`;

const PsInfo = styled.div`
   display: flex;
   flex-direction: column;
   justify-content: center;
   width: 40%;
   height: 100%;
`;

const Resume = styled.div`
   display: flex;
   flex-direction: column;
   justify-content: center;
   width: 40%;
   height: 100%;
   margin-left: 5rem;
   white-space: nowrap;
   & h3 {
      line-height: 3rem;
   }
`;

const Btn = styled.div`
   width: 104%;
   height: 12%;
   display: flex;
   align-items: end;
   justify-content: end;
   & button {
      margin-left: 1rem;
      width: 24%;
      height: 135%;
      background-color: #0b4434;
      color: white;
      border: bla;
      font-size: 18px;
      font-family: "TmonMonsori";
      letter-spacing: 3px;
      &:hover {
         cursor: pointer;
      }
   }
`;

const Wrapper = styled.div`
   height: 100%;
   width: 100%;
   display: flex;
`;
export function ProfessorEmployee() {
   return (
      <>
         <NavBar />
         <Container>
            <Header>
               <h1>이력서 검토 후 채용 대기 중인 교수님 명단입니다.</h1>
            </Header>
            <PfsWrap>
               <Professor>
                  <Profile />
               </Professor>
               <Info>
                  <Wrapper>
                     <PsInfo>
                        <h2>이름 : 정수빈</h2>
                        <h3>성별 : 여</h3>
                        <h3>생년월일 : 1990년 03월 12일 (31세)</h3>
                        <h3>전화번호 : 010-5432-18976</h3>
                        <h3>이메일 : sb03@dw.com </h3>
                     </PsInfo>
                     <Resume>
                        <h3>
                           &#60;경력사항&#62;
                           <br />
                           전공: 모바일 앱 개발 석사학위
                           <br />
                           경력: 5년, iOS 및 Android 애플리케이션 개발자
                        </h3>
                     </Resume>
                  </Wrapper>

                  <Btn>
                     <button>등록하기</button>
                     <button>탈퇴조치</button>
                  </Btn>
               </Info>
            </PfsWrap>
         </Container>
         <MonacaInfo />
      </>
   );
}
