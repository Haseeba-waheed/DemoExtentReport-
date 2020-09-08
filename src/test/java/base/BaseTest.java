package base;
import org.apache.commons.io.FileUtils;
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
   static WebDriver driver;
   // private static IOUtils FileUtils;

    @BeforeClass
    public static void startTest()
    {
        System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

        report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
        test = report.startTest("startTest","Before test");
        test.log(LogStatus.PASS, "Setup completed");
    }

    @Test
    public void extentReportsDemo()throws IOException
    {
        System.out.println("Test Case One with Thread Id:- "
                + Thread.currentThread().getId());
        test = report.startTest("extentReportsDemo","extentReportsDemo started");

        driver.get("https://www.yahoo.com");

        if(driver.getTitle().equals("Google"))
        {
            System.out.println("extentReportsDemo title matched");

            test.log(LogStatus.PASS, "Navigated to the specified URL");
        }
        else
        {
            System.out.println("extentReportsDemo title not matched");

            //test.log(LogStatus.FAIL, "Test Failed");
            //System.out.println("Demo :: "+capture(driver));
            test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver))+"Test Failed");
        }
    }


    @Test
    public void extentReportsDemo2() throws IOException {

        System.out.println("Test Case  two with Thread Id:- "
                + Thread.currentThread().getId());
        test = report.startTest("extentReportsDemo2","extentReportsDemo2 started");

        driver.get("https://www.google.co.in");
        if(driver.getTitle().equals("Google"))
        {
            System.out.println("extentReportsDemo2 title matched");
            test.log(LogStatus.PASS, "Navigated to the specified URL");
        }
        else {
            System.out.println("extentReportsDemo2 title not matched");

           // test.log(LogStatus.FAIL, "Test Failed");
            //capture(driver);

          test.log(LogStatus.FAIL, test.addScreenCapture( capture(driver))+"Test Failed");

        }

    }
      public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(".\\src\\images\\" + System.currentTimeMillis()
                + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        System.out.println("capture" +errflpath);
        return errflpath;
    }


    @AfterClass
    public static void endTest()
    {
        report.endTest(test);
        report.flush();
    }
}