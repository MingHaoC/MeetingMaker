import { Button, Container } from "@mui/material";
import { Link } from "react-router-dom";

const LandingPage = () => {
  return (
    <Container>
      <p>
        <Link to="/create">
          <Button>Test</Button>
        </Link>
      </p>
    </Container>
  );
};

export default LandingPage;
