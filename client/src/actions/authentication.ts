import { Dispatch } from "redux";
import { AxiosResponse, AxiosError } from "axios";
import * as userServices from "../services";
import { ActionTypes } from "./";
import history from "../history";
import jwtDecode from "jwt-decode";
import * as alert from "./alert";

export interface User {
  id: number;
  email: string;
  exp: number;
  expiresIn: number;
  iat: number;
}

export interface AuthenicationInfo {
  token: string;
}

export interface AuthenticationData {
  email: string;
  password: string;
  firstName?: string;
  lastName?: string;
}

// Authication actions interfaces

export interface RegisterAction {
  type: ActionTypes.REGISTER;
}

export interface LoginAction {
  type: ActionTypes.SIGN_IN;
  payload: User;
}

export interface LogoutAction {
  type: ActionTypes.SIGN_OUT;
}

export interface RefreshAction {
  type: ActionTypes.REFRESH;
  payload: User;
}

export const register =
  (formData: AuthenticationData) => (dispatch: Dispatch) => {
    userServices
      .register(formData)
      .then((response: AxiosResponse) => {
        if (response) {
          dispatch<RegisterAction>({ type: ActionTypes.REGISTER });
          history.push("/register/success");
        }
      })
      .catch((err: AxiosError) => {
        if (err.response)
          dispatch<alert.errorAction>(alert.error(err.response.data, "error"));
        else dispatch(alert.error("an error has occurred", "error"));
      });
  };

export const login = (formData: AuthenticationData) => (dispatch: Dispatch) => {
  userServices
    .login(formData)
    .then((response: AxiosResponse<AuthenicationInfo>) => {
      const user = jwtDecode<User>(response.data.token);
      dispatch<LoginAction>({
        type: ActionTypes.SIGN_IN,
        payload: user,
      });
      localStorage.setItem("token", response.data.token);
      history.push("/");
    })
    .catch((err: AxiosError) => {
      if (err.response)
        dispatch<alert.errorAction>(
          alert.error(err.response.data.error, "error")
        );
      else
        dispatch<alert.errorAction>(
          alert.error("An error has occurred", "error")
        );
    });
};

export const verifyEmail =
  (uuid: string) =>
  (dispatch: Dispatch): Promise<boolean | void> => {
    return Promise.resolve(
      userServices
        .verifyEmail(uuid)
        .then((response: AxiosResponse) => {
          return true;
        })
        .catch((err: AxiosError) => {
          if (err.response)
            dispatch<alert.errorAction>(
              alert.error(err.response.data.error, "error")
            );
          else
            dispatch<alert.errorAction>(
              alert.error("An error has occurred", "error")
            );
          return false;
        })
    );
  };

export const refresh = () => (dispatch: Dispatch) => {
  const token = localStorage.getItem("token");
  if (token)
    userServices
      .refresh(token)
      .then((response: AxiosResponse<AuthenicationInfo>) => {
        const user = jwtDecode<User>(response.data.token);
        dispatch<RefreshAction>({
          type: ActionTypes.REFRESH,
          payload: user,
        });
        localStorage.setItem("token", response.data.token);
      })
      .catch((err: AxiosError) => {
        if (err.response)
          dispatch<alert.errorAction>(
            alert.error(err.response.data.error, "error")
          );
        else
          dispatch<alert.errorAction>(
            alert.error("An error has occurred", "error")
          );
      });
};

export const logout = (): LogoutAction => {
  localStorage.clear();
  return { type: ActionTypes.SIGN_OUT };
};
