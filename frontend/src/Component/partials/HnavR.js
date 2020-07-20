import React from "react";
import cookie from "react-cookies";
import { isEmpty } from "../GlobalF";
import { Nav, Navbar, NavDropdown, Image } from "react-bootstrap";


export const Greeting = () => {
  const userstate = cookie.load("X-AUTH-TOKEN");



  if (!isEmpty(userstate)) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
};

const GuestGreeting = () => {
  return (
    <Nav>
      <Nav.Link href="/user/loginform">로그인</Nav.Link>
      <Nav.Link href="/user/signupform">회원가입</Nav.Link>
    </Nav>
  );
};

const UserGreeting = () => {
  return (
    <Nav>
      <Nav.Link href="#home">로그아웃</Nav.Link>
      <NavDropdown title="회원정보" id="basic-nav-dropdown">
        <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
        <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
        <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
      </NavDropdown>
    </Nav>
  );
};
