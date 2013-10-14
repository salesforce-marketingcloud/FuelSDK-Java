//
// ETListService.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import java.util.List;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETList;

public interface ETListService extends ETCrudService
{
    public ETServiceResponse<ETList> get(ETClient client)
        throws ETSdkException;

	public ETServiceResponse<ETList> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETServiceResponse<ETList> post(ETClient client, ETList list)
	    	throws ETSdkException;

	public ETServiceResponse<ETList> patch(ETClient client, ETList list)
			throws ETSdkException;

	public ETServiceResponse<ETList> delete(ETClient client, ETList list)
	    	throws ETSdkException;
	
	public ETServiceResponse<ETList> post(ETClient client, List<ETList> lists)
			throws ETSdkException;
	
	public ETServiceResponse<ETList> patch(ETClient client, List<ETList> lists)
			throws ETSdkException;
	
	public ETServiceResponse<ETList> delete(ETClient client, List<ETList> lists)
			throws ETSdkException;

}
