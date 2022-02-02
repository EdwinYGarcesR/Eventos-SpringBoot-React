package co.unicauca.events.access.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import co.unicauca.events.domain.model.Event;
import co.unicauca.events.domain.model.TokenInfo;
import co.unicauca.events.domain.model.User;

public interface IEventRequestDao {

  public List<Event> findAll() throws InterruptedException, ExecutionException;

  public Event findById(String id) throws InterruptedException, ExecutionException;

  public Map<String, Object> save(Event eventRequest, TokenInfo dataUserCheck);

  public User addMember(TokenInfo dataUserCheck, String id) throws InterruptedException, ExecutionException;
}
