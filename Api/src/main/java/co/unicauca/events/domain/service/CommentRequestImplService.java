package co.unicauca.events.domain.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.unicauca.events.access.dao.ICommentRequestDao;
import co.unicauca.events.domain.model.Comment;
import co.unicauca.events.domain.model.TokenInfo;

@Service
public class CommentRequestImplService implements ICommentRequestService {

  @Autowired
  private ICommentRequestDao commentRequesDao;

  @Override
  public Map<String, Object> create(Comment comment, String token, String id) throws FirebaseAuthException {
    TokenInfo dataUserCheck = VerifyToken.verifyToken(token);
    Map<String, Object> savedComment = commentRequesDao.save(comment, dataUserCheck, id);
    return savedComment;
  }

  @Override
  public List<Comment> findAllById(String id) throws InterruptedException, ExecutionException {
    List<Comment> commentRequest = commentRequesDao.findAllById(id);
    return commentRequest;
  }
}
