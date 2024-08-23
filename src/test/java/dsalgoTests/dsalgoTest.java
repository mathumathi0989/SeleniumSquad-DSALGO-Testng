package dsalgoTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.dsalgoPage;

public class dsalgoTest {

	private WebDriver driver;
    private dsalgoPage DsalgoPage;
    private String url = "http://dsportalapp.herokuapp.com/";
    private String alertMessage ;
    
    @BeforeClass

    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        DsalgoPage = new dsalgoPage(driver);
    }

    
    @Test(priority=1)
    public void testgetStarted() {
    	DsalgoPage.clickGetStarted();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    
   // @Test(dependsOnMethods="testgetStarted")
    @Test(priority=2)
	public void testRegister() {
    	DsalgoPage.clickregister();
    	DsalgoPage.enterUserName("Anu");
    	DsalgoPage.enterPassword("yathumathi");
    	DsalgoPage.enterConfirmPassword("yathumathi");
    	DsalgoPage.clickregisterButton();
    	DsalgoPage.getAlertMessage();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	DsalgoPage.clickSignOut();
    }
    
    //(dependsOnMethods="testgetStarted")
    @Test(priority=3)
    public void testSignin() {
    	DsalgoPage.clickSignIn();
    	DsalgoPage.enterUserName("Anu");
    	DsalgoPage.enterPasswordSignIn("yathumathi");
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
