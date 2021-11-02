import React, { FC, useState } from "react";
import {
  Avatar,
  Box,
  Button,
  Container,
  FormControl,
  Grid,
  TextField,
  Typography,
} from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import CssBaseline from "@mui/material/CssBaseline";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { register } from "../../actions/index";
import { AuthenticationData } from "../../actions";
import validate from "validate.js";
import constraints from "../../constraints/constraints";

interface Props {
  register: (formValue: AuthenticationData) => void;
}

const Register: FC<Props> = (props) => {
  interface error {
    firstName?: String;
    lastName?: String;
    emailAddress?: String;
    password?: String;
  }

  const initalError: error = {
    firstName: "",
    lastName: "",
    emailAddress: "",
    password: "",
  };

  // input information
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState(initalError);

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const error = validate(
      {
        firstName: firstName,
        lastName: lastName,
        emailAddress: email,
        password: password,
      },
      {
        firstName: constraints.firstName,
        lastName: constraints.lastName,
        emailAddress: constraints.emailAddress,
        password: constraints.password,
      }
    );

    if (error) setErrors(error);
    else {
      setErrors(initalError);
      const formValue: AuthenticationData = {
        firstName,
        lastName,
        email,
        password,
      };
      props.register(formValue);
    }
  };

  const theme = createTheme();

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <FormControl
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign Up
          </Typography>

          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2} style={{ marginBottom: "8px" }}>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  id="firstName"
                  label="First name"
                  name="firstName"
                  autoComplete="firstName"
                  onChange={(e) => setFirstName(e.target.value)}
                  error={!!(errors && errors.firstName)}
                  helperText={
                    errors && errors.firstName ? errors.firstName[0] : ""
                  }
                  fullWidth
                  autoFocus
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  id="lastName"
                  label="Last name"
                  name="lastName"
                  autoComplete="lastName"
                  onChange={(e) => setLastName(e.target.value)}
                  error={!!(errors && errors.lastName)}
                  helperText={
                    errors && errors.lastName ? errors.lastName[0] : ""
                  }
                  fullWidth
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  onChange={(e) => setEmail(e.target.value)}
                  error={!!(errors && errors.emailAddress)}
                  helperText={
                    errors && errors.emailAddress ? errors.emailAddress[0] : ""
                  }
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="Password"
                  id="password"
                  autoComplete="current-password"
                  onChange={(e) => setPassword(e.target.value)}
                  error={!!(errors && errors.password)}
                  helperText={
                    errors && errors.password ? errors.password[0] : ""
                  }
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            <Grid container>
              <Grid item xs>
                <Link to="/forgotpassword">Forgot password?</Link>
              </Grid>
              <Grid item>
                <Link to="/login">Already have an account?</Link>
              </Grid>
            </Grid>
          </Box>
        </FormControl>
      </Container>
    </ThemeProvider>
  );
};

export default connect(null, { register })(Register);
