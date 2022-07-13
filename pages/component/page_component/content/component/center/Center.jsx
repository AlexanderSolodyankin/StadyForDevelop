import React from 'react';
import NasWay from './Center.module.scss'
import { useState, useEffect } from "react";
import axios from 'axios';

const Center = () => {
    return (
        <div className={NasWay.Center}>
            <h1>Контент</h1>
        </div>
    );
};

export default Center;