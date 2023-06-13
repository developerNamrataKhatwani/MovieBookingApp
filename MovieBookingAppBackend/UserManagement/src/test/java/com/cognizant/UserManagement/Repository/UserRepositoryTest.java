package com.cognizant.UserManagement.Repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.UserManagement.UserManagementApplication;
import com.cognizant.UserManagement.Entity.User;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = { UserManagementApplication.class })used for integration testing
@ActiveProfiles("local")
public class UserRepositoryTest {

	@Mock
	UserRepository userRepository;

	private static final String username = "testUserName";
	private static final String fullName = "testFullName";
	private static final String password = "testpassword";
	private static final String role = "testRole";
	private static final String email = "testEmail";
	private static final Long phoneNumber = 9408412771L;
	private static final String secretQuestion = "testQuestion";
	private static final String secretAnswer = "testAnswer";

	@Test
	public void testEntityContructorFields() {
		User testUser = new User(9893893L, username, fullName, password, role, email, phoneNumber, secretQuestion,
				secretAnswer);
		assertEquals(testUser.getUsername(),"testUserName");
		assertEquals(testUser.getRole(), "testRole");
	}
}
