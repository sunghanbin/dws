import React from "react";
import { Form, Button, ButtonGroup } from "react-bootstrap";
import axios from "axios";
import qs from "qs";
import { Redirect } from "react-router-dom";
import Headernav from "../partials/Headernav";
import dotenv from "dotenv";
dotenv.config();

const disableList = document.getElementsByClassName("form-control");



class SignUpForm extends React.Component {
  state = {
    name: "",
    userid: "",
    password: "",
    phonno: "",
    email: "",
    checkid: false,
    checkpwd: false,
  };

  disabledswich = (firstinput, andinput) => {
    disableList[0].disabled = firstinput;

    for (var i = 1; i < disableList.length; i++) {
      disableList[i].disabled = andinput;
    }
  };

  // 비번확인
  pwdMatch = (e) => {
    var errText = document.getElementById("pswd2Msg");
    if (disableList.password.value === disableList.password2.value) {
      errText.classList.remove("d-block");
      errText.classList.add("d-none");
      this.setState((state) => {
        return { checkpwd: true };
      });
    } else {
      errText.classList.remove("d-none");
      errText.classList.add("d-block");
      this.setState((state) => {
        return { checkpwd: false };
      });
    }
  };

  handleuseidcompare = async (e) => {
    // const params = new URLSearchParams();
    // params.append("userid", this.state.userid);
    await axios
      .get(`${process.env.REACT_APP_RESTAPI_SERVER}/user/ckuser`, {
        params: {
          userid: this.state.userid
        }
      })
      .then((response) => {
        this.state.checkid = response.data;
        if (this.state.checkid) {
          alert("사용 가능한 아이디 입니다.");

          this.disabledswich("disabled", false);
        } else {
          alert(`사용 불가능한 아이디 입니다. \n 다시입력해주세요`);
          disableList[0].value = "";
        }
        // 응답 처리
      })
      .catch((error) => {
        // 예외 처리
      });
  };
  retryuserid = (e) => {
    this.disabledswich(false, "disabled");
    this.setState((state) => {
      return { checkid: false, userid: "" };
    });
  };

  handleonChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  handleSubmit = async (e) => {
    const sendDt = {
      name: this.state.name,
      userid: this.state.userid,
      password: this.state.password,
      phonno: this.state.phonno,
      email: this.state.email,
    };

    if (this.state.checkid && this.state.checkpwd) {
      await axios
        .post(
          `${process.env.REACT_APP_RESTAPI_SERVER}/user/signup`,
          qs.stringify(sendDt)
        )
        .then((response) => {
          window.location.replace("/user/signupform");
          // 응답 처리
        })
        .catch((error) => {
          // 예외 처리
          console.log(error.response);
        });
    } else if (this.state.checkid || this.state.checkpwd) {
      if (!this.state.checkid) {
        alert("아이디를 확인해주세요!");
      } else {
        alert("비밀번호를 확인해주세요!");
      }
    } else {
      alert("아이디와 비밀번호를 확인해주세요.");
    }

    e.preventDefault();
  };

  render() {
    return (
      <Form onSubmit={this.handleSubmit}>
        <Form.Group className="submit_form">
          <Form.Label>Userid</Form.Label>
          <Form.Control
            type="text"
            placeholder="A unique userID"
            value={this.state.userid}
            onChange={this.handleonChange}
            name="userid"
          />
          <ButtonGroup className="form_btnG">
            <Button onClick={this.retryuserid}> 아이디 재설정</Button>
            <Button onClick={this.handleuseidcompare}>
              {" "}
                아이디 중복확인
              </Button>
          </ButtonGroup>
        </Form.Group>
        <Form.Group className="submit_form" >
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="your Full name"
            value={this.state.name}
            onChange={this.handleonChange}
            name="name"
            disabled="disabled"
          />
        </Form.Group>
        <Form.Group className="submit_form" >
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="A password"
            value={this.state.password}
            onChange={this.handleonChange}
            onBlur={this.pwdMatch}
            name="password"
            disabled="disabled"
          />
        </Form.Group>
        <Form.Group className="submit_form" >
          <Form.Label>Password confirm</Form.Label>
          <Form.Control
            type="password"
            placeholder="A password"
            onBlur={this.pwdMatch}
            name="password2"
            disabled="disabled"
          />
          <span id="pswd2Msg" className="d-none">
            비밀번호가 일치하지 않습니다.
            </span>
        </Form.Group>
        <Form.Group className="submit_form" >
          <Form.Label>PhonNo</Form.Label>
          <Form.Control
            type="text"
            placeholder="Your Phon No"
            value={this.state.phonno}
            onChange={this.handleonChange}
            name="phonno"
            disabled="disabled"
          />
        </Form.Group>
        <Form.Group className="submit_form" >
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Your email"
            value={this.state.email}
            onChange={this.handleonChange}
            name="email"
            disabled="disabled"
          />
        </Form.Group>
        <Button type="submit" className="submit_btn">Signup</Button>
      </Form>

    );
  }
}
export default SignUpForm;
