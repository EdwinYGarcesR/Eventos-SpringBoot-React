import {
  Container,
  Form,
  FormControl,
  Nav,
  Navbar,
  Button,
  NavDropdown,
} from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { auth } from '../firebase/firebase'
import { useStore } from '../store/StoreProvider'
import { signOut } from 'firebase/auth'
import './styles.css'

export default function NavBar() {
  const { loggedInUser } = useStore()

  return (
    <div className='sticky-top'>
      {loggedInUser && (
        <Navbar expand='lg' style={{ backgroundColor: '#e3f2fd' }}>
          <Container>
            <Navbar.Brand className='logo'>Eventos Unicauca</Navbar.Brand>
            <Navbar.Toggle aria-controls='navbarScroll' />
            <Navbar.Collapse id='navbarScroll'>
              <Nav
                className='me-auto my-2 my-lg-0'
                style={{ maxHeight: '290px' }}
                navbarScroll
              >
                <Nav.Item className='link'>
                  <Link to='/home' className='text-decoration-none text-muted'>Inicio</Link>
                </Nav.Item>
                <Nav.Item className='link'>
                  <Link to='/create' className='text-decoration-none text-muted'>Crear Evento</Link>
                </Nav.Item>
                <Nav.Item className='link'>
                  <Link to='/create' className='text-decoration-none text-muted'>Calendario</Link>
                </Nav.Item>
                <NavDropdown title='Opciones' id='navbarScrollingDropdown'>
                  <NavDropdown.Item className='d-flex justify-content-center'>
                    <Link className='link-secondary' to='/create'>
                      Cuenta
                    </Link>
                  </NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item className='d-flex justify-content-center'>
                    <Button onClick={() => signOut(auth)} variant='danger'>
                      Cerrar Sesion
                    </Button>
                  </NavDropdown.Item>
                </NavDropdown>
              </Nav>
              <Form className='d-flex'>
                <FormControl
                  type='search'
                  placeholder='Search'
                  className='me-2'
                  aria-label='Search'
                />
                <Button variant='outline-success'>Search</Button>
              </Form>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      )}
    </div>
  )
}
