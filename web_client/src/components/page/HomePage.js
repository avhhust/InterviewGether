import React, { useEffect, useState } from 'react';
import {api} from '../../utils/server-api.js';

const HomePage = () => {

  const[greeting, setGreeting] = useState('Welcome to InterviewGether!');

  const fetchData = () => {
    api.get('/greeting')
    .then(response => {
      setGreeting(response.data);
      console.log(response.status);
    })
    .catch(err => console.error(err));
  }

  return (
    <div className='home_container'>
      <p className='greeting'>
        {greeting}
        <button onClick={fetchData}>Connect</button>
      </p>
    </div>
  );
}

export default HomePage;