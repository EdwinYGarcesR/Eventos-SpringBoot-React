import { useEffect, useState } from 'react'
import { Alert } from 'react-bootstrap'
import ListGroupCustum from '../components/ListGroup'
import { getData } from '../utils/fetch'

export default function HomePage() {
  const [events, setEvents] = useState([])
  const [api, setApi] = useState(false)

  useEffect(() => {
    getData('eventRequest')
      .then(setEvents)
      .catch(() => setApi(true))
  }, [])

  return (
    <div className=''>
      <div className='d-flex justify-content-center p-5'>
        <h3>Eventos que podrian interesarte</h3>
      </div>
      <ListGroupCustum events={events} />
      <br />
      {api && (
        <Alert key='alert' variant='danger'>
          La API de spring boot se encuentra desconectada
        </Alert>
      )}
    </div>
  )
}
