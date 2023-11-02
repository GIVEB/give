import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {

  const [strArr, setStrArr] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/donorcards')
      .then((response) => {
        const dd = response.data;
        const arr = Object.values(dd);
        setStrArr(arr);
        console.log(arr);
      })
      .catch((error) => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      });
  }, []);

  
  return (
    <div>
      {strArr.map((element, index) => (
        element.map((ex,ed)=>(
          <div key={ed}>
          <div>{ex.cardId}</div>
          <div>{ex.donorCenter}</div>
          <div>{ex.birth}</div>
          <div>{ex.donorDate}</div>
          <div>{ex.gender}</div>
          <div>{ex.kind}</div>
          <div>{ex.name}</div>
          <div>{ex.registrationDate}</div>
          <div>{ex.userId}</div>
        </div>
        ))
      ))}
    </div>
  );
}

export default App;
