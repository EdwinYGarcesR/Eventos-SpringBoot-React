import { Col, Container, Row } from 'react-bootstrap'

export default function Footer() {
  return (
    <div className='p-3 bg-secondary text-white'>
      <Container>
        <Row>
          <Col className='d-flex justify-content-center'>Climaco fernando</Col>
          <Col className='d-flex justify-content-center'>
            Developer en react
          </Col>
          <Col className='d-flex justify-content-center'>
            Universidad del cauca
          </Col>
        </Row>
        <Row>
          <Col className='d-flex justify-content-center'>
            Visita nuestras redes sociales
          </Col>
          <Col className='d-flex justify-content-center'>facebook</Col>
          <Col className='d-flex justify-content-center'>feedback</Col>
        </Row>
      </Container>
    </div>
  )
}
