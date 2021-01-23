package co.edu.udea.covapi.storage.service;

import co.edu.udea.covapi.model.Media;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

    Media uploadFile(MultipartFile multipartFile) throws Exception;

}
