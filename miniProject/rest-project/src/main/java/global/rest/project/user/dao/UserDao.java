package global.rest.project.user.dao;

import org.springframework.data.repository.CrudRepository;

import global.rest.project.model.user.UserDetails;

public interface UserDao extends CrudRepository<UserDetails, Long> {
	
	UserDetails findByEmail(String email);
}
