package com.etouch.taf.core.test.util;

import static org.junit.Assert.*;

import java.util.Properties;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import com.etouch.taf.core.datamanager.pdf.PDFFileReader;
import com.etouch.taf.util.FileDownload;
import com.etouch.taf.util.FileReader;

public class FileReaderTest {
	private static Properties prop = null;
	FileReader reader = null;
	String downloadUrl = null;
	String filePath = null;
	
	@Before
	public void setup()
	{	
		prop = TafTestUtil.loadProps(TafTestUtil.propFilePath);
		downloadUrl = prop.getProperty("fileDownloadURL");
		filePath = prop.getProperty("fileDownloadPath");
		try{
		HttpResponse response = new FileDownload().downloadFile(downloadUrl, filePath);
		System.out.println(response.getEntity().getContentType().getValue());
		reader = new FileReader(response);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void test() {
		PDFFileReader fileReader = (PDFFileReader)reader.getReader(filePath+"\\tempfile");
		try{
				//CommonUtil.sop(fileReader.parsePdf(filePath+"\\tempfile"));
			Assert.assertNotNull(fileReader.parsePdf(filePath+"\\tempfile"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}
