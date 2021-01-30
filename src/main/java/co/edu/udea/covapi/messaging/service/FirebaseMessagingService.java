package co.edu.udea.covapi.messaging.service;

import co.edu.udea.covapi.messaging.dto.MessageRequestDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseMessagingService implements MessagingService {

    private static final Logger LOG = LoggerFactory.getLogger(FirebaseMessagingService.class);


    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${spring.cloud.gcp.firestore.credentials.location}")
    private String credentialsLocation;


    @PostConstruct
    private void initializeFirebase() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        try (InputStream inputStream = cl.getResourceAsStream(credentialsLocation.replace("classpath:",""))){
            FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(inputStream)).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                LOG.info("Firebase application has been initialized");
            }
        }
    }


    @Override
    public void sendMessageToDevice(MessageRequestDTO request) {

    }
}
