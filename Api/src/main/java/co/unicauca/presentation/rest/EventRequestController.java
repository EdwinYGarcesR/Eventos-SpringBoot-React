package co.unicauca.presentation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.unicauca.domain.model.Event;
import co.unicauca.domain.service.IEventRequestService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("eventRequest")
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
}
