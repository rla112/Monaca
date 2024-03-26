import { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import { MonacaContext } from "../MonacaLMS";
import { useNavigate } from "react-router-dom";

export function Logout() {
   const [loginState, setLoginState] = useState(null);
   const navigate = useNavigate();

   useEffect(() => {
      localStorage.setItem("loginState", JSON.stringify({ id: null }));
      setLoginState({ id: null });
      navigate("/");
   }, []);
   return <></>;
}
