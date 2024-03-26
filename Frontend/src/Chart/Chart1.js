import { Chart as chartJS } from "chart.js/auto";
import { Bar } from "react-chartjs-2";
import styled from "styled-components";

const Container = styled.div`
   width: 50vw;
   height: 50vh;
   margin: auto;
`;

export function Chart1() {
   const data = [
      { year: 2010, count: 10 },
      { year: 2011, count: 20 },
      { year: 2012, count: 15 },
      { year: 2013, count: 25 },
      { year: 2014, count: 22 },
      { year: 2015, count: 30 },
      { year: 2016, count: 28 },
   ];

   return (
      <>
         <h3>기본 사용법 : Bar 차트</h3>
         <Container>
            <Bar
               data={{
                  labels: [
                     "Red",
                     "Blue",
                     "Yellow",
                     "Green",
                     "Purple",
                     "Orange",
                  ],
                  datasets: [
                     {
                        label: "# of Votes",
                        data: [12, 19, 3, 5, 2, 3],
                        borderWidth: 1,
                     },
                  ],
               }}
               options={{
                  scales: {
                     y: {
                        beginAtZero: true, //0을 맨 밑으로 내리고자 할 때 쓰임
                     },
                  },
               }}
            />
         </Container>
         <h3>배열 map 사용법</h3>
         <Container>
            <Bar
               data={{
                  labels: data.map((d) => d.year),
                  datasets: [
                     {
                        label: "# of Votes",
                        data: data.map((d) => d.count),
                        borderWidth: 1,
                     },
                  ],
               }}
               options={{
                  scales: {
                     y: {
                        beginAtZero: true, //0을 맨 밑으로 내리고자 할 때 쓰임
                     },
                  },
               }}
            />
         </Container>
         <h3>옵션 커스터마이즈 사용법</h3>
         <Container>
            <Bar
               data={{
                  labels: data.map((d) => d.year),
                  datasets: [
                     {
                        label: "# of Votes",
                        data: data.map((d) => d.count),
                        borderWidth: 1,
                     },
                  ],
               }}
               options={{
                  animation: false, //디폴트가 ture
                  plugins: {
                     legend: {
                        //범례
                        display: true,
                        position: "bottom",
                     },
                     tooltip: {
                        // = title
                        enabled: false,
                     },
                  },
                  scales: {
                     y: {
                        beginAtZero: true, //0을 맨 밑으로 내리고자 할 때 쓰임
                     },
                  },
               }}
            />
         </Container>
      </>
   );
}
