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
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An <code>ETDataExtract</code> object can perform data extract activity for data extension and tracking data
 * in the Salesforce Marketing Cloud.
 */

public class ETDataExtract 
{
    private Soap soap;
    
    public HashMap<String, String> extractType;
    private SimpleDateFormat dateFormat;
    
    private Date StartDate; 
    private Date EndDate; 
    
    private String DECustomerKey;
    private String _AsyncID;
    private String OutputFileName;
    
    private boolean HasColumnHeaders;
    private boolean ExtractBounces;
    private boolean ExtractClicks;
    private boolean ExtractConversions;
    private boolean ExtractOpens;
    private boolean ExtractSends;
    private boolean ExtractSendJobs;
    private boolean ExtractSurveyResponses;
    private boolean IncludeTestSends;
    private boolean ExtractUnsubs;
    
    /** 
    * Class constructor, Initializes a new instance of the class.
    * @param client         the ETCleint object
    */     
    public ETDataExtract(ETClient client)
    {
        try {
            extractType = new HashMap<String, String>();
            
            soap = client.getSoapConnection().getSoap("Extract");  
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
            
            ExtractBounces = false;
            ExtractClicks = true;
            ExtractConversions = true;
            ExtractOpens = true;
            ExtractSendJobs = true;
            ExtractSends = true;
            ExtractSurveyResponses = true;
            IncludeTestSends = false;
            ExtractUnsubs = true;
            HasColumnHeaders = true;
            _AsyncID = "0";            
            
            populateExtractType(client);
            
        } catch (Exception ex) {
            Logger.getLogger(ETDataExtract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sends a data extension extract request. 
     * @param deCustomerKey         the customer key of the data extension 
     * @param outputFileName        the output file name
     * @param hasColumnHeaders      true if the output file should contain column headers, false otherwise
     * @return                      ExtractResponseMsg object which holds the status, request ID, etc
     * @throws Exception 
     */
    public ExtractResponseMsg extractDataExtension( String deCustomerKey, String outputFileName, boolean hasColumnHeaders) throws Exception {
        setDECustomerKey(deCustomerKey);
        setHasColumnHeaders(hasColumnHeaders);
        setOutputFileName(outputFileName);
        return extractDataExtension();        
    }
    
    /**
     * Sends a data extension extract request. 
     * @return  ExtractResponseMsg object which holds the status, request ID, etc
     * @throws Exception 
     */
    public ExtractResponseMsg extractDataExtension() throws Exception{
        validate();

        String dateInString = "2017-08-01 12:00 AM";
        StartDate = dateFormat.parse(dateInString);         
        EndDate = dateFormat.parse(dateInString);  
        
        String[] paramNames = { "DECustomerKey", "HasColumnHeaders", "_AsyncID", "OutputFileName", "StartDate", "EndDate" };
        return performDataExtract("Data Extension Extract", paramNames);
    }
    
    /**
     * Sends an extract request for tracking data. 
     * @return  ExtractResponseMsg object which holds the status, request ID, etc
     * @throws Exception 
     */    
    public ExtractResponseMsg extractTrackingData() throws Exception
    {
        validateTrackingDataParams();
        String[] paramNames = {"ExtractClicks", "ExtractBounces","ExtractConversions","ExtractSendJobs","ExtractSends","ExtractSurveyResponses","IncludeTestSends","ExtractUnsubs", "ExtractOpens", "OutputFileName", "StartDate", "EndDate"};
        return performDataExtract("Tracking Extract", paramNames);
    }    
    
    private ExtractResponseMsg performDataExtract(String extractName, String[] paramNames) throws Exception
    {
        
        ArrayList<ExtractParameter> extractParameters = new ArrayList();
        ExtractParameter extractParam = null;
        Class obj = ETDataExtract.class;
        
        for(String param: paramNames){
            Field field = obj.getDeclaredField(param);
            Object value = field.get(this);
            extractParam = new ExtractParameter();

            extractParam.setName(field.getName());
            if(value instanceof Date){
                extractParam.setValue(dateFormat.format(value));            
            }
            else if(value != null)
                extractParam.setValue(value.toString());
            else
                continue;
            
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
    
    private void validate() throws ETSdkException
    {
        if(this.getDECustomerKey()==null || this.getOutputFileName()==null)
            throw new ETSdkException("Customer Key and Output file name needs to be set.");
        String ext = this.getOutputFileName().toLowerCase();
        if(!ext.endsWith(".csv") && !ext.endsWith(".zip"))
            throw new ETSdkException("Invalid file extension. Only csv or zip allowed.");
    }
    
    private void validateTrackingDataParams() throws ETSdkException
    {
        if(this.getOutputFileName()==null || this.getOutputFileName().trim()=="")
            throw new ETSdkException("Output file name can not be empty or null.");
        String ext = this.getOutputFileName().toLowerCase();
        if(!ext.endsWith(".zip"))
            throw new ETSdkException("Invalid file extension. Only zip allowed.");
        
    }
    
    
    private void populateExtractType(ETClient client) throws ETSdkException
    {
        ETResponse<ETExtractDescription> response = client.retrieve(ETExtractDescription.class);
        for(ETResult<ETExtractDescription> r : response.getResults()){
            extractType.put(r.getObject().getName(), r.getObject().getId());
        }    
    }    

    /**
     * @return the outputFileName
     */
    public String getOutputFileName() {
        return OutputFileName;
    }

    /**
     * @param outputFileName the outputFileName to set
     */
    public void setOutputFileName(String outputFileName) {
        this.OutputFileName = outputFileName;
    }

    /**
     * @param StartDate the StartDate to set
     */
    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    /**
     * @param EndDate the EndDate to set
     */
    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    /**
     * @return the DECustomerKey
     */
    public String getDECustomerKey() {
        return DECustomerKey;
    }

    /**
     * @param DECustomerKey the DECustomerKey to set
     */
    public void setDECustomerKey(String DECustomerKey) {
        this.DECustomerKey = DECustomerKey;
    }

    /**
     * @return the ExtractBounces
     */
    public boolean isExtractBounces() {
        return ExtractBounces;
    }

    /**
     * @param ExtractBounces the ExtractBounces to set
     */
    public void setExtractBounces(boolean ExtractBounces) {
        this.ExtractBounces = ExtractBounces;
    }

    /**
     * @return the ExtractClicks
     */
    public boolean isExtractClicks() {
        return ExtractClicks;
    }

    /**
     * @param ExtractClicks the ExtractClicks to set
     */
    public void setExtractClicks(boolean ExtractClicks) {
        this.ExtractClicks = ExtractClicks;
    }

    /**
     * @return the ExtractConversions
     */
    public boolean isExtractConversions() {
        return ExtractConversions;
    }

    /**
     * @param ExtractConversions the ExtractConversions to set
     */
    public void setExtractConversions(boolean ExtractConversions) {
        this.ExtractConversions = ExtractConversions;
    }

    /**
     * @return the ExtractOpens
     */
    public boolean isExtractOpens() {
        return ExtractOpens;
    }

    /**
     * @param ExtractOpens the ExtractOpens to set
     */
    public void setExtractOpens(boolean ExtractOpens) {
        this.ExtractOpens = ExtractOpens;
    }

    /**
     * @return the ExtractSends
     */
    public boolean isExtractSends() {
        return ExtractSends;
    }

    /**
     * @param ExtractSends the ExtractSends to set
     */
    public void setExtractSends(boolean ExtractSends) {
        this.ExtractSends = ExtractSends;
    }

    /**
     * @return the ExtractSendJobs
     */
    public boolean isExtractSendJobs() {
        return ExtractSendJobs;
    }

    /**
     * @param ExtractSendJobs the ExtractSendJobs to set
     */
    public void setExtractSendJobs(boolean ExtractSendJobs) {
        this.ExtractSendJobs = ExtractSendJobs;
    }

    /**
     * @return the ExtractSurveyResponses
     */
    public boolean isExtractSurveyResponses() {
        return ExtractSurveyResponses;
    }

    /**
     * @param ExtractSurveyResponses the ExtractSurveyResponses to set
     */
    public void setExtractSurveyResponses(boolean ExtractSurveyResponses) {
        this.ExtractSurveyResponses = ExtractSurveyResponses;
    }

    /**
     * @return the IncludeTestSends
     */
    public boolean isIncludeTestSends() {
        return IncludeTestSends;
    }

    /**
     * @param IncludeTestSends the IncludeTestSends to set
     */
    public void setIncludeTestSends(boolean IncludeTestSends) {
        this.IncludeTestSends = IncludeTestSends;
    }

    /**
     * @return the ExtractUnsubs
     */
    public boolean isExtractUnsubs() {
        return ExtractUnsubs;
    }

    /**
     * @param ExtractUnsubs the ExtractUnsubs to set
     */
    public void setExtractUnsubs(boolean ExtractUnsubs) {
        this.ExtractUnsubs = ExtractUnsubs;
    }

    /**
     * @return the StartDate
     */
    public Date getStartDate() {
        return StartDate;
    }

    /**
     * @return the EndDate
     */
    public Date getEndDate() {
        return EndDate;
    }

    /**
     * @return the _AsyncID
     */
    public String getAsyncID() {
        return _AsyncID;
    }

    /**
     * @param _AsyncID the _AsyncID to set
     */
    private void setAsyncID(String _AsyncID) {
        this._AsyncID = _AsyncID;
    }

    /**
     * @return the HasColumnHeaders
     */
    public boolean isHasColumnHeaders() {
        return HasColumnHeaders;
    }

    /**
     * @param HasColumnHeaders the HasColumnHeaders to set
     */
    public void setHasColumnHeaders(boolean HasColumnHeaders) {
        this.HasColumnHeaders = HasColumnHeaders;
    }
    
}
