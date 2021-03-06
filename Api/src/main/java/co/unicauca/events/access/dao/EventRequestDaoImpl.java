package co.unicauca.events.access.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import co.unicauca.events.domain.model.Event;
import co.unicauca.events.domain.model.TokenInfo;
import co.unicauca.events.domain.model.User;

@Service
public class EventRequestDaoImpl implements IEventRequestDao {

  private final Firestore db;

  EventRequestDaoImpl() {
    db = FirestoreClient.getFirestore();
  }

  @Override
  public List<Event> findAll() throws InterruptedException, ExecutionException {
    List<Event> events = new ArrayList<>();

    ApiFuture<QuerySnapshot> future = db.collection("events").get();
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();

    for (DocumentSnapshot document : documents) {
      Event event = document.toObject(Event.class);
      event.setId(document.getId());
      event.getOwner().setUid(null);
      events.add(event);
    }

    return events;
  }

  @Override
  public Event findById(String id) throws InterruptedException, ExecutionException {
    DocumentReference docRef = db.collection("events").document(id);
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document = future.get();

    if (document.exists()) {
      Event event = document.toObject(Event.class);
      event.setId(document.getId());
      event.getOwner().setUid(null);
      return event;
    } else {
      return null;
    }
  }

  @Override
  public Map<String, Object> save(Event event, TokenInfo dataUserCheck) {
    Map<String, Object> docData = new HashMap<>();

    String uid = dataUserCheck.getUid();
    String name = dataUserCheck.getName();
    String photoUrl = dataUserCheck.getPhotoUrl();

    docData.put("description", event.getDescription());
    docData.put("end", event.getEnd());
    docData.put("location", event.getLocation());
    docData.put("start", event.getStart());
    docData.put("type", event.getType());
    docData.put("name", event.getName());
    docData.put("imageUrl", event.getImageUrl());
    docData.put("score", 0);

    User owner = new User();
    owner.setUid(uid);
    owner.setName(name);
    owner.setPhotoUrl(photoUrl);
    docData.put("owner", owner);

    db.collection("events").add(docData);

    return docData;
  }

  @Override
  public User addMember(TokenInfo dataUserCheck, String id) throws InterruptedException, ExecutionException {
    DocumentReference eventRef = db.collection("events").document(id);
    Map<String, Object> docData = new HashMap<>();

    String uid = dataUserCheck.getUid();
    String name = dataUserCheck.getName();
    String photoUrl = dataUserCheck.getPhotoUrl();
    String email = dataUserCheck.getEmail();
    docData.put("uid", uid);
    docData.put("name", name);
    docData.put("photoUrl", photoUrl);
    docData.put("email", email);

    ApiFuture<WriteResult> arrayUnion = eventRef.update("members",
        FieldValue.arrayUnion(docData));

    User owner = new User();
    owner.setUid(uid);
    owner.setName(name);
    owner.setPhotoUrl(photoUrl);
    owner.setEmail(email);

    return owner;
  }
}
