package week4.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LensKart {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.lenskart.com/");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement compGlasses = driver.findElement(By.linkText("Computer Glasses"));
		Actions builder = new Actions(driver);
		builder.moveToElement(compGlasses).perform();

		WebElement men = driver.findElement(By.xpath("(//span[text()='men'])[2]"));
		builder.moveToElement(men).perform();

		driver.findElement(By.xpath("(//span[text()='PREMIUM RANGE'])[1]")).click();

		driver.findElement(By.xpath("//span[text()='Round']")).click();

		Thread.sleep(3000);

		
		List<Integer> frameCount = new ArrayList<Integer>();
		List<WebElement> colorFramesChkBoxs = driver
				.findElements(By.xpath("//div[text()='FRAME COLOR']/following-sibling::ul//span"));
		
		for (WebElement colorFramesChkBox : colorFramesChkBoxs) {
			String colorFrames = colorFramesChkBox.getText();
			String noOfFramesPerColor = colorFrames.replaceAll("[^0-9]", "");
			//String frameColor = colorFrames.replaceAll("[^a-zA-Z]", "");
			//System.out.println(frameColor);
			int noOfFrames = Integer.parseInt(noOfFramesPerColor);
			frameCount.add(noOfFrames);

		}
		Integer max = Collections.max(frameCount);
		System.out.println("Maximum no of count: "+max);
		String string = max.toString();
		
		for(WebElement chkMaxColor:colorFramesChkBoxs) {
			if(chkMaxColor.getText().contains(string)){
				
				System.out.println(chkMaxColor.getText());
				
				chkMaxColor.findElement(By.xpath("//preceding-sibling::input[@type='checkbox']")).click();
				break;
			}
		}
		
		
	}
}
