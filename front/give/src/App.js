// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
   const [hello, setHello] = useState('')

   useEffect(()=> {
    fetch('http://localhost:8080/donorcards', {
        method : "GET"   
    }).then(res=>res.json()).then(res=>{
        console.log(1, res);
        setHello(res);
    });              
}, []);


    return (
        <div>
            백엔드에서 가져온 데이터입니다 : {hello}
        </div>
    );
}

export default App;