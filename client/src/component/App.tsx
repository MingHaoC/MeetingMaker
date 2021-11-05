import { Router, Route, Switch } from "react-router-dom";
import { connect } from "react-redux";
import { ThemeProvider } from "@mui/system";
import { Box, Container, CssBaseline } from "@mui/material";
import MainTheme from "../Theme/MainTheme";
import { refresh } from "../actions/index";
import Alert from "./Alert";
import history from "../history";
import LandingPage from "./LandingPage";
import Header from "./Header";
import Login from "./Authentication/Login";
import Register from "./Authentication/Register";
import RegisterSuccess from "./Authentication/RegisterSuccess";
import EmailVerify from "./Authentication/EmailVerify";
import NotFound from "./NotFound";

interface Props {
  refresh: () => void;
}

const App = ({ refresh }: Props) => {
  // useEffect(() => {
  //   refresh();
  // }, [refresh]);

  return (
    <ThemeProvider theme={MainTheme}>
      <CssBaseline />
      <Container component="main" maxWidth={false} style={{ padding: 0 }}>
        <Router history={history}>
          <Box component="main">
            <Header />
            <Alert />
            <Switch>
              <>
                <Route path="/" exact component={LandingPage} />
                {/* <Route path="/home" exact component={Home} /> */}
                <Route path="/register" exact component={Register} />
                <Route
                  path="/register/success"
                  exact
                  component={RegisterSuccess}
                />
                <Route path="/verify/email/:uuid" component={EmailVerify} />
                <Route path="/login" exact component={Login} />
                {/* <Route path="/resetPassword" exact component={ForgotPassword} /> */}
                <Route path={"/404"} exact component={NotFound} />
                {/* <Route component={NotFound} /> */}
              </>
            </Switch>
          </Box>
          {/* <Footer /> */}
        </Router>
      </Container>
    </ThemeProvider>
  );
};

export default connect(null, { refresh })(App);
