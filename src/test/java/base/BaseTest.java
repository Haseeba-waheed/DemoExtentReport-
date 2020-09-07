package base;
import jdk.incubator.jpackage.internal.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    static ExtentTest test;
    static ExtentReports report;
    private static IOUtils FileUtils;

    @BeforeClass
    public static void startTest()
    {
        report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
        test = report.startTest("ExtentDemo");
    }
    @Test
    public void extentReportsDemo()
    {
        System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.co.in");
        if(driver.getTitle().equals("Goooogle"))
        {
            test.log(LogStatus.PASS, "Navigated to the specified URL");
        }
        else
        {
            test.log(LogStatus.FAIL, "Test Failed");
        }
    }
    @Test
    public void extentReportsDemo2() throws IOException {
        System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.co.in");
        if(driver.getTitle().equals("Google"))
        {
            test.log(LogStatus.PASS, "Navigated to the specified URL");
        }
        else {
            test.log(LogStatus.FAIL, "Test Failed");
            //test.log(LogStatus.FAIL, test.addScreenCapture("C:\\Users\\Haseeba.waheed\\Documents\\IntelliJ\\src\\images")+"Test Failed");

        }

    }
  /*  public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()
                + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }*/


    @AfterClass
    public static void endTest()
    {
        report.endTest(test);
        report.flush();
    }
}