import api from "./api";
import { AuthenticationData } from "../actions/";

export const register = (formValue: AuthenticationData) => {
  return api.post("/user/register", formValue);
};

export const login = (formValue: AuthenticationData) => {
  return api.post("/user/login", formValue);
};

export const verifyEmail = (uuid: String) => {
  return api.post("/user/verify/email", { uuid: uuid });
};

export const refresh = (token: string) => {
  return api.post("/refresh", token);
};
