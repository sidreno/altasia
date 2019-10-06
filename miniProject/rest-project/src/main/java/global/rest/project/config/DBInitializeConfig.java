package global.rest.project.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	private static Logger logger = LoggerFactory.getLogger(DBInitializeConfig.class);
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			logger.info("Executing SQL insert query ===============================================");
			    try{
			    	statement.executeUpdate("INSERT INTO USER_INFO (id, email, name, password, phones, created, isActive, token) " +
							"VALUES (UUID(), 'test@test.com','testName','password', '[{\"number\": \"1234567\",\"cityCode\": \"1\",\"countryCode\": \"57\"}]', " +
							"DATE '2018-01-01', 'true', '123-token')");
			    	logger.info("SQL query executed ===============================================");
			    }
			    catch(Exception e)
				{
					logger.error("Error in SQL insert query execution ===============================================");
					e.printStackTrace();
				}
			statement.close();
			connection.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
