import { useEffect } from "react";
import { Router, Route, Switch } from "react-router-dom";
import { connect } from "react-redux";
import { ThemeProvider } from "@mui/system";
import { createTheme } from "@mui/material/styles";

import { refresh } from "../actions/index";
import Alert from "./Alert";
import history from "../history";
import LandingPage from "./LandingPage";
import Header from "./Header";
import Login from "./Authentication/Login";
import Register from "./Authentication/Register";

interface Props {
  refresh: () => void;
}

const theme = createTheme();

const _App = ({ refresh }: Props) => {
  useEffect(() => {
    refresh();
  }, [refresh]);

  return (
    <ThemeProvider theme={theme}>
      <Router history={history}>
        <div>
          <Header />
          <Alert />
          <Switch>
            <Route path="/" exact component={LandingPage} />
            {/* <Route path="/home" exact component={Home} /> */}
            <Route path="/register" exact component={Register} />
            <Route path="/login" exact component={Login} />
            {/* <Route path="/resetPassword" exact component={ForgotPassword} /> */}
          </Switch>
        </div>
        {/* <Footer /> */}
      </Router>
    </ThemeProvider>
  );
};

export const App = connect(null, { refresh })(_App);
