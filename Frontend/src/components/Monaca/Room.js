import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function checkRole(auth) {
  let url = "";
  if (auth.includes("ROLE_ADMIN")) {
    url = "/admin-room";
    return url;
  } else if (auth.includes("ROLE_USER")) {
    url = "/user-room";
  } else {
    url = "/pf-room";
  }

  return url;
}

export function Room() {
  const loginState = JSON.parse(sessionStorage.getItem("loginState"));
  console.log("Login State:", loginState);

  const jwtToken = loginState.token;

  const base64Url = jwtToken.split(".")[1]; // 페이로드 부분을 추출합니다.
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/"); // Base64-URL을 Base64로 변환합니다.
  const payload = JSON.parse(atob(base64)); // Base64로 인코딩된 페이로드를 디코딩하고, JSON으로 파싱합니다.

  const { auth } = payload;
  const url = checkRole(auth);
  const navigate = useNavigate();
  const moveToRoleRoom = () => {
    navigate(url, { replace: true });
  };

  useEffect(() => {
    moveToRoleRoom();
  }, []);

  return <></>;
}
