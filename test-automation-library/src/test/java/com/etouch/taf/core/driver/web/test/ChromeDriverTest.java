package com.etouch.taf.core.driver.web.test;


import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.etouch.taf.core.TestBed;
import com.etouch.taf.core.TestBedManager;
import com.etouch.taf.core.config.BrowserConfig;
import com.etouch.taf.core.driver.web.ChromeDriver;
import com.etouch.taf.core.test.util.TafTestUtil;
import com.etouch.taf.util.CommonUtil;
import com.etouch.taf.util.LogUtil;
import com.etouch.taf.util.SoftAssertor;

public class ChromeDriverTest {
	
	static Log log=LogUtil.getLog(ChromeDriverTest.class);
	TestBed testbed = null;
	private static Properties prop = null;
	ChromeDriver driverObj = null;
	
	@Before
	public void setUp()
	{
		prop = TafTestUtil.loadProps(TafTestUtil.propFilePath);
		TafTestUtil.initialize();
		
		testbed = new TestBed();
		BrowserConfig bcfg = new BrowserConfig();
		bcfg.setName("Chrome");
		bcfg.setVersion("35");
		bcfg.setDriverLocation(prop.getProperty("chromeDriverPath"));
		testbed.setBrowser(bcfg);	 
	}
	
	@Test
	public void test() {		
	try{
			driverObj = new ChromeDriver(testbed);	
			driverObj.buildDriver();
			SoftAssertor.assertNotNull(driverObj.getDriver());		
		}catch(Exception ex)
		{
			log.error(ex.getMessage());
		}
		
	}
	
	@After
	public void tearDown(){
		WebDriver driver = null;
		CommonUtil.sop("At tear down ");
		try {
			 driver = (WebDriver) driverObj.getDriver();
			driver.wait(150000);
		} catch (Exception e) {
			//SoftAssertor.addVerificationFailure(e.getMessage());
		}
		driver.close();
		driver.quit();
		//mainPage.stopRecording();
	}
	
	
}

	
	