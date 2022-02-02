package co.unicauca.events.access.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import co.unicauca.events.domain.model.Comment;
import co.unicauca.events.domain.model.TokenInfo;

@Service
public class CommentRequestDaoImpl implements ICommentRequestDao {

  private final Firestore db;

  CommentRequestDaoImpl() {
    db = FirestoreClient.getFirestore();
  }

  @Override
  public Map<String, Object> save(Comment comment, TokenInfo dataUserCheck, String id) {
    String uid = dataUserCheck.getUid();
    String name = dataUserCheck.getName();
    String photoUrl = dataUserCheck.getPhotoUrl();

    Map<String, Object> docData = new HashMap<>();

    docData.put("date", comment.getDate());
    docData.put("text", comment.getText());

    for (Map.Entry<String, Object> entry : docData.entrySet()) {
      if (entry.getValue().equals(""))
        return null;
    }

    docData.put("name", name);
    docData.put("photoUrl", photoUrl);
    docData.put("uid", uid);
    docData.put("id", id);

    db.collection("comments").add(docData);

    docData.replace("uid", null);

    return docData;
  }

  @Override
  public List<Comment> findAllById(String id) throws InterruptedException, ExecutionException {
    List<Comment> comments = new ArrayList<>();

    ApiFuture<QuerySnapshot> future = db.collection("comments").get();
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();

    for (DocumentSnapshot document : documents) {
      Comment comment = document.toObject(Comment.class);
      comment.setId(document.getId());
      comment.setUid(null);
      comments.add(comment);
    }

    return comments;
  }
}
