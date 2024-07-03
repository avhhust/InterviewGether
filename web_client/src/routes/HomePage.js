import React, {useEffect, useState} from 'react';
import * as resApi from '../api/resourceApi';

const HomePage = () => {

  const[greeting, setGreeting] = useState('Welcome to InterviewGether!');

  useEffect(() => {
    const fetchData = async () => {
      const response = await resApi.getHomeInfo();
      setGreeting(response.data);
    }
  }, []);

  return (
    <p className='greeting'>{greeting}</p>
  );
}

export default HomePage;