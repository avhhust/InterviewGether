import React from 'react'
import { ProfileIcon } from './ProfileIcon'
import { MenuItem } from './MenuItem'
import { Link } from 'react-router-dom'
import { VscAccount } from "react-icons/vsc";
import { FaHistory } from "react-icons/fa";
import { RiCalendarScheduleLine } from "react-icons/ri";
import { IoPeopleSharp } from "react-icons/io5";
import { VscFeedback } from "react-icons/vsc";
import { PiSignOutBold } from "react-icons/pi";

const SlideMenu = () => {

    return (
        <div className="slide_menu">
            <div className="user_info">
                <ProfileIcon size={'65px'} />
                <div className="user_creds">
                    <Link to={"/profile"}>avhhust</Link>
                    <p>Software Engineer</p>
                </div>
            </div>

            <div className="items_container">
                <MenuItem url={'/profile'} name="My profile" icon={<VscAccount />} />
                <div className="two_column_content items_container">
                    <MenuItem name="Upcoming" icon={<RiCalendarScheduleLine />} />
                    <MenuItem url={"mock-history"} name="History" icon={<FaHistory />} />
                    <MenuItem name="Get Interview" icon={<IoPeopleSharp />} />
                    <MenuItem name="Feedbacks" icon={<VscFeedback />} />
                </div>
                <span>
                    <MenuItem name="Sign out" icon={<PiSignOutBold />} />
                </span>
            </div>
        </div>
    )
}

export default SlideMenu;