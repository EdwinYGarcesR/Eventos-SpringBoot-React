package co.unicauca.access.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.subst.Token;
import co.unicauca.domain.model.Event;
import co.unicauca.domain.model.TokenInfo;
import co.unicauca.domain.model.User;

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
  public Event save(Event event, TokenInfo dataUserCheck) {
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

    for (Map.Entry<String, Object> entry : docData.entrySet()) {
      if (entry.getValue().equals(""))
        return null;
    }

    User owner = event.getOwner();
    owner.setUid(uid);
    owner.setName(name);
    owner.setPhotoUrl(photoUrl);

    docData.put("owner", owner);
    docData.put("score", 0);

    db.collection("events").add(docData);

    return (Event) docData;
  }
}
