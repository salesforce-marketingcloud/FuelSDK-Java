/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * An <code>ETAsset</code> object represents aa asset
 * in the Salesforce Marketing Cloud.
 */

@RestObject(path = "/asset/v1/content/assets",
            primaryKey = "id",
            collection = "items",
            totalCount = "count")
public class ETAsset  extends ETRestObject{


    @Expose
    @ExternalName("id")
    private String id = null;
    @Expose
    @ExternalName("name")
    private String name = null;
    @Expose
    @ExternalName("description")
    private String description = null;
    
    @Expose
    @ExternalName("customerKey")
    private String customerKey = null;
    @Expose
    @ExternalName("objectID")
    private String objectID = null;
    @Expose
    @ExternalName("contentType")
    private String contentType = null;
    @Expose
    @ExternalName("enterpriseId")
    private String enterpriseId = null;    
    @Expose
    @ExternalName("memberId")
    private String memberId = null; 
    @Expose
    @ExternalName("content")
    private String content = null;     
    
    @Expose
    @ExternalName("createdDate")
    private Date createdDate = null;
    @Expose
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
        
    
    /** 
    * @return The Identifier of the ETAsset object.
    */    
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETAsset object.
    */    
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @return     The name of the ETAsset object.
    */     
    public String getName() {
        return name;
    }

    /** 
    * @param name   The name of the ETAsset object.
    */     
    public void setName(String name) {
        this.name = name;
    }

    /** 
    * @return     The description of the ETAsset object.
    */    
    public String getDescription() {
        return description;
    }

    /** 
    * @param description    The description of the ETAsset object.
    */    
    public void setDescription(String description) {
        this.description = description;
    }

    /** 
    * @return     The created date of the ETAsset object.
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** 
    * @param createdDate        The created date of the ETAsset object.
    */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /** 
    * @return     The modified date of the ETAsset object.
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /** 
    * @param modifiedDate       The modified date of the ETAsset object.
    */    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }    
    
    /**
     * @return the customerKey
     */
    public String getCustomerKey() {
        return customerKey;
    }

    /**
     * @param customerKey the customerKey to set
     */
    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    /**
     * @return the objectID
     */
    public String getObjectID() {
        return objectID;
    }

    /**
     * @param objectID the objectID to set
     */
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the enterpriseId
     */
    public String getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * @param enterpriseId the enterpriseId to set
     */
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }    
    
}
