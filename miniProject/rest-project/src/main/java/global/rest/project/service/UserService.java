package global.rest.project.service;

import global.rest.project.model.user.UserDetails;

public interface UserService {

    UserDetails searchUser(String email);
}
