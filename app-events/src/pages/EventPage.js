import { useEffect, useState } from 'react'
import {
  Alert,
  Button,
  Col,
  Container,
  FloatingLabel,
  Form,
  ListGroup,
  Modal,
  Row,
  Spinner,
} from 'react-bootstrap'
import { useLocation } from 'react-router-dom'
import { useStore } from '../store/StoreProvider'
import { getData, postData } from '../utils/fetch'

export default function EventPage() {
  const history = useLocation()
  const [event, setEvent] = useState(undefined)
  const [api, setApi] = useState(false)
  const { loggedInUser } = useStore()
  const [comment, setComment] = useState('')
  const [comments, setComments] = useState([])
  const id = history.pathname.substring(7)
  const [modalShow, setModalShow] = useState(false)
  const [members, setMembers] = useState([])

  useEffect(() => {
    window.scroll(0, 0)
    const id = history.pathname.substring(7)
    getData(`eventRequest/${id}`)
      .then(setEvent)
      .catch(() => setApi(true))
    getData(`commentRequest/${id}`)
      .then(setComments)
      .catch(() => setApi(true))
  }, [history])

  const handleClick = () => {
    const id = history.pathname.substring(7)

    const commentDTO = {
      date: new Date(),
      text: comment,
    }

    postData(`commentRequest/${id}`, commentDTO, loggedInUser.accessToken)
      .then(comment => {
        setComment('')
        getData(`commentRequest/${id}`)
          .then(setComments)
          .catch(() => setApi(true))
      })
      .catch(() => console.log('error'))
  }

  const handleAddMember = () => {
    postData(`eventRequest/add/${id}`, null, loggedInUser.accessToken)
      .then(() => {
        getData(`eventRequest/${id}`)
          .then(setEvent)
          .catch(() => setApi(true))
      })
      .catch(() => console.log('error'))
  }

  const handleMembersView = () => {}

  return (
    <div>
      {api ? (
        <>
          <br />
          <Alert key='alert' variant='danger'>
            No se ha encontrado el evento
          </Alert>
        </>
      ) : (
        <>
          {event && (
            <div className=''>
              <br />
              <Container className='min-vh-100'>
                <Row>
                  <Col xs={12} md={5}>
                    {' '}
                    <img
                      alt='...'
                      src={event.imageUrl}
                      className='img-fluid rounded float-start'
                      width='300'
                      heigth='300'
                    />
                  </Col>
                  <Col xs={12} md={7}>
                    <div>
                      <h3>{event.name}</h3>
                    </div>
                    <p>{event.description}</p>
                    <p>
                      Fecha de inicio:{' '}
                      {event.start.substring(0, 10).replaceAll('-', '/')}
                    </p>
                    <p>
                      Fecha de finalizacion:{' '}
                      {event.end.substring(0, 10).replaceAll('-', '/')}
                    </p>
                    <div>
                      <Button
                        variant='success'
                        onClick={handleAddMember}
                        className='m-2'
                      >
                        Unirme
                      </Button>
                      <Button
                        variant='success'
                        onClick={() => setModalShow(true)}
                      >
                        Miembros
                      </Button>
                      <MyVerticallyCenteredModal
                        show={modalShow}
                        onHide={() => setModalShow(false)}
                      >
                        {event.members?.map(c => (
                          <>
                            <ListGroup.Item className='d-flex align-items-center'>
                              <img
                                src={c.photoUrl}
                                alt='Avatar'
                                className='bg-info rounded-circle'
                                style={{ width: '40px', heigth: '40px' }}
                              />
                              <p className='p-2 text-break m-0'>
                                <span className='fw-bold'>{c.name} </span>
                                <span>{c.email}</span>
                                {c.text}
                              </p>
                            </ListGroup.Item>
                          </>
                        ))}
                      </MyVerticallyCenteredModal>
                    </div>
                  </Col>
                  <Container>
                    <br />
                    <h3>comentarios</h3>
                    <ListGroup variant='flush'>
                      <Form>
                        <FloatingLabel
                          controlId='floatingInput'
                          label='Escribe aqui tu comentario'
                          className='mb-3'
                          onkey
                        >
                          <Form.Control
                            type='text'
                            placeholder='ddd'
                            value={comment}
                            onChange={e => setComment(e.target.value)}
                          />
                        </FloatingLabel>
                        <div class='d-flex flex-row-reverse bd-highlight'>
                          <Button
                            onClick={() => handleClick()}
                            variant='success'
                          >
                            Comentar
                          </Button>
                        </div>
                      </Form>
                      {comments.map(c => (
                        <>
                          <ListGroup.Item className='d-flex align-items-center'>
                            <img
                              src={c.photoUrl}
                              alt='Avatar'
                              className='bg-info rounded-circle'
                              style={{ width: '40px', heigth: '40px' }}
                            />
                            <p className='p-2 text-break m-0'>
                              <span className='fw-bold'>{c.name}: </span>
                              {c.text}
                            </p>
                          </ListGroup.Item>
                        </>
                      ))}
                    </ListGroup>
                  </Container>
                </Row>
              </Container>
              <br />
            </div>
          )}
          {event === undefined && (
            <div className='flex-column vh-100 d-flex justify-content-center align-items-center'>
              <Spinner animation='border' role='status'>
                <span className='visually-hidden'>Loading...</span>
              </Spinner>
            </div>
          )}
        </>
      )}
    </div>
  )
}

function MyVerticallyCenteredModal(props) {
  return (
    <Modal
      {...props}
      size='lg'
      aria-labelledby='contained-modal-title-vcenter'
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id='contained-modal-title-vcenter'>
          Miembros
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>{props.children}</Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>Close</Button>
      </Modal.Footer>
    </Modal>
  )
}
