//
// ETCampaignAsset.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;

@InternalRestType(type = "CampaignAsset", restPath="/hub/v1/campaigns/{campaignId}/assets/{id}", collectionKey="entities", primaryKey="campaignId", 
	urlProps={"campaignId", "id"})
public class ETCampaignAsset extends BaseRestSerializableObject implements ETObject{

	/** The id of {@link ETCampaign} to which this Asset is associated. */
	@InternalRestField(jsonKey = "campaignId")
	private String campaignId;

	/** The type. */
	@InternalRestField(jsonKey = "type")
	private String type;

	/** The itemID. */
	@InternalRestField(jsonKey = "itemID")
	private String itemID;

	/**
	 * Instantiates a new campaign.
	 */
	public ETCampaignAsset() {
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.exacttarget.model.rest.BaseRestObject#toString()
	 */
	@Override
	public String toString() {
		String superStr = super.toString();
		return "CampaignAsset [" + superStr + ", campaignId=" + campaignId + ", type=" + type + ", itemID=" + itemID + "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ETCampaignAsset other = (ETCampaignAsset) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
}
