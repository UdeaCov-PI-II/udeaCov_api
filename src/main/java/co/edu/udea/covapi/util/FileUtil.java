package co.edu.udea.covapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);


    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static File convertToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try(FileOutputStream fos = new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    public static void removeFile(File file){
        try {
            if(!file.delete() && LOG.isErrorEnabled()){
              LOG.error(String.format("El archivo %s no se pudo eliminar correctamemte", file.getName()));
            }
        }catch (Exception e){
            if(LOG.isErrorEnabled()){
                LOG.error(String.format("El archivo %s no se pudo eliminar correctamemte", file.getName()),e);
            }
        }

    }
}
