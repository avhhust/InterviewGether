import React from 'react';
import { Link } from "react-router-dom";

export const AppLogo = ({appName, link}) => {
  return (
    <div className="app-logo">
        <Link to={link || "/home"}>
          <h2 className="app-name">{appName}</h2>
        </Link>
    </div>
  )
}
