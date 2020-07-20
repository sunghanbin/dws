import React from "react";
import { Route } from "react-router-dom";
import SignUpForm from "../Component/User/SignUpForm";
import LoginForm from "../Component/User/LoginForm";

class user extends React.Component {
  render() {
    return (
      <div>
        <Route exact path={this.props.match.path} component={SignUpForm} />
        <Route
          path={`${this.props.match.path}/signupform`}
          component={SignUpForm}
        />
        <Route
          path={`${this.props.match.path}/loginform`}
          component={LoginForm}
        />
      </div>
    );
  }
}

export default user;
