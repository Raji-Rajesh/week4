package week4.day1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailSort {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
		WebElement fromStation = driver.findElement(By.id("txtStationFrom"));
		fromStation.clear();
		fromStation.sendKeys("Chennai Egmore");
		fromStation.sendKeys(Keys.ENTER);
		
		
		WebElement toStation = driver.findElement(By.id("txtStationTo"));
		toStation.clear();
		toStation.sendKeys("Madurai Jn");
		toStation.sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		driver.findElement(By.id("buttonFromTo")).click();
		Thread.sleep(3000);
		
		WebElement trainListTable = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']"));
		List<WebElement> trains = trainListTable.findElements(By.xpath("//tbody/tr"));
		int size = trains.size();
		System.out.println(size);
		Thread.sleep(3000);
		
		for(int i=1;i<=size;i++) {
		Thread.sleep(2000);
		WebElement eachTrain = trains.get(i);
		String text = eachTrain.findElement(By.xpath("(//td[contains(@title,'First Station')])["+i+"]//preceding-sibling::td/a")).getText();
		System.out.println(text);
		if(!text.equals("")) {
			
			String text1 = eachTrain.findElement(By.xpath("(//td[contains(@title,'First Station')])["+i+"]//preceding-sibling::td[1]/a")).getText();
			System.out.println(text1);
		}
		}
		Thread.sleep(3000);
		driver.close();
		
	}
}
