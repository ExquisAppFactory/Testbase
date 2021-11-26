import React from "react";
import { Switch, Route, BrowserRouter } from "react-router-dom";
import Fund from "./fund/fund";
import Home from "./home/home";
import Landing from "./home/landing";
import Login from "./login/login";
import { Header } from "./navigation/header";
import Register from "./register/register";

const RouterConfig = () => {
  return (
    <BrowserRouter>
      {/* <Header userInfo={userInfo} /> */}
      <Switch>
        <Route exact path="/" component={Landing} />
        <Route exact path="/home" component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/fund" component={Fund} />
      </Switch>
    </BrowserRouter>
  );
};

export default RouterConfig;
