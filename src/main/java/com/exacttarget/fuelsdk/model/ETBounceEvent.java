//
// ETBounceEvent.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.BounceEvent;

@InternalSoapType(type=BounceEvent.class)
public class ETBounceEvent extends BaseTrackingEvent implements ETObject {

	public ETBounceEvent(){}
}
