import { configureStore } from "@reduxjs/toolkit";

import authReducer from "./slices/authSlice";
import messageReducer from "./slices/message";

const reducer =  {
  auth: authReducer,
  message: messageReducer,
};

console.log(reducer);

export const store = configureStore({
  reducer,
  devTools: true,
});
