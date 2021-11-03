import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import jwtDecode from "jwt-decode";
import { store } from "../index";
import { ActionTypes } from "../actions";
import {
  AuthenicationInfo,
  RefreshAction,
  User,
} from "../actions/authentication";
import * as userServices from "../services";
import history from "../history";

type CallbackFunction = () => void;
let isRefreshing = true;
let subscribers: CallbackFunction[] = [];

const token = localStorage.getItem("token");

const request = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Authorization: `${token ? "bear " + token : null}`,
  },
});

// copy the old request but with updated token
function newRequest(
  originalRequest: AxiosRequestConfig
): Promise<AxiosRequestConfig> {
  originalRequest.headers["Authorization"] =
    "Bearer " + localStorage.getItem("token");
  return axios.request(originalRequest);
}

// Refresh the token and dispatch an action to autheication reducer
// to update the stored information in the state
const refreshTokenRequest = () => {
  const token = localStorage.getItem("token");
  if (token)
    userServices.refresh(token).then((response: AxiosResponse<string>) => {
      const user = jwtDecode<User>(response.data);
      store.dispatch<RefreshAction>({
        type: ActionTypes.REFRESH,
        payload: user,
      });
      localStorage.setItem("token", response.data);
      onAccessTokenFetched();
      isRefreshing = true;
    });
};

// Loop through array to excute all callback
function onAccessTokenFetched() {
  subscribers.forEach((callback: CallbackFunction) => {
    callback();
  });
  subscribers = [];
}

// Response interceptor to check for error
request.interceptors.response.use(
  (response) => response,
  (error: AxiosResponse<AxiosError>) => {
    const originalRequest = error.config;
    if (error.request.status === 401) {
      if (isRefreshing) refreshTokenRequest();
      isRefreshing = false;
      if (originalRequest.url !== "/refresh") {
        const retryOriginalRequest = new Promise((resolve) => {
          subscribers.push(() => {
            resolve(newRequest(originalRequest));
          });
        });
        return retryOriginalRequest;
      } else Promise.resolve();
    } else if (error.request.status === 403) {
      subscribers = [];
      localStorage.removeItem("token");
      history.push("/login");
      return Promise.reject(error);
    }
    return Promise.reject(error);
  }
);

export default request;
