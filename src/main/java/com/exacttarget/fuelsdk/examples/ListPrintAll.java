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
import com.exacttarget.fuelsdk.ETListService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.soap.ETListServiceImpl;

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

        ETClient client = new ETClient();

        ETListService service = new ETListServiceImpl();

        ETResponse<ETList> response = service.get(client);
        for (ETList list : response.getResults()) {
            System.out.println(list.getName());
        }
    }
}
