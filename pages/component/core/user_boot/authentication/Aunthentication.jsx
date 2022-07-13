import React from 'react';
import axios from 'axios'
import { useState, useEffect } from "react";
import { Formik, Field, Form } from "formik";

const Aunthentication = () => {

  const sendRequest = (data) => {
    axios({
      method: "post",
      url: "http://localhost:8080/authorized",
      data: {
        login: data.login,
        password: data.password,
      },
    }).then(res => {
      localStorage.setItem("TOKEN", res.data)
    }).catch(err=>{
      console.log(err)
    });
  }; 

    return (
        <div>
               <Formik
            initialValues={{
              login: "",
              password: "",
            }}
            onSubmit={(values) => {
              // alert(JSON.stringify(values, null, 2)); 
              sendRequest(values);

            }}
          >
            <Form>
              <div>
                <Field
                  id="login"
                  name="login"
                  placeholder="Ваш логин"
                />
                <br />
                <Field
                  id="password"
                  name="password"
                  placeholder="Ваш пароль"
                />
                <br />
              </div>
              <button type="submit"> Войти </button>
              <br />
            </Form>
          </Formik>
        </div>
    );
};

export default Aunthentication;