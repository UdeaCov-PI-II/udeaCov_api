package co.edu.udea.covapi.messaging.service;

import co.edu.udea.covapi.messaging.dto.MessageRequestDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
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
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = StringUtils.EMPTY;
        try {
            response = sendAndGetResponse(message);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            if(LOG.isErrorEnabled()){
                LOG.error(String.format("Error sending message to token. Device token: %s" ,request.getToken()),e);
            }
        }
        if(LOG.isInfoEnabled()){
            LOG.info(String.format("Sent message to device token: %s. Response: %s . Message: %s ",request.getToken(),response,jsonOutput));
        }
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageToToken(MessageRequestDTO request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(MessageRequestDTO request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(Notification.builder().setTitle(request.getTitle()).setBody(request.getMessage()).build());
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationSoundEnum.DEFAULT.getValue())
                        .setColor(NotificationColorEnum.DEFAULT.getValue()).setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

}
