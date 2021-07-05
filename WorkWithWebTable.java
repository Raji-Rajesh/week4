package week4.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkWithWebTable {

	public static void main(String[] args) {
		// to set up the browser and url
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 1. Get row count
		WebElement webTable = driver.findElement(By.id("table_id"));
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		int noOfRows = tableRows.size();
		System.out.println(noOfRows);

		// 2.Get column count
		List<WebElement> tableColumns = webTable.findElements(By.tagName("th"));
		int noOfColumns = tableColumns.size();
		System.out.println(noOfColumns);

		// Get progress value of interact with elements
		List<WebElement> LearningDetails = driver.findElements(By.xpath("//table[@id='table_id']//tr/td[1]"));
		int j=1;
		for(WebElement LearningDetail:LearningDetails) {		
			j++;
			if(LearningDetail.getText().contains("Learn to interact with Elements")) 
			{
				String text = LearningDetail.findElement(By.xpath("//tr["+j+"]/td[2]")).getText();
				System.out.println("Progress value of Learn to interact with Elements is: "+text);
				break;
			}
			
		}
		
		
		
		
		//String progValue = driver.findElement(By.xpath("(//table[@id='table_id']//tr)[3]/td[2]")).getText();
		//System.out.println(progValue);

		// 4.Check the vital task for least completed task

		// To find out the minimum value of Progress
		List<Integer> progressNumValues = new ArrayList<Integer>();
		List<WebElement> progressValues = driver.findElements(By.xpath("//table[@id='table_id']//tr/td[2]"));
		for (WebElement progressValue : progressValues) {
			String removePercentage = progressValue.getText().replaceAll("%", "");
			int progressNum = Integer.parseInt(removePercentage);
			progressNumValues.add(progressNum);
		}
		Integer min = Collections.min(progressNumValues);
		String minNum = String.valueOf(min);
		System.out.println("Minimum Progress: " + minNum);

		// To find out Vital task of minimum progress value
		int i = 1;
		for (WebElement progressToVital : progressValues) {
			i++;
			if (progressToVital.getText().contains(minNum)) {
				progressToVital.findElement(By.xpath("//table[@id='table_id']//tr[" + i + "]/td[3]/input")).click();
				break;
			}
		}

	}
}
