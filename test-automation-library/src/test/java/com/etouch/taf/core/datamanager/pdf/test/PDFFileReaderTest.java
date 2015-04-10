package com.etouch.taf.core.datamanager.pdf.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import com.etouch.taf.core.datamanager.pdf.PDFFileReader;
import com.etouch.taf.core.test.util.TafTestUtil;
import com.etouch.taf.mobile.experitest.cloud.ExperitestCloudDriver;
import com.etouch.taf.util.CommonUtil;
import com.etouch.taf.util.LogUtil;

public class PDFFileReaderTest {
	
	private static Log log = LogUtil.getLog(PDFFileReaderTest.class);
	
	private static Properties prop = null;
	
	 /** The original PDF that will be parsed. **/
    public static String input1 = null;
    
    /** The resulting text file. */
    private static String result1 = null;
    
    /** The original PDF that will be parsed. **/
    private static String input2 = null;
    
    /** The resulting image destination path */
    private static String result2 = null;
    
    
    @Before
	public void setUp()
	{
		prop = TafTestUtil.loadProps(TafTestUtil.propFilePath);
		input1 = prop.getProperty("inputPDfPath1");
		result1 = prop.getProperty("resultPDFPath1");
		input2 = prop.getProperty("inputPDfPath2");
		result2 = prop.getProperty("resultPDfPath2");
	}
    
	@Test
	public void testParsePDFtoText() {
		boolean isTxtCreated = false;
		try{
			new PDFFileReader().parsePDFtoText(input1, result1);
			isTxtCreated = true;			
		}catch(Exception ex)
		{
			log.error(ex.getMessage());
		}
		
		Assert.assertTrue(isTxtCreated);
		
	}
	
	@Test
	public void testParsePDF() {		
		try{
			 String str = new PDFFileReader().parsePdf(input2);
			 Assert.assertNotNull(str);
			 CommonUtil.sop(str);
						
		}catch(Exception ex)
		{
			log.error(ex.getMessage());
		}
		
	}
	
	
	@Test
	public void testExtractImages() {		
		try{
			 new PDFFileReader().extractImages(input2, result2);
			 boolean isFolderExists = TafTestUtil.checkFolderExists(prop.getProperty("extractedImagePath"));
			 Assert.assertNotNull(isFolderExists);
						
		}catch(Exception ex)
		{
			log.error(ex.getMessage());
		}
		
	}
	 

}
