/* eslint-disable array-callback-return */
import styled from "styled-components";
import { MonacaInfo } from "../MonacaInfo";
import { NavBar } from "../NavBar";
import { useEffect, useState } from "react";

import Tc from "../image/TrashCan.jpg";
import { useNavigate } from "react-router-dom";
import {
  deleteLectureCartById,
  getAllLectureCart,
  getLectureById,
} from "../api";

const Wrapper = styled.div`
  width: 100vw;
  height: 100vh;
  font-family: "GmarketSansMedium";
`;

const Header = styled.div`
  width: 100%;
  height: 12vh;
  background-color: #0b4434;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "TmonMonsori";
  & h1 {
    color: white;
    font-size: 2rem;
    letter-spacing: 5px;
  }
`;

const Section1 = styled.div`
  width: 100%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Folder = styled.div`
  margin-top: 5rem;
  border-top: 2px solid black;
  border-bottom: 2px solid black;
  width: 60%;
  height: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow: auto;
`;

const FInner = styled.div`
  width: 100%;
  height: 96%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Main = styled.div`
  width: 90%;
  height: 90%;
`;

const Header2 = styled.div`
  width: 100%;
  height: 15%;
  border-top: 1px solid silver;
  border-bottom: 1px solid silver;
  display: flex;
  align-items: center;
  justify-content: center;
  & :nth-child(1) {
    height: 100%;
    width: 8%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(2) {
    height: 100%;
    width: 42%;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
  }
  & :nth-child(3) {
    height: 100%;
    width: 21%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(4) {
    height: 100%;
    width: 19%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(5) {
    height: 100%;
    width: 10%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
`;

const Lecture = styled.div`
  margin-top: 2.5rem;
  width: 100%;
  height: 40%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-top: 1px dashed silver;
  border-bottom: 1px dashed silver;
  &:hover {
    cursor: pointer;
  }

  & :nth-child(2) {
    height: 100%;
    width: 40%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(3) {
    height: 100%;
    width: 25%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  & :nth-child(4) {
    height: 100%;
    width: 25%;
    display: flex;

    align-items: center;
    justify-content: center;
  }
`;

const TrashWrap = styled.div`
  width: 10%;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const TrashPic = styled.div`
  background-image: url(${Tc});
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  width: 45%;
  height: 35%;
`;

const LecturePic1 = styled.div`
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  width: 10%;
  height: 20%;
`;

const Total = styled.div`
  width: 60%;
  height: 5%;
  /* background-color: bisque; */
  display: flex;
  align-items: center;
  justify-content: end;
  font-size: 1.2rem;
`;

const Button = styled.div`
  width: 10%;
  height: 25%;
  display: flex;
  flex-direction: column;
  align-items: end;
  justify-content: center;
  & button {
    width: 100%;
    height: 90%;
    background-color: white;
    border: 1px solid black;
    font-size: large;
    font-family: "GmarketSansMedium";
    &:active {
      background-color: #6666;
    }
    &:hover {
      cursor: pointer;
    }
  }
`;

export function Cart() {
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  console.log("Login State:", loginState);
  const [lectureCartData, setLectureCartData] = useState([]); // 초기에 null로 설정
  console.log("lectureCartData :", lectureCartData);
  const [userCartData, setUserCartData] = useState([]);
  // const cartId = userCartData.map((userCart) => {
  //    userCart.lecture
  // })
  const navigate = useNavigate();

  useEffect(() => {
    getAllLectureCart()
      .then((response) => {
        console.log(response);
        setUserCartData(response.data);
        console.log(response.data);
        if (response.data) {
          Promise.all(
            response.data.map((lectureCart) =>
              getLectureById(lectureCart.lectureId)
            )
          )
            .then((results) => {
              const lecturesData = results.map((result) => result.data);
              setLectureCartData(lecturesData);
            })
            .catch((error) => {
              console.error("강의 정보 불러오기 오류:", error);
            });
        }
      })
      .catch((error) => {
        console.error("장바구니 정보 불러오기 오류:", error);
      });
  }, []);

  async function purchaseLectureCart(id) {
    try {
      const response = await fetch(
        `http://localhost:8080/api/lectureCart/purchase/${id}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );
      const responseData = await response.json();
      if (response.ok) {
      } else {
        console.error(`Error : ${responseData.message}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }

  async function deleteLectureCartById(id) {
    console.log(id);
    try {
      const response = await fetch(
        `http://localhost:8080/api/lectureCart/delete/${id}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${loginState.token}`,
          },
        }
      );
      // 상태 업데이트
      if (response.ok) {
        const lectureId = userCartData.find(
          (item) => item.id === id
        )?.lectureId; // id = 지운 cartId
        setUserCartData((prevUserCartData) =>
          prevUserCartData.filter((item) => item.id !== id)
        ); // 남겨진 애들을 상태업데이트
        if (lectureId) {
          setLectureCartData((prevLectureCartData) =>
            prevLectureCartData.filter((lecture) => lecture.id !== lectureId)
          );
        }
      } else {
        console.error(`Error : ${response.statusText}`);
      }
    } catch (error) {
      console.error("오류 발생", error);
    }
  }

  const handlePurchase = async (id) => {
    try {
      await purchaseLectureCart(id);
      navigate(`/stud-class-room`);
    } catch (error) {
      console.error("구매 처리 중 오류가 발생했습니다.", error);
    }
  };

  const handleDelete = async (id) => {
    await deleteLectureCartById(id);
  };

  return (
    <>
      <Wrapper>
        <Header>
          <h1>
            {loginState
              ? `${loginState.loginId}님의 장바구니`
              : "로그인 후 이용해주세요."}
          </h1>
        </Header>
        <Section1>
          <Folder>
            <FInner>
              <Main>
                <Header2>
                  <h4>버리기</h4>
                  <h4>강의사진</h4>
                  <h4>제목</h4>
                  <h4>가격</h4>
                  <h4>구매버튼</h4>
                </Header2>
                {lectureCartData?.map((lectureData) => {
                  const cartItem = userCartData.find(
                    (item) => item.lectureId === lectureData.id
                  );
                  if (cartItem) {
                    return (
                      <Lecture key={lectureData.id}>
                        <TrashWrap>
                          <TrashPic onClick={() => handleDelete(cartItem.id)} />
                        </TrashWrap>
                        <LecturePic1
                          style={{
                            backgroundImage: `url(${lectureData.image})`,
                            width: "300px",
                            height: "200px",
                            backgroundRepeat: "no-repeat",
                            backgroundPosition: "center",
                            backgroundSize: "contain",
                            marginLeft: "5rem",
                            marginRight: "5rem",
                          }}
                        />
                        <h4>{lectureData.lectureName}</h4>
                        <h4>{lectureData.price}원</h4>
                        <Button>
                          <button onClick={() => handlePurchase(cartItem.id)}>
                            구매하기
                          </button>
                        </Button>
                      </Lecture>
                    );
                  }
                })}
              </Main>
            </FInner>
          </Folder>
          <Total>
            <p>
              총 금액 :
              {lectureCartData.reduce(
                (total, lectureData) => total + lectureData.price,
                0
              )}
              원 입니다.
            </p>
          </Total>
        </Section1>
      </Wrapper>
      <MonacaInfo />
    </>
  );
}
