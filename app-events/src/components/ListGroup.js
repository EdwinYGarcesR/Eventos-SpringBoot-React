import { Badge, ListGroup } from 'react-bootstrap'
import { Link } from 'react-router-dom'

export default function ListGroupCustum({ events }) {
  return (
    <ListGroup as='ol' numbered className='min-vh-100'>
      {events.map(event => (
        <ListGroup.Item
          as='li'
          className='d-flex justify-content-between align-items-start'
          key={event.id}
        >
          <div className='ms-2 me-auto'>
            <div className='fw-bold'>
              <Link
                className='fw-bold text-decoration-none'
                to={`/event/${event.id}`}
              >
                {event.name}
              </Link>
            </div>
            <p>{event.description.substring(0, 300)} . . .</p>

            <p>
              Fecha de inicio:{' '}
              {event.start.substring(0, 10).replaceAll('-', '/')}
            </p>
            <p>
              Fecha de finalizacion:{' '}
              {event.end.substring(0, 10).replaceAll('-', '/')}
            </p>
          </div>
          <Badge variant='primary' pill>
            {event.score}
          </Badge>
        </ListGroup.Item>
      ))}
    </ListGroup>
  )
}
