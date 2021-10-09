import { User, ActionTypes, AuthenticationAction } from "../actions";

export interface AuthStateMap {
  user: User | null;
  isSignedIn: boolean;
}

// Inital state of Authreducer store
const initalState: AuthStateMap = {
  user: null,
  isSignedIn: false,
};

export const AuthenticationReducer = (
  state = initalState,
  action: AuthenticationAction
) => {
  switch (action.type) {
    case ActionTypes.REGISTER:
      return { ...state };
    case ActionTypes.SIGN_IN:
      return { ...state, isSignedIn: true, user: action.payload };
    case ActionTypes.SIGN_OUT:
      return { ...state, isSignedIn: false, user: null };
    case ActionTypes.REFRESH:
      return { ...state, isSignedIn: true, user: action.payload };
    default:
      return state;
  }
};
