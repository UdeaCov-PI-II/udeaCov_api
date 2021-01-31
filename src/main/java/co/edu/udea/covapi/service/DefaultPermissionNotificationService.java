package co.edu.udea.covapi.service;

import co.edu.udea.covapi.messaging.MessagingConstants;
import co.edu.udea.covapi.messaging.dto.MessageRequestBuilder;
import co.edu.udea.covapi.messaging.service.MessagingService;
import co.edu.udea.covapi.model.Permission;
import co.edu.udea.covapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DefaultPermissionNotificationService implements PermissionNotificationService {

    @Autowired
    private UserService userService;

    @Autowired
    private MessagingService messagingService;

    @Override
    public void notifyNextReviewer(Permission permission) throws ExecutionException, InterruptedException {
        List<User> users = userService.getByAttribute(User.class,"role",permission.getNextReviewer());
        users.forEach(user->
            messagingService.sendMessageToDevice(MessageRequestBuilder.builder()
                    .setTitle(MessagingConstants.NEEDS_APPROVE_TITLE)
                    .setMessage(String.format(MessagingConstants.NEEDS_APPROVE_MESSAGE, permission.getModelId()))
                    .setDeviceToken(user.getDeviceToken()).build())
        );
    }

    @Override
    public void notifyApprovedPermission(Permission permission) throws ExecutionException, InterruptedException {
        User user = userService.getModel(permission.getUser(), User.class);
        messagingService.sendMessageToDevice(MessageRequestBuilder.builder()
                .setTitle(MessagingConstants.APPROVED_TITLE)
                .setMessage(String.format(MessagingConstants.APPROVED_MESSAGE, permission.getModelId()))
                .setDeviceToken(user.getDeviceToken()).build());
    }

    @Override
    public void notifyRejectedPermission(Permission permission) throws ExecutionException, InterruptedException {
        User user = userService.getModel(permission.getUser(), User.class);
        messagingService.sendMessageToDevice(MessageRequestBuilder.builder()
                .setTitle(MessagingConstants.NO_APPROVED_TITLE)
                .setMessage(String.format(MessagingConstants.NO_APPROVED_MESSAGE, permission.getModelId()))
                .setDeviceToken(user.getDeviceToken()).build());
    }
}
