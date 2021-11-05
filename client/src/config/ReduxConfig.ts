// configureStore.js
import { applyMiddleware, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import reduxThunk from "redux-thunk";

import reducers from "../reducers";

const persistConfig = {
  key: "root",
  storage,
};

export const configureRedux = () => {
  const persistedReducer = persistReducer(persistConfig, reducers);

  const store = createStore(
    persistedReducer,
    composeWithDevTools(applyMiddleware(reduxThunk))
  );

  const persistor = persistStore(store);

  return { store, persistor };
};
