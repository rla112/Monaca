import styled from "styled-components";
import MainBoard from "./image/MainBoard.jpg";
import candy from "./image/CandyLogo.png";

const Container = styled.div`
   width: 80vw;
   height: 70vh;
   background-image: url(${MainBoard});
   background-repeat: no-repeat;
   background-position: center;
   background-size: contain;
   display: flex;
   align-items: center;
   justify-content: center;
`;

const Logo = styled.div`
   background-image: url(${candy});
   background-position: center;
   background-repeat: no-repeat;
   background-size: contain;
   width: 400px;
   height: 400px;
`;

export function Backboard() {
   return (
      <>
         <Container>
            <Logo></Logo>
         </Container>
      </>
   );
}
