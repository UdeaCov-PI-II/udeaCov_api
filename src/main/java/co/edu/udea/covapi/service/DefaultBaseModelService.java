package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.FirebaseModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class DefaultBaseModelService<T extends FirebaseModel> implements BaseModelService<T> {

    private String collectionName;
    private static final Logger logger = LoggerFactory.getLogger(DefaultBaseModelService.class);

    @Autowired
    private Firestore firestore;


    @Override
    public String save(T model) throws InterruptedException, ExecutionException {
        model.setCreationTime(new Date());
        ApiFuture<DocumentReference> apiFuture = this.firestore.collection(getCollectionName()).add(model);
        DocumentReference documentReference = apiFuture.get();
        model.setModelId(documentReference.getId());
        this.update(documentReference.getId(),model);
        return documentReference.getId();
    }

    @Override
    public T get(String id, Class<T> entityClass) throws ExecutionException, InterruptedException {
        // this.firestore.collection("users").document("tom") works also
        ApiFuture<DocumentSnapshot> apiFuture = this.firestore.document(getCollectionName() + id).get();
        // .get() blocks on response
        DocumentSnapshot documentSnapshot = apiFuture.get();
        T model = documentSnapshot.toObject(entityClass);
        if(model != null){
            model.setModelId(documentSnapshot.getId());
        }
        return model;
    }

    @Override
    public void update(String id, T newUser) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> apiFuture = this.firestore.document(getCollectionName() + id)
                .set(newUser);
        WriteResult writeResult = apiFuture.get();
        logger.info("Update time: {}", writeResult.getUpdateTime());
    }

    @Override
    public void delete(String id) throws ExecutionException, InterruptedException {
        // document deletion does not delete its sub collections
        // see https://firebase.google.com/docs/firestore/manage-data/delete-data#collections
        ApiFuture<WriteResult> apiFuture = this.firestore.document(getCollectionName() + id).delete();
        WriteResult writeResult = apiFuture.get();
        logger.info("Update time: {}", writeResult.getUpdateTime());
    }

    @Override
    public DocumentReference getReference(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> apiFuture = this.firestore.document(getCollectionName() + id).get();
        return apiFuture.get().getReference();
    }

    @Override
    public List<T> getAll(Class<T> entityClass) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = this.firestore.collection(getCollectionName()).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map(doc ->{
            T model = doc.toObject(entityClass);
            model.setModelId(doc.getId());
            return model;
        }).collect(Collectors.toList());
    }

    @Override
    public List<T> getByAttribute(Class<T> entityClass, String field, Object value) throws ExecutionException, InterruptedException {
        CollectionReference collection = this.getFirestore().collection(getCollectionName());
        Query query = collection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return documents.stream().map(doc ->{
            T model = doc.toObject(entityClass);
            model.setModelId(doc.getId());
            return model;
        }).collect(Collectors.toList());
    }


    public String getCollectionName() {
        return collectionName;
    }

    public Firestore getFirestore() {
        return firestore;
    }
}
