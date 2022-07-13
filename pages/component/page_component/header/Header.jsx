import React from 'react';
import NasWay from  './Header.module.scss'
import Image from 'next/image';

import logo from './media/img/logo-NotFon.png'


const Header = () => {
    return (
        <div className={NasWay.Header}>
            {/* <div></div> */}
            <h1>Зачатки Разума</h1>
            <Image src={logo.src} alt="Logo" width={86} height={76}/>
            
        </div>
    );
};

export default Header;