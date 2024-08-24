import React, { useEffect, useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import { Link } from "react-router-dom";
import { ProfileIcon } from './ProfileIcon';
import { AppLogo } from './AppLogo';
import { redirectToAuthServer } from '../../services/apiService';
import Button from './Button';
import Menu from './Menu';
import { useAuth } from '../../auth/AuthContext';

const Header = () => {
  const isMobile = useMediaQuery({ maxWidth: 767 });
  const [showMenu, setShowMenu] = useState(false);

  // const {isAuthenticated} = useAuth();

  const handleSignIn = (e) => {
    e.preventDefault();
    redirectToAuthServer();
  }

  // useEffect(() => {
    // setShowMenu(isAuthenticated);
  // }, [isAuthenticated]);

  const menu_links = [
    { path: "/home", label: "Home" },
    { path: "/about", label: "About" },
    { path: "/contacts", label: "Contacts" }
  ]

  return (
    <div className="header_container">
      <div className="header">
        
        <AppLogo appName={"InterviewGether"} link={'/'} />
        
        <div className={'menu ' + isMobile && 'slide_menu'}>
          <ul className="links">
            {menu_links.map(link => (
              <li key={link.path}>
                <Link to={link.path}>{link.label}</Link>
              </li>
            ))}
          </ul>
        </div>
        
        {
          showMenu ? <Menu/> : 
          <Button label={'Sign In'} onClick={handleSignIn} id={'signin_btn'}/>
        }
      </div>
    </div>
  );
}

export default Header;