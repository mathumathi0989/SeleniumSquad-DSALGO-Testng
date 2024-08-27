package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ExcelUtils;

public class dsalgoPage {
	
	private WebDriver driver;
	 private WebDriverWait wait;
	 private Properties properties;
	 
	  public dsalgoPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        this.properties = new Properties();
	        try {
	            FileInputStream fileInput = new FileInputStream("src/test/resources/config.properties");
	            properties.load(fileInput);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
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
	 private By register = By.linkText("Register");
	 private By userName = By.xpath("//input[@id='id_username']");
	 private By password = By.xpath("//input[@id='id_password1']");
	 private By confirmPassword = By.xpath("//input[@id='id_password2']");
	 private By registerButton = By.xpath("//input[@value='Register']");
	 private By loginLink = By.xpath("//a[normalize-space()='Login']");
	 
	 public void clickregister() {
		 driver.findElement(register).click();
	 }
	 public void enterRegUserName() {
		 driver.findElement(userName).sendKeys(properties.getProperty("username"));
	 }
	 public void enterUserName(String uName) {
		 driver.findElement(userName).sendKeys(uName);
	 }
	 public void enterPassword(String uPwd) {
		 driver.findElement(password).sendKeys(uPwd);
	 }
	 public void enterRegPassword() {
		 driver.findElement(password).sendKeys(properties.getProperty("password"));
	 }
	 public void enterConfirmPassword() {
		 driver.findElement(confirmPassword).sendKeys(properties.getProperty("confirmPassword"));
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
	//	 driver.findElement(signOut).click();
	//	 WebElement signOutButton = wait.until(ExpectedConditions.elementToBeClickable(signOut));
	//	 signOutButton.click();
		 WebElement signOutButton = driver.findElement(signOut);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signOutButton);
		 signOutButton.click();


	 }
	 
	 //Data Structures list
	 public void dslist() throws IOException {
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
	 
		private By searcharray=By.xpath("//a[normalize-space()='Search the array']");
	 	private By maxCons=By.xpath("//a[normalize-space()='Max Consecutive Ones']");
	 	private By findNum=By.xpath("//a[normalize-space()='Find Numbers with Even Number of Digits']");
	 	private By squaresOf=By.xpath("//a[contains(text(),'Squares of')]");
	 public void topicsCovered() throws IOException {
	        List<WebElement> links1 = driver.findElements(By.xpath("//a[@class='list-group-item']"));
	        for (int i = 0; i < links1.size()-1; i++) {
	        	 // Refetch the list of links to avoid stale element reference
	            links1 = driver.findElements(By.xpath("//a[@class='list-group-item']"));
	            WebElement link1 = links1.get(i);   
	        	String url = link1.getAttribute("href");
	            System.out.println("Clicking on: " + url);
	            link1.click();
	            System.out.println("Title of the Topics Covered: " + driver.getTitle());
	    		//practice questions section
	            driver.findElement(pracQues).click();
	            try {
	                WebElement link = driver.findElement(By.linkText("Search the array"));
	                if( link.isDisplayed()) {
	                for(int j=0;j<links1.size();j++)
	            	{
	            		 links1 = driver.findElements(By.xpath("//a[@class='list-group-item']"));
	     	            WebElement link2 = links1.get(i);   
	     	        	String url1 = link2.getAttribute("href");
	     	            System.out.println("Clicking on: " + url1);
	     	            String ele = link2.getText();
	     	            link2.click();
	    	            System.out.println("Title of the Array Practice Covered: " + driver.getTitle());
	    	            
	    	            
						if (ele.equalsIgnoreCase("Search the array")) {
						    
							List<String> pythonCodes = ExcelUtils
									.readPythonCodeFromExcel("src/test/resources/testdatas.xlsx", 0);
							
							System.out.println("Title of the Array Practice Covered: " + pythonCodes.get(0));
							System.out.println("Title of the Array Practice Covered: " + pythonCodes.get(1));
							
							clearandExecutePracticeQues(pythonCodes.get(1));
							clickRun();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@id='output']")));
							String codeOutput = driver.findElement(By.xpath("//pre[@id='output']")).getText();
							System.out.println("Run output: " + codeOutput);

							List<String> pythonCodes2 = ExcelUtils
									.readPythonCodeFromExcel("src/test/resources/testdatas.xlsx", 1);

							clearandExecutePracticeQues(pythonCodes.get(1));
							clickSubmit();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@id='output']")));
							String codeOutput1 = driver.findElement(By.xpath("//pre[@id='output']")).getText();
							System.out.println("Submit output: " + codeOutput1);
							driver.navigate().back();
						}
							
							else if (ele.equalsIgnoreCase("Max Consecutive Ones")) {
							List<String> pythonCodes = ExcelUtils
									.readPythonCodeFromExcel("src/test/resources/testdatas.xlsx", 0);
							clearandExecutePracticeQues(pythonCodes.get(2));
							clickRun();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@id='output']")));
						  	String codeOutput = driver.findElement(By.xpath("//pre[@id='output']")).getText();
						     System.out.println("Run output: " + codeOutput);

							List<String> pythonCodes2 = ExcelUtils
									.readPythonCodeFromExcel("src/test/resources/testdatas.xlsx", 1);
							clearandExecutePracticeQues(pythonCodes2.get(2));
							clickSubmit();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@id='output']")));
						 	String codeOutput1 = driver.findElement(By.xpath("//pre[@id='output']")).getText();
						   System.out.println("Submit output: " + codeOutput1);
						   
							driver.navigate().back();

						} 
							else if (ele.equalsIgnoreCase("Find Numbers with Even Number of Digits")) {
							List<String> pythonCodes = ExcelUtils
									.readPythonCodeFromExcel("src/test/resources/testdatas.xlsx", 0);
							clearandExecutePracticeQues(pythonCodes.get(3));
							clickRun();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@id='output']")));
						  	String codeOutput = driver.findElement(By.xpath("//pre[@id='output']")).getText();
						     System.out.println("Run output: " + codeOutput);

							List<String> pythonCodes2 = ExcelUtils
									.readPythonCodeFromExcel("src/test/resources/testdatas.xlsx", 1);
							clearandExecutePracticeQues(pythonCodes2.get(3));
							clickSubmit();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@id='output']")));
						 	String codeOutput1 = driver.findElement(By.xpath("//pre[@id='output']")).getText();
						   System.out.println("Submit output: " + codeOutput1);  
						   
							driver.navigate().back();

						 } 
						
					}
	                }
	                else {
	                	System.out.println("No Praction Question listed");
	                	Assert.assertEquals(driver.getTitle(), "Practice Questions");
	                }
	                
	            } catch (Exception e) {
	            }
	            
			   driver.navigate().back();
	            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	            clickTryHere();
	            editTryHere("");
	            editTryHere("print\"hello\"");
	            editTryHere("invalid code");
	            
	             driver.navigate().back();
	             wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
	      
						  driver.navigate().back();
	           
	        }
	        }
	 
	 
	 //Try Here
	 private By tryhereButton = By.xpath("//a[@class='btn btn-info']");
	 private By runButton = By.xpath("//button[@type='button']");
	 private By output = By.xpath("//pre[@id='output']");
	 public void clickTryHere() {
	//	 driver.findElement(tryhereButton).click();
		 new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
				WebElement element = driver.findElement(tryhereButton);
				element.click();

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
	 
	 public void clickSubmit() {
		 driver.findElement(submit).click();

	 }
	

		//Practice Questions

		private By pracQues = By.xpath("//a[@class='list-group-item list-group-item-light text-info']");

		private By submit = By.xpath("//input[@type='submit']");

		public void clearandExecutePracticeQues(String code) throws IOException {

			WebElement codeMirrorElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='CodeMirror-scroll']")));

			// JavaScript to clear the CodeMirror editor
			String clearScript = "var editor = document.querySelector('.CodeMirror').CodeMirror;"
					+ "editor.setValue('');"; // Sets the value to an empty string, effectively clearing it

			// Execute the script to clear the editor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(clearScript);

			// Python code to enter into the CodeMirror editor
			System.out.println("Executing: " + code);

			// JavaScript to set the code in the CodeMirror editor
			String enterCodeScript = "var editor = document.querySelector('.CodeMirror').CodeMirror;"
					+ "editor.setValue(arguments[0]);";

			// Execute the script to enter the code
			js.executeScript(enterCodeScript, code);

		}

	}
