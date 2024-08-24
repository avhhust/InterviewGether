import React, {useState} from 'react';
import { api } from '../services/apiService';

const HomePage = () => {

  const[apiResponse, setApiResponse] = useState('');

  const handleApiCall = async (e) => {
    e.preventDefault();
    try{
      const response = await api.get('/api/profile');
      setApiResponse(response.data);
    } catch(error){

    }
  }
  
  return (
    <div className="home_container">
      <button onClick={handleApiCall}>Call API</button>
      <p>{apiResponse}</p>
    </div>
  );
}

export default HomePage;