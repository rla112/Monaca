import styled from "styled-components";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getLectureById, updateClassRoomAndAddLectureToClassRoom } from "../api";


const Container = styled.div`
   width: 100vw;
   height: 100vh;
`;

const Box = styled.div`
   width: 100vw;
   height: 100vh;
   display: flex;
   align-items: center;
   justify-content: center;
`;

const Thumb = styled.div`
   width: 100%;
   height: 100%;
   background-repeat: no-repeat;
   background-position: center;
   background-size: cover;
   display: flex;
   align-items: center;
   justify-content: center;
`;

const Play = styled.div`
   width: 10%;
   height: 20%;
`;

const Button = styled.div`
   width: 100%;
   height: 100%;
   border-radius: 50%;
   background-color: #6666;
   display: flex;
   align-items: center;
   justify-content: center;
   &:hover {
      cursor: pointer;
   }
`;

const Triangle = styled.div`
   border-bottom: 30px solid transparent;
   border-top: 30px solid transparent;
   border-left: 60px solid white;
   border-right: 1px solid transparent;
`;

export function WatchTheLecture() {
   const { lectureId } = useParams();

   const [lectures, setLectures] = useState({});

   const [complete, setComplete] = useState({});

   const navigate = useNavigate();

   const navigateToClassRoom = () => {
      navigate("/stud-class-room");
   };

   useEffect(() => {
      getLectureById(lectureId)
      .then((response) => {
         setLectures(response.data);
         console.log(response.data);
      })
      .catch((error) => {
         console.error("강의 비디오 불러오기 오류 : ", error);
      });
   }, [lectureId]);

   useEffect(() => {
      updateClassRoomAndAddLectureToClassRoom(lectureId)
      .then((response) => {
         setComplete(response.data);
         console.log(response.data);
      })
      .catch((error) => {
         console.error("수료실로 옮기기를 실패해였습니다. : ", error);
      });
   }, [lectureId]);

   return (
      <>
      <Container>
            <Box>
            <Thumb style={{
               backgroundImage : `url(${lectures.video})`
            }}>
               <Play>
                  <Button onClick={navigateToClassRoom}>
                     <Triangle />
                  </Button>
               </Play>
            </Thumb>
         </Box>
      </Container>
      </>
   );
}
