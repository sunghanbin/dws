import React from "react";
import { Nav, Navbar, NavDropdown, Image } from "react-bootstrap";
import logo from "../../images/logo1.png";
import { connect } from "react-redux";
import { Greeting } from "../partials/HnavR";

const Headernav = () => {

  return (
    <div className="header-navbar">
      <Nav>
      </Nav>
      <Nav>
        <Nav.Link href="/">
          <Image src={logo} className="logo"></Image>
        </Nav.Link>
      </Nav>
      <Greeting />
    </div>
  );
}
export default Headernav;
