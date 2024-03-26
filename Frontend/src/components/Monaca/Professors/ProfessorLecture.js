import styled from "styled-components";

const Box = styled.div`
   width: 500px;
   height: 500px;
   background-color: aqua;
`;
// 교수가 올린 강의만
export function ProfessorLecture() {
   return <Box>This is ProfessorLecture Test</Box>;
}
