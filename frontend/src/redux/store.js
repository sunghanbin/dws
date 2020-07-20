import { createStore } from "redux";
import * as actions from "./actions/index"

const initialState = {
  pagecount: 0
}

const reducer = (state = initialState, action) => {

  switch (action.type) {
    case actions.CHANGE:
      return {
        ...state,
        pagecount: action.pagecount
      };
    default:
      return state;
  }

};

const store = createStore(reducer);


export default store;
