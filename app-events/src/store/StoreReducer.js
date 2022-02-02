const types = {
  authLogin: 'SET_LOGIN',
}

const initialStore = {
  loggedInUser: undefined,
}

function storeReducer(state, action) {
  switch (action.type) {
    case types.authLogin:
      return {
        ...state,
        loggedInUser: action.payload,
      }
    default:
      return state
  }
}

export { initialStore, types }
export default storeReducer
