package co.unicauca.events.domain.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuthException;

import co.unicauca.events.domain.model.Event;
import co.unicauca.events.presentation.rest.exception.EventErrorDomainException;
import co.unicauca.events.presentation.rest.exception.ResourceNotFoundException;
import co.unicauca.events.presentation.rest.exception.TokenErrorInvalid;

/**
 * Interfaz de implementacion de metodos de CRUD para la clase Event
 */
public interface IEventRequestService {
  /**
   * Crear un evento
   * 
   * @param eventRequest Event
   * @return Event creado
   * @throws TokenErrorInvalid
   * @throws FirebaseAuthException
   */
  public Map<String, Object> create(Event eventRequest, String token) throws EventErrorDomainException, TokenErrorInvalid, FirebaseAuthException;

  /**
   * Encontrar un evento por su id
   * 
   * @param idRequest Id del evento
   * @return Event enocntrado
   * @throws ExecutionException
   * @throws InterruptedException
   */
  public Event findById(String idRequest) throws ResourceNotFoundException, InterruptedException, ExecutionException;

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
