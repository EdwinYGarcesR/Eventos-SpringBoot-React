import { signInWithPopup } from 'firebase/auth'
import { Button, Spinner } from 'react-bootstrap'
import { useLocation } from 'react-router-dom'
import { useHistory } from 'react-router-dom'
import { provider, auth } from '../firebase/firebase'
import { useStore } from '../store/StoreProvider'
import './styles.css'

const Login = () => {
  const history = useHistory()
  const location = useLocation()
  const previusObjectURL = location.state?.from

  return (
    <div className='login-page'>
      <div className='flex-column vh-100 d-flex justify-content-center align-items-center'>
        <h1 className='logo display-1 fw-bold text-center p-5'>
          Eventos Unicauca
        </h1>
        <Button
          onClick={() =>
            signInWithPopup(auth, provider).then(() =>
              history.push(previusObjectURL || '/home')
            )
          }
        >
          Iniciar sesion con Google
        </Button>
        <div className='fw-normal text-center lh-sm p-5'>
          <p>
            Hola mundo, Una aplicacion web construida para la materia Ing de
            software 2
          </p>
        </div>
      </div>
    </div>
  )
}

export default function LoginPage() {
  const { loggedInUser } = useStore()

  return (
    <>
      {loggedInUser === null && Login()}
      {loggedInUser === undefined && (
        <div className='flex-column vh-100 d-flex justify-content-center align-items-center'>
          <Spinner animation='border' role='status'>
            <span className='visually-hidden'>Loading...</span>
          </Spinner>
        </div>
      )}
    </>
  )
}
