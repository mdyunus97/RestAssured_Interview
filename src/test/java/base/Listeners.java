package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;

public class Listeners implements ITestListener {

	public Properties prop;
	public FileInputStream fis;
	ExtentReports extent;
	ExtentTest test;

	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

		try {
			fis = new FileInputStream("src/test/resources/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestAssured.baseURI = prop.getProperty("baseURL");
	}

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult result) {
	    test.fail(result.getThrowable());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
