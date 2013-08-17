//
// PrintAllLists.java -
//
//      Prints the names of all lists in the ExactTarget account
//      represented by the specified CLIENT_ID and CLIENT_SECRET.
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.examples;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETList;

public class PrintAllLists {
    public static void main(String[] args)
        throws ETSdkException
    {
        ETConfiguration configuration
            = new ETConfiguration("/fuelsdk-test.properties");
        ETClient client = new ETClient(configuration);
        ETListService service = client.getListService();
        ETServiceResponse<ETList> response = service.get(client);
        for (ETList list : response.getResults()) {
            System.out.println(list.getName());
        }
    }
}
