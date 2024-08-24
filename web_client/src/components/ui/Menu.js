import React, { useEffect, useRef, useState } from 'react'
import { ProfileIcon } from './ProfileIcon'
import SlideMenu from './SlideMenu';

const Menu = () => {
    const [isVisible, setIsVisible] = useState(false);
    const wrapperRef = useRef(null);

    useEffect(() => {
        function handleClickOutside(event) {
          if (wrapperRef.current && !wrapperRef.current.contains(event.target)) {
            setIsVisible(false);
          }
        }
        document.addEventListener("mousedown", handleClickOutside);
        return () => {
          document.removeEventListener("mousedown", handleClickOutside);
        };
      }, [wrapperRef]);

    const openCloseMenu = (e) => {
        e.preventDefault();
        setIsVisible(prev => !prev);
    }

    return (
        <div ref={wrapperRef} className="menu_container">
            <div className="photo_btn" onClick={openCloseMenu}>
                <ProfileIcon/>
            </div>
            <div hidden={!isVisible}>
                <SlideMenu/>
            </div>
        </div>
    )
}

export default Menu