package co.unicauca.events.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Firebase {
  public static String FILE_SERVICE_ACCOUNT_NAME = "src/main/resources/eventos-unicauca-808b2-firebase-adminsdk-jz8s6-6de7fdb3ff.json";

  public static void initializeFirebase() {
    try (
        // Use a service account
        InputStream serviceAccount = new FileInputStream(FILE_SERVICE_ACCOUNT_NAME)) {
      GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(credentials)
          .build();

      if (FirebaseApp.getApps().size() == 0)
        FirebaseApp.initializeApp(options);
      FirebaseApp.getInstance();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
