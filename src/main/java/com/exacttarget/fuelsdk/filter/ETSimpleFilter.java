//
// ETSimpleFilter.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.filter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ETSimpleFilter implements ETFilter {


	protected String property;
	protected ETFilterOperators operator;
	protected List<String> values;
	protected List<Date> dateValues;

	public ETSimpleFilter(){

	}

	@SuppressWarnings("unchecked")
	public ETSimpleFilter(String property, ETFilterOperators operator, @SuppressWarnings("rawtypes") List values) {
		this.property = property;
		this.operator = operator;

		if (values.size() > 0) {
			Object o = values.get(0);
			if (o instanceof String) {
				this.values = (List<String>)values;
			} else if (o instanceof Date) {
				this.dateValues = (List<Date>)values;
			}
		}

	}

	public ETSimpleFilter(String property, ETFilterOperators operator, String value) {
		this.property = property;
		this.operator = operator;
		this.values = Arrays.asList(value);
	}

	public ETSimpleFilter(String property, ETFilterOperators operator, Date value) {
		this.property = property;
		this.operator = operator;
		this.dateValues = Arrays.asList(value);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public ETFilterOperators getOperator() {
		return operator;
	}

	public void setOperator(ETFilterOperators operator) {
		this.operator = operator;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public List<Date> getDateValues() {
		return dateValues;
	}

	public void setDateValues(List<Date> dateValues) {
		this.dateValues = dateValues;
	}


}
