package co.unicauca.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.unicauca.access.dao.IEventRequestDao;
import co.unicauca.domain.model.Event;
import co.unicauca.presentation.rest.exception.EventErrorDomainException;
import co.unicauca.presentation.rest.exception.EventRequestError;
import co.unicauca.presentation.rest.exception.ResourceNotFoundException;

@Service
public class EventrequestImplService implements IEventRequestService {

  @Autowired
  private IEventRequestDao eventRequestDao;

  @Override
  public Event create(Event eventRequest) throws EventErrorDomainException {
    List<EventRequestError> errors = validateEventRequest(eventRequest);

    if (!errors.isEmpty())
      throw new EventErrorDomainException(errors);

    Event eventRequestSave = eventRequestDao.save(eventRequest);
    if (eventRequestSave != null) {
    }

    return eventRequestSave;
  }

  @Override
  public Event findById(String id) throws ResourceNotFoundException {
    Event eventRequest = eventRequestDao.findById(id);
    if (eventRequest == null)
      throw new ResourceNotFoundException(id);

    return eventRequest;
  }

  @Override
  public List<Event> findAll() {
    return (List<Event>) eventRequestDao.findAll();
  }

  @Override
  public Event update() {
    return null;
  }

  @Override
  public Event delete() {
    return null;
  }

  private List<EventRequestError> validateEventRequest(final Event eventRequest) {
    List<EventRequestError> errors = new ArrayList<>();
    Date currentDate = new Date();
    return errors;
  }
}
