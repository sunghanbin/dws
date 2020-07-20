import React from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
import qs from "qs";
import dotenv from "dotenv";
import cookie from "react-cookies";
import Headernav from "../partials/Headernav";
dotenv.config();

class LoginForm extends React.Component {
  state = {
    userid: "",
    password: "",
  };

  handleSubmit = async (e) => {
    const { userOn } = this.props;
    console.log(this.props);
    const sendDt = {
      userid: this.state.userid,
      password: this.state.password,
    };

    axios
      .post(
        `${process.env.REACT_APP_RESTAPI_SERVER}/user/login`,
        qs.stringify(sendDt),
        { withCredentials: true }
      )
      .then((response) => {
        const {
          data: { flag, error },
        } = response;

        if (!flag) {
          if (error === "id") {
            alert(`등록되지 않은 아이디 입니다.\n 아이디를 확인해주세요`);
            window.location.replace("/user/loginform");
          } else {
            alert(`비밀번호가 일치 하지 않습니다.`);
            window.location.replace("/user/loginform");
          }
        }
        window.location.replace("/");
      })
      .catch((error) => {
        console.log(error);
        window.location.replace("/user/signupform");
      });
    e.preventDefault();
  };

  handleonChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  render() {
    const { userState } = this.props;
    return (
      <>

        <Form onSubmit={this.handleSubmit}>
          <Form.Group className="submit_form">
            <Form.Label>Userid</Form.Label>
            <Form.Control
              type="text"
              placeholder="userID"
              value={this.state.userid}
              onChange={this.handleonChange}
              name="userid"
            />
          </Form.Group>
          <Form.Group className="submit_form">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="password"
              value={this.state.password}
              onChange={this.handleonChange}
              name="password"
            />
          </Form.Group>
          <Button type="submit" className="submit_btn">Login</Button>
        </Form>
      </>
    );
  }
}


export default LoginForm;
