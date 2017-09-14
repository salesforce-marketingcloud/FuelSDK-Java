/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.internal.ExtractOptions;
import com.exacttarget.fuelsdk.internal.ExtractParameter;
import com.exacttarget.fuelsdk.internal.ExtractRequest;
import com.exacttarget.fuelsdk.internal.ExtractRequestMsg;
import com.exacttarget.fuelsdk.internal.ExtractResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ETDataExtract {
    private ETClient client;
    private Soap soap;
    public HashMap<String, String> extractType;
    public HashMap<String, String> parameters;
    
    private String deCustomerKey;
    private String asyncID;
    private boolean hasColumnHeaders;
    private String startDate;
    private String endDate;
    private String outputFileName;
    
    
    public ETDataExtract(){
        try {
            client = new ETClient("fuelsdk.properties");
            extractType = new HashMap<String, String>();
            parameters = new HashMap<String, String>();

            soap = client.getSoapConnection().getSoap("Extract");            
            
            asyncID = "0";
            hasColumnHeaders = true;
            startDate = "2017-06-01 12:00:00 AM";
            endDate = "2017-08-01 12:00:00 AM";
            populateExtractType();
            
        } catch (ETSdkException ex) {
            Logger.getLogger(ETDataExtract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateExtractType() throws ETSdkException{
        ETResponse<ETExtractDescription> response = client.retrieve(ETExtractDescription.class);
        for(ETResult<ETExtractDescription> r : response.getResults()){
            //System.out.print("ID="+ r.getObject().getId());  
            //System.out.println(", Name="+ r.getObject().getName()); 
            extractType.put(r.getObject().getName(), r.getObject().getId());
        }    
    }
    
    public ExtractResponseMsg dataExtract(String extractName) throws ETSdkException
    {
        validate();
        return performDataExtract(extractName);
    }
    
    public ExtractResponseMsg doDataExtract(String extractName)
    {
        ArrayList<ExtractParameter> extractParameters = new ArrayList();
        ExtractParameter extractParam = null;
        
        for(String key: parameters.keySet()){
            extractParam = new ExtractParameter();
            extractParam.setName(key);
            extractParam.setValue(parameters.get(key));
            extractParameters.add(extractParam);
        }
        ExtractRequest.Parameters eparams = new ExtractRequest.Parameters();
        eparams.getParameter().addAll(extractParameters);        

        ExtractRequest request = new ExtractRequest();
        request.setOptions(new ExtractOptions());
        request.setID(extractType.get(extractName));
        request.setParameters(eparams);

        ExtractRequestMsg erm = new ExtractRequestMsg();
        erm.getRequests().add(request);  
        
        ExtractResponseMsg resp = soap.extract(erm);
        return resp;
    }
    
    public ExtractResponseMsg performDataExtract(String extractName)
    {
        
        ArrayList<ExtractParameter> extractParameters = new ArrayList();
        
        ExtractParameter extractParam = null;
        extractParam = new ExtractParameter();
        
        extractParam.setName("DECustomerKey");
        extractParam.setValue(this.getDeCustomerKey());
        extractParameters.add(extractParam);
    
        extractParam = new ExtractParameter();
        extractParam.setName("HasColumnHeaders");
        extractParam.setValue(isHasColumnHeaders()+"");
        extractParameters.add(extractParam);

        extractParam = new ExtractParameter();
        extractParam.setName("_AsyncID");
        extractParam.setValue(this.getAsyncID());
        extractParameters.add(extractParam);

        extractParam = new ExtractParameter();
        extractParam.setName("OutputFileName");
        extractParam.setValue(this.getOutputFileName());
        extractParameters.add(extractParam);  
        
        extractParam = new ExtractParameter();
        extractParam.setName("StartDate");
        extractParam.setValue(this.getStartDate());
        extractParameters.add(extractParam);  

        extractParam = new ExtractParameter();
        extractParam.setName("EndDate");
        extractParam.setValue(this.getEndDate());
        extractParameters.add(extractParam);    
        
        ExtractRequest.Parameters eparams = new ExtractRequest.Parameters();
        eparams.getParameter().addAll(extractParameters);
        
        ExtractRequest request = new ExtractRequest();
        request.setOptions(new ExtractOptions());
        request.setID(extractType.get(extractName));
        request.setParameters(eparams);
        
        ExtractRequestMsg erm = new ExtractRequestMsg();
        erm.getRequests().add(request);  
        
        ExtractResponseMsg resp = soap.extract(erm);
        return resp;
    }
    
    private void validate() throws ETSdkException
    {
        if(this.getDeCustomerKey()==null || this.getOutputFileName()==null)
            throw new ETSdkException("Customer Key and Output file name needs to be set.");
        String ext = this.getOutputFileName().toLowerCase();
        if(!ext.endsWith(".csv") && !ext.endsWith(".zip"))
            throw new ETSdkException("Invalid file extension. Only csv or zip allowed.");
    }
    
    /**
     * @return the client
     */
    public ETClient getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(ETClient client) {
        this.client = client;
    }

    /**
     * @return the soap
     */
    public Soap getSoap() {
        return soap;
    }

    /**
     * @param soap the soap to set
     */
    public void setSoap(Soap soap) {
        this.soap = soap;
    }

    /**
     * @return the deCustomerKey
     */
    public String getDeCustomerKey() {
        return deCustomerKey;
    }

    /**
     * @param deCustomerKey the deCustomerKey to set
     */
    public void setDeCustomerKey(String deCustomerKey) {
        this.deCustomerKey = deCustomerKey;
    }

    /**
     * @return the asyncID
     */
    public String getAsyncID() {
        return asyncID;
    }

    /**
     * @param asyncID the asyncID to set
     */
    public void setAsyncID(String asyncID) {
        this.asyncID = asyncID;
    }

    /**
     * @return the hasColumnHeaders
     */
    public boolean isHasColumnHeaders() {
        return hasColumnHeaders;
    }

    /**
     * @param hasColumnHeaders the hasColumnHeaders to set
     */
    public void setHasColumnHeaders(boolean hasColumnHeaders) {
        this.hasColumnHeaders = hasColumnHeaders;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the outputFileName
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @param outputFileName the outputFileName to set
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    
}
