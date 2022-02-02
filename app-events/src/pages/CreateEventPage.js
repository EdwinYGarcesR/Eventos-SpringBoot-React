import 'react-datepicker/dist/react-datepicker.css'
import { Alert, Button, Form } from 'react-bootstrap'
import { useHistory } from 'react-router-dom'
import { useState } from 'react'
import { useStore } from '../store/StoreProvider'
import DatePicker, { registerLocale } from 'react-datepicker'
import es from 'date-fns/locale/es'
import { postData } from '../utils/fetch'
registerLocale('es', es)

export default function CreateEventPage() {
  const [api, setApi] = useState(undefined)
  const [description, setDescription] = useState('')
  const [endDate, setEndDate] = useState(new Date())
  const [location, setLocation] = useState('')
  const [startDate, setStartDate] = useState(new Date())
  const [summary, setSummary] = useState('')
  const [url, setUrl] = useState('')
  const { loggedInUser } = useStore()
  const history = useHistory()

  console.log(loggedInUser.accessToken)

  const gapi = window.gapi
  const CLIENT_ID = process.env.REACT_APP_CLIENT_ID
  const API_KEY = process.env.REACT_APP_API_KEY
  const DISCOVERY_DOCS = [
    'https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest',
  ]
  const SCOPES = 'https://www.googleapis.com/auth/calendar.events'

  const onSubmit = event => {
    event.preventDefault()

    gapi.load('client:auth2', () => {
      gapi.client
        .init({
          apiKey: API_KEY,
          clientId: CLIENT_ID,
          discoveryDocs: DISCOVERY_DOCS,
          scope: SCOPES,
        })
        .then(() => {
          gapi.auth2
            .getAuthInstance()
            .signIn()
            .then(() => {
              const event = {
                summary: summary,
                description: description,
                start: {
                  dateTime: startDate,
                  timeZone: 'America/Bogota',
                },
                end: {
                  dateTime: endDate,
                  timeZone: 'America/Bogota',
                },
              }

              const request = gapi.client.calendar.events.insert({
                calendarId: 'primary',
                resource: event,
              })

              request.execute(function (event) {
                setUrl(event.htmlLink)
                // window.open(event.htmlLink)
              })

              const eventDTO = {
                description,
                end: endDate,
                location,
                start: startDate,
                name: summary,
                url,
                imageUrl:
                  'https://www.unicauca.edu.co/gico/themes/dptogrupo/resources/img/blue.png',
                eventType: 'academico',
                type: 'academico',
              }

              postData('eventRequest', eventDTO, loggedInUser.accessToken)
                .then(() => {
                  setApi(true)
                  setTimeout(() => {
                    history.push('/home')
                  }, 2000)
                })
                .catch(() => setApi(false))
            })
        })
    })
  }

  const obSubmit2 = event => {
    event.preventDefault()
    const eventDTO = {
      description,
      end: endDate,
      location,
      start: startDate,
      name: summary,
      url,
      imageUrl:
        'https://www.unicauca.edu.co/gico/themes/dptogrupo/resources/img/blue.png',
      eventType: 'academico',
      type: 'academico',
    }

    postData('eventRequest', eventDTO, loggedInUser.accessToken)
      .then(() => {
        setApi(true)
        setTimeout(() => {
          history.push('/home')
        }, 2000)
      })
      .catch(() => setApi(false))
  }

  return (
    <div className='vh-100'>
      <div className='d-flex justify-content-center p-5'>
        <h3>Crear un evento</h3>
      </div>
      <Form onSubmit={obSubmit2}>
        <Form.Group className='mb-3'>
          <Form.Label>Nombre del evento</Form.Label>
          <Form.Control
            type='text'
            placeholder='Nombre del evento'
            onChange={e => setSummary(e.target.value)}
          />
        </Form.Group>
        <Form.Group className='mb-3'>
          <Form.Label>Descripcion del evento</Form.Label>
          <Form.Control
            as='textarea'
            type='text'
            placeholder='Descripcion del evento'
            onChange={e => setDescription(e.target.value)}
          />
        </Form.Group>
        <Form.Group className='mb-3'>
          <Form.Label>Lugar donde se llevara acabo el evento</Form.Label>
          <Form.Control
            type='text'
            placeholder='Lugar'
            onChange={e => setLocation(e.target.value)}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Fecha de inicio del evento</Form.Label>
          <DatePicker
            dateFormat='Pp'
            locale='es'
            onChange={date => setStartDate(date)}
            selected={startDate}
            showTimeSelect
          />
        </Form.Group>
        <br />
        <Form.Group>
          <Form.Label>Fecha de finalizacion del evento</Form.Label>
          <DatePicker
            dateFormat='Pp'
            locale='es'
            onChange={date => setEndDate(date)}
            selected={endDate}
            showTimeSelect
          />
        </Form.Group>
        <br />
        <Button constiant='primary' type='submit'>
          Submit
        </Button>
        <br />
        {api === true && (
          <Alert key='success' variant='success'>
            Evento creado con exito
          </Alert>
        )}
        {api === false && (
          <Alert key='alert' variant='danger'>
            La API de spring boot se encuentra desconectada
          </Alert>
        )}
      </Form>
    </div>
  )
}
