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

export function UserBar() {
   const navigate = useNavigate();

   const navigateToUserRoom = () => {
      navigate("/user-room");
   };

   const navigateToUserLrngMgmt = () => {
      navigate("/user-lngmgmt");
   };

   const navigateToUserCourseMgmt = () => {
      navigate("/user-coursemgmt");
   };

   const navigateToClassRoom = () => {
      navigate("/stud-class-room");
   }

   const navigateToCart = () => {
      navigate("/my-cart");
   };

   const navigateToLectureList = () => {
      navigate("/lecture-list");
   };

   const navigateToItemShop = () => {
      navigate("/item-shop");
   };

   return (
      <>
         <SubWrap>
            <Section1>
               <List>
                  <h3 onClick={navigateToUserRoom}>나의방</h3>
                  <br />
                  <ul>
                     <li>
                        <Click onClick={navigateToUserRoom}>내 캐릭터</Click>
                        <h5>내 캐릭터 꾸미기</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToItemShop}>상점</Click>
                        <h5>캐릭터 치장 아이템 구매</h5>
                     </li>
                  </ul>
               </List>
               <List>
                  <h3 onClick={navigateToUserLrngMgmt}>학습관리</h3> <br />
                  <ul>
                     <li>
                        <Click onClick={navigateToClassRoom}> 강의실</Click>
                        <h5>
                           수강신청한 강의 듣기, <br />내 진도율 확인하기
                        </h5>
                     </li>
                     <li>
                        <Click onClick={navigateToUserLrngMgmt}>수강이력</Click>
                        <h5>수료한 강의, 성적확인하기</h5>
                     </li>
                     {/* <li>
                        <Click onClick={navigateToUserLrngMgmt}>출석부</Click>
                        <h5>출석현황 보기</h5>
                     </li> */}
                     <li>
                        <Click onClick={navigateToUserLrngMgmt}>
                           내 질문 보기
                        </Click>
                        <h5>
                           내가 한 질문 보기
                        </h5>
                     </li>
                     {/* <li>
                        <Click onClick={navigateToUserLrngMgmt}>시험정보</Click>
                        <h5>
                           응시가능한 시험 /
                           <br />내 시험성적 확인하기
                        </h5>
                     </li> */}
                  </ul>
               </List>
               <List>
                  <h3 onClick={navigateToUserCourseMgmt}>수강신청 관리</h3>
                  <br />
                  <ul>
                     <li>
                        <Click onClick={navigateToUserCourseMgmt}>
                           강의 찜
                        </Click>
                        <h5>찜한 강의 모아보기</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToCart}>수강 장바구니</Click>
                        <h5>위 글씨를 클릭하면 이동해요!</h5>
                     </li>
                     <li>
                        <Click onClick={navigateToLectureList}>강의목록</Click>
                        <h5>위 글씨를 클릭하면 이동해요!</h5>
                     </li>
                  </ul>
               </List>
            </Section1>
         </SubWrap>
      </>
   );
}
