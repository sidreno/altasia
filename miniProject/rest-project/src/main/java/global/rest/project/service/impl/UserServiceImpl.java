package global.rest.project.service.impl;

import global.rest.project.model.user.UserDetails;
import global.rest.project.service.UserService;
import global.rest.project.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails searchUser(String email) {
        return userDao.findByEmail(email);
    }
}
