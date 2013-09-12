package com.exacttarget.fuelsdk.soap;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETEmailSendDefinitionService;
import com.exacttarget.fuelsdk.ETEmailService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETSendClassificationService;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETDataSourceType;
import com.exacttarget.fuelsdk.model.ETEmail;
import com.exacttarget.fuelsdk.model.ETEmailSendDefinition;
import com.exacttarget.fuelsdk.model.ETSendClassification;
import com.exacttarget.fuelsdk.model.ETSendDefinitionList;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETEmailSendDefinitionTest {

	protected static Logger logger = Logger.getLogger(ETEmailSendDefinitionTest.class);
	protected static ETEmailSendDefinitionService service;
	protected static ETClient client = null;
	protected static ETConfiguration configuration = null;
	
	private String NewSendDefinitionName = "PHPSDKSendDefinition";
	private String SendableDataExtensionCustomerKey = "DaveTSDE";
	private String EmailIDForSendDefinition = "3113962";
	private int ListIDForSendDefinition = 1667355;
	private String SendClassificationCustomerKey = "DynamicFromTest"; //"1180";
	
	@BeforeClass
	public static void setUp() throws ETSdkException {
		logger.debug("SetUp");
		configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
		
		service = new ETEmailSendDefinitionServiceImpl();
	}
	
	@Test
	public void A_TestRetrieve() throws ETSdkException {
		
		ETServiceResponse<ETEmailSendDefinition> response = service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETEmailSendDefinition sendDefinition : response.getResults()) {
			System.out.println(sendDefinition);
		}
	}
	
	@Test
	public void B_TestPost() throws ETSdkException {
		
		ETSendClassificationService sendClassService = new ETSendClassificationServiceImpl();
		ETSendClassification sendClassification = sendClassService.get(client).getResults().get(0);
		
		ETSendDefinitionList sendDefinitionList = new ETSendDefinitionList();
		sendDefinitionList.setCustomerKey(SendableDataExtensionCustomerKey);
		sendDefinitionList.setDataSourceTypeID(ETDataSourceType.CUSTOM_OBJECT);
		
		List<ETSendDefinitionList> sendDefinitionListCollection = new ArrayList<ETSendDefinitionList>();
		sendDefinitionListCollection.add(sendDefinitionList);
		
		ETEmailService emailService = new ETEmailServiceImpl();
		ETServiceResponse<ETEmail> emailResponse = emailService.get(client);
		
		ETEmailSendDefinition emailSendDefinition = new ETEmailSendDefinition();
		emailSendDefinition.setName(NewSendDefinitionName);
		emailSendDefinition.setCustomerKey(NewSendDefinitionName);
		emailSendDefinition.setDescription(NewSendDefinitionName);
		emailSendDefinition.setSendClassification(sendClassification);
		emailSendDefinition.setSendDefinitionList(sendDefinitionListCollection);
		emailSendDefinition.setEmail(emailResponse.getResults().get(0));
		
		ETServiceResponse<ETEmailSendDefinition> response = service.post(client, emailSendDefinition);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
	@Test
	public void C_TestDelete() throws ETSdkException {
	
		ETEmailSendDefinition emailSendDefinition = new ETEmailSendDefinition();
		emailSendDefinition.setCustomerKey(NewSendDefinitionName);
		
		ETServiceResponse<ETEmailSendDefinition> response = service.delete(client, emailSendDefinition);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
	@Test 
	public void D_TestCreateToList() throws ETSdkException {
		
		ETSendClassificationService sendClassService = new ETSendClassificationServiceImpl();
		ETSendClassification sendClassification = sendClassService.get(client).getResults().get(0);
		
		ETSendDefinitionList sendDefinitionList = new ETSendDefinitionList();
		sendDefinitionList.setId(ListIDForSendDefinition);
		sendDefinitionList.setDataSourceTypeID(ETDataSourceType.LIST);
		
		List<ETSendDefinitionList> sendDefinitionListCollection = new ArrayList<ETSendDefinitionList>();
		sendDefinitionListCollection.add(sendDefinitionList);
		
		ETEmailService emailService = new ETEmailServiceImpl();
		ETServiceResponse<ETEmail> emailResponse = emailService.get(client);
		
		ETEmailSendDefinition emailSendDefinition = new ETEmailSendDefinition();
		emailSendDefinition.setName(NewSendDefinitionName);
		emailSendDefinition.setCustomerKey(NewSendDefinitionName);
		emailSendDefinition.setDescription(NewSendDefinitionName);
		emailSendDefinition.setSendClassification(sendClassification);
		emailSendDefinition.setSendDefinitionList(sendDefinitionListCollection);
		emailSendDefinition.setEmail(emailResponse.getResults().get(0));
		
		ETServiceResponse<ETEmailSendDefinition> response = service.post(client, emailSendDefinition);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
	@Test
	public void E_TestSend() throws ETSdkException {
		
		ETEmailSendDefinition emailSendDefinition = new ETEmailSendDefinition();
		emailSendDefinition.setCustomerKey(NewSendDefinitionName);
		
		ETServiceResponse<ETEmailSendDefinition> response = service.send(client, emailSendDefinition);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
}
