





import com.appmodule.signUpflow;
import com.utils.SetUp;


	public class signUpFlow extends SetUp {
		
		signUpflow signUp = new signUpflow();

		
		@Test (groups= {"test"}, priority = 0)
		public void verifySignUpCreateAcc() throws Exception {
			
			String result = signUp.checkSignUpCreateAcc();
			assertEquals(result,"ok");
			
		}
		
		@Test (groups= {"test"}, priority = 1)
		public void verifySignInPage() throws Exception {
			
			String result = signUp.checkSignInPage();
			assertEquals(result,"ok");
			
		}
		
	}