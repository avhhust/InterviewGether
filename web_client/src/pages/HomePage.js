import React, {useState} from 'react';
import { api } from '../services/apiService';

const HomePage = () => {

  // const[greeting, setGreeting] = useState('Welcome to InterviewGether!');
  const[apiResponse, setApiResponse] = useState('');

  const handleApiCall = (e) => {
    e.preventDefault();
    api.get('/api/profile')
    .then(response => setApiResponse(response.data))
    .catch(error => console.log(error))

  }
  
  return (
    <div className="home_container">
      <button onClick={handleApiCall}>Call API</button>
      <p>{apiResponse}</p>
    </div>
  );
}

export default HomePage;