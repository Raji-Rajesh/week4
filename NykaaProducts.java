package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaProducts {
	

	public static void main(String[] args) throws InterruptedException {
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver=new ChromeDriver();
	driver.get("https://www.nykaa.com/");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
	Actions builder=new Actions(driver);
	builder.moveToElement(brands).perform();
	Thread.sleep(2000);
	WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
	builder.moveToElement(popular).perform();
	Thread.sleep(2000);
	WebElement loreal = driver.findElement(By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']"));
	loreal.click();
	
	Set<String> currentSessions = driver.getWindowHandles();
	List<String> currentOpenedSessions=new ArrayList<String>(currentSessions);
	String latestWindow = currentOpenedSessions.get(1);
	driver.switchTo().window(latestWindow);
	
	String lorealTitle = driver.getTitle();
	if (lorealTitle.contains("L'Oreal Paris")){
		System.out.println("We are in L'Oreal Paris page");
	}	
	
	driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
	driver.findElement(By.xpath("//span[text()='customer top rated']/following-sibling::div")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[text()='Category']/following-sibling::div/i")).click();
	
	driver.findElement(By.xpath("//span[text()='Hair']")).click();
	driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
	driver.findElement(By.xpath("//span[text()='Shampoo']/parent::span/following-sibling::div")).click();
	
	String shampoo = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
	if(shampoo.contains("ampoo"))
	{
		System.out.println("Shampoo filtered");
	}
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//h2)/span[contains(text(),'Paris Colour Protect Shampoo')]")).click();

	Thread.sleep(2000);
	Set<String> newOpenWindows = driver.getWindowHandles();
	List<String> newOpenSessions=new ArrayList<String>(newOpenWindows);
	String colorProtectShampooWindow = newOpenSessions.get(2);
	driver.switchTo().window(colorProtectShampooWindow);
	
	driver.findElement(By.xpath("//span[text()='175ml']")).click();
	String mrp = driver.findElement(By.xpath("//span[text()='MRP:']/parent::span/following-sibling::span/span")).getText();
	String MRP = mrp.replaceAll("[^0-9]", "");
	System.out.println("MRP of L'Oreal paris colour protect shampoo is: "+MRP);
	
	driver.findElement(By.xpath("//button[text()='ADD TO BAG']")).click();
	driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
	
	String grandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
	String grandTotalActual = grandTotal.replaceAll("[^0-9]", "");
	System.out.println("Grand Total: "+grandTotalActual);
	
	WebElement svgObject = driver.findElement(By.xpath("//button[@type='button']//i[@class='proceed-arrow']/svg"));
	Actions builder1 = new Actions(driver);
	builder1.click(svgObject).build().perform();
	//driver.findElement(By.xpath("//button[@type='button']//i[@class='proceed-arrow']/svg")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
	
	
	}
}
