import {
  Card,
  CardContent,
  Typography,
  Container,
  Button,
} from "@mui/material";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import { Link } from "react-router-dom";

const RegisterSuccess = () => {
  return (
    <Container>
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
            <Typography gutterBottom variant="h5" component="div">
              <CheckCircleOutlineIcon />
            </Typography>
            <Typography gutterBottom variant="h5" component="div">
              Success!
            </Typography>
          </div>
        </CardContent>
        <CardContent>
          <Typography variant="body2" color="text.secondary">
            Congratulations, your account was successfully created, please click
            on the link that has just been sent to your email account to verify
            your email!
          </Typography>
        </CardContent>
        <CardContent style={{ textAlign: "center" }}>
          <Link to="/login" style={{ textDecoration: "none" }}>
            <Button
              style={{ borderRadius: 25, backgroundColor: "#90ee90" }}
              variant="contained"
            >
              Go back to login
            </Button>
          </Link>
        </CardContent>
      </Card>
    </Container>
  );
};

export default RegisterSuccess;
