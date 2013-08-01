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
import com.exacttarget.fuelsdk.ETList;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;

public class PrintAllLists {
    private static final String CLIENT_ID =
            "PUT_CLIENT_ID_HERE";
    private static final String CLIENT_SECRET =
            "PUT_CLIENT_SECRET_HERE";

    public static void main(String[] args)
        throws ETSdkException
    {
        ETConfiguration configuration = new ETConfiguration();
        configuration.setClientId(CLIENT_ID);
        configuration.setClientSecret(CLIENT_SECRET);
        ETClient client = new ETClient(configuration);
        ETListService service = client.getListService();
        ETServiceResponse<ETList> response = service.get(client);
        for (ETList list : response.getResults()) {
            System.out.println(list.getName());
        }
    }
}
