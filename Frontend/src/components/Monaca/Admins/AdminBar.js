import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const SubWrap = styled.div`
  display: flex;
  height: 100%;
  width: 20%;
  font-family: "GmarketSansMedium";
`;

const Section1 = styled.div`
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(3, 1fr);
`;

const List = styled.div`
  width: 100%;
  height: 90%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  & h4 {
    margin-bottom: 0.5rem;
  }
  & h3 {
    color: #ff5865;
    &:hover {
      cursor: pointer;
    }
  }
  & ul {
    margin-left: 1rem;
    list-style: none;
    display: flex;
    flex-direction: column;
  }
  & li {
    & h5 {
      margin-bottom: 10px;
      color: #666666;
    }
  }
`;

const Click = styled.h4`
  &:hover {
    cursor: pointer;
  }
`;

export function AdminBar() {
  const navigate = useNavigate();
  const navigateToLectureList = () => {
    navigate("/lecture-list");
  };
  const navigateToAdminRoom = () => {
    navigate("/admin-room");
  };
  const navigateToStudentMgmt = () => {
    navigate("/stud-mgmt");
  };

  const navigateToClassRoom = () => {
    navigate("/stud-class-room");
  };

  const navigateToAdminLmgnt = () => {
    navigate("/admin-lmgnt");
  };
  const navigateToQnA = () => {
    navigate("/lecture-qna");
  };
  const navigateToAdminNotice = () => {
    navigate("/admin-notice");
  };

  const navigateToAdminChart = () => {
    navigate("/admin-chart");
  };

  const navigateToAdminShop = () => {
    navigate("/admin-shop");
  };

  return (
    <>
      <SubWrap>
        <Section1>
          <List>
            <h3 onClick={navigateToAdminRoom}>나의방</h3>
            <br />
            <ul>
              <li>
                <Click onClick={navigateToAdminRoom}>내 캐릭터</Click>
                <h5>내가 보유한 캐릭터 꾸미기</h5>
              </li>

              <li>
                <Click onClick={navigateToAdminShop}>상점</Click>
                <h5>내가 보유한 캐릭터를</h5>
                <h5>꾸밀 수 있는 아이템 사기</h5>
              </li>
            </ul>
          </List>
          <List>
            <h3 onClick={navigateToAdminLmgnt}>학습관리</h3> <br />
            <ul>
              <li>
                <Click onClick={navigateToLectureList}>강의 목록</Click>
                <h5>전 강의 보기</h5>
              </li>
              <li>
                <Click onClick={navigateToClassRoom}>강의실</Click>
                <h5>수강 중인 강의 보기</h5>
              </li>
              <li>
                <Click onClick={navigateToAdminNotice}>공지사항 등록</Click>
              </li>
            </ul>
          </List>
          <List>
            <h3 onClick={navigateToAdminChart}>회원 관리실</h3> <br />
            <ul>
              <li>
                <Click onClick={navigateToAdminChart}>전체 회원수 관리</Click>
                <h5>모든 회원 인원 통계</h5>
              </li>
              <li>
                <Click onClick={navigateToAdminChart}>
                  유료 / 무료 강의 통계
                </Click>
                <h5>강의 통계</h5>
              </li>
              <li>
                <Click onClick={navigateToAdminChart}>유료강의 결제 통계</Click>
                <h5>판매 통계</h5>
              </li>
            </ul>
          </List>
        </Section1>
      </SubWrap>
    </>
  );
}
