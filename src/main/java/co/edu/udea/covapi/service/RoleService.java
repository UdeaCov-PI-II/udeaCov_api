package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Role;

import java.util.concurrent.ExecutionException;

public interface RoleService extends BaseModelService<Role>{
    String getUserRoleId() throws ExecutionException, InterruptedException;
    String getRoleId(final String role) throws ExecutionException, InterruptedException;

}
