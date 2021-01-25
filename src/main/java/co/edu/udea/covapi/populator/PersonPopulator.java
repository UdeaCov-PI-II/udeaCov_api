package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.response.PersonResponseDTO;
import co.edu.udea.covapi.dto.request.PermissionRequestDTO;
import co.edu.udea.covapi.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonPopulator implements Populator<Person, PersonResponseDTO, PermissionRequestDTO> {

    @Override
    public void populate(Person source, PersonResponseDTO target) {
        target.setDocumentNumber(source.getDocumentNumber());
        target.setDocumentType(source.getDocumentType());
        target.setEmail(source.getEmail());
        target.setFullName(source.getFullName());
        target.setId(source.getModelId());
    }

    @Override
    public void inverselyPopulate(PermissionRequestDTO source, Person target){
        throw new UnsupportedOperationException("The method is not implemented yet");
    }

}
