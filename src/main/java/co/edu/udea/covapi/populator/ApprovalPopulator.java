package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.ApprovalRequestDTO;
import co.edu.udea.covapi.dto.response.ApprovalResponseDTO;
import co.edu.udea.covapi.dto.response.UserResponseDTO;
import co.edu.udea.covapi.model.Approval;
import co.edu.udea.covapi.model.User;
import co.edu.udea.covapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class ApprovalPopulator implements Populator<Approval, ApprovalResponseDTO, ApprovalRequestDTO> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPopulator userPopulator;

    @Override
    public void populate(Approval source, ApprovalResponseDTO target) throws ExecutionException, InterruptedException {
        User user = userService.getModel(source.getReviewer(), User.class);
        UserResponseDTO userResponse = new UserResponseDTO();
        userPopulator.populate(user,userResponse);
        target.setUser(userResponse);
        target.setApproved(source.isApproved());
        target.setReason(source.getReason());
    }

    @Override
    public void inverselyPopulate(ApprovalRequestDTO source, Approval target) throws ExecutionException, InterruptedException {
       target.setReviewer(userService.getReference(source.getUserId()));
       target.setApproved(source.isApproved());
       target.setReason(source.getReason());
    }
}
