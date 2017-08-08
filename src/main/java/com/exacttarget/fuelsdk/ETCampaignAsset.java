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

/**
 * An <code>ETCampaignAsset</code> object represents a campaign asset
 * in the Salesforce Marketing Cloud.
 */

@RestObject(path = "/hub/v1/campaigns/{campaignId}/assets",
            primaryKey = "id",
            collection = "entities",
            totalCount = "count")
public class ETCampaignAsset extends ETRestObject {
    @Expose
    @ExternalName("id")
    private String id = null;
    @Expose
    @ExternalName("campaignId")
    private String campaignId = null;
    @Expose
    @ExternalName("createdDate")
    private Date createdDate = null;
    @Expose
    @ExternalName("type")
    private String type = null;
    @Expose
    @ExternalName("objectId")
    private String objectId = null;

    /** 
    * @return The Identifier of the ETCampaignAsset object.
    */    
    public String getId() {
        return id;
    }

    /** 
    * @param id     The Identifier of the ETCampaignAsset object.
    */    
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @return The Campaign Identifier of the ETCampaignAsset object.
    */    
   public String getCampaignId() {
        return campaignId;
    }

    /** 
    * @param campaignId     The Identifier of the ETCampaignAsset object.
    */    
   public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    /** 
    * @return     The created date of the ETCampaignAsset object.
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /** 
    * @param createdDate        The created date of the ETCampaignAsset object.
    */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

   /** 
    * @return The type of the ETCampaignAsset object.
    */    
      public String getType() {
        return type;
    }

    /** 
    * @param type        The type of the ETCampaignAsset object.
    */
    public void setType(String type) {
        this.type = type;
    }

    /** 
    * @return The Object Identifier of the ETCampaignAsset object.
    */    
     public String getObjectId() {
        return objectId;
    }

   /** 
    * @param objectId        The Object Identifier of the ETCampaignAsset object.
    */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
