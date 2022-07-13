import React from 'react';


import Registration from './registration/Registration';
import Aunthentication from './authentication/Aunthentication';

const actions = (props) => {
    if(props.registr){return(<Registration/>)}
    else  return (<Aunthentication/>)
}

const UserBoot = (props) => {
    return (
        <div>
            <h2>User Моделька</h2>
            {actions(props)}
        </div>
    );
};

export default UserBoot;