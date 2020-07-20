import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { CookiesProvider } from "react-cookie";
import { Provider } from "react-redux";
import store from "./redux/store";
import Hnav from "./Component/partials/Headernav";
import SideMenu from "./Component/partials/SideMenu";
// bootstrap 연결.
import "bootstrap/dist/css/bootstrap.min.css";

ReactDOM.render(
  <Provider store={store}>
    <CookiesProvider>
      <Hnav />
      <div className="contain">
        <SideMenu />
        <App />
      </div>
    </CookiesProvider>
  </Provider>,
  document.getElementById("root")
);

