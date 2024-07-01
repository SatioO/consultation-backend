package com.accion.consultation.controllers;

import com.accion.consultation.models.dto.consultation.CreateSessionRequestDTO;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.auth.Credentials;
import com.google.auth.oauth2.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1/consultation")
public class ConsultationController {
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections
        .singletonList("https://www.googleapis.com/auth/meetings.space.created");
    private static final String CREDENTIALS_FILE_PATH = "/machine-8d649-5c6acd4f298d.json";
    private static final String USER = "default";
    private final WebClient webClient;

    public ConsultationController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public ResponseEntity<Void> getPosts() {
        Flux<String> response = webClient.get().uri("/posts").retrieve().bodyToFlux(String.class);
        response.subscribe(result -> System.out.println(result));
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(path = "/session")
    public ResponseEntity<Credentials> createSession(@RequestBody CreateSessionRequestDTO body) throws IOException {
        List<String> scopes = List.of("https://www.googleapis.com/auth/meetings.space.created", "https://www.googleapis.com/auth/calendar",
            "https://www.googleapis.com/auth/calendar.events");
        InputStream serviceAccount = Objects.requireNonNull(ConsultationController.class.getResourceAsStream(CREDENTIALS_FILE_PATH));

        GoogleCredentials credentials = GoogleCredentials
            .fromStream(serviceAccount)
            .createScoped(scopes);

        credentials.refreshIfExpired();
        System.out.println(credentials.getAccessToken());

//        System.out.println(credentials.getRequestMetadata());
//        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
//        ServiceAccountCredentials.newBuilder().setServiceAccountUser(USER).setPrivateKey(credentials).build();
        return ResponseEntity.ok().body(null);
    }

    private ResponseEntity<Credentials> outhProcess() throws Exception {
        try(InputStream in = ConsultationController.class.getResourceAsStream(CREDENTIALS_FILE_PATH)) {
            if(in == null) {
                throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
            }

            ClientId clientId = ClientId.fromStream(in);
            System.out.println("client_id: " + clientId.getClientId());
            URI callbackUri = null;
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().build();
            try {
                callbackUri = URI.create(receiver.getRedirectUri());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserAuthorizer authorizer = UserAuthorizer.newBuilder().setClientId(clientId)
                .setCallbackUri(callbackUri)
                .setScopes(SCOPES).setPKCEProvider(new DefaultPKCEProvider() {
                    // Temporary fix for https://github.com/googleapis/google-auth-library-java/issues/1373
                    @Override
                    public String getCodeChallenge() {
                        return super.getCodeChallenge().split("=")[0];
                    }
                })
                .setTokenStore(TOKEN_STORE)
                .build();

            Credentials credentials = authorizer.getCredentials(USER);
            if (credentials != null) {
                System.out.println(credentials);
                return ResponseEntity.ok().body(credentials);
            } else {
                URL authorizationUrl = authorizer.getAuthorizationUrl(USER, "", null);
                if (Desktop.isDesktopSupported() &&
                    Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(authorizationUrl.toURI());
                } else {
                    System.out.printf("Open the following URL to authorize access: %s\n",
                        authorizationUrl.toExternalForm());
                }

                String code = receiver.waitForCode();
                credentials = authorizer.getAndStoreCredentialsFromCode(USER, code, callbackUri);
                System.out.println(credentials);
                return ResponseEntity.ok().body(credentials);
            }
        }
    }

    private static final TokenStore TOKEN_STORE = new TokenStore() {
        private Path pathFor(String id) {
            return Paths.get(".", TOKENS_DIRECTORY_PATH, id + ".json");
        }

        @Override
        public String load(String id) throws IOException {
            if (!Files.exists(pathFor(id))) {
                return null;
            }
            return Files.readString(pathFor(id));
        }

        @Override
        public void store(String id, String token) throws IOException {
            Files.createDirectories(Paths.get(".", TOKENS_DIRECTORY_PATH));
            Files.writeString(pathFor(id), token);
        }

        @Override
        public void delete(String id) throws IOException {
            if (!Files.exists(pathFor(id))) {
                return;
            }
            Files.delete(pathFor(id));
        }
    };
}
