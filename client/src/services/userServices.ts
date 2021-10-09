import api from "./api";
import { FormData } from "../actions/";

export const register = (formValue: FormData) => {
  return api.post("/register", formValue);
};

export const login = (formValue: FormData) => {
  return api.post("/login", formValue);
};

export const refresh = (token: string) => {
  return api.post("/refresh", token);
};
