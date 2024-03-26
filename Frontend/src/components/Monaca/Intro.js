import styled from "styled-components";
import intro from "./image/intro.jpg";
import { useEffect } from "react";

const Container = styled.div`
   width: 100vw;
   height: 100vh;
   background-image: url(${intro});
   background-repeat: no-repeat;
   background-size: cover;
`;
const Intro = ({ setShowIntro }) => {
   useEffect(() => {
      const timeoutId = setTimeout(() => {
         setShowIntro(false);
      }, 1000);

      return () => clearTimeout(timeoutId);
   }, [setShowIntro]);

   return <Container />;
};

export default Intro;
