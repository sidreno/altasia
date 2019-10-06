package global.rest.project.model.user;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="USER_INFO")
public class UserDetails {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator",
			parameters = {
					@Parameter(
							name = "uuid_gen_strategy_class",
							value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
					)
			}
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String phones;
	@Column
	private Timestamp created;
	@Column
	private Timestamp  modified;
	@Column
	private String isActive;
	@Column
	private String token;
	@Column
	private Timestamp lastLogin;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public UserDetails setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserDetails setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserDetails setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getPhones() {
		return phones;
	}

	public UserDetails setPhones(String phones) {
		this.phones = phones;
		return this;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public String getActive() {
		return isActive;
	}

	public void setActive(String active) {
		isActive = active;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
}
