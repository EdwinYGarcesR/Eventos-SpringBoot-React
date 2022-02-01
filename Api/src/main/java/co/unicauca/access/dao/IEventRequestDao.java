package co.unicauca.access.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import co.unicauca.domain.model.Event;
import co.unicauca.domain.model.TokenInfo;

public interface IEventRequestDao {

  public List<Event> findAll() throws InterruptedException, ExecutionException;

  public Event findById(String id) throws InterruptedException, ExecutionException;

  public Event save(Event eventRequest, TokenInfo dataUserCheck);
}
