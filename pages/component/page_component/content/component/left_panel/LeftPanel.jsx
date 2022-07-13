import React from "react";
import NasWay from "./LeftPanel.module.scss";

import { useState, useEffect } from "react";
import { Formik, Field, Form } from "formik";

import UserBoot from "../../../../core/user_boot/UserBoot";

// Проверка на размер экрана//////////////////////////////////////////////////////////
// function useWindowSize() {
//   const [windowSize, setWindowSize] = useState({
//     width: undefined,
//     height: undefined,
//   });
//   useEffect(() => {
//     if (typeof window !== "undefined") {
//       function handleResize() {
//         setWindowSize({
//           width: window.innerWidth,
//           height: window.innerHeight,
//         });
//       }
//       window.addEventListener("resize", handleResize);
//       handleResize();
//       return () => window.removeEventListener("resize", handleResize);
//     }
//   }, []);
//   return windowSize;
// }
///////////////////////////////////////////////////////////////////////////////////

const registr = (props) => {
  return props;
};

const LeftPanel = () => {
  const [registerView, setRegisterView] = useState(false)

  const changeRegisterView = () => {
    setRegisterView(registerView === false)
  }

  return (
    <div className={NasWay.LeftPanel}>
      {
        registerView ? <UserBoot registr/> : <UserBoot/>
      }

      <button onClick={() => changeRegisterView()}>{registerView ? "Войти" : "Зарегистрироваться"}</button>
      <br />
      <button>Кнопка помощи по домашке</button>
      <br />
      <button> Кнопка примера кода если пользователь не зареган</button>
    </div>
  );
};

export default LeftPanel;
