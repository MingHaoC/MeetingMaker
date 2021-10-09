import { combineReducers } from "redux";
import { AuthenticationReducer, AuthStateMap } from "./AuthenticationReducer";
import { AlertReducer } from "./AlertReducer";
import { alertMessage } from "../actions";

export interface StoreState {
  AuthenticationReducer: AuthStateMap;
  AlertReducer: alertMessage;
}

export default combineReducers<StoreState>({
  AuthenticationReducer,
  AlertReducer,
});
