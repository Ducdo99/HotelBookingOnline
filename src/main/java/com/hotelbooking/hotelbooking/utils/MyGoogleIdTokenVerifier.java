package com.hotelbooking.hotelbooking.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class MyGoogleIdTokenVerifier {
    private final static NetHttpTransport netHttpTransport = new NetHttpTransport();
    private final static GsonFactory gsonFactory = new GsonFactory();
    @Value("${my.client.id}")
    private String clientID; // The OAuth 2.0 Client ID for Android/IOS, Web app,...

    private GoogleIdToken verifyGoogleIdTokenString(String idTokenString)
            throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(netHttpTransport, gsonFactory)
                .setAudience(Collections.singletonList(clientID.trim()))
                .build();
        // GoogleIdTokenVerifier.verify() method verifies JWT string
        return verifier.verify(idTokenString.trim());
    }

    public Payload getPayloadFromIDToken(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdToken idToken = this.verifyGoogleIdTokenString(idTokenString);
        return idToken == null ? null : idToken.getPayload();
    }
}
