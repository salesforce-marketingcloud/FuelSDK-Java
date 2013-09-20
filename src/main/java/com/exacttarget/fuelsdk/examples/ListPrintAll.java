//
// ListPrintAll.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.examples;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETConfiguration;
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.model.ETList;

public class ListPrintAll {
    private static final String NAME = ListPrintAll.class.getSimpleName();
    private static final String[] ARGS = {};

    public static void main(String[] args)
        throws ETSdkException
    {
        if (args.length != ARGS.length) {
            System.out.print("Usage: " + NAME);
            for (String arg : ARGS) {
                System.out.print(" " + arg);
            }
            System.out.println();
            System.exit(1);
        }

        ETConfiguration configuration = new ETConfiguration();

        ETClient client = new ETClient(configuration);

        ETListService service = client.getListService();

        ETServiceResponse<ETList> response = service.get(client);
        for (ETList list : response.getResults()) {
            System.out.println(list.getName());
        }
    }
}
