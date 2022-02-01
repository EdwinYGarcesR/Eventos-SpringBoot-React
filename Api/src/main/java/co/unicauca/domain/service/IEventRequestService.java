package co.unicauca.domain.service;

import java.util.List;

import co.unicauca.domain.model.Event;

/**
 * Interfaz de implementacion de metodos de CRUD para la clase Event
 */
public interface IEventRequestService {
  /**
   * Crear un evento
   * 
   * @param eventRequest Event
   * @return Event creado
   */
  public Event create(Event eventRequest);

  /**
   * Encontrar un evento por su id
   * 
   * @param idRequest Id del evento
   * @return Event enocntrado
   */
  public Event findById(String idRequest);

  /**
   * Listar los eventos disponibles
   * 
   * @return List<Event> Event disponibles
   */
  public List<Event> findAll();

  /**
   * Actualizar un evento
   * 
   * @return Event editado
   */
  public Event update();

  /**
   * Eliminar un evento
   * 
   * @return Event eliminado
   */
  public Event delete();
}
