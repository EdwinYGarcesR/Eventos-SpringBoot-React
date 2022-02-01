package co.unicauca.domain.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import co.unicauca.domain.model.TokenInfo;

public class VerifyToken {
  public static TokenInfo verifyToken(String token) throws FirebaseAuthException {
    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
    TokenInfo tokenInfo = new TokenInfo();

    tokenInfo.setEmail(decodedToken.getEmail());
    tokenInfo.setName(decodedToken.getName());
    tokenInfo.setUid(decodedToken.getUid());
    tokenInfo.setPhotoUrl(decodedToken.getPicture());

    return tokenInfo;
  }
}
