import {
  Box,
  Button,
  Card,
  Container,
  Grid,
  Stack,
  Typography,
} from "@mui/material";
import { Link } from "react-router-dom";
import Icon from "@mdi/react";
import {
  mdiClipboardEditOutline,
  mdiAccountGroup,
  mdiCellphoneMessage,
} from "@mdi/js";

const LandingPage = () => {
  const Cards = [
    {
      header: "1. Create Meeting",
      subheader: "Create your meeting and optional meeeting times",
      icon: <Icon path={mdiClipboardEditOutline} size={4} />,
    },
    {
      header: "2. Share With Others",
      subheader: "Invite others to vote on and disucss meeting times",
      icon: <Icon path={mdiAccountGroup} size={4} />,
    },
    {
      header: "3. Get Results",
      subheader:
        "Check results in real time and get notified when meeting is chosen",
      icon: <Icon path={mdiCellphoneMessage} size={4} />,
    },
  ].map((card) => {
    return (
      <Grid item key={card.header} xs={12} sm={7} md={4} lg={4}>
        <Card variant="outlined" sx={{ textAlign: "center" }}>
          <Typography variant="h5" component="div">
            {card.header}
          </Typography>
          <Typography variant="h5" component="div">
            {card.icon}
          </Typography>
          <Typography
            variant="h5"
            component="div"
            sx={{ backgroundColor: "rgb(149, 219, 216)" }}
          >
            {card.subheader}
          </Typography>
        </Card>
      </Grid>
    );
  });

  return (
    <>
      <Box
        sx={{
          bgcolor: "background.paper",
          pt: 8,
          pb: 6,
        }}
      >
        <Container maxWidth="sm">
          <Typography
            component="h1"
            variant="h3"
            align="center"
            color="text.primary"
            gutterBottom
          >
            MeetingMaker
          </Typography>
          <Typography
            variant="h5"
            align="center"
            color="text.secondary"
            paragraph
          >
            Schedule meetings faster than ever!
          </Typography>
          <Stack
            sx={{ pt: 4 }}
            direction="row"
            spacing={2}
            justifyContent="center"
          >
            <Link className="item" to="/login">
              <Button
                variant="outlined"
                size="large"
                sx={{
                  color: "#343a40",
                  border: "2px solid #343a40",
                  fontSize: "24px",
                }}
              >
                Login
              </Button>
            </Link>

            <Link className="item" to="/register">
              <Button
                variant="outlined"
                size="large"
                sx={{
                  color: "#343a40",
                  border: "2px solid #343a40",
                  fontSize: "24px",
                }}
              >
                Register
              </Button>
            </Link>
          </Stack>
        </Container>
      </Box>
      <Container sx={{ py: 8 }} maxWidth="lg">
        <Grid container spacing={4} justifyContent="center">
          {Cards}
        </Grid>
      </Container>
    </>
  );
};

export default LandingPage;
