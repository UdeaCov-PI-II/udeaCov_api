package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.PermissionMediasRequestDTO;
import co.edu.udea.covapi.dto.response.MediaResponseDTO;
import co.edu.udea.covapi.model.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaPopulator implements Populator<Media, MediaResponseDTO, PermissionMediasRequestDTO> {

    @Override
    public void populate(Media source, MediaResponseDTO target) {
        target.setName(source.getName());
        target.setOriginalName(source.getOriginalName());
        target.setDowloadUrl(source.getDowloadUrl());
    }

    @Override
    public void inverselyPopulate(PermissionMediasRequestDTO source, Media target){
        throw new UnsupportedOperationException("The method is not implemented yet");

    }
}
