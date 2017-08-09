//
// This file is part of the Fuel Java SDK.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.exacttarget.fuelsdk;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * An <code>ETCampaign</code> object represents a campaign
 * in the Salesforce Marketing Cloud.
 */

@RestObject(path = "/hub/v1/campaigns",
            primaryKey = "id",
            collection = "items",
            totalCount = "count")
public class ETCampaign extends ETRestObject {
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
    @ExternalName("createdDate")
    private Date createdDate = null;
    @Expose
    @ExternalName("modifiedDate")
    private Date modifiedDate = null;
    @Expose @SerializedName("campaignCode")
    @ExternalName("code")
    private String code = null;
    @Expose
    @ExternalName("color")
    private String color = null;
    @Expose
    @ExternalName("favorite")
    private Boolean favorite = null;

    /** 
    * @return The Identifier of the ETCampaign object.
    */    
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETCampaign object.
    */    
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @return     The name of the ETCampaign object.
    */     
    public String getName() {
        return name;
    }

    /** 
    * @param name   The name of the ETCampaign object.
    */     
    public void setName(String name) {
        this.name = name;
    }

    /** 
    * @return     The description of the ETCampaign object.
    */    
    public String getDescription() {
        return description;
    }

    /** 
    * @param description    The description of the ETCampaign object.
    */    
    public void setDescription(String description) {
        this.description = description;
    }

    /** 
    * @return     The created date of the ETCampaign object.
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** 
    * @param createdDate        The created date of the ETCampaign object.
    */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /** 
    * @return     The modified date of the ETCampaign object.
    */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /** 
    * @param modifiedDate       The modified date of the ETCampaign object.
    */    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /** 
    * @return     The campaign code of the ETCampaign object.
    */     
    public String getCode() {
        return code;
    }

    /** 
    * @param code       The campaign code of the ETCampaign object.
    */    
    public void setCode(String code) {
        this.code = code;
    }

    /** 
    * @return     The color of the ETCampaign object.
    */     
    public String getColor() {
        return color;
    }

    /** 
    * @param color       The color of the ETCampaign object.
    */    
    public void setColor(String color) {
        this.color = color;
    }

    /** 
    * @return     True if it is a favorite campaign, false otherwise.
    */     
    public Boolean getFavorite() {
        return favorite;
    }

    /** 
    * @param favorite       Sets if it is a favorite campaign.
    */    
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
