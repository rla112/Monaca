import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const SubWrap = styled.div`
   display: flex;
   height: 100%;
   width: 20%;
   font-family: "GmarketSansMedium";
`;

const Section1 = styled.div`
   width: 100%;
   height: 100%;
   display: grid;
   grid-template-rows: repeat(3, 1fr);
`;

const List = styled.div`
   width: 100%;
   height: 90%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   & h4 {
      margin-bottom: 0.5rem;
   }
   & h3 {
      color: #ff5865;
      &:hover {
         cursor: pointer;
      }
   }
   & ul {
      margin-left: 1rem;
      list-style: none;
      display: flex;
      flex-direction: column;
   }
   & li {
      & h5 {
         margin-bottom: 10px;
         color: #666666;
      }
   }
`;

const Click = styled.h4`
   &:hover {
      cursor: pointer;
   }
`;

export function PFRBar() {
   const navigate = useNavigate();
   const navigateToLearningMgmt = () => {
      navigate("/pf-lngmgmt");
   };
   const navigateToProfessorRoom = () => {
      navigate("/pf-room");
   };

   const navigateToStudentMgmt = () => {
      navigate("/stud-mgmt");
   };

   const navigateToPfsProfile = () => {
      navigate("/pf-profile");
   };

   const navigateToRegisteredLecture = () => {
      navigate("/reged-lectures");
   };

   return (
      <>
         <SubWrap>
            <Section1>
               <List>
                  <h3 onClick={navigateToProfessorRoom}>나의방</h3>
                  <br />
                  <ul>
                     <li>
                        <Click onClick={navigateToProfessorRoom}>
                           내 캐릭터
                        </Click>
                        <h5>내 캐릭터 꾸미기</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToPfsProfile}>내 프로필</Click>
                        <h5>등록된 내 프로필 보기</h5>
                     </li>
                  </ul>
               </List>
               <List>
                  <h3 onClick={navigateToLearningMgmt}>학습관리</h3> <br />
                  <ul>
                     <li>
                        <Click onClick={navigateToRegisteredLecture}>
                           {" "}
                           강의실
                        </Click>
                        <h5>
                           내가 등록한 강의 <br />
                           모아보기
                        </h5>
                     </li>
                     <li>
                        <Click onClick={navigateToLearningMgmt}>
                           강의 / 요약본 등록
                        </Click>
                        <h5>강의와 요약본 등록하기</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToLearningMgmt}>
                           질문 모아보기
                        </Click>
                        <h5>
                           나의 강의에 달린
                           <br />
                           질의응답 모아보기
                        </h5>
                     </li>
                  </ul>
               </List>
               <List>
                  <h3 onClick={navigateToStudentMgmt}>학생관리</h3> <br />
                  <ul>
                     <li>
                        <Click onClick={navigateToStudentMgmt}>시험 목록</Click>
                        <h5>등록했던 시험 목록 확인하기</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToStudentMgmt}>
                           학생 시험지 목록
                        </Click>
                        <h5>학생이 제출한 시험지 모어보기</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToStudentMgmt}>
                           학생 출결 관리
                        </Click>
                     </li>
                     <li>
                        <Click onClick={navigateToStudentMgmt}>
                           학생 진도율 관리
                        </Click>
                     </li>
                     <li>
                        <Click onClick={navigateToStudentMgmt}>
                           강의 매출 관리
                        </Click>
                     </li>
                  </ul>
               </List>
            </Section1>
         </SubWrap>
      </>
   );
}
