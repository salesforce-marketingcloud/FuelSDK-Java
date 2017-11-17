package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.ETRestConnection.Response;

public interface IETRestConnection {
    Response get(String path) throws ETSdkException;
    Response post(String path, String payload) throws ETSdkException;
    Response patch(String path, String payload) throws ETSdkException;
    Response delete(String path) throws ETSdkException;
}
