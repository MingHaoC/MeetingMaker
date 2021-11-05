import GitHubIcon from "@mui/icons-material/GitHub";
import { AppBar, Container, Grid, Toolbar, Typography } from "@mui/material";
import { Box } from "@mui/system";
import { Link } from "react-router-dom";

const Footer = () => {
  return (
    <Box
      component="footer"
      sx={{
        py: 3,
        px: 2,
        mt: "auto",
        backgroundColor: "rgba(0,0,0,.87)",
      }}
    >
      <Container maxWidth="sm">
        <Typography variant="body1">
          My sticky footer can be found here.
        </Typography>
      </Container>
    </Box>
  );
};
export default Footer;
