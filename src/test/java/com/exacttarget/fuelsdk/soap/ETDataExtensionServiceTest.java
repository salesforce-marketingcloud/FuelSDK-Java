package com.exacttarget.fuelsdk.soap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETDataExtensionColumnService;
import com.exacttarget.fuelsdk.ETDataExtensionRowService;
import com.exacttarget.fuelsdk.ETDataExtensionService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETFilterOperators;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETDataExtension;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;
import com.exacttarget.fuelsdk.model.ETDataExtensionFieldType;
import com.exacttarget.fuelsdk.model.ETDataExtensionRow;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ETDataExtensionServiceTest {

	protected static Logger logger = Logger.getLogger(ETDataExtensionServiceTest.class);
	protected static ETDataExtensionService service;
	protected static ETClient client = null;
	protected static ETConfiguration configuration = null;
	
	private String DataExtensionNameForTesting = "JavaSDKTestDE";
	
	
	@BeforeClass
	public static void setUp() throws ETSdkException {
		logger.debug("SetUp");
		configuration = new ETConfiguration("/fuelsdk-test.properties");
        client = new ETClient(configuration);
		
		service = new ETDataExtensionServiceImpl();
	}
	
	
	@Test
	public void A_TestGet() throws ETSdkException {
		
		ETServiceResponse<ETDataExtension> response = service.get(client);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for(ETDataExtension de : response.getResults()) {
			
			System.out.println(de.toString());
		}
		
		
	}
	
	
	
	@Test
	public void B_TestPost() throws ETSdkException {
		
		List<ETDataExtensionColumn> columns = new ArrayList<ETDataExtensionColumn>();
		ETDataExtensionColumn col1 = new ETDataExtensionColumn();
		col1.setName("Key");
		col1.setFieldType(ETDataExtensionFieldType.TEXT);
		col1.setIsPrimaryKey(true);
		col1.setMaxLength(100);
		col1.setIsRequired(true);
		
		ETDataExtensionColumn col2 = new ETDataExtensionColumn();
		col2.setName("Value");
		col2.setFieldType(ETDataExtensionFieldType.TEXT);
		
		columns.add(col1);
		columns.add(col2);
		
		ETDataExtension de = new ETDataExtension();
		de.setName(DataExtensionNameForTesting);
		de.setCustomerKey(DataExtensionNameForTesting);
		de.setColumns(columns);
		
		ETServiceResponse<ETDataExtension> response = service.post(client, de);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
	
	@Test
	public void C_TestPatch() throws ETSdkException {
		
		List<ETDataExtensionColumn> columns = new ArrayList<ETDataExtensionColumn>();
		ETDataExtensionColumn col1 = new ETDataExtensionColumn();
		col1.setName("AnExtraField");
		col1.setFieldType(ETDataExtensionFieldType.TEXT);
		
		columns.add(col1);
		
		ETDataExtension de = new ETDataExtension();
		de.setName(DataExtensionNameForTesting);
		de.setCustomerKey(DataExtensionNameForTesting);
		de.setColumns(columns);
		
		ETServiceResponse<ETDataExtension> response = service.patch(client, de);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
	
	@Test
	public void D_TestGetSingleDE() {
		
	}
	
	
	@Test 
	public void E_TestGetDataExtensionColumns () throws ETSdkException {
		
		ETFilter filter = new ETSimpleFilter("DataExtension.CustomerKey", ETFilterOperators.EQUALS, Arrays.asList(DataExtensionNameForTesting));
		
		ETDataExtensionColumnService columnService = new ETDataExtensionColumnServiceImpl();
		ETServiceResponse<ETDataExtensionColumn> response = columnService.get(client, filter);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		Assert.assertNotNull(response.getResults());
		
		for (ETDataExtensionColumn col : response.getResults()) {
			
			System.out.println(col);
			
		}
	}
	
	
	@Test
	public void F_TestAddRowToDataExtension() throws ETSdkException {
	
		ETDataExtensionRowService rowService = new ETDataExtensionRowServiceImpl();
		
		Map<String, String> columns = new HashMap<String, String>();
		columns.put("Key", "JavaSDKTEST");
		columns.put("Value", "ItWorks!");
		
		ETDataExtensionRow row = new ETDataExtensionRow();
		row.setColumns(columns);
		row.setName(DataExtensionNameForTesting);
		
		ETServiceResponse<ETDataExtensionRow> response = rowService.post(client, row);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
	}
	
	
	@Test
	public void G_TestAddRowToDataExtensionByCustomerKey() throws ETSdkException {
		
		ETDataExtensionRowService rowService = new ETDataExtensionRowServiceImpl();
		
		Map<String, String> columns = new HashMap<String, String>();
		columns.put("Key", "JavaSDKTEST2");
		columns.put("Value", "ItWorks!");
		
		ETDataExtensionRow row = new ETDataExtensionRow();
		row.setColumns(columns);
		row.setCustomerKey(DataExtensionNameForTesting);
		
		ETServiceResponse<ETDataExtensionRow> response = rowService.post(client, row);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
	}
	
	
	@Test
	public void H_TestGetAllRowsFromDataExtension() throws ETSdkException {
		
		ETDataExtensionRowService rowService = new ETDataExtensionRowServiceImpl();
		
		ETServiceResponse<ETDataExtensionRow> response = rowService.get(client, DataExtensionNameForTesting, Arrays.asList("Key", "Value"));
		
		Assert.assertNotNull(response.getResults());
		Assert.assertTrue(response.getStatus());
		
		for (ETDataExtensionRow row : response.getResults()) {
			
			System.out.println(row);
			
		}
	}
	
	
	@Test
	public void I_TestUpdateRow() throws ETSdkException {
		
		ETDataExtensionRowService rowService = new ETDataExtensionRowServiceImpl();
		
		Map<String, String> columns = new HashMap<String, String>();
		columns.put("Key", "JavaSDKTEST2");
		columns.put("Value", "ItWorks!  Updated!");
		
		ETDataExtensionRow row = new ETDataExtensionRow();
		row.setColumns(columns);
		row.setCustomerKey(DataExtensionNameForTesting);
		
		ETServiceResponse<ETDataExtensionRow> response = rowService.patch(client, row);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
	}
	
	
	@Test
	public void J_TestGetRowsWithFilter() throws ETSdkException {
		
		ETDataExtensionRowService rowService = new ETDataExtensionRowServiceImpl();
		
		ETFilter filter = new ETSimpleFilter("Key", ETFilterOperators.EQUALS, Arrays.asList("JavaSDKTEST2"));
		
		ETServiceResponse<ETDataExtensionRow> response = rowService.get(client, DataExtensionNameForTesting, Arrays.asList("Key", "Value"), filter);
		
		Assert.assertNotNull(response.getResults());
		Assert.assertTrue(response.getStatus());
		
		for (ETDataExtensionRow row : response.getResults()) {
			
			System.out.println(row);
			
		}
	}
	
	@Test
	public void K_TestDeleteRow() throws ETSdkException {
		
		ETDataExtensionRowService rowService = new ETDataExtensionRowServiceImpl();
		
		Map<String, String> keys = new HashMap<String, String>();
		keys.put("Key", "JavaSDKTEST2");
		keys.put("Value", "ItWorks!  Updated!");
		
		ETDataExtensionRow row = new ETDataExtensionRow();
		row.setKeys(keys);
		row.setCustomerKey(DataExtensionNameForTesting);
		
		ETServiceResponse<ETDataExtensionRow> response = rowService.delete(client, row);
		
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getStatus());
		
	}
	
	@Test
	public void L_TestDeleteDataExtension() throws ETSdkException {
		
		ETDataExtension dataExtension = new ETDataExtension();
		dataExtension.setCustomerKey(DataExtensionNameForTesting);
		dataExtension.setName(DataExtensionNameForTesting);
		
		ETServiceResponse<ETDataExtension> response = service.delete(client, dataExtension);
		Assert.assertTrue(response.getStatus());
		
	}
	
	
}
