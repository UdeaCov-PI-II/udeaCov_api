package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.response.PersonResponseDTO;
import co.edu.udea.covapi.dto.response.PermissionResponseDTO;
import co.edu.udea.covapi.dto.response.UnitResponseDTO;
import co.edu.udea.covapi.model.Person;
import co.edu.udea.covapi.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitPopulator implements Populator<Unit, UnitResponseDTO, PermissionResponseDTO> {

    @Autowired
    private PersonPopulator personPopulator;

    @Override
    public void populate(Unit source, UnitResponseDTO target){
        target.setId(source.getModelId());
        target.setName(source.getName());
        Person person = source.getManager() != null ? source.getManager() : new Person();
        PersonResponseDTO personResponse = new PersonResponseDTO();
        personPopulator.populate(person, personResponse);
        target.setManager(personResponse);
    }

    @Override
    public void inverselyPopulate(PermissionResponseDTO source, Unit target){
        throw new UnsupportedOperationException("The method is not implemented yet");
    }
}
