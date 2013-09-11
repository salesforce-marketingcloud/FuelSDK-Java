package com.exacttarget.fuelsdk;

import org.junit.Assert;
import org.junit.Test;

import com.exacttarget.fuelsdk.model.ETObject;

public abstract class ETCrudServiceTest<T extends ETObject> extends ETGetServiceTest<ETObject> {
	
	@Test
	public void TestCRUDService() throws ETSdkException
	{
		
		TestPost();
		
		T found = TestRetrieveSingle();
		
		TestPatch(found);
		
		T foundUpdated = TestRetrieveSingleUpdated();
		
		DeleteSingle(foundUpdated);
		
	}
	
	protected T TestRetrieveSingle() throws ETSdkException {
		ETServiceResponse<T> response = (ETServiceResponse<T>) service.get(client, etObject.getClass(), filter);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
		
	}
	
	protected T TestRetrieveSingleUpdated() throws ETSdkException {
		ETServiceResponse<T> response = (ETServiceResponse<T>) service.get(client, etObject.getClass(), filterUpdated);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResults());
		Assert.assertEquals(1, response.getResults().size());
		System.out.println(response.getResults().get(0));
		return response.getResults().get(0);
	}

	protected void TestPost() throws ETSdkException
	{
		ETServiceResponse<T> response =  (ETServiceResponse<T>) ((ETCrudService)service).post(client, etObject);
	}
	
	protected abstract void TestPatch(T found) throws ETSdkException;
		
	
	protected void DeleteSingle(T etObject) throws ETSdkException
	{
				
		ETServiceResponse<T> response2 =  (ETServiceResponse<T>) ((ETCrudService)service).delete(client, etObject);
		 
	}
	
}
