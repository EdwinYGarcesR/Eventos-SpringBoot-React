import { createContext, useContext, useEffect, useReducer } from 'react'
import { auth } from '../firebase/firebase'
import storeReducer, { initialStore, types } from './StoreReducer'

const StoreContext = createContext()

function StoreProvider({ children }) {
  const [store, dispatch] = useReducer(storeReducer, initialStore)

  /**
   * Agente de escucha que mantiene la sesion activa.
   */

  return (
    <StoreContext.Provider value={[store, dispatch]}>
      {children}
    </StoreContext.Provider>
  )
}

const useStore = () => useContext(StoreContext)[0]
const useDispatch = () => useContext(StoreContext)[1]

export { useStore, useDispatch }
export default StoreProvider
