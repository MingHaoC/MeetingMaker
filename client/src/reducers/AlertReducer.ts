import { alertMessage, ActionTypes, AlertAction } from "../actions";

const initialState: alertMessage = {
  message: "",
  alertType: "",
};

export const AlertReducer = (state = initialState, action: AlertAction) => {
  switch (action.type) {
    case ActionTypes.SUCCESS:
      return {
        ...state,
        message: action.payload.message,
        alertType: action.payload.alertType,
      };
    case ActionTypes.ERROR:
      return {
        ...state,
        message: action.payload.message,
        alertType: action.payload.alertType,
      };
    default:
      return state;
  }
};
