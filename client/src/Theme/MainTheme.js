import { createTheme } from "@mui/material/styles";

export default createTheme({
  palette: {
    primary: { main: "#343a40", contrastText: "white" },
    secondary: { main: "#1976d2", contrastText: "white" },
  },
  components: {
    MuiButton: {
      variants: [
        {
          props: { variant: "outlined" },
          style: {
            "&:hover": {
              border: "2px solid #343a40",
              fontSize: "24px",
              backgroundColor: "#343a40",
              color: "white",
            },
          },
        },
      ],
    },
  },
});
