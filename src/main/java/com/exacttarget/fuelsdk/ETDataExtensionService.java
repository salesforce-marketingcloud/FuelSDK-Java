//
// ETDataExtensionService.java -
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
import com.exacttarget.fuelsdk.model.ETDataExtension;

public interface ETDataExtensionService extends ETCrudService {

	public ETServiceResponse<ETDataExtension> get(ETClient client)
	        throws ETSdkException;

	public ETServiceResponse<ETDataExtension> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETServiceResponse<ETDataExtension> post(ETClient client, ETDataExtension dataExtension)
	    	throws ETSdkException;

    public ETServiceResponse<ETDataExtension> patch(ETClient client, ETDataExtension dataExtension)
    		throws ETSdkException;

    public ETServiceResponse<ETDataExtension> delete(ETClient client, ETDataExtension dataExtension)
	    	throws ETSdkException;
    
    public ETServiceResponse<ETDataExtension> post(ETClient client, List<ETDataExtension> dataExtensions)
	    	throws ETSdkException;

    public ETServiceResponse<ETDataExtension> patch(ETClient client, List<ETDataExtension> dataExtensions)
    		throws ETSdkException;

    public ETServiceResponse<ETDataExtension> delete(ETClient client, List<ETDataExtension> dataExtensions)
	    	throws ETSdkException;
}
