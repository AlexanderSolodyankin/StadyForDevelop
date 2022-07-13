import React from 'react';
import NasWay from './Content.module.scss';


import LeftPanel from './component/left_panel/LeftPanel';
import Center from './component/center/Center';
import RightPanel from './component/right_panel/RightPanel'
const Content = () => {
    return (
        <div className={NasWay.Content}>
            <div className={NasWay.Panels}>
                <LeftPanel/>
                <Center/>
                <RightPanel/>
            </div>
            
        </div>
    );
};

export default Content;