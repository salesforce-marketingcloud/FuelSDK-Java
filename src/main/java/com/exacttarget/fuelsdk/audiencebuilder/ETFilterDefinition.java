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

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ETFilterDefinition {
    public class Condition {
        @SerializedName("ID")
        @Expose
        private String id = null;
        @SerializedName("Operator")
        @Expose
        private String operator = null;
        @SerializedName("ConditionValue")
        @Expose
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
        @SerializedName("Operator")
        @Expose
        private String operator = null;
        @SerializedName("ConditionSetName")
        @Expose
        private String conditionSetName = null;
        @SerializedName("Condition")
        @Expose
        private List<Condition> condition = null;

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

        public List<Condition> getCondition() {
            return condition;
        }

        public void setCondition(List<Condition> condition) {
            this.condition = condition;
        }
    }

    @SerializedName("PersistenceID")
    @Expose
    private String persistenceId = null;
    @SerializedName("UseEnterprise")
    @Expose
    private Boolean useEnterprise = null;
    @SerializedName("UseAlsoEngine")
    @Expose
    private Boolean useAlsoEngine = true;
    @SerializedName("Source")
    @Expose
    private String source = "AudienceBuilder";
    @SerializedName("ConditionSet")
    @Expose
    private ConditionSet conditionSet = null;

    public String getPersistenceId() {
        return persistenceId;
    }

    public void setPersistenceId(String persistenceId) {
        this.persistenceId = persistenceId;
    }

    public Boolean getUseEnterprise() {
        return useEnterprise;
    }

    public void setUseEnterprise(Boolean useEnterprise) {
        this.useEnterprise = useEnterprise;
    }

    public Boolean getUseAlsoEngine() {
        return useAlsoEngine;
    }

    public void setUseAlsoEngine(Boolean useAlsoEngine) {
        this.useAlsoEngine = useAlsoEngine;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ConditionSet getConditionSet() {
        return conditionSet;
    }

    public void setConditionSet(ConditionSet conditionSet) {
        this.conditionSet = conditionSet;
    }
}
