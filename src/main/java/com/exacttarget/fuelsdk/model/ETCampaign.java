//
// ETCampaign.java -
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

@InternalRestType(type = "Campaign", restPath="/hub/v1/campaigns/{id}", collectionKey="items", primaryKey="id", urlProps={"id"})
public class ETCampaign extends BaseRestSerializableObject implements ETObject{

	/** The name. */
	@InternalRestField(jsonKey = "name")
	private String name;

	/** The description. */
	@InternalRestField(jsonKey = "description")
	private String description;

	/** The campaign code. */
	@InternalRestField(jsonKey = "campaignCode")
	private String campaignCode;

	/** The color. */
	@InternalRestField(jsonKey = "color")
	private String color;

	/** The favorite. */
	@InternalRestField(jsonKey = "favorite")
	private boolean favorite;

	/**
	 * Instantiates a new campaign.
	 */
	public ETCampaign() {
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the campaign code.
	 *
	 * @return the campaignCode
	 */
	public String getCampaignCode() {
		return campaignCode;
	}

	/**
	 * Sets the campaign code.
	 *
	 * @param campaignCode the campaignCode to set
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Checks if is favorite.
	 *
	 * @return the favorite
	 */
	public boolean isFavorite() {
		return favorite;
	}

	/**
	 * Sets the favorite.
	 *
	 * @param favorite the favorite to set
	 */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.exacttarget.model.rest.BaseRestObject#toString()
	 */
	@Override
	public String toString() {
		String superStr = super.toString();
		return "Campaign [" + superStr + ", name=" + name + ", description=" + description
				+ ", campaignCode=" + campaignCode + ", color=" + color + ", favorite=" + favorite + "]";
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
		ETCampaign other = (ETCampaign) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String getRequestBody() {
		// TODO Auto-generated method stub
		return null;
	}
}
