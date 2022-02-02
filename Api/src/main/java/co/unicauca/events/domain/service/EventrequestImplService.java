package co.unicauca.events.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.unicauca.events.access.dao.IEventRequestDao;
import co.unicauca.events.domain.model.Event;
import co.unicauca.events.domain.model.TokenInfo;
import co.unicauca.events.presentation.rest.exception.EnumErrorCode;
import co.unicauca.events.presentation.rest.exception.EventErrorDomainException;
import co.unicauca.events.presentation.rest.exception.EventRequestError;
import co.unicauca.events.presentation.rest.exception.ResourceNotFoundException;
import co.unicauca.events.presentation.rest.exception.TokenErrorInvalid;

@Service
public class EventrequestImplService implements IEventRequestService {

  @Autowired
  private IEventRequestDao eventRequestDao;

  @Override
  public Map<String, Object> create(Event eventRequest, String token)
      throws EventErrorDomainException, TokenErrorInvalid, FirebaseAuthException {
    List<EventRequestError> errors = validateEventRequest(eventRequest);

    TokenInfo dataUserCheck = VerifyToken.verifyToken(token);

    if (dataUserCheck == null)
      throw new TokenErrorInvalid(token);

    if (!errors.isEmpty())
      throw new EventErrorDomainException(errors);

    Map<String, Object> eventRequestSave = eventRequestDao.save(eventRequest, dataUserCheck);
    if (eventRequestSave != null) {
    }

    return eventRequestSave;
  }

  @Override
  public Event findById(String id) throws ResourceNotFoundException, InterruptedException, ExecutionException {
    Event eventRequest = eventRequestDao.findById(id);
    if (eventRequest == null)
      throw new ResourceNotFoundException(id);

    return eventRequest;
  }

  @Override
  public List<Event> findAll() throws InterruptedException, ExecutionException {
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

  private List<EventRequestError> validateEventRequest(final Event event) {
    List<EventRequestError> errors = new ArrayList<>();
    Map<String, Object> docData = new HashMap<>();

    docData.put("description", event.getDescription());
    docData.put("end", event.getEnd());
    docData.put("location", event.getLocation());
    docData.put("start", event.getStart());
    docData.put("type", event.getType());
    docData.put("name", event.getName());
    docData.put("imageUrl", event.getImageUrl());

    for (Map.Entry<String, Object> entry : docData.entrySet()) {
      if (entry.getValue().equals(""))
        errors.add(new EventRequestError(EnumErrorCode.EMPTY_FIELD, entry.getKey(), "Field empty"));
    }

    return errors;
  }
}
