import styled from "styled-components";
import { NavBar } from "../NavBar";
import { MonacaInfo } from "../MonacaInfo";
import Kiosk1 from "../image/Kiosk/Kiosk1.jpg";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

const Container = styled.div`
   width: 100vw;
   height: 100vh;
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   font-family: "GmarketSansMedium";
   /* background-color: aqua; */
`;

const Wrap = styled.div`
   width: 60%;
   height: 80%;
   border: 20px solid #fcc757;
   border-radius: 20px;
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
   gap: 4%;
   /* background-color: beige; */
`;

const Buttons = styled.div`
   width: 58%;
   height: 5%;
   display: flex;
   gap: 1%;
   & button {
      width: 20%;
      height: 100%;
      border-top-right-radius: 15px;
      border-top-left-radius: 15px;
      font-family: "GmarketSansMedium";
      font-size: large;
      font-weight: bolder;
      &:hover {
         cursor: pointer;
      }
      &:active {
         background-color: #6666;
      }
      &:nth-child(2) {
         background-color: white;
         border: 1px solid black;
      }
      &:nth-child(1) {
         background-color: #6666;
         border: 1px solid black;
      }
   }
`;

const H1 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 5px double black;
   /* background-color: blue; */
`;

const H2 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 1px solid black;
   border-top: 1px solid black;
   & h3 {
      width: 40%;
      height: 100%;
      border-right: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
   }
   & select {
      width: 40%;
      height: 100%;
      font-family: "GmarketSansMedium";
   }
`;

const Select = styled.div`
   display: flex;
   width: 80%;
   height: 60%;
   gap: 20px;
   align-items: center;
   justify-content: center;
`;

const H3 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 1px solid black;
   border-top: 1px solid black;
   & h3 {
      width: 40%;
      height: 100%;
      border-right: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
   }
`;

const Input = styled.div`
   display: flex;
   width: 80%;
   height: 60%;
   gap: 20px;
   align-items: center;
   justify-content: space-between;
   & input {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 40%;
      height: 100%;
      font-family: "GmarketSansMedium";
      padding: 5px;
      margin-left: 1rem;
   }
`;

const H4 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 1px solid black;
   border-top: 1px solid black;
   & h3 {
      width: 40%;
      height: 100%;
      border-right: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
   }
`;

const H5 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 1px solid black;
   border-top: 1px solid black;
   & h3 {
      width: 40%;
      height: 100%;
      border-right: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
   }
`;

const H6 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 1px solid black;
   border-top: 1px solid black;
   & h3 {
      width: 40%;
      height: 100%;
      border-right: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
   }
`;

const H7 = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   border-bottom: 1px solid black;
   border-top: 1px solid black;
   & h3 {
      width: 40%;
      height: 100%;
      border-right: 1px solid black;
      display: flex;
      align-items: center;
      justify-content: center;
   }
`;

const Watch = styled.div`
   width: 90%;
   height: 90%;
   display: grid;
   grid-template-rows: 60px 60px 60px 90px 90px 60px 60px 60px;
   grid-row-gap: 10px;
   align-items: center;
`;

const LectureWrapper = styled.div`
   width: 100%;
   height: 100%;
   overflow: auto;
   /* background-color: brown; */
`;

const Lectures = styled.div`
   width: 100%;
   height: 35%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
   border-bottom: 1px solid #666666;
   /* background-color: burlywood; */
`;

const Lecture = styled.div`
   width: 80%;
   height: 80%;
   display: grid;
   grid-template-columns: 25% 50% 25%;
   /* background-color: aquamarine; */
`;

const Image = styled.div`
   width: 100%;
   height: 100%;
   background-image: url(${Kiosk1});
   background-size: cover;
   background-repeat: no-repeat;
   &:hover {
      cursor: pointer;
   }
`;

const Info = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
   font-size: 1.3rem;
   background-color: red;
`;

const Button = styled.div`
   width: 100%;
   height: 100%;
   display: flex;
   align-items: center;
   justify-content: center;
   & button {
      width: 50%;
      height: 20%;
      background-color: white;
      border: 1px solid black;
      font-size: 1rem;
      font-family: "GmarketSansMedium";
      &:hover {
         cursor: pointer;
      }
      &:active {
         background-color: #6666;
      }
   }
   /* background-color: orange; */
`;

const Button2 = styled.div`
   width: 100%;
   height: 100%;
   /* background-color: purple; */
   display: flex;
   align-items: center;
   justify-content: end;
   & button {
      width: 10%;
      height: 80%;
      background-color: white;
      border: 1px solid black;
      font-family: "GmarketSansMedium";
      font-weight: bolder;
      font-size: large;
      &:hover {
         cursor: pointer;
      }
      &:active {
         background-color: #6666;
      }
   }
`;

export function UploadLecture() {
   const navigate = useNavigate();

   const navigateToRegisteredLecture = () => {
      navigate("/reged-lectures");
   };

   const navigateToUploadLecture = () => {
      navigate("/upload-lecture");
   };

   const navigateToRegistrationConfirm = () => {
      navigate("/confirm");
   };

   const [selectedOption, setSelectedOption] = useState("");
   const handleSelectChange = (event) => {
      setSelectedOption(event.target.value);
   };

   return (
      <>
         <NavBar />
         <Container>
            <Buttons>
               <button onClick={navigateToRegisteredLecture}>
                  등록된 강의
               </button>
               <button onClick={navigateToUploadLecture}>강의 등록하기</button>
            </Buttons>
            <Wrap>
               <Watch>
                  <H1>
                     <h3>등록</h3>
                  </H1>
                  <H2>
                     <h3>강의 카테고리</h3>
                     <Select>
                        <select
                           id="subjectCategory"
                           value={selectedOption}
                           onChange={handleSelectChange}
                        >
                           <option value="">카테고리를 선택해주세요.</option>
                           <option value="키오스크">키오스크</option>
                           <option value="웹사이트">웹사이트</option>
                           <option value="모바일">모바일</option>
                           <option value="국가복지 및 민원">
                              국가복지 및 민원
                           </option>
                        </select>
                        {selectedOption && <p>{selectedOption} 선택완료</p>}
                     </Select>
                  </H2>
                  <H3>
                     <h3>강의 명</h3>
                     <Input>
                        <input
                           type="text"
                           placeholder="강의 제목을 입력해 주세요."
                        />
                        <p>&#8251;특수문자는 포함할 수 없습니다.&#8251;</p>
                     </Input>
                  </H3>
                  <H4>
                     <h3>대표사진 주소</h3>
                     <Input>
                        <input type="text" />
                        <p>&#8251;사진은 꼭 url형식으로 넣어주세요&#8251;</p>
                     </Input>
                  </H4>
                  <H5>
                     <h3>콘텐츠 주소</h3>
                     <Input>
                        <input type="text" />
                        <p>&#8251;콘텐츠는 꼭 url형식으로 넣어주세요&#8251;</p>
                     </Input>
                  </H5>
                  <H6>
                     <h3>학습시간</h3>
                     <Input>
                        <input type="text" />
                        <p>&#8251;초 단위로 숫자만 입력해주세요.&#8251;</p>
                     </Input>
                  </H6>
                  <H7>
                     <h3>가격</h3>
                     <Input>
                        <input type="text" />
                        <p>&#8251;단위를 뺀 숫자만 입력해주세요.&#8251;</p>
                     </Input>
                  </H7>
                  <Button2>
                     <button onClick={navigateToRegistrationConfirm}>
                        등록하기
                     </button>
                  </Button2>
               </Watch>
            </Wrap>
         </Container>
         <MonacaInfo />
      </>
   );
}
