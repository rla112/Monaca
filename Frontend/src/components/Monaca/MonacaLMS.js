// 총괄 컴포넌트
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { NavBar } from "./NavBar";
import { Main } from "./Mains/Main";
import { Notice } from "./Admins/Notice";
import { StudentRegister } from "./Student/StudentRegister";
import { Message } from "./Messages/Message";
import styled from "styled-components";
import { ProfessorRegister } from "./Professors/ProfessorRegister";
import { LectureList } from "./Lectures/LectureList";
import { LectureInfo } from "./Lectures/LectureInfo";
import { ProfessorList } from "./Professors/ProfessorList";
import Intro from "./Intro";
import { createContext, useEffect, useState } from "react";
import { ProfessorEmployee } from "./Professors/ProfessorEmployee";
import { LearningMgmt } from "./Professors/LearningMgmt";
import { StudentMgmt } from "./Professors/StudentMgmt";
import { Material } from "./Lectures/Material";
import { GradeGuide } from "./GradeGuide";
import { LectureQnA } from "./Lectures/LectureQnA";
import { LectureNotice } from "./Lectures/LectureNotice";
import { ExamPaper } from "./Student/ExamPaper";
import { QueryClient } from "react-query";
import { ProfessorRoom } from "./Professors/ProfessorRoom";
import { UserRoom } from "./Student/UserRoom";
import { UserLrngMgmt } from "./Student/UserLrngMgmt";
import { UserCourseMgmt } from "./Student/UserCourseMgmt";
// import { LectureCart } from "./Lectures/LectureCart"
import { OutBox } from "./Messages/OutBox";
import { InBox } from "./Messages/InBox";
import { ItemShop } from "./Student/ItemShop";
import { PfsProfile } from "./Professors/PfsProfile";
import { OutBoxDetaile } from "./Messages/OutBoxDetaile";
import { InBoxDetaile } from "./Messages/InBoxDetaile";
import { DoQnA } from "./Student/DoQnA";
import { Logout } from "./Mains/Logout";
import { Cart } from "./Student/Cart";
import { ClassRoom } from "./Student/ClassRoom";
import { CompletedLecture } from "./Student/CompletedLecture";
import { RegisteredLecture } from "./Professors/RegisteredLecture";
import { UploadLecture } from "./Professors/UploadLecture";
import { RegistrationConfirm } from "./Professors/RegistrationConfirm";
import { InfoUi } from "./Professors/InfoUi";
import { AdminBar } from "./Admins/AdminBar";
import { AdminRoom } from "./Admins/AdminRoom";
import { AdminLMgnt } from "./Admins/AdminLMgnt";
import { WriteaNotice } from "./Admins/WriteaNotice";
import { AdminNotice } from "./Admins/AdminNotice";
import { AdminChart } from "./Admins/AdminChart";
import { WatchTheLecture } from "./Student/WatchTheLecture";
import { AdminShop } from "./Admins/AdminShop";
import { LectureWrap } from "./Lectures/LectureWrap";
import { ProfessorWrap } from "./Professors/ProfessorWrap";
import { ProfessorInfo } from "./Professors/ProfessorInfo";
import { WatchTheLectureWrap } from "./Student/WatchTheLectureWrap";
import { Room } from "./Room";

const Container = styled.div`
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const client = new QueryClient();
export const MonacaContext = createContext();

export function MonacaLMS() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [showIntro, setShowIntro] = useState(true);

  const [loginState, setLoginState] = useState(
    JSON.parse(sessionStorage.getItem("loginState"))
  );

  useEffect(() => {
    const storedLoginState = JSON.parse(sessionStorage.getItem("loginState"));
    if (storedLoginState && storedLoginState.id) {
      setIsLoggedIn(true);
    }
  }, []);

  return (
    <>
      <MonacaContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
        <BrowserRouter>
          <Container>
            <div>
              <Routes>
                {showIntro && (
                  <Route
                    index
                    element={<Intro setShowIntro={setShowIntro} />}
                  />
                )}
                <Route path="/" element={<NavBar />}>
                  <Route index element={<Main />} />
                  <Route path="notice" element={<Notice />} />
                  <Route path="lecture-list" element={<LectureList />} />
                  <Route path="professor-list" element={<ProfessorList />} />
                  <Route path="grade-guide" element={<GradeGuide />} />
                  <Route path="Room" element={<Room />} />
                  <Route path="user-room" element={<UserRoom />} />
                  <Route path="pf-room" element={<ProfessorRoom />} />
                  <Route path="user-lngmgmt" element={<UserLrngMgmt />} />
                  <Route path="user-coursemgmt" element={<UserCourseMgmt />} />
                  <Route path="my-cart" element={<Cart />} />
                  <Route path="admin-room" element={<AdminRoom />} />
                  <Route path="admin-chart" element={<AdminChart />} />
                  <Route path="admin-lmgnt" element={<AdminLMgnt />} />
                  <Route path="message" element={<Message />} />
                  <Route path="out-box" element={<OutBox />} />
                  <Route path="in-box" element={<InBox />} />
                  <Route path="out-box-detaile" element={<OutBoxDetaile />} />
                  <Route path="in-box-detaile" element={<InBoxDetaile />} />
                  <Route path="lecture" element={<LectureWrap />}>
                    <Route path=":lectureId" element={<LectureInfo />} />
                    <Route path=":lectureId/qna" element={<LectureQnA />} />
                    <Route path=":lectureId/qna/do-qna" element={<DoQnA />} />
                    <Route
                      path=":lectureId/notice"
                      element={<LectureNotice />}
                    />
                  </Route>
                  <Route path="professor" element={<ProfessorWrap />}>
                    <Route path=":name" element={<ProfessorInfo />} />
                  </Route>
                  <Route path="stud-class-room" element={<ClassRoom />} />
                  <Route
                    path="watch-the-lecture"
                    element={<WatchTheLectureWrap />}
                  >
                    <Route path=":lectureId" element={<WatchTheLecture />} />
                  </Route>
                  <Route path="cp-lectures" element={<CompletedLecture />} />
                </Route>

                <Route path="/student-register" element={<StudentRegister />} />
                <Route
                  path="professor-register"
                  element={<ProfessorRegister />}
                />
                <Route path="logout" element={<Logout />} />
                <Route path="employee" element={<ProfessorEmployee />} />
                <Route path="pf-lngmgmt" element={<LearningMgmt />} />
                <Route path="stud-mgmt" element={<StudentMgmt />} />
                <Route path="material" element={<Material />} />
                <Route path="exam-paper" element={<ExamPaper />} />

                <Route path="item-shop" element={<ItemShop />} />
                <Route path="pf-profile" element={<PfsProfile />} />
                <Route path="reged-lectures" element={<RegisteredLecture />} />
                <Route path="upload-lecture" element={<UploadLecture />} />
                <Route path="confirm" element={<RegistrationConfirm />} />
                <Route path="info-ui" element={<InfoUi />} />
                <Route path="admin-bar" element={<AdminBar />} />
                <Route path="write-a-notice" element={<WriteaNotice />} />
                <Route path="admin-notice" element={<AdminNotice />} />

                <Route path="admin-shop" element={<AdminShop />} />
              </Routes>
            </div>
          </Container>
        </BrowserRouter>
      </MonacaContext.Provider>
    </>
  );
}
