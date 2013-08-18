package com.exacttarget.fuelsdk.rest;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETObject;

public class ETCrudServiceImpl extends ETGetServiceImpl implements
		ETCrudService {

	public <T extends ETObject> ETServiceResponse<T> post(ETClient client,
			T object) throws ETSdkException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends ETObject> ETServiceResponse<T> patch(ETClient client,
			T object) throws ETSdkException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends ETObject> ETServiceResponse<T> delete(ETClient client,
			T object) throws ETSdkException {
		// TODO Auto-generated method stub
		return null;
	}

}
