package dsalgoTests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.dsalgoPage;

public class dsalgoTest {

	private WebDriver driver;
    private dsalgoPage DsalgoPage;
    private String url = "http://dsportalapp.herokuapp.com/";
    private String alertMessage ;
    private static final Logger logger = LogManager.getLogger(dsalgoTest.class);

    
 
    
    @BeforeClass

    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        DsalgoPage = new dsalgoPage(driver);
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
    
   // @Test(dependsOnMethods="testgetStarted")
    @Test(priority=2)
	public void testRegister() {
    	DsalgoPage.clickregister();
    	DsalgoPage.enterUserName("mathu1234");
    	DsalgoPage.enterPassword("gurdev123");
    	DsalgoPage.enterConfirmPassword("gurdev123");
    	DsalgoPage.clickregisterButton();
    	DsalgoPage.getAlertMessage();
    	logger.info("user registered");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	DsalgoPage.clickSignOut();
    	logger.info("signedout");
    }
    
    //(dependsOnMethods="testgetStarted")
    @Test(priority=3)
    public void testSignin() {
    	DsalgoPage.clickSignIn();
    	DsalgoPage.enterUserName("seleniumsquad");
    	DsalgoPage.enterPasswordSignIn("numpyninja");
    	DsalgoPage.clickLoginSignInPage();
    	alertMessage = DsalgoPage.getAlertMessage();
    	AssertJUnit.assertEquals(alertMessage, "You are logged in");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
    	
    }
    
    
    
    
    
    @Test(priority=4,dependsOnMethods="testSignin",groups="Regression")
    public void testDSALGO() {
    	
    	//DSList - TopicsCovered 
    	DsalgoPage.dslist();	
    
    }
   
    
    //@Test(dependsOnMethods="testDSALGO")
    @Test(priority=5)
    public void testSignout() {
    	DsalgoPage.clickSignOut();
    	DsalgoPage.getAlertMessage();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

   @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
