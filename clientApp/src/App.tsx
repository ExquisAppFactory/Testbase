import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { DashboardPage } from './pages/app';
import { RegisterPage, LoginPage } from './pages/auth';

export default function App () {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/dashboard" component={DashboardPage} />
                <Route path="/register" component={RegisterPage} />
                <Route path="/login" component={LoginPage} />
                <Route path="/" component={LoginPage} />
            </Switch>
        </BrowserRouter>
    )
}