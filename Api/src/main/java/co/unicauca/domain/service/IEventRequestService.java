package co.unicauca.domain.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import co.unicauca.domain.model.Event;
import co.unicauca.presentation.rest.exception.EventErrorDomainException;
import co.unicauca.presentation.rest.exception.ResourceNotFoundException;

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
  public Event create(Event eventRequest) throws EventErrorDomainException;

  /**
   * Encontrar un evento por su id
   * 
   * @param idRequest Id del evento
   * @return Event enocntrado
   */
  public Event findById(String idRequest) throws ResourceNotFoundException;

  /**
   * Listar los eventos disponibles
   * 
   * @return List<Event> Event disponibles
   * @throws ExecutionException
   * @throws InterruptedException
   */
  public List<Event> findAll() throws InterruptedException, ExecutionException;

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
