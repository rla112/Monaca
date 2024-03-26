import { createGlobalStyle } from "styled-components";
import { QueryClient, QueryClientProvider } from "react-query";
import { MonacaLMS } from "./components/Monaca/MonacaLMS";
// import { Chart1 } from "./components/Chart/Chart1";
// import { Chart2 } from "./components/Chart/Chart2";
// import { DeliveryShop } from "./components/Delivery/DeliveryShop";

const GlobalStyle = createGlobalStyle`
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'TmonMonsori';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/TmonMonsori.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
   *{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    user-select: none;
   }
`;

// const client = new QueryClient();

// export default function App() {
//    return (
//       <>
//          <QueryClientProvider client={client}>
//             <GlobalStyle />
//             <DeliveryShop />
//          </QueryClientProvider>
//          {/* <GlobalStyle /> */}
//       </>
//    );
// }

const client = new QueryClient();
export default function App() {
  return (
    <>
      <QueryClientProvider client={client}>
        <GlobalStyle />
        <MonacaLMS />
        {/* // <NavBar />
            // <Chart1 />
            // <Chart2 /> */}
      </QueryClientProvider>
    </>
  );
}
