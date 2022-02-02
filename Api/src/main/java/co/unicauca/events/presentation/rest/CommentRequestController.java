package co.unicauca.events.presentation.rest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.unicauca.events.domain.model.Comment;
import co.unicauca.events.domain.service.ICommentRequestService;

@RestController
@RequestMapping("commentRequest")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class CommentRequestController {
  @Autowired
  private ICommentRequestService commentRequestService;

  /**
   * Crea un comentario en un evento
   * 
   * @param comment
   * @return Comentario
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws FirebaseAuthException
   */
  @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Map<String, Object>> create(@RequestHeader("Authorization") String token,
      @RequestBody Comment comment, @PathVariable String id)
      throws InterruptedException, ExecutionException, FirebaseAuthException {
    Map<String, Object> response = commentRequestService.create(comment, token, id);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  /**
   * Obtiene los comentarios de un evento
   * 
   * @param id
   * @return Lista de comentarios
   * @throws InterruptedException
   * @throws ExecutionException
   */
  @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Comment>> findAllById(@PathVariable String id)
      throws InterruptedException, ExecutionException {
    List<Comment> response = commentRequestService.findAllById(id);
    return new ResponseEntity<List<Comment>>(response, HttpStatus.OK);
  }
}
