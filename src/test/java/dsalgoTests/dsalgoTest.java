package dsalgoTests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.dsalgoPage;

public class dsalgoTest {

	private WebDriver driver;
    private dsalgoPage DsalgoPage;
    private String url = "http://dsportalapp.herokuapp.com/";
    private String alertMessage ;
    
    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        DsalgoPage = new dsalgoPage(driver);
    }

    
    @Test
    public void testgetStarted() {
    	DsalgoPage.clickGetStarted();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    
   // @Test(dependsOnMethods="testgetStarted")
    public void testRegister() {
    	DsalgoPage.clickregister();
    	DsalgoPage.enterUserName("mathu");
    	DsalgoPage.enterPassword("mathumathi");
    	DsalgoPage.enterConfirmPassword("mathumathi");
    	DsalgoPage.clickregisterButton();
    	DsalgoPage.getAlertMessage();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    
    
    @Test(dependsOnMethods="testgetStarted")
    public void testSignin() {
    	DsalgoPage.clickSignIn();
    	DsalgoPage.enterUserName("mathu");
    	DsalgoPage.enterPasswordSignIn("mathumathi");
    	DsalgoPage.clickLoginSignInPage();
    	alertMessage = DsalgoPage.getAlertMessage();
    	Assert.assertEquals(alertMessage, "You are logged in");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
    	
    }
    
    
    
    
    
    @Test(dependsOnMethods="testSignin")
    public void testDSALGO() {
    	
    	//DSList - TopicsCovered 
    	DsalgoPage.dslist();
    	
    	
    
    }
   
    
    @Test(dependsOnMethods="testDSALGO")
    public void testSignout() {
    	DsalgoPage.clickSignOut();
    	DsalgoPage.getAlertMessage();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
