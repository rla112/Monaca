import styled from "styled-components";

// 이모지
import Fav from "../image/Fav.jpg";
import FreeLec from "../image/Free.jpg";
import Newbie from "../image/Newbie.jpg";

import { NavLink } from "react-router-dom";
import { useEffect, useState } from "react";
import { getAllLectureByFreeLectures, getLectureById } from "../api";

const Container = styled.div`
  height: 200vh;
  width: 100vw;
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
  align-items: center;
  margin: 0 auto;
  font-family: "GmarketSansMedium";
`;

const Free = styled.div`
  justify-content: center;
  align-items: center;
  width: 90%;
  height: 80%;
  margin: 1rem auto;
`;

const FreeIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 20%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const FreePic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  border: 1px solid silver;
  border-radius: 1rem;
  height: 50%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

const MasterSec = styled.div`
  justify-content: center;
  align-items: center;
  width: 90%;
  height: 80%;
  margin: 1rem auto;
`;

const MtPic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  border: 1px solid silver;
  border-radius: 1rem;
  height: 50%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

const MasterIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 20%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const FavSec = styled.div`
  justify-content: center;
  align-items: center;
  width: 90%;
  height: 80%;
  margin: 0 auto;
`;

const FavIn = styled(NavLink)`
  display: flex;
  flex-direction: column;
  width: 20%;
  height: 90%;
  margin: 1rem;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
`;

const FavPic = styled.div`
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  border: 1px solid silver;
  border-radius: 1rem;
  height: 50%;
  width: 100%;
  display: flex;
  &:hover {
    cursor: pointer;
  }
`;

const LectureInfo = styled.div`
  height: 50%;
  /* background-color: lightgrey; */
  border: 1px solid silver;
  width: 100%;
  border-radius: 1rem;
  margin: 1rem;
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
  line-height: 4rem;
  &:hover {
    cursor: pointer;
  }
`;

const Header = styled.div`
  display: flex;
  flex-direction: column;
  height: 20%;
  width: 78%;
  margin: 0 auto;
  justify-content: center;
  & p {
    font-size: 1rem;
  }
`;

const Title = styled.div`
  height: 50%;
  font-size: 2rem;
  display: flex;
`;

const Info = styled.div`
  height: 50%;
  font-size: 1.5rem;
`;

const FreeWrap = styled.div`
  display: flex;
  height: 90%;
  width: 80%;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
`;
const MasterWrap = styled.div`
  display: flex;
  height: 90%;
  width: 80%;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
`;
const FavWrap = styled.div`
  display: flex;
  height: 90%;
  width: 80%;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
`;

const FreeEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 3%;
  background-image: url(${FreeLec});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  margin-left: 1rem;
`;

const NewbieEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 3%;
  background-image: url(${Newbie});
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  margin-left: 1rem;
`;

const FavEmoji = styled.div`
  display: flex;
  height: 100%;
  width: 3%;
  background-image: url(${Fav});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center;
  margin-left: 1rem;
`;

export function MainLecture() {
  // 무료 강의 불러오기
  const [frees, setFrees] = useState([]);

  useEffect(() => {
    getAllLectureByFreeLectures()
      .then((response) => {
        setFrees(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("무료 강의 불러오기 오류 : ", error);
      });
  }, []);

  // 고수가 되는 강의 불러오기
  const [kiosk1, setKiosk1] = useState([]);

  useEffect(() => {
    getLectureById(1)
      .then((response) => {
        setKiosk1(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("키오스크1 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [kiosk2, setKiosk2] = useState([]);

  useEffect(() => {
    getLectureById(2)
      .then((response) => {
        setKiosk2(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("키오스크2 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [wel19, setWel19] = useState([]);

  useEffect(() => {
    getLectureById(19)
      .then((response) => {
        setWel19(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("국가 복지19 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [web10, setWeb10] = useState([]);

  useEffect(() => {
    getLectureById(10)
      .then((response) => {
        setWeb10(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("웹사이트10 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [mob11, setMob11] = useState([]);

  useEffect(() => {
    getLectureById(11)
      .then((response) => {
        setMob11(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("모바일11 강의 불러오기 오류 : ", error);
      });
  }, []);

  // 인기순 강의 불러오기

  const [kiosk4, setKiosk4] = useState([]);

  useEffect(() => {
    getLectureById(4)
      .then((response) => {
        setKiosk4(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("키오스크4 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [mob14, setMob14] = useState([]);

  useEffect(() => {
    getLectureById(14)
      .then((response) => {
        setMob14(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("모바일14 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [wel16, setWel16] = useState([]);

  useEffect(() => {
    getLectureById(16)
      .then((response) => {
        setWel16(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("국가 복지16 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [web9, setWeb9] = useState([]);

  useEffect(() => {
    getLectureById(9)
      .then((response) => {
        setWeb9(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("웹사이트9 강의 불러오기 오류 : ", error);
      });
  }, []);

  const [kiosk3, setKiosk3] = useState([]);

  useEffect(() => {
    getLectureById(3)
      .then((response) => {
        setKiosk3(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("키오스크3 강의 불러오기 오류 : ", error);
      });
  }, []);

  return (
    <>
      <Container>
        <Free>
          <Header>
            <Title>
              부담 없이 들을 수 있는 무료 강의 <FreeEmoji />
            </Title>
            <Info>유료 강의가 부담이 될 때 무료 강의로 시작해 봐요.</Info>
            <p>아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.</p>
          </Header>
          <FreeWrap>
            {frees.map((lecture) => (
              <FreeIn key={lecture.id} to={`/lecture/${lecture.id}`}>
                <FreePic
                  style={{
                    backgroundImage: `url(${lecture.image})`,
                  }}
                ></FreePic>
                <LectureInfo>
                  <p>&#60;{lecture.lectureName}&#62;</p>
                  <p>{lecture.subTitle}</p>
                </LectureInfo>
              </FreeIn>
            ))}
          </FreeWrap>
        </Free>
        <MasterSec>
          <Header>
            <Title>
              초보도 고수가 되는강의 <NewbieEmoji />
            </Title>
            <Info>시작이 어렵다면 쉬운 강의와 함께 시작해 봐요.</Info>{" "}
            <p>아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.</p>
          </Header>
          <MasterWrap>
            <MasterIn to={`/lecture/${kiosk1.id}`}>
              <MtPic
                style={{
                  backgroundImage: `url(${kiosk1.image})`,
                }}
              ></MtPic>
              <LectureInfo>
                <p>&#60;{kiosk1.lectureName}&#62;</p>
                <p>{kiosk1.subTitle}</p>
              </LectureInfo>
            </MasterIn>
            <MasterIn to={`/lecture/${kiosk2.id}`}>
              <MtPic
                style={{
                  backgroundImage: `url(${kiosk2.image})`,
                }}
              ></MtPic>
              <LectureInfo>
                <p>&#60;{kiosk2.lectureName}&#62;</p>
                <p>{kiosk2.subTitle}</p>
              </LectureInfo>
            </MasterIn>
            <MasterIn to={`/lecture/${wel19.id}`}>
              <MtPic
                style={{
                  backgroundImage: `url(${wel19.image})`,
                }}
              ></MtPic>
              <LectureInfo>
                <p>&#60;{wel19.lectureName}&#62;</p>
                <p>{wel19.subTitle}</p>
              </LectureInfo>
            </MasterIn>
            <MasterIn to={`/lecture/${web10.id}`}>
              <MtPic
                style={{
                  backgroundImage: `url(${web10.image})`,
                }}
              ></MtPic>
              <LectureInfo>
                <p>&#60;{web10.lectureName}&#62;</p>
                <p>{web10.subTitle}</p>
              </LectureInfo>
            </MasterIn>
            <MasterIn to={`/lecture/${mob11.id}`}>
              <MtPic
                style={{
                  backgroundImage: `url(${mob11.image})`,
                }}
              ></MtPic>
              <LectureInfo>
                <p>&#60;{mob11.lectureName}&#62;</p>
                <p>{mob11.subTitle}</p>
              </LectureInfo>
            </MasterIn>
          </MasterWrap>
        </MasterSec>
        <FavSec>
          <Header>
            <Title>
              모나카 회원이 많이 선택한 강의 <FavEmoji />
            </Title>
            <Info>인기 있는 강의별로 골라봐요.</Info>{" "}
            <p>아래의 사진을 누르면 해당 강의 상세 페이지로 이동해요.</p>
          </Header>
          <FavWrap>
            <FavIn to={`/lecture/${kiosk4.id}`}>
              <FavPic
                style={{
                  backgroundImage: `url(${kiosk4.image})`,
                }}
              ></FavPic>
              <LectureInfo>
                <p>&#60;{kiosk4.lectureName}&#62;</p>
                <p>{kiosk4.subTitle}</p>
              </LectureInfo>
            </FavIn>
            <FavIn to={`/lecture/${mob14.id}`}>
              <FavPic
                style={{
                  backgroundImage: `url(${mob14.image})`,
                }}
              ></FavPic>
              <LectureInfo>
                <p>&#60;{mob14.lectureName}&#62;</p>
                <p>{mob14.subTitle}</p>
              </LectureInfo>
            </FavIn>
            <FavIn to={`/lecture/${wel16.id}`}>
              <FavPic
                style={{
                  backgroundImage: `url(${wel16.image})`,
                }}
              ></FavPic>
              <LectureInfo>
                <p>&#60;{wel16.lectureName}&#62;</p>
                <p>{wel16.subTitle}</p>
              </LectureInfo>
            </FavIn>
            <FavIn to={`/lecture/${web9.id}`}>
              <FavPic
                style={{
                  backgroundImage: `url(${web9.image})`,
                }}
              ></FavPic>
              <LectureInfo>
                <p>&#60;{web9.lectureName}&#62;</p>
                <p>{web9.subTitle}</p>
              </LectureInfo>
            </FavIn>
            <FavIn to={`/lecture/${kiosk3.id}`}>
              <FavPic
                style={{
                  backgroundImage: `url(${kiosk3.image})`,
                }}
              ></FavPic>
              <LectureInfo>
                <p>&#60;{kiosk3.lectureName}&#62;</p>
                <p>{kiosk3.subTitle}</p>
              </LectureInfo>
            </FavIn>
          </FavWrap>
        </FavSec>
      </Container>
    </>
  );
}
