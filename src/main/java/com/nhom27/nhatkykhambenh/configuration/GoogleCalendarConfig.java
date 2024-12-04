package com.nhom27.nhatkykhambenh.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GoogleCalendarConfig {
    private static final String APPLICATION_NAME = "Spring MVC Google Calendar Integration";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

//    public static Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
//        InputStreamReader clientSecrets = new InputStreamReader(
//                Objects.requireNonNull(GoogleCalendarConfig.class.getResourceAsStream("/credentials.json")));
//
//        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecrets);
//
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                httpTransport, JSON_FACTORY, secrets, SCOPES)
//                .setAccessType("offline")
//                .build();
//
//        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//    }

//    public static Calendar getCalendarService() throws IOException {
//        final NetHttpTransport httpTransport = new NetHttpTransport();
//        return new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }

    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    public static Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        InputStreamReader clientSecrets = new InputStreamReader(
                Objects.requireNonNull(GoogleCalendarConfig.class.getResourceAsStream("/credentials.json")));

        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecrets);
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static Calendar getCalendarService() throws IOException {
        InputStream serviceAccountStream = Objects.requireNonNull(
                GoogleCalendarConfig.class.getResourceAsStream("/credentials.json"));

        GoogleCredential credential = GoogleCredential.fromStream(serviceAccountStream)
                .createScoped(Collections.singleton(CalendarScopes.CALENDAR));

        return new Calendar.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
