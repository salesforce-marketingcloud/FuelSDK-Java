package com.exacttarget.fuelsdk.soap;

import org.junit.Before;

import com.exacttarget.fuelsdk.ETGetServiceTest;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.model.ETOpenEvent;

public class ETOpenEventTest extends ETGetServiceTest<ETOpenEvent> {

	@Before
    public void setUp()
        throws ETSdkException
    {
		super.setUp();
		
		service = new ETOpenEventServiceImpl();
		
		etObject = new ETOpenEvent();
    }
	

}
