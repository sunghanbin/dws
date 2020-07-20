import React from "react";
import { Nav, NavDropdown } from "react-bootstrap";






class SideMenu extends React.Component {


    menuvisible = (e) => {
        const sidemenu = document.querySelector(".sidemenu");

        console.log("df");
    }
    render() {
        return (
            <>
                <div className="smalltab" onClick={this.menuvisible}>menu</div>
                <div className="sidemenu">
                    <Nav.Link href="/saller">거래처 검색</Nav.Link>


                </div>
            </>

        );
    }
}


export default SideMenu;