package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.UserRequestDTO;
import co.edu.udea.covapi.dto.response.UserResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.Role;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.model.UserHealthInfo;
import co.edu.udea.covapi.model.UserUniversityInfo;
import co.edu.udea.covapi.service.RoleService;
import co.edu.udea.covapi.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@Component
public class UserPopulator implements Populator<User, UserResponseDTO, UserRequestDTO> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserHealthInfoPopulator healthInfoPopulator;

    @Autowired
    private UserUniversityInfoPopulator universityInfoPopulator;


    @Override
    public void populate(User source, UserResponseDTO target) throws ExecutionException, InterruptedException {
        target.setFullName(source.getFullName());
        target.setRole(roleService.getModel(source.getRole(),Role.class));
        target.setId(source.getModelId());
        target.setUsername(source.getUsername());
    }

    @Override
    public void inverselyPopulate(UserRequestDTO source, User target) throws ExecutionException, InterruptedException, PopulatorException {
        target.setUsername(source.getUsername());
        target.setPassword(encoder.encode(source.getPassword()));
        target.setEmail(source.getEmail());
        target.setDocumentType(source.getDocumentType());
        target.setDocumentNumber(source.getDocumentNumber());
        target.setFullName(source.getFullName());
        try {
            target.setBirthday(DateUtil.convert(source.getBirthday()));
        } catch (ParseException e) {
            throw new PopulatorException("La fecha de cumpleaños no coincide con el patrón " + DateUtil.DATE_PATTERN);
        }
        target.setPersonalEmail(source.getPersonalEmail());
        target.setArlName(source.getArlName());
        target.setTown(source.getTown());
        UserUniversityInfo userUniversityInfo = new UserUniversityInfo();
        UserHealthInfo healthInfo = new UserHealthInfo();
        universityInfoPopulator.inverselyPopulate(source.getUniversityInfo(),userUniversityInfo);
        healthInfoPopulator.inverselyPopulate(source.getHealthInfo(),healthInfo);
        target.setUniversityInfo(userUniversityInfo);
        target.setHealthInfo(healthInfo);
        target.setRole(roleService.getReference(StringUtils.isEmpty(source.getRoleId()) ? roleService.getUserRoleId()
                : roleService.getRoleId(source.getRoleId())));
        target.setPhoneContact(source.getPhoneContact());
    }

}
