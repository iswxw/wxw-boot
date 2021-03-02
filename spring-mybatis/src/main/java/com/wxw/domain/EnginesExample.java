package com.wxw.domain;

import java.util.ArrayList;
import java.util.List;

public class EnginesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EnginesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andEngineIsNull() {
            addCriterion("ENGINE is null");
            return (Criteria) this;
        }

        public Criteria andEngineIsNotNull() {
            addCriterion("ENGINE is not null");
            return (Criteria) this;
        }

        public Criteria andEngineEqualTo(String value) {
            addCriterion("ENGINE =", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotEqualTo(String value) {
            addCriterion("ENGINE <>", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineGreaterThan(String value) {
            addCriterion("ENGINE >", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineGreaterThanOrEqualTo(String value) {
            addCriterion("ENGINE >=", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineLessThan(String value) {
            addCriterion("ENGINE <", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineLessThanOrEqualTo(String value) {
            addCriterion("ENGINE <=", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineLike(String value) {
            addCriterion("ENGINE like", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotLike(String value) {
            addCriterion("ENGINE not like", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineIn(List<String> values) {
            addCriterion("ENGINE in", values, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotIn(List<String> values) {
            addCriterion("ENGINE not in", values, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineBetween(String value1, String value2) {
            addCriterion("ENGINE between", value1, value2, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotBetween(String value1, String value2) {
            addCriterion("ENGINE not between", value1, value2, "engine");
            return (Criteria) this;
        }

        public Criteria andSupportIsNull() {
            addCriterion("SUPPORT is null");
            return (Criteria) this;
        }

        public Criteria andSupportIsNotNull() {
            addCriterion("SUPPORT is not null");
            return (Criteria) this;
        }

        public Criteria andSupportEqualTo(String value) {
            addCriterion("SUPPORT =", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotEqualTo(String value) {
            addCriterion("SUPPORT <>", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportGreaterThan(String value) {
            addCriterion("SUPPORT >", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPORT >=", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportLessThan(String value) {
            addCriterion("SUPPORT <", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportLessThanOrEqualTo(String value) {
            addCriterion("SUPPORT <=", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportLike(String value) {
            addCriterion("SUPPORT like", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotLike(String value) {
            addCriterion("SUPPORT not like", value, "support");
            return (Criteria) this;
        }

        public Criteria andSupportIn(List<String> values) {
            addCriterion("SUPPORT in", values, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotIn(List<String> values) {
            addCriterion("SUPPORT not in", values, "support");
            return (Criteria) this;
        }

        public Criteria andSupportBetween(String value1, String value2) {
            addCriterion("SUPPORT between", value1, value2, "support");
            return (Criteria) this;
        }

        public Criteria andSupportNotBetween(String value1, String value2) {
            addCriterion("SUPPORT not between", value1, value2, "support");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("COMMENT =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("COMMENT <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("COMMENT >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENT >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("COMMENT <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("COMMENT <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("COMMENT like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("COMMENT not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("COMMENT in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("COMMENT not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("COMMENT between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("COMMENT not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andTransactionsIsNull() {
            addCriterion("TRANSACTIONS is null");
            return (Criteria) this;
        }

        public Criteria andTransactionsIsNotNull() {
            addCriterion("TRANSACTIONS is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionsEqualTo(String value) {
            addCriterion("TRANSACTIONS =", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsNotEqualTo(String value) {
            addCriterion("TRANSACTIONS <>", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsGreaterThan(String value) {
            addCriterion("TRANSACTIONS >", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONS >=", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsLessThan(String value) {
            addCriterion("TRANSACTIONS <", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTIONS <=", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsLike(String value) {
            addCriterion("TRANSACTIONS like", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsNotLike(String value) {
            addCriterion("TRANSACTIONS not like", value, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsIn(List<String> values) {
            addCriterion("TRANSACTIONS in", values, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsNotIn(List<String> values) {
            addCriterion("TRANSACTIONS not in", values, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsBetween(String value1, String value2) {
            addCriterion("TRANSACTIONS between", value1, value2, "transactions");
            return (Criteria) this;
        }

        public Criteria andTransactionsNotBetween(String value1, String value2) {
            addCriterion("TRANSACTIONS not between", value1, value2, "transactions");
            return (Criteria) this;
        }

        public Criteria andXaIsNull() {
            addCriterion("XA is null");
            return (Criteria) this;
        }

        public Criteria andXaIsNotNull() {
            addCriterion("XA is not null");
            return (Criteria) this;
        }

        public Criteria andXaEqualTo(String value) {
            addCriterion("XA =", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaNotEqualTo(String value) {
            addCriterion("XA <>", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaGreaterThan(String value) {
            addCriterion("XA >", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaGreaterThanOrEqualTo(String value) {
            addCriterion("XA >=", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaLessThan(String value) {
            addCriterion("XA <", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaLessThanOrEqualTo(String value) {
            addCriterion("XA <=", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaLike(String value) {
            addCriterion("XA like", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaNotLike(String value) {
            addCriterion("XA not like", value, "xa");
            return (Criteria) this;
        }

        public Criteria andXaIn(List<String> values) {
            addCriterion("XA in", values, "xa");
            return (Criteria) this;
        }

        public Criteria andXaNotIn(List<String> values) {
            addCriterion("XA not in", values, "xa");
            return (Criteria) this;
        }

        public Criteria andXaBetween(String value1, String value2) {
            addCriterion("XA between", value1, value2, "xa");
            return (Criteria) this;
        }

        public Criteria andXaNotBetween(String value1, String value2) {
            addCriterion("XA not between", value1, value2, "xa");
            return (Criteria) this;
        }

        public Criteria andSavepointsIsNull() {
            addCriterion("SAVEPOINTS is null");
            return (Criteria) this;
        }

        public Criteria andSavepointsIsNotNull() {
            addCriterion("SAVEPOINTS is not null");
            return (Criteria) this;
        }

        public Criteria andSavepointsEqualTo(String value) {
            addCriterion("SAVEPOINTS =", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsNotEqualTo(String value) {
            addCriterion("SAVEPOINTS <>", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsGreaterThan(String value) {
            addCriterion("SAVEPOINTS >", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsGreaterThanOrEqualTo(String value) {
            addCriterion("SAVEPOINTS >=", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsLessThan(String value) {
            addCriterion("SAVEPOINTS <", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsLessThanOrEqualTo(String value) {
            addCriterion("SAVEPOINTS <=", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsLike(String value) {
            addCriterion("SAVEPOINTS like", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsNotLike(String value) {
            addCriterion("SAVEPOINTS not like", value, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsIn(List<String> values) {
            addCriterion("SAVEPOINTS in", values, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsNotIn(List<String> values) {
            addCriterion("SAVEPOINTS not in", values, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsBetween(String value1, String value2) {
            addCriterion("SAVEPOINTS between", value1, value2, "savepoints");
            return (Criteria) this;
        }

        public Criteria andSavepointsNotBetween(String value1, String value2) {
            addCriterion("SAVEPOINTS not between", value1, value2, "savepoints");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}