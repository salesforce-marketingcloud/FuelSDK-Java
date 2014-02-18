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

import java.util.ArrayList;
import java.util.List;

public class ETComplexFilter implements ETFilter {

	protected ETFilter leftOperand;
	protected ETFilter rightOperand;
	protected ETLogicalOperators operator = ETLogicalOperators.AND;
	protected List<ETFilter> additionalOperands = new ArrayList<ETFilter>();

	public ETComplexFilter(){

	}

	public ETComplexFilter(ETFilter leftOperand, ETFilter rightOperand,
			ETLogicalOperators operator) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
	}


	public ETComplexFilter(ETFilter leftOperand, ETFilter rightOperand,
			ETLogicalOperators operator, List<ETFilter> additionalOperands) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		this.additionalOperands = additionalOperands;
	}

	public ETFilter getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(ETFilter leftOperand) {
		this.leftOperand = leftOperand;
	}

	public ETFilter getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(ETFilter rightOperand) {
		this.rightOperand = rightOperand;
	}

	public ETLogicalOperators getOperator() {
		return operator;
	}

	public void setOperator(ETLogicalOperators operator) {
		this.operator = operator;
	}

	public List<ETFilter> getAdditionalOperands() {
		return additionalOperands;
	}

	public void setAdditionalOperands(List<ETFilter> additionalOperands) {
		this.additionalOperands = additionalOperands;
	}

	@Override
	public String toString() {
		return "ETComplexFilter [leftOperand=" + leftOperand
				+ ", rightOperand=" + rightOperand + ", operator=" + operator
				+ ", additionalOperands=" + additionalOperands + "]";
	}

}
