import { Switch } from 'react-router-dom'

import LoginPage from '../pages/LoginPage'
import HomePage from '../pages/HomePage'
import PrivateRoute from './PrivateRoute'
import PublicRoute from './PublicRoute'
import CreateEventPage from '../pages/CreateEventPage'
import EventPage from '../pages/EventPage'

export default function AppRouter() {
  return (
    <Switch>
      <PublicRoute exact path='/' component={LoginPage} />
      <PrivateRoute exact path='/home' component={HomePage} />
      <PrivateRoute exact path='/create' component={CreateEventPage} />
      <PrivateRoute exact path='/event/:id' component={EventPage} />
    </Switch>
  )
}
