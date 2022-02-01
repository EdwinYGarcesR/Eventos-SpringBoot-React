package co.unicauca.access.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import co.unicauca.domain.model.Event;

public interface IEventRequestDao {

  public List<Event> findAll() throws InterruptedException, ExecutionException;

  public Event findById(String id);

  public Event save(Event eventRequest);
}
