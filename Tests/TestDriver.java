

public class TestDriver {
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		org.junit.runner.JUnitCore.main(
				"daoTests.AuthDaoTest",
				"daoTests.DaoTest",
				"daoTests.EventDaoTest",
				"daoTests.PersonDaoTest",
				"daoTests.UserDaoTest",
				"unitTests.LoadServiceTest",
				"unitTests.RegisterServiceTest",
				"unitTests.FillServiceTest",
				"unitTests.PersonServiceTest",
				"unitTests.EventServiceTest",
				"unitTests.GenerateTest",
				"unitTests.ServerProxyTest"
				);
	}

}
