package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DefaultUserService extends DefaultBaseModelService<User> implements UserService {

    @Override
    public String getCollectionName() {
        return "users/";
    }

    @Override
    public User getByUsername(String username) throws ExecutionException, InterruptedException {
        CollectionReference users = this.getFirestore().collection(getCollectionName());
        Query query = users.whereEqualTo("username", username);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if(!documents.isEmpty()) {
            QueryDocumentSnapshot document = documents.get(0);
            User user = document.toObject(User.class);
            user.setModelId(document.getId());
            return user;
        }
        return null;
    }
}