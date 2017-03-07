package daoTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AuthDao;
import model.AuthToken;

public class AuthDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAuth() {
		AuthDao aDao = new AuthDao();
		AuthToken token = new AuthToken("iclee141", "bob", "theAuthCode", null);
		AuthToken token2 = new AuthToken("parky", "hello", "theAuthCode", null);
		boolean failed = false;
		try {
			aDao.addAuth(token);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			aDao.addAuth(token2);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		AuthToken feedback = null;
		try {
			feedback = aDao.getAuth(token.getUserName());
			aDao.getAuth(token2.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			aDao.delete(feedback.getUserId(), "People");
		} catch (SQLException e) {
			System.err.println("couldn't delete the persons or the persons didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			aDao.getAuth(feedback.getUserId());
		} catch (SQLException e) {
			System.out.println("They were all deleted");
			failed = true;
		}
		assertTrue(failed);
		System.out.println("All authorization token tests went well");
	}

	@Test
	public void testGetAuthByCode() {
		AuthDao aDao = new AuthDao();
		AuthToken token = new AuthToken("iclee141", "bob", "theAuthCode", null);
		AuthToken token2 = new AuthToken("parky", "hello", "theAuthCode", null);
		boolean failed = false;
		try {
			aDao.addAuth(token);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			aDao.addAuth(token2);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		AuthToken feedback = null;
		try {
			feedback = aDao.getAuthByCode(token.getAuthCode());
			aDao.getAuthByCode(token2.getAuthCode());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertTrue(feedback.equals(token));
		assertFalse(failed);
		try {
			aDao.delete(feedback.getUserId(), "People");
		} catch (SQLException e) {
			System.err.println("couldn't delete the persons or the persons didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			aDao.getAuth(feedback.getUserId());
		} catch (SQLException e) {
			System.out.println("They were all deleted");
			failed = true;
		}
		assertTrue(failed);
		System.out.println("All authorization token tests went well");
	}

	@Test
	public void testAddAuth() {
		AuthDao aDao = new AuthDao();
		AuthToken token = new AuthToken("iclee141", "bob", "theAuthCode", null);
		AuthToken token2 = new AuthToken("parky", "hello", "theAuthCode", null);
		boolean failed = false;
		try {
			aDao.addAuth(token);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			aDao.addAuth(token2);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
	}

}
