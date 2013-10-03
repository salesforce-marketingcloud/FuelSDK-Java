//
// ETComplexFilter.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
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
