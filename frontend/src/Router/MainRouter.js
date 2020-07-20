import React from "react";
import { Route, Switch, BrowserRouter as Router } from "react-router-dom";
import home from "../Component/Home";
import user from "./UserRouter";
import buyer from "../Component/sellerCp/buyerSerch"

const MainRouter = () => {
  return (
    <Router>
      <main>
        <Switch>
          <Route exact path="/" component={home} />
          <Route path="/user" component={user} />
          <Route path="/saller" component={buyer} />
        </Switch>
      </main>
    </Router>
  );
};

export default MainRouter;
