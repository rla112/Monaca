import styled from "styled-components";
import { NavBar } from "../NavBar";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { MonacaInfo } from "../MonacaInfo";
import { AdminBar } from "./AdminBar";
import { Bar, Doughnut, Line } from "react-chartjs-2";
import { Chart, plugins, registerables } from "chart.js";
Chart.register(...registerables);

const Container = styled.div`
  width: 100vw;
  height: 200vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: "GmarketSansMedium";
`;

const Wrapper = styled.div`
  width: 70%;
  height: 100%;
  /* background-color: aliceblue; */
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ChartSection = styled.div`
  width: 80%;
  height: 90%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  /* background-color: beige; */
`;

const ChartInner1 = styled.div`
  width: 90%;
  height: 25%;
  /* background-color: blue; */
  margin-bottom: 1rem;
`;

const Chart1 = styled.div`
  width: 100%;
  height: 100%;
  /* background-color: burlywood; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ChartInner2 = styled.div`
  width: 90%;
  height: 35%;
  display: flex;
  /* background-color: cadetblue; */
`;

const DoughnutWrap = styled.div`
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  /* background-color: chartreuse; */
`;

const Doughnut1 = styled.div`
  width: 80%;
  height: 42%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
  /* background-color: chocolate; */
`;

const Doughnut2 = styled.div`
  width: 80%;
  height: 56%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  /* margin-bottom: 10px; */
  /* background-color: coral; */
`;

const LineWrap = styled.div`
  width: 60%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  /* background-color: crimson; */
`;

const ChartInner3 = styled.div`
  width: 90%;
  height: 35%;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: blue; */
`;

const LineWrap2 = styled.div`
  width: 90%;
  height: 70%;
  /* background-color: azure; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export function AdminChart() {
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [lectureData, setLectureData] = useState([]);
  const [freeLectures, setFreeLectures] = useState([]);
  const [paidLectures, setPaidLectures] = useState([]);
  const [paymentStatus, setPaymentStatus] = useState([]);
  const [classPopulation, setClassPopulation] = useState([]);
  const [freeLecPopulation, setFreeLecPopulation] = useState([]);
  const [signUpTrend, setSignUpTrend] = useState([]);

  // 총 유저 몇 명인지 통계
  async function findUsersWithRoleUserOnly() {
    if (!loginState) {
      return Promise.resolve();
    }
    try {
      const response = await fetch(
        `http://localhost:8080/api/users/non-admin-or-professor`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );

      const responseData = await response.json();

      if (response.ok) {
        // 2024년도 가입자만 필터링
        const data2024 = responseData.data.filter((user) => {
          const year = new Date(user.createAt).getFullYear();
          return year === 2024;
        });

        // 월별 가입자 수 집계
        const signUpByMonth = data2024.reduce((acc, user) => {
          const month = new Date(user.createAt).getMonth() + 1; // 월은 0부터 시작하므로 +1
          acc[month] = (acc[month] || 0) + 1;
          return acc;
        }, {});

        // setState를 사용해 차트 데이터 업데이트
        setSignUpTrend(signUpByMonth);
        console.log("24년 월별 가입자 : ", signUpByMonth);
      } else {
        console.error(`Error: ${responseData.message}`);
      }
    } catch (error) {
      console.error("An error occurred", error);
    }
  }

  // 유료 강의 전체에서 카테고리별 + 강의명별 결제 현황
  async function getLectureSummaryByPaid() {
    if (!loginState) {
      return Promise.resolve();
    }
    try {
      const response = await fetch(
        `http://localhost:8080/api/lectureSummary/paid`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );

      const responseData = await response.json();

      if (response.ok) {
        setPaymentStatus(responseData.data);
        console.log("유료강의 정보 : ", responseData);
      } else {
        console.error(`오류: ${responseData.message}`);
      }
    } catch (error) {
      console.error("결제현황 불러오기 오류입니다. : ", error);
    }
  }

  // 유료, 무료 강의 갯수 현황
  async function getLecture() {
    try {
      const response = await fetch(`http://localhost:8080/api/lecture/all`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${loginState.token}`,
        },
      });
      const responseData = await response.json();
      console.log(responseData);

      if (response.ok) {
        const allLectures = responseData.data;
        const freeLectures = allLectures.filter(
          (lecture) => lecture.price === 0
        );
        const paidLectures = allLectures.filter((lecture) => lecture.price > 0);

        setLectureData(allLectures);
        setFreeLectures(freeLectures);
        setPaidLectures(paidLectures);
      } else {
        console.error(`Error: ${responseData.message}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }

  // 무료 강의 전체에서 카테고리별 + 강의명별 각 강의를 듣는 사용자의 수 전체 조회
  async function getLectureSummaryByFree() {
    if (!loginState) {
      return Promise.resolve();
    }
    try {
      const response = await fetch(
        `http://localhost:8080/api/lectureSummary/free`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );

      const responseData = await response.json();

      if (response.ok) {
        setFreeLecPopulation(responseData.data);
        console.log("무료강의 수강 중인 인구 정보 : ", responseData);
      } else {
        console.error(`오류 : ${responseData.message}`);
      }
    } catch (error) {
      console.error("무료강의 만 수강하는 인구 불러오기 오류입니다. : ", error);
    }
  }

  // 유 / 무료 강의 전체에서 각 강의를 듣는 사용자 수 전체 조회
  async function getLectureSummary() {
    if (!loginState) {
      return Promise.resolve();
    }
    try {
      const response = await fetch(`http://localhost:8080/api/lectureSummary`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${loginState.token}`,
        },
      });

      const responseData = await response.json();

      if (response.ok) {
        setClassPopulation(responseData.data);
        console.log("전체 강의 수강인구 정보 : ", responseData);
      } else {
        console.error(`오류: ${responseData.message}`);
      }
    } catch (error) {
      console.error("강의 전체 수강인구 불러오기 오류입니다. : ", error);
    }
  }

  // 총 유저 수 BarChart
  const signUpChartData = {
    labels: Object.keys(signUpTrend).map((month) => `${month}월`),
    datasets: [
      {
        label: "월별 가입자 수",
        data: Object.values(signUpTrend),
        backgroundColor: "rgba(53, 162, 235, 0.5)",
        borderColor: "rgb(53, 162, 235)",
        borderWidth: 1,
      },
    ],
  };

  const signUpChartOptions = {
    plugins: {
      legend: {
        display: false,
      },
    },
    scales: {
      x: {
        ticks: {
          maxRotation: 90,
          minRotation: 45,
          font: {
            size: 12,
            family: "GmarketSansMedium",
          },
        },
      },
      y: {
        beginAtZero: true,
        min: 0,
        max: 20, // 최대값을 20으로 설정
        ticks: {
          stepSize: 2, // 눈금 간격을 2로 설정
          font: {
            size: 12,
            family: "GmarketSansMedium",
          },
        },
      },
    },
  };

  //유료강의 판매 LineChart
  const totalPayment = {
    labels: paymentStatus.map((total) => total.lectureName),
    datasets: [
      {
        data: paymentStatus.map((total) => total.totalPayment),
        borderColor: "rgb(255, 82, 119)",
        borderWidth: 1,
      },
    ],
  };

  const paymentOptions = {
    plugins: {
      legend: {
        display: false,
      },
    },
    scales: {
      x: {
        ticks: {
          maxRotation: 90,
          minRotation: 45,
          font: {
            // 여기에 글씨체 설정을 추가합니다.
            size: 12, // 예시: 글씨 크기
            family: "GmarketSansMedium", // 예시: 글씨체
          },
        },
      },
      y: {
        beginAtZero: true,
        min: 0,
        max: 400000,
        ticks: {
          font: {
            // y축에도 동일하게 글씨체 설정을 추가합니다.
            size: 12, // 예시: 글씨 크기
            family: "GmarketSansMedium", // 예시: 글씨체
          },
        },
      },
    },
  };

  // 유 / 무료 강의 전체에서 각 강의를 듣는 전체 사용자 수 Line
  const totalClassPopulation = {
    labels: classPopulation.map((lecture) => lecture.lectureName),
    datasets: [
      {
        data: classPopulation.map((lecture) => lecture.participantCount),
        borderColor: "rgb(242, 153, 185)",
        borderWidth: 1,
      },
    ],
  };

  const populationOptions = {
    plugins: {
      legend: {
        display: false,
      },
    },
    scales: {
      x: {
        ticks: {
          maxRotation: 90, // 필요에 따라 조정
          minRotation: 45, // 필요에 따라 조정
          autoSkip: false, // 모든 라벨이 보이도록 설정
          maxTicksLimit: 20, // 최대 틱 수 제한 조정
          font: {
            size: 12,
            family: "GmarketSansMedium",
          },
        },
      },
      y: {
        beginAtZero: true,
        min: 0,
        max: 20,
        ticks: {
          font: {
            size: 12,
            family: "GmarketSansMedium",
          },
        },
      },
    },
  };

  // 무료 강의를 듣고 있는 인구수 Doughnut
  const totalFreeLecPopulation = {
    labels: freeLecPopulation.map((free) => free.lectureName),
    datasets: [
      {
        data: freeLecPopulation.map((free) => free.participantCount),
        backgroundColor: [
          "rgba(255, 99, 132, 0.2)", // 예시 색상 1 (연한 빨강)
          "rgba(54, 162, 235, 0.2)", // 예시 색상 2 (연한 파랑)
          "rgba(255, 206, 86, 0.2)", // 예시 색상 3 (연한 노랑)
          "rgba(75, 192, 192, 0.2)", // 예시 색상 4 (연한 초록)
          "rgba(153, 102, 255, 0.2)", // 예시 색상 5 (연한 보라)
        ],
        borderColor: [
          "rgba(255, 99, 132, 1)", // 테두리 색상 1 (빨강)
          "rgba(54, 162, 235, 1)", // 테두리 색상 2 (파랑)
          "rgba(255, 206, 86, 1)", // 테두리 색상 3 (노랑)
          "rgba(75, 192, 192, 1)", // 테두리 색상 4 (초록)
          "rgba(153, 102, 255, 1)", // 테두리 색상 5 (보라)
        ],
        borderWidth: 1,
      },
    ],
  };

  const totalFreeOptions = {
    plugins: {
      legend: {
        position: "right",
        labels: {
          font: {
            family: "'GmarketSansMedium'", // 여기에 원하는 글꼴을 지정합니다.
            size: "9",
          },
        },
      },
    },
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        await Promise.all([
          findUsersWithRoleUserOnly(),
          getLecture(),
          getLectureSummaryByPaid(),
          getLectureSummary(),
          getLectureSummaryByFree(),
        ]);
      } catch (error) {
        console.error("An error occurred", error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <Container>
        <Wrapper>
          <AdminBar />
          <ChartSection>
            <ChartInner1>
              <Chart1>
                <h3>유료 강의 판매현황</h3>
                <Line data={totalPayment} options={paymentOptions} />
              </Chart1>
            </ChartInner1>
            <ChartInner2>
              <DoughnutWrap>
                <Doughnut1>
                  <h5>강의 업로드 현황</h5>
                  <Doughnut
                    data={{
                      labels: ["무료강의", "유료강의"],
                      datasets: [
                        {
                          data: [freeLectures.length, paidLectures.length],
                          backgroundColor: [
                            "rgba(75,192,192,0.2)",
                            "rgba(255,99,132,0.2)",
                          ],
                          borderColor: [
                            "rgba(75,192,192,1)",
                            "rgba(255,99,132,1)",
                          ],
                          borderWidth: 1,
                        },
                      ],
                    }}
                    options={{
                      plugins: {
                        legend: {
                          labels: {
                            font: {
                              size: 12,
                              family: "GmarketSansMedium",
                            },
                          },
                          position: "right",
                        },
                      },
                    }}
                  />
                </Doughnut1>
                <Doughnut2>
                  <h5>무료 강의 수강인구수 현황</h5>
                  <Doughnut
                    data={totalFreeLecPopulation}
                    options={totalFreeOptions}
                  />
                </Doughnut2>
              </DoughnutWrap>
              <LineWrap>
                <h4>전체 회원 중 수강 중인 회원 강의별 통계</h4>
                <Line data={totalClassPopulation} options={populationOptions} />
              </LineWrap>
            </ChartInner2>
            <ChartInner3>
              <LineWrap2>
                <h4>24년도 월별 화원증가 추이</h4>
                <Line data={signUpChartData} options={signUpChartOptions} />
              </LineWrap2>
            </ChartInner3>
          </ChartSection>
        </Wrapper>
        <MonacaInfo />
      </Container>
    </>
  );
}
