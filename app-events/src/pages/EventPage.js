import { useEffect, useState } from 'react'
import {
  Alert,
  Button,
  Col,
  Container,
  FloatingLabel,
  Form,
  ListGroup,
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

  useEffect(() => {
    window.scroll(0, 0)
    const id = history.pathname.substring(7)
    getData(`event/${id}`)
      .then(setEvent)
      .catch(() => setApi(true))
  }, [history])

  const handleClick = () => {
    const id = history.pathname.substring(7)

    const commentDTO = {
      nameOwner: loggedInUser.displayName,
      photoUrl: loggedInUser.photoURL,
      idEvent: id,
      comment,
    }

    postData('event/comment', commentDTO)
      .then(() => {
        setComment('')
        getData(`event/${id}`)
          .then(setEvent)
          .catch(() => setApi(true))
      })
      .catch(() => console.log('error'))
  }

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
                    />
                  </Col>
                  <Col xs={12} md={7}>
                    <div>
                      <h3>{event.summary}</h3>
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
                          <Button onClick={() => handleClick()} variant='success'>
                            Comentar
                          </Button>
                        </div>
                      </Form>
                      {event.comments.map(c => (
                        <>
                          <ListGroup.Item className='d-flex align-items-center'>
                            <img
                              src={c.photoUrl}
                              alt='Avatar'
                              className='bg-info rounded-circle'
                              style={{ width: '40px', heigth: '40px' }}
                            />
                            <p className='p-2 text-break m-0'>
                              <span className='fw-bold'>{c.nameOwner}: </span>
                              {c.comment}
                            </p>
                          </ListGroup.Item>
                        </>
                      ))}
                      <ListGroup.Item>Dapibus ac facilisis in</ListGroup.Item>
                      <ListGroup.Item>Morbi leo risus</ListGroup.Item>
                      <ListGroup.Item>Porta ac consectetur ac</ListGroup.Item>
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