import {
  Backdrop,
  Button,
  Card,
  CardContent,
  CircularProgress,
  Container,
  Typography,
} from "@mui/material";
import React, { FC, useState, useEffect } from "react";
import { connect } from "react-redux";
import { useParams } from "react-router";
import { Link } from "react-router-dom";
import { verifyEmail } from "../../actions/index";
import history from "../../history";

interface Props {
  verifyEmail: (uuid: string) => Promise<boolean | void>;
}

const EmailVerify: FC<Props> = ({ verifyEmail }) => {
  const { uuid }: string | null | any = useParams();

  const [open, setOpen] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const verify = async () => {
      const check = await verifyEmail(uuid);
      setOpen(false);
      if (!check) setError(true);
    };

    if (typeof uuid === "string") verify();
    else history.push("/404");
  }, [uuid, verifyEmail]);

  const displaySuccessMessage = () => {
    if (!open && !error)
      return (
        <div>
          <Card
            sx={{ maxWidth: 345 }}
            style={{ margin: "auto", marginTop: "64px" }}
          >
            <CardContent style={{ backgroundColor: "#90ee90" }}>
              <div
                style={{
                  textAlign: "center",
                  margin: "10% 0 10% 0",
                  color: "white",
                }}
              >
                <Typography
                  gutterBottom
                  variant="h5"
                  component="div"
                ></Typography>
                <Typography gutterBottom variant="h5" component="div">
                  Success!
                </Typography>
              </div>
            </CardContent>
            <CardContent>
              <Typography variant="body2" color="text.secondary">
                Congratulations, your email was successfully verified! You can
                now login to MeetingMaker!
              </Typography>
            </CardContent>
            <CardContent style={{ textAlign: "center" }}>
              <Link to="/login" style={{ textDecoration: "none" }}>
                <Button
                  style={{ borderRadius: 25, backgroundColor: "grey" }}
                  variant="contained"
                >
                  login
                </Button>
              </Link>
            </CardContent>
          </Card>
        </div>
      );
    else if (!open && error)
      return (
        <div>
          <Card
            sx={{ maxWidth: 345 }}
            style={{ margin: "auto", marginTop: "64px" }}
          >
            <CardContent style={{ backgroundColor: "red" }}>
              <div
                style={{
                  textAlign: "center",
                  margin: "10% 0 10% 0",
                  color: "white",
                }}
              >
                <Typography
                  gutterBottom
                  variant="h5"
                  component="div"
                ></Typography>
                <Typography gutterBottom variant="h5" component="div">
                  Error!
                </Typography>
              </div>
            </CardContent>
            <CardContent>
              <Typography variant="body2" color="text.secondary">
                The verification link you have enter is invalid, please contact
                support!
              </Typography>
            </CardContent>
            <CardContent style={{ textAlign: "center" }}>
              <Link to="/login" style={{ textDecoration: "none" }}>
                <Button
                  style={{ borderRadius: 25, backgroundColor: "grey" }}
                  variant="contained"
                >
                  login
                </Button>
              </Link>
            </CardContent>
          </Card>
        </div>
      );
    else return null;
  };

  return (
    <Container component="main" maxWidth="xs">
      <Backdrop
        sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={open}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
      {displaySuccessMessage()}
    </Container>
  );
};

export default connect(null, { verifyEmail })(EmailVerify);
