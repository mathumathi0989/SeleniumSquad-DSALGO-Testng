package dsalgoTests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.dsalgoPage;
import utilities.BaseClass;

public class dsalgoTest extends BaseClass  {

	
    private dsalgoPage DsalgoPage;
    private String url = "http://dsportalapp.herokuapp.com/";
    private String alertMessage ;
    private static final Logger logger = LogManager.getLogger(dsalgoTest.class);

    
 
    
    @BeforeClass

    public void setUp() {
    
    	 DsalgoPage = new dsalgoPage(driver);
        driver.get(url);
        driver.manage().window().maximize();
       
    }

    public WebDriver getDriver() {
        return driver;
    }
    
    @Test(priority=1)
    public void testgetStarted() {
    	DsalgoPage.clickGetStarted();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	logger.info("opened url and get started");
    }
    
  
    @Test(priority=2,dependsOnMethods="testgetStarted")
	public void testRegister() {
    	DsalgoPage.clickregister();
    	DsalgoPage.enterUserName("mathuma1432");
    	DsalgoPage.enterPassword("gurdev143");
    	DsalgoPage.enterConfirmPassword("gurdev143");
    	DsalgoPage.clickregisterButton();
    	DsalgoPage.getAlertMessage();
    	logger.info("user registered");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	DsalgoPage.clickSignOut();
    	logger.info("signedout");
    }
    
    
    @Test(priority=3,dependsOnMethods="testgetStarted")
    public void testSignin() {
    	DsalgoPage.clickSignIn();
    	DsalgoPage.enterUserName("seleniumsquad");
    	DsalgoPage.enterPasswordSignIn("numpyninja");
    	DsalgoPage.clickLoginSignInPage();
    	alertMessage = DsalgoPage.getAlertMessage();
    	AssertJUnit.assertEquals(alertMessage, "You are logged in");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
    	logger.info("user signed in");
    }
    
    
    
    
    
    @Test(priority=4,dependsOnMethods="testSignin",groups="Regression")
    public void testDSALGO() {
    	
    	//DSList - TopicsCovered 
    	DsalgoPage.dslist();	
    	logger.info("user completed topics covered validation");
    }
   
    
    
    @Test(priority=5,dependsOnMethods="testDSALGO")
    public void testSignout() {
    	DsalgoPage.clickSignOut();
    	DsalgoPage.getAlertMessage();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	logger.info("user logged out");
    }

   @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
