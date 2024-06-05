import React from "react";
import { Link } from "react-router-dom";

export const ProfileIcon = ({src, alt}) => {
    return (
      <div className="profile-icon">
        <Link to={"/profile"}> 
            <img className={"profile-image"} src={src} alt={alt} />
        </Link>
      </div>
    );
}
