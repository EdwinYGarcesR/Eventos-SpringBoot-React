import { Route } from 'react-router'
import { useLocation } from 'react-router-dom'
import { useHistory } from 'react-router-dom'
import { useStore } from '../store/StoreProvider'

export default function PublicRoute(params) {
  const { loggedInUser } = useStore()
  const history = useHistory()
  const location = useLocation()
  const previusObjectURL = location.state?.from

  if (loggedInUser) history.push(previusObjectURL || '/home')

  return <Route {...params} />
}
