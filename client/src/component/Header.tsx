import AppBar from "@mui/material/AppBar";
import { StoreState } from "../reducers/";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { logout } from "../actions/index";
import { FC } from "react";
import { Box } from "@mui/system";
import { Toolbar, Typography } from "@mui/material";
import "../css/header.css";

interface Props {
  isSignedIn: boolean;
  logout: typeof logout;
}

const renderSignIn = (props: Props) => {
  if (!props.isSignedIn)
    return (
      <Link to="/login" className="item">
        Login
      </Link>
    );

  return (
    <Link onClick={() => props.logout()} to="/" className="item">
      Logout
    </Link>
  );
};

const Header: FC<Props> = (props) => {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar color="primary" position="relative">
        <Toolbar>
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1 }}
            style={{ color: "white" }}
          >
            <Link className="item" to="/">
              MeetingMaker
            </Link>
          </Typography>

          <Typography variant="h6" component="div" sx={{ flexGrow: 0 }}>
            {renderSignIn(props)}
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

const mapStateToProps = (state: StoreState) => {
  return { isSignedIn: state.AuthenticationReducer.isSignedIn };
};

export default connect(mapStateToProps, { logout })(Header);
