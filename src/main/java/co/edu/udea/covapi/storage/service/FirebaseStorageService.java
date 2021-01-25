package co.edu.udea.covapi.storage.service;

import co.edu.udea.covapi.model.Media;
import co.edu.udea.covapi.util.MultiPartFileUtil;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class FirebaseStorageService implements StorageService {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${spring.cloud.gcp.firestore.credentials.location}")
    private String credentialsLocation;

    private StorageOptions storageOptions;
    private static String bucketSufix = ".appspot.com";
    private String bucketId;


    @PostConstruct
    private void initializeFirebase() throws IOException {
        File file = ResourceUtils.getFile(credentialsLocation);
        try (InputStream in = new FileInputStream(file)){
            this.bucketId = projectId + bucketSufix;
            this.storageOptions = StorageOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(GoogleCredentials.fromStream(in)).build();
        }
    }

    @Override
    public Media uploadFile(MultipartFile multipartFile) throws Exception {
        File file = MultiPartFileUtil.convertToFile(multipartFile);
        Path filePath = file.toPath();
        String newFileName = generateFileName(multipartFile);
        Storage storage = storageOptions.getService();
        BlobId blobId = BlobId.of(this.bucketId, newFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));
        URL url = blob.signUrl(30, TimeUnit.DAYS);
        Media media = new Media();
        media.setOriginalName(Objects.requireNonNull(file.getName()));
        media.setName(newFileName);
        media.setDowloadUrl(url.toString());
        media.setCreationTime(new Date());
        return media;
    }


    private String generateFileName(MultipartFile multiPart) {
        return Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_") + "-" + new Date().getTime();
    }

}
