import { useEffect } from 'react'
import { Container } from 'react-bootstrap'
import Footer from '../components/Footer'
import NavBar from '../components/NavBar'
import { auth } from '../firebase/firebase'
import { useDispatch } from '../store/StoreProvider'
import { types } from '../store/StoreReducer'
import './styles.css'

export default function Layout({ children }) {
  const dispatch = useDispatch()

  useEffect(() => {
    const unsuscribe = auth.onAuthStateChanged(authUser => {
      dispatch({ type: types.authLogin, payload: authUser })
    })
    return () => unsuscribe()
  }, [])

  return (
    <div>
      <NavBar />
      <Container>{children}</Container>
      <Footer />
    </div>
  )
}
