import React from 'react';
import { Link } from "react-router-dom";

export const AppLogo = ({appName}) => {
  return (
    <div className="app-logo">
        <Link to="/home">
          <h2 className="app-name">{appName}</h2>
        </Link>
    </div>
  )
}
