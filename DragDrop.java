import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.*;

public class DragDrop {

	WebDriver driver;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/udaykumar/eclipse-workspace/SeleniumFramework/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver();
		// WebDriver driver=new ChromeDr();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://jqueryui.com/droppable/");

		Actions myaction = new Actions(driver);

		// switch to frame
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

		WebElement dragfrom = driver.findElement(By.xpath("//*[@id='draggable']"));

		// find
		WebElement dragto = driver.findElement(By.xpath("//*[@id='droppable']"));
		Thread.sleep(1000);

		// perform drag operation
		myaction.dragAndDrop(dragfrom, dragto).build().perform();
		Thread.sleep(1000);

	}

	@Test
	public void colorTest() throws NumberFormatException {
		
		
		String Expectedclr = "#fffa90";
		WebElement dragto = driver.findElement(By.xpath("//*[@id='droppable']"));
		String actualclr = dragto.getCssValue("background");
		System.out.println(actualclr); //rgb(255, 250, 144) none repeat scroll 50% 0% / auto padding-box border-box
		String color = actualclr.replace(" none repeat scroll 50% 0% / auto padding-box border-box", "");
		System.out.println(color); //rgb(255, 250, 144)
		
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		Assert.assertEquals(hex,Expectedclr);


	}



	@AfterTest
	public void teadDown() throws InterruptedException{
		// wait for 1 sec
		Thread.sleep(1000);
		driver.close();

	}
}