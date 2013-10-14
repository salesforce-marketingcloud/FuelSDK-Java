//
// ETFolderService.java -
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
import com.exacttarget.fuelsdk.model.ETFolder;

public interface ETFolderService extends ETCrudService {

	public ETServiceResponse<ETFolder> get(ETClient client)
	        throws ETSdkException;

	public ETServiceResponse<ETFolder> get(ETClient client, ETFilter filter)
	        throws ETSdkException;

	public ETServiceResponse<ETFolder> post(ETClient client, ETFolder folder)
	    	throws ETSdkException;

	public ETServiceResponse<ETFolder> patch(ETClient client, ETFolder folder)
			throws ETSdkException;

	public ETServiceResponse<ETFolder> delete(ETClient client, ETFolder folder)
	    	throws ETSdkException;
	
	public ETServiceResponse<ETFolder> post(ETClient client, List<ETFolder> folders)
	    	throws ETSdkException;

	public ETServiceResponse<ETFolder> patch(ETClient client, List<ETFolder> folders)
			throws ETSdkException;

	public ETServiceResponse<ETFolder> delete(ETClient client, List<ETFolder> folders)
	    	throws ETSdkException;

}
