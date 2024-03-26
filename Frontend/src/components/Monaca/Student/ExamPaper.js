import styled from "styled-components";

const Container = styled.div`
   width: 100vw;
   height: 100vh;
   background-color: bisque;
   font-size: 10rem;
`;

export function ExamPaper() {
   return (
      <>
         <Container>시험지</Container>
      </>
   );
}
