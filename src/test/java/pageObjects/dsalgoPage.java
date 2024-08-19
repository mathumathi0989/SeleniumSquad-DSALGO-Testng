package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class dsalgoPage {
	
	private WebDriver driver;
	 private WebDriverWait wait;
	 
	 
	  public dsalgoPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        
	    }
	  
	 //GetStarted page
	 private By getStartedButton = By.xpath("//button[@class='btn']");
	 public void clickGetStarted() {
		 driver.findElement(getStartedButton).click();
	 }
	 
	 //home page
	 private By numpyNinja = By.xpath("//a[@class='navbar-brand']");
	 private By alert = By.xpath("//div[@role='alert']");
	 public void clickNumpyNinja() {
		 driver.findElement(numpyNinja).click();
	 }
	 public String getAlertMessage() {
		 return driver.findElement(alert).getText();	  
	 }
	
	 //Register
	 private By register = By.xpath("//a[normalize-space()='Register']");
	 private By userName = By.xpath("//input[@id='id_username']");
	 private By password = By.xpath("//input[@id='id_password1']");
	 private By confirmPassword = By.xpath("//input[@id='id_password2']");
	 private By registerButton = By.xpath("//input[@value='Register']");
	 private By loginLink = By.xpath("//a[normalize-space()='Login']");
	 public void clickregister() {
		 driver.findElement(register).click();
	 }
	 public void enterUserName(String uName) {
		 driver.findElement(userName).sendKeys(uName);
	 }
	 public void enterPassword(String pwd) {
		 driver.findElement(password).sendKeys(pwd);
	 }
	 public void enterConfirmPassword(String confirmPwd) {
		 driver.findElement(confirmPassword).sendKeys(confirmPwd);
	 }
	 public void clickregisterButton() {
		 driver.findElement(registerButton).click();
	 }
	 public void clickLogin() {
		 driver.findElement(loginLink).click();
	 }
	 
	 //Signin
	 private By signIn = By.xpath("//a[normalize-space()='Sign in']");
	 private By passwordLogin = By.xpath("//input[@id='id_password']");
	 private By loginButton = By.xpath("//input[@value='Login']");
	 private By registerLink = By.xpath("//a[normalize-space()='Register!']");
	 public void clickSignIn() {
		 driver.findElement(signIn).click();
	 }
	 public void enterPasswordSignIn(String pwd) {
		 driver.findElement(passwordLogin).sendKeys(pwd);
	 }
	 public void clickLoginSignInPage() {
		 driver.findElement(loginButton).click();
	 }
	 public void clickRegisterLoginPage() {
		 driver.findElement(registerLink).click();
	 }
	 
	 //SignOut
	 private By signOut = By.xpath("//a[normalize-space()='Sign out']");
	 
	 public void clickSignOut() {
		 driver.findElement(signOut).click();
	 }
	 
	 //Data Structures list
	 public void dslist() {
	//	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 List<WebElement> links = driver.findElements(By.xpath("//div[@class='row row-cols-1 row-cols-md-3 g-4']/div//a"));
	        int numLinks = links.size();
	        for (int i = 0; i < numLinks; i++) {
	        List<WebElement>	currentLinks = driver.findElements(By.xpath("//div[@class='row row-cols-1 row-cols-md-3 g-4']/div//a"));
	          
	            WebElement link = currentLinks.get(i);
	            String url = link.getAttribute("href");
	            driver.navigate().to(url);
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
	            System.out.println("Title of the page: " + driver.getTitle());
	            topicsCovered();
	            driver.navigate().back();
	            System.out.println("-------------------------------------------------------------------");
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
	        }
	 }
	 
	 //Topics Covered
	 public void topicsCovered() {
	        List<WebElement> links1 = driver.findElements(By.xpath("//a[@class='list-group-item']"));
	        for (int i = 0; i < links1.size(); i++) {
	        	WebElement link1 = links1.get(i);
	            String url = link1.getAttribute("href");
	            System.out.println("Clicking on: " + url);
	            link1.click();
	            System.out.println("Title of the Topics Covered: " + driver.getTitle());
	            clickTryHere();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	            editTryHere("");
	            editTryHere("print\"hello\"");
	            editTryHere("invalid code");
	             driver.navigate().back();
	             wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
	             /*
	             System.out.println(links1.size());
	 				if (i == links1.size() - 1) {
	 					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

	 					
	 					By practiceLinkLocator = By.xpath("//a[@class='list-group-item list-group-item-light text-info']");
	 				try {
						WebElement practiceLink = driver.findElement(practiceLinkLocator);
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", practiceLink);
						wait.until(ExpectedConditions.elementToBeClickable(practiceLink));
						practiceLink.click();
						wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
						System.out.println("Title of the Practice Questions page: " + driver.getTitle());
					} catch (Exception e) {
						System.out.println("Practice questions link not found or not clickable.");
						  driver.navigate().back();
						  driver.navigate().back();
						  }
						  }
						  
						  */
						  driver.navigate().back();
		
	           
	        }
	        }
	 
	 
	 //Try Here
	 private By tryhereButton = By.xpath("//a[@class='btn btn-info']");
	 private By runButton = By.xpath("//button[@type='button']");
	 private By output = By.xpath("//pre[@id='output']");
	 public void clickTryHere() {
		 driver.findElement(tryhereButton).click();
	 }
	 public void editTryHere(String code) {
	        String script = "var editor = document.querySelector('.CodeMirror').CodeMirror;" +
	                        "editor.setValue(arguments[0]);";
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript(script, code);
	        clickRun();
	        try {
	            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	            System.out.println("Alert message: " + alert.getText());
	            alert.accept();
	        } catch (Exception e) {
	            try {
	            	wait.until(ExpectedConditions.visibilityOfElementLocated(output));
	            	String codeOutput = driver.findElement(output).getText();
	                System.out.println("Code output: " + codeOutput);
	            } catch (Exception ex) {
	                System.out.println("No output element found or no output generated.");
	            }
	        }

	 }
	 public void clickRun() {
		 driver.findElement(runButton).click();
	 }
	 

	 //Practice Questions
	 
	 
	

	
}
