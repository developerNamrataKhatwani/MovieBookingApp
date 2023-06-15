package com.cognizant.UserManagement.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.cognizant.UserManagement.Entity.User;
import com.cognizant.UserManagement.Exception.UserFoundException;
import com.cognizant.UserManagement.Repository.UserRepository;
import com.cognizant.UserManagement.Service.Impl.UserDetailServiceImpl;
import com.cognizant.UserManagement.Service.Impl.UserServiceImpl;

//@DataJpaTest can run without this
@RunWith(MockitoJUnitRunner.class)//JUnit 4
public class UserServiceTest {
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserDetailServiceImpl userDetailServiceImpl;

	private static final String username = "Usernametest";
	private static final String fullName = "testFullName";
	private static final String password = "testpassword";
	private static final String role = "testRole";
	private static final String email = "testEmail";
	private static final Long phoneNumber = 9408412771L;
	private static final String secretQuestion = "testQuestion";
	private static final String secretAnswer = "testAnswer";
	
	User testUser = new User(9893893L, username, fullName, password, role, email, phoneNumber, secretQuestion,
			secretAnswer);
	
	@Test
	public void testUserByUsername() {
//		testUser.setUsername(username);
		when(userRepository.findByUsername(username)).thenReturn(testUser);
		User testResult = userServiceImpl.getUserByUsername(username);
		assertEquals(testResult.getUsername(), testUser.getUsername());

	}
	
	@Test(expected=UserFoundException.class)
	public void testSaveUser() throws UserFoundException {
		testUser.setUsername(username);
		when(userRepository.findByUsername(username)).thenReturn(testUser);
		testUser.setPassword(password);
		
		User testResult = userServiceImpl.saveUser(testUser);
		assertEquals(testResult.getUsername(), testUser.getUsername());
	}

}
