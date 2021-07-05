package week4.day1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetStarterWithCalendars {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/Calendar.html");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Select 10th day of the following month
		
		driver.findElement(By.id("datepicker")).click();
		WebElement calendarTable = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
		List<WebElement> noOfRows = calendarTable.findElements(By.xpath("//tbody/tr"));
		int totalRows = noOfRows.size();
		System.out.println("Total no of Rows: "+totalRows);
		for(int i=0;i<=totalRows-1;i++) {
			WebElement entireRow = noOfRows.get(i);
			List<WebElement> rowValues = entireRow.findElements(By.tagName("td"));
			int noOfValuesInRow = rowValues.size();
			//System.out.println("No of values per row: "+noOfValuesInRow);
			for(int j=0;j<noOfValuesInRow;j++) {
				String actualday = rowValues.get(j).getText();
				String expectedday="11";
				if(actualday.equals(expectedday)) {
					rowValues.get(j).click();
					break;
				}
			}
			}
		
					
		
	}

}
