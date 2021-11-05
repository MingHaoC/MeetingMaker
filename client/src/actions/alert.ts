import { ActionTypes } from "./";

export interface alertMessage {
  message: string[] | string;
  alertType: string;
}

// Alert actions interfaces

export interface successAction {
  type: ActionTypes.SUCCESS;
  payload: alertMessage;
}

export interface errorAction {
  type: ActionTypes.ERROR;
  payload: alertMessage;
}

export interface clearAction {
  type: ActionTypes.CLEAR;
}

export const success = (
  message: string[] | string,
  alertType: string
): successAction => {
  return { type: ActionTypes.SUCCESS, payload: { message, alertType } };
};

export const error = (
  message: string[] | string,
  alertType: string
): errorAction => {
  return { type: ActionTypes.ERROR, payload: { message, alertType } };
};

export const clear = (): clearAction => {
  return { type: ActionTypes.CLEAR };
};
