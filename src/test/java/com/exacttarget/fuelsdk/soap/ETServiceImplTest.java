package com.exacttarget.fuelsdk.soap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETContentAreaService;
import com.exacttarget.fuelsdk.ETEmailService;
import com.exacttarget.fuelsdk.ETFolderService;
import com.exacttarget.fuelsdk.ETListSubscriberService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETContentArea;
import com.exacttarget.fuelsdk.model.ETEmail;
import com.exacttarget.fuelsdk.model.ETFolder;
import com.exacttarget.fuelsdk.model.ETListSubscriber;

public class ETServiceImplTest {

	
	
	protected ETClient client = null;
	protected ETConfiguration configuration = null;

    @Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
    }
	
	@Test
	@Ignore
	public void TestContentAreaService() throws ETSdkException
	{
		ETContentAreaService service = new ETContentAreaServiceImpl();
		ETServiceResponse<ETContentArea> response =  service.get(client);
		
		for (ETContentArea ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	@Ignore
	public void TestFolderService() throws ETSdkException
	{
		ETFolderService service = new ETFolderServiceImpl();
		ETServiceResponse<ETFolder> response =  service.get(client);
		
		for (ETFolder ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	@Ignore
	public void TestEmailService() throws ETSdkException
	{
		ETEmailService service = new ETEmailServiceImpl();
		ETServiceResponse<ETEmail> response =  service.get(client);
		
		for (ETEmail ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	@Test
	@Ignore
	public void TestListSubscriberService() throws ETSdkException
	{
		ETListSubscriberService service = new ETListSubscriberServiceImpl();
		ETServiceResponse<ETListSubscriber> response =  service.get(client);
		
		for (ETListSubscriber ret : response.getResults()) {
			System.out.println(ret.toString());
		}
	}
	
	

}
