//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
