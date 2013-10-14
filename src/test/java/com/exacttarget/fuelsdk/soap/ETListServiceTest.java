//
// ETListServiceTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListType;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETListServiceTest {
	
	protected static Logger logger = Logger.getLogger(ETListServiceTest.class);
	
	protected ETListService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	private String NameOfTestList = "JavaSDKListTest";
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
		service = new ETListServiceImpl();
	}
	
	@Test
	public void A_TestGetCollectionService() throws ETSdkException
	{
		ETServiceResponse<ETList> response =  service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETList ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
	
	@Test
	public void B_TestPost() throws ETSdkException {
		
		ETList list = new ETList();
		list.setCustomerKey(NameOfTestList);
		list.setName(NameOfTestList);
		list.setDescription("This list was created with the JavaSDK");
		list.setListType(ETListType.PRIVATE);

		ETServiceResponse<ETList> response = service.post(client, list);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestList);
		ETServiceResponse<ETList> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		
		for(ETList orgFound : responseFound.getResults()) {
			logger.debug(orgFound.toString());
		}
		
	}
	
	@Test
	public void C_TestPatch() throws ETSdkException {
		
		ETList list = new ETList();
		list.setCustomerKey(NameOfTestList);
		list.setDescription("New Description");
		
		ETServiceResponse<ETList> response = service.patch(client, list);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestList);
		ETServiceResponse<ETList> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		Assert.assertEquals("New Description", responseFound.getResults().get(0).getDescription());
		
	}
	
	@Test
	public void D_TestDelete() throws ETSdkException {
		
		ETList list = new ETList();
		list.setCustomerKey(NameOfTestList);
		
		ETServiceResponse<ETList> response = service.delete(client, list);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was deleted
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestList);
		ETServiceResponse<ETList> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(0, responseFound.getResults().size());
		
	}
	
	@Test
	public void E_TestBatchPost() throws ETSdkException {
		
		List<ETList> lists = new ArrayList<ETList>();
		
		ETList list = new ETList();
		list.setCustomerKey(NameOfTestList);
		list.setName(NameOfTestList);
		list.setDescription("This list was created with the JavaSDK");
		list.setListType(ETListType.PRIVATE);
		lists.add(list);
		
		ETList list2 = new ETList();
		list2.setCustomerKey(NameOfTestList + "2");
		list2.setName(NameOfTestList);
		list2.setDescription("This list was created with the JavaSDK");
		list2.setListType(ETListType.PRIVATE);
		lists.add(list2);
		
		ETList list3 = new ETList();
		list3.setCustomerKey(NameOfTestList + "3");
		list3.setName(NameOfTestList);
		list3.setDescription("This list was created with the JavaSDK");
		list3.setListType(ETListType.PRIVATE);
		lists.add(list3);

		ETServiceResponse<ETList> response = service.post(client, lists);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was created
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestList);
		ETServiceResponse<ETList> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(1, responseFound.getResults().size());
		
		for(ETList orgFound : responseFound.getResults()) {
			logger.debug(orgFound.toString());
		}
		
	}
	
	@Test
	public void F_TestBatchDelete() throws ETSdkException {
		
		List<ETList> lists = new ArrayList<ETList>();
		
		ETList list = new ETList();
		list.setCustomerKey(NameOfTestList);
		lists.add(list);
		ETList list2 = new ETList();
		list2.setCustomerKey(NameOfTestList + "2");
		lists.add(list2);
		
		ETList list3 = new ETList();
		list3.setCustomerKey(NameOfTestList + "3");
		lists.add(list3);
		
		
		ETServiceResponse<ETList> response = service.delete(client, lists);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
		// Test it was deleted
		ETFilter filter = new ETSimpleFilter("CustomerKey", ETFilterOperators.EQUALS, NameOfTestList);
		ETServiceResponse<ETList> responseFound = service.get(client, filter);
		
		Assert.assertNotNull(responseFound);
		Assert.assertTrue(responseFound.getStatus());
		Assert.assertNotNull(responseFound.getResults());
		Assert.assertEquals(0, responseFound.getResults().size());
		
	}
}


