import React from 'react';

import NasWay from './NavigationPatel.module.scss';

function NavigationPanel() {
    return (
        <div className={NasWay.Navigation}>
            <ul>
                <a href="">Главная</a>
                <a href="">Список технологий</a>
                <a href="">форум</a>
                <a href="">справочник</a>
                <a href="">игры</a>
                {/* <a href="">Примеры работ</a> */}
                {/* <a href="">Список участников</a> */}
                {/* <a href="">Связь с ментаром</a> */}
                <a href="">Какой выбрать язык</a>
                <a href="">Проверка знаний</a>
                <a href="">О сайте</a>
            </ul>
        </div>
    );
}

export default NavigationPanel;