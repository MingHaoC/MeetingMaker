import { toast } from "react-toastify";
import { alertMessage } from "../actions";
import "react-toastify/dist/ReactToastify.css";

export const alert = ({ message, alertType }: alertMessage) => {
  let msg = "";
  if (Array.isArray(message))
    for (let i = 0; i < message.length; i++) {
      msg = message[i];
      fireMessage(msg, alertType);
    }
  else fireMessage(message, alertType);
};

const fireMessage = (message: string, alertType: string) => {
  if (alertType === "error")
    toast.error(message, {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
  else
    toast.success(message, {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
};
