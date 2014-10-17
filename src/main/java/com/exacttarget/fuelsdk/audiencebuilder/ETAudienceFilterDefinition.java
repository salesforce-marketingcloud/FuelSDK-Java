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

package com.exacttarget.fuelsdk.audiencebuilder;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ETAudienceFilterDefinition {
    @Expose
    @SerializedName("PersistenceID")
    private String persistenceId = null;
    @Expose
    @SerializedName("UseEnterprise")
    private Boolean useEnterprise = false;
    @Expose
    @SerializedName("UseAlsoEngine")
    private Boolean useAlsoEngine = true;
    @Expose
    @SerializedName("Source")
    private String source = "AudienceBuilder";
    @Expose
    @SerializedName("ConditionSet")
    private ConditionSet conditionSet = new ConditionSet();

    public ETAudienceFilterDefinition() {
        conditionSet.setOperator("OR"); // XXX?
        conditionSet.setConditionSetName("");
    }

    public String getPersistenceId() {
        return persistenceId;
    }

    public void setPersistenceId(String persistenceId) {
        this.persistenceId = persistenceId;
    }

    public ConditionSet getConditionSet() {
        return conditionSet;
    }

    public void addCondition(Condition condition) {
        conditionSet.addCondition(condition);
    }

    public class Condition {
        @Expose
        @SerializedName("ID")
        private String id = null;
        @Expose
        @SerializedName("Operator")
        private String operator = null;
        @Expose
        @SerializedName("ConditionValue")
        private String conditionValue = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getConditionValue() {
            return conditionValue;
        }

        public void setConditionValue(String conditionValue) {
            this.conditionValue = conditionValue;
        }
    }

    public class ConditionSet {
        @Expose
        @SerializedName("Operator")
        private String operator = null;
        @Expose
        @SerializedName("ConditionSetName")
        private String conditionSetName = null;
        @Expose
        @SerializedName("Condition")
        private List<Condition> conditions = new ArrayList<Condition>();

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getConditionSetName() {
            return conditionSetName;
        }

        public void setConditionSetName(String conditionSetName) {
            this.conditionSetName = conditionSetName;
        }

        public List<Condition> getConditions() {
            return conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }
    }
}
