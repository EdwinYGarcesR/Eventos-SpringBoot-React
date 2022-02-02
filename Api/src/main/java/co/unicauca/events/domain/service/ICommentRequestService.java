package co.unicauca.events.domain.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuthException;

import co.unicauca.events.domain.model.Comment;

public interface ICommentRequestService {

  public Map<String, Object> create(Comment comment, String token, String id) throws FirebaseAuthException;

  public List<Comment> findAllById(String id) throws InterruptedException, ExecutionException;
}
