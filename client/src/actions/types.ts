import {
  RegisterAction,
  LoginAction,
  LogoutAction,
  RefreshAction,
} from "./authentication";

import { successAction, errorAction } from "./alert";

export enum ActionTypes {
  SIGN_IN,
  SIGN_OUT,
  REGISTER,
  REFRESH,
  SUCCESS,
  ERROR,
}

export type AuthenticationAction =
  | RegisterAction
  | LoginAction
  | LogoutAction
  | RefreshAction;

export type AlertAction = successAction | errorAction;
