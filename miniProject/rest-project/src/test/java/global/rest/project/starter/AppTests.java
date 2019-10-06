package global.rest.project.starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class AppTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void findUsersMatchTest()throws Exception {
		String expectedResponse = "{\"name\":\"testName\",\"email\":\"test@test.com\",\"password\":\"password\"," +
				"\"phones\":[{\"number\":\"1234567\",\"cityCode\":\"1\",\"countryCode\":\"57\"}]}";
		mvc.perform(get("/user/test@test.com"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResponse));
	}

	@Test
	public void findUsersNoMatchTest()throws Exception {
		String expectedResponse = "{\"mensaje\":\"Usuario no encontrado para el email other@test.com\"}";
		mvc.perform(get("/user/other@test.com"))
				.andExpect(status().isNotFound())
				.andExpect(content().string(expectedResponse));
	}

}

