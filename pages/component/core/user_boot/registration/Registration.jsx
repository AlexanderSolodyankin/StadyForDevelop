import React from 'react';

import { useState, useEffect } from "react";
import axios from 'axios'
import { Formik, Field, Form } from "formik";





const Registration = () => {

    const sendRequest = (data) => {
        axios({
          method: "post",
          url: "http://localhost:8080/",
          data: {
            login: data.login,
            password: data.password,
            email: data.email,
            phone: data.phone,
          },
        }).then((res) => {
          console.log("Полученны данные " + res);
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
              email: "",
              phone: "",
            }}
            onSubmit={(values) => {
            //   alert(JSON.stringify(values, null, 2)); // нужен для того чтобы отслеживать что отправилось
              sendRequest(values)
              // Тут могла бы быть ваша реклама!!!
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
                <Field
                  id="email"
                  name="email"
                  placeholder="Ваша почта"
                />
                <br />
                
                <Field
                  id="phone"
                  name="phone"
                  placeholder="Ваш номер телефона"
                />
                <br />
              </div>

              <button type="submit">Submit</button>
              <br />
              <label >
                Нажимая кнопку «Получить консультацию»», Вы даете
                <br /> согласие на <a href="#"> обработку персональных данных</a> и принимаете
                <br />
               <a href="#"> политику конфиденциальности.</a>
              </label>
            </Form>
          </Formik>
            
        </div>
    );
};

export default Registration;