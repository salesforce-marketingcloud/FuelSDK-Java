//
// BaseRestSerializableObject.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalRestField;

public abstract class BaseRestSerializableObject {

	/** The id. */
	@InternalRestField(jsonKey = "id")
	protected String id;

	/** The created date. */
	@InternalRestField(jsonKey = "createdDate")
	protected String createdDate;

	/** The modified date. */
	@InternalRestField(jsonKey = "modifiedDate")
	protected String modifiedDate;

	/**
	 * Instantiates a new eT rest object.
	 */
	public BaseRestSerializableObject() {
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id=" + id + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate;
	}
}
