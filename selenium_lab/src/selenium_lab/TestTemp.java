package selenium_lab;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestTemp {
	
	WebDriver driver;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
	}
	
	@Test
	public void testValidUser(){
		testLogin("andy","apple",true);
		testLogin("bob","bathtub",true);
		testLogin("charley","china",true);
	     driver.quit();

	}
	
	@Test
	public void testCaseInSensitive() throws InterruptedException{
	    TimeUnit.SECONDS.sleep(10);
		testLogin("Andy","apple",true);
		testLogin("BoB","  bathtub  ",true);
	     driver.quit();

	}
	
	@Test
	public void testInvalid() throws InterruptedException{
	    TimeUnit.SECONDS.sleep(10);
		testLogin("andy","aPple",false);
		testLogin("bob","batHtub",false);
	     driver.quit();

	}
	
	@Test
	public void test1Place() throws InterruptedException{
		testConverter("451","232.8");
	}
	
	@Test
	public void test2Place() throws InterruptedException{
		testConverter("111","43.89");
	}
	
	@Test
	public void testNonNumeric() throws InterruptedException{
		testInvalidTemp("9.73E2");
	}
	
	@Test
	public void testAbcTemp() throws InterruptedException{
		testInvalidTemp("abc");
	}
	
	public void testInvalidTemp(String invalid) throws InterruptedException{
	    TimeUnit.SECONDS.sleep(10);
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement queryId = driver.findElement(By.name("userId"));
        WebElement queryPasswd = driver.findElement(By.name("userPassword"));
        queryId.clear();
        queryId.sendKeys("charley");
        queryPasswd.clear();
        queryPasswd.sendKeys("china");
        queryPasswd.submit();
        long end = System.currentTimeMillis() + 5000;
        String title=null;
	      while (System.currentTimeMillis() < end) {
	    	  title = driver.getTitle();
	          // If results have been returned, the results are displayed in a drop down.
	          if (!title.equals("Dummy login page for Testing lab")) {
	            break;
	          }
	      }
	        WebElement queryTemp = driver.findElement(By.name("farenheitTemperature"));
	        queryTemp.clear();
	        queryTemp.sendKeys(invalid);
	        queryTemp.submit();
	        end = System.currentTimeMillis() + 5000;
		      while (System.currentTimeMillis() < end) {
		    	  title = driver.getTitle();
		          // If results have been returned, the results are displayed in a drop down.
		          if (!title.equals(" Online temperature conversion calculator ")) {
		            break;
		          }
		      }
		      WebElement celcius = driver.findElement(By.xpath("/html/body/*[self::h2]"));
		      assertTrue(celcius.getText().contains("Need to enter a valid temperature!Got a NumberFormatException on "+invalid));
		      driver.quit();
	}
	
	public void testConverter(String fTemp, String cTemp) throws InterruptedException{
	    TimeUnit.SECONDS.sleep(10);
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement queryId = driver.findElement(By.name("userId"));
        WebElement queryPasswd = driver.findElement(By.name("userPassword"));
        queryId.clear();
        queryId.sendKeys("charley");
        queryPasswd.clear();
        queryPasswd.sendKeys("china");
        queryPasswd.submit();
        long end = System.currentTimeMillis() + 5000;
        String title=null;
	      while (System.currentTimeMillis() < end) {
	    	  title = driver.getTitle();
	          // If results have been returned, the results are displayed in a drop down.
	          if (!title.equals("Dummy login page for Testing lab")) {
	            break;
	          }
	      }
	        WebElement queryTemp = driver.findElement(By.name("farenheitTemperature"));
	        queryTemp.clear();
	        queryTemp.sendKeys(fTemp);
	        queryTemp.submit();
	        end = System.currentTimeMillis() + 5000;
		      while (System.currentTimeMillis() < end) {
		    	  title = driver.getTitle();
		          // If results have been returned, the results are displayed in a drop down.
		          if (!title.equals(" Online temperature conversion calculator ")) {
		            break;
		          }
		      }
		      WebElement celcius = driver.findElement(By.xpath("/html/body/*[self::h2]"));
		      assertTrue(celcius.getText().contains(cTemp));
		      driver.quit();
	}
	
	public void testLogin(String id, String passwd,boolean valid){
		driver.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement queryId = driver.findElement(By.name("userId"));
        WebElement queryPasswd = driver.findElement(By.name("userPassword"));
        queryId.clear();
        queryId.sendKeys(id);
        queryPasswd.clear();
        queryPasswd.sendKeys(passwd);
        queryPasswd.submit();
        long end = System.currentTimeMillis() + 5000;
        String title=null;
	      while (System.currentTimeMillis() < end) {
	    	  title = driver.getTitle();
	          // If results have been returned, the results are displayed in a drop down.
	          if (!title.equals("Dummy login page for Testing lab")) {
	            break;
	          }
	      }
	     if(title.equals("Bad Login")){
	    	 if(valid)
	    		 fail();
	     }else{
	    	 if(!valid)
	    		 fail();
	     }
	}
	
}
