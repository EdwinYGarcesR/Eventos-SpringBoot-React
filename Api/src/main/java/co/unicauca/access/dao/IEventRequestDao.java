package co.unicauca.access.dao;

import java.util.List;

import co.unicauca.domain.model.Event;

public interface IEventRequestDao {

  public List<Event> findAll();

  public Event findById(String id);

  public Event save(Event eventRequest);
}
