package co.unicauca.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.unicauca.domain.model.Event;
import co.unicauca.domain.service.IEventRequestService;
import co.unicauca.presentation.rest.exception.EventErrorDomainException;
import co.unicauca.presentation.rest.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("eventRequest")
@CrossOrigin(origins = { "http://localhost:3000" })
public class EventRequestController {
  @Autowired
  private IEventRequestService eventRequestService;

  /**
   * Obtiene todos los eventos
   * 
   * @return Lista con todos los eventos
   */
  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public HttpEntity<List<Event>> findAll() {
    List<Event> response = eventRequestService.findAll();
    return new ResponseEntity<List<Event>>(response, HttpStatus.OK);
  }

  /**
   * Ontiene un evento por su id
   * 
   * @param id
   * @return Evento
   */
  @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public HttpEntity<Event> findById(@PathVariable String id) throws ResourceNotFoundException {
    Event response = eventRequestService.findById(id);
    return new ResponseEntity<Event>(response, HttpStatus.OK);
  }

  /**
   * Crear un evento, requiere token de autentificacion del cliente
   * 
   * @param token
   * @param event
   * @return Evento
   * @throws EventErrorDomainException
   */
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public HttpEntity<Event> create(@RequestHeader("Authorization") String token, @RequestBody Event event)
      throws EventErrorDomainException {
    Event response = eventRequestService.create(event);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
