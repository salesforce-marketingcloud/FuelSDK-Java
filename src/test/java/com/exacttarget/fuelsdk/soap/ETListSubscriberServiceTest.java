//
// ETListSubscriberServiceTest.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETListSubscriberService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETSubscriberService;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListSubscriber;
import com.exacttarget.fuelsdk.model.ETListType;
import com.exacttarget.fuelsdk.model.ETSubscriber;
import com.exacttarget.fuelsdk.model.ETSubscriberList;
import com.exacttarget.fuelsdk.model.ETSubscriberStatus;


@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETListSubscriberServiceTest {
	
	protected static Logger logger = Logger.getLogger(ETListSubscriberServiceTest.class);
	
	protected ETListSubscriberService service;
	protected ETClient client = null;
	protected ETConfiguration configuration = null;
	
	protected ETList list;
	protected ETSubscriber subscriber;
	protected ETListService listService;
	protected ETSubscriberService subscriberService;
	
	private String NewListName = "JavaSDKListSubscriberTestCreate";
	private String SubscriberTestEmail = "JavaSDKListSubscriberTest@bh.exacttarget.com";
	
	@Before
    public void setUp()
        throws ETSdkException
    {
        configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
        
		service = new ETListSubscriberServiceImpl();
		listService = new ETListServiceImpl();
		subscriberService = new ETSubscriberServiceImpl();
		
		// Create a List
		ETList createdList = new ETList();
		createdList.setCustomerKey(NewListName);
		createdList.setName(NewListName);
		createdList.setDescription("This list was created with the JavaSDK");
		createdList.setListType(ETListType.PRIVATE);

		ETList foundList = listService.post(client, createdList).getResults().get(0);
		
		list = new ETList();
		list.setId(foundList.getId());
		
		
	}
	
	@Test
	public void A_TestAddSubscriberToList() throws ETSdkException {
		
		// Create a Subscriber to a List

		ETSubscriberList subscriberList = new ETSubscriberList();
		subscriberList.setId(list.getId());
		subscriberList.setStatus(ETSubscriberStatus.ACTIVE);
		
		subscriber = new ETSubscriber();
		subscriber.setEmailAddress(SubscriberTestEmail);
		subscriber.setSubscriberKey(SubscriberTestEmail);
		subscriber.setLists(Arrays.asList(subscriberList));
		
		ETResponse<ETSubscriber> response = subscriberService.post(client, subscriber);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
	}
	
	@Test
	public void B_TestGetCollectionSubscribersOnListService() throws ETSdkException {
		
		ETFilter filter = new ETSimpleFilter("ListID", ETFilterOperators.EQUALS, Arrays.asList(""+list.getId()));
		ETResponse<ETListSubscriber> response =  service.get(client, filter);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETListSubscriber ret : response.getResults()) {
			logger.debug(ret.toString());
		}
	}
	
	
	@After
	public void TareDown() throws ETSdkException {
		
		try {
			// Delete the List
			ETList createdList = new ETList();
			createdList.setCustomerKey(NewListName);
			
			listService.delete(client, createdList);
			
			// Delete the Subscriber
			ETSubscriber subscriberDelete = new ETSubscriber();
			subscriberDelete.setEmailAddress(SubscriberTestEmail);
			subscriberDelete.setSubscriberKey(SubscriberTestEmail);
			
			subscriberService.delete(client, subscriberDelete);
		} catch(Exception e) {}
	}
}


