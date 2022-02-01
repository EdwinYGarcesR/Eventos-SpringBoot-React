import { Redirect, Route } from 'react-router'
import { useStore } from '../store/StoreProvider'
import { useLocation } from 'react-router-dom'

export default function PrivateRoute(params) {
  const { loggedInUser } = useStore()
  const location = useLocation()

  if (!loggedInUser)
    return <Redirect to={{ pathname: '/', state: { from: location } }} />

  return <Route {...params} />
}
