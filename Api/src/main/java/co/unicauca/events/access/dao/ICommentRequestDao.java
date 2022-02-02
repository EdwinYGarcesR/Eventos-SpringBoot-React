package co.unicauca.events.access.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import co.unicauca.events.domain.model.Comment;
import co.unicauca.events.domain.model.TokenInfo;

public interface ICommentRequestDao {

  public Map<String, Object> save(Comment comment, TokenInfo dataUserCheck, String id);

  public List<Comment> findAllById(String id) throws InterruptedException, ExecutionException;

}
