package com.powersi.biz.medicare.hosp.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.powersi.comm.bean.BaseBean;

public class Kzh0422DtoExample extends BaseBean{
	private static final long serialVersionUID = 1L;
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Kzh0422DtoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andKzh0422idIsNull() {
            addCriterion("kzh0422ID is null");
            return (Criteria) this;
        }

        public Criteria andKzh0422idIsNotNull() {
            addCriterion("kzh0422ID is not null");
            return (Criteria) this;
        }

        public Criteria andKzh0422idEqualTo(Integer value) {
            addCriterion("kzh0422ID =", value, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idNotEqualTo(Integer value) {
            addCriterion("kzh0422ID <>", value, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idGreaterThan(Integer value) {
            addCriterion("kzh0422ID >", value, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idGreaterThanOrEqualTo(Integer value) {
            addCriterion("kzh0422ID >=", value, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idLessThan(Integer value) {
            addCriterion("kzh0422ID <", value, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idLessThanOrEqualTo(Integer value) {
            addCriterion("kzh0422ID <=", value, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idIn(List<Integer> values) {
            addCriterion("kzh0422ID in", values, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idNotIn(List<Integer> values) {
            addCriterion("kzh0422ID not in", values, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idBetween(Integer value1, Integer value2) {
            addCriterion("kzh0422ID between", value1, value2, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh0422idNotBetween(Integer value1, Integer value2) {
            addCriterion("kzh0422ID not between", value1, value2, "kzh0422id");
            return (Criteria) this;
        }

        public Criteria andKzh04idIsNull() {
            addCriterion("kzh04id is null");
            return (Criteria) this;
        }

        public Criteria andKzh04idIsNotNull() {
            addCriterion("kzh04id is not null");
            return (Criteria) this;
        }

        public Criteria andKzh04idEqualTo(Integer value) {
            addCriterion("kzh04id =", value, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idNotEqualTo(Integer value) {
            addCriterion("kzh04id <>", value, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idGreaterThan(Integer value) {
            addCriterion("kzh04id >", value, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idGreaterThanOrEqualTo(Integer value) {
            addCriterion("kzh04id >=", value, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idLessThan(Integer value) {
            addCriterion("kzh04id <", value, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idLessThanOrEqualTo(Integer value) {
            addCriterion("kzh04id <=", value, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idIn(List<Integer> values) {
            addCriterion("kzh04id in", values, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idNotIn(List<Integer> values) {
            addCriterion("kzh04id not in", values, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idBetween(Integer value1, Integer value2) {
            addCriterion("kzh04id between", value1, value2, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andKzh04idNotBetween(Integer value1, Integer value2) {
            addCriterion("kzh04id not between", value1, value2, "kzh04id");
            return (Criteria) this;
        }

        public Criteria andAke001IsNull() {
            addCriterion("AKE001 is null");
            return (Criteria) this;
        }

        public Criteria andAke001IsNotNull() {
            addCriterion("AKE001 is not null");
            return (Criteria) this;
        }

        public Criteria andAke001EqualTo(String value) {
            addCriterion("AKE001 =", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001NotEqualTo(String value) {
            addCriterion("AKE001 <>", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001GreaterThan(String value) {
            addCriterion("AKE001 >", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001GreaterThanOrEqualTo(String value) {
            addCriterion("AKE001 >=", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001LessThan(String value) {
            addCriterion("AKE001 <", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001LessThanOrEqualTo(String value) {
            addCriterion("AKE001 <=", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001Like(String value) {
            addCriterion("AKE001 like", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001NotLike(String value) {
            addCriterion("AKE001 not like", value, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001In(List<String> values) {
            addCriterion("AKE001 in", values, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001NotIn(List<String> values) {
            addCriterion("AKE001 not in", values, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001Between(String value1, String value2) {
            addCriterion("AKE001 between", value1, value2, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke001NotBetween(String value1, String value2) {
            addCriterion("AKE001 not between", value1, value2, "ake001");
            return (Criteria) this;
        }

        public Criteria andAke002IsNull() {
            addCriterion("AKE002 is null");
            return (Criteria) this;
        }

        public Criteria andAke002IsNotNull() {
            addCriterion("AKE002 is not null");
            return (Criteria) this;
        }

        public Criteria andAke002EqualTo(String value) {
            addCriterion("AKE002 =", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002NotEqualTo(String value) {
            addCriterion("AKE002 <>", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002GreaterThan(String value) {
            addCriterion("AKE002 >", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002GreaterThanOrEqualTo(String value) {
            addCriterion("AKE002 >=", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002LessThan(String value) {
            addCriterion("AKE002 <", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002LessThanOrEqualTo(String value) {
            addCriterion("AKE002 <=", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002Like(String value) {
            addCriterion("AKE002 like", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002NotLike(String value) {
            addCriterion("AKE002 not like", value, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002In(List<String> values) {
            addCriterion("AKE002 in", values, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002NotIn(List<String> values) {
            addCriterion("AKE002 not in", values, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002Between(String value1, String value2) {
            addCriterion("AKE002 between", value1, value2, "ake002");
            return (Criteria) this;
        }

        public Criteria andAke002NotBetween(String value1, String value2) {
            addCriterion("AKE002 not between", value1, value2, "ake002");
            return (Criteria) this;
        }

        public Criteria andBka056IsNull() {
            addCriterion("BKA056 is null");
            return (Criteria) this;
        }

        public Criteria andBka056IsNotNull() {
            addCriterion("BKA056 is not null");
            return (Criteria) this;
        }

        public Criteria andBka056EqualTo(BigDecimal value) {
            addCriterion("BKA056 =", value, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056NotEqualTo(BigDecimal value) {
            addCriterion("BKA056 <>", value, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056GreaterThan(BigDecimal value) {
            addCriterion("BKA056 >", value, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BKA056 >=", value, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056LessThan(BigDecimal value) {
            addCriterion("BKA056 <", value, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056LessThanOrEqualTo(BigDecimal value) {
            addCriterion("BKA056 <=", value, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056In(List<BigDecimal> values) {
            addCriterion("BKA056 in", values, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056NotIn(List<BigDecimal> values) {
            addCriterion("BKA056 not in", values, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("BKA056 between", value1, value2, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka056NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BKA056 not between", value1, value2, "bka056");
            return (Criteria) this;
        }

        public Criteria andBka057IsNull() {
            addCriterion("BKA057 is null");
            return (Criteria) this;
        }

        public Criteria andBka057IsNotNull() {
            addCriterion("BKA057 is not null");
            return (Criteria) this;
        }

        public Criteria andBka057EqualTo(BigDecimal value) {
            addCriterion("BKA057 =", value, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057NotEqualTo(BigDecimal value) {
            addCriterion("BKA057 <>", value, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057GreaterThan(BigDecimal value) {
            addCriterion("BKA057 >", value, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BKA057 >=", value, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057LessThan(BigDecimal value) {
            addCriterion("BKA057 <", value, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057LessThanOrEqualTo(BigDecimal value) {
            addCriterion("BKA057 <=", value, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057In(List<BigDecimal> values) {
            addCriterion("BKA057 in", values, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057NotIn(List<BigDecimal> values) {
            addCriterion("BKA057 not in", values, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("BKA057 between", value1, value2, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka057NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BKA057 not between", value1, value2, "bka057");
            return (Criteria) this;
        }

        public Criteria andBka058IsNull() {
            addCriterion("BKA058 is null");
            return (Criteria) this;
        }

        public Criteria andBka058IsNotNull() {
            addCriterion("BKA058 is not null");
            return (Criteria) this;
        }

        public Criteria andBka058EqualTo(BigDecimal value) {
            addCriterion("BKA058 =", value, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058NotEqualTo(BigDecimal value) {
            addCriterion("BKA058 <>", value, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058GreaterThan(BigDecimal value) {
            addCriterion("BKA058 >", value, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BKA058 >=", value, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058LessThan(BigDecimal value) {
            addCriterion("BKA058 <", value, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058LessThanOrEqualTo(BigDecimal value) {
            addCriterion("BKA058 <=", value, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058In(List<BigDecimal> values) {
            addCriterion("BKA058 in", values, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058NotIn(List<BigDecimal> values) {
            addCriterion("BKA058 not in", values, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("BKA058 between", value1, value2, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka058NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BKA058 not between", value1, value2, "bka058");
            return (Criteria) this;
        }

        public Criteria andBka060IsNull() {
            addCriterion("BKA060 is null");
            return (Criteria) this;
        }

        public Criteria andBka060IsNotNull() {
            addCriterion("BKA060 is not null");
            return (Criteria) this;
        }

        public Criteria andBka060EqualTo(String value) {
            addCriterion("BKA060 =", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060NotEqualTo(String value) {
            addCriterion("BKA060 <>", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060GreaterThan(String value) {
            addCriterion("BKA060 >", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060GreaterThanOrEqualTo(String value) {
            addCriterion("BKA060 >=", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060LessThan(String value) {
            addCriterion("BKA060 <", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060LessThanOrEqualTo(String value) {
            addCriterion("BKA060 <=", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060Like(String value) {
            addCriterion("BKA060 like", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060NotLike(String value) {
            addCriterion("BKA060 not like", value, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060In(List<String> values) {
            addCriterion("BKA060 in", values, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060NotIn(List<String> values) {
            addCriterion("BKA060 not in", values, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060Between(String value1, String value2) {
            addCriterion("BKA060 between", value1, value2, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka060NotBetween(String value1, String value2) {
            addCriterion("BKA060 not between", value1, value2, "bka060");
            return (Criteria) this;
        }

        public Criteria andBka061IsNull() {
            addCriterion("BKA061 is null");
            return (Criteria) this;
        }

        public Criteria andBka061IsNotNull() {
            addCriterion("BKA061 is not null");
            return (Criteria) this;
        }

        public Criteria andBka061EqualTo(Integer value) {
            addCriterion("BKA061 =", value, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061NotEqualTo(Integer value) {
            addCriterion("BKA061 <>", value, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061GreaterThan(Integer value) {
            addCriterion("BKA061 >", value, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061GreaterThanOrEqualTo(Integer value) {
            addCriterion("BKA061 >=", value, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061LessThan(Integer value) {
            addCriterion("BKA061 <", value, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061LessThanOrEqualTo(Integer value) {
            addCriterion("BKA061 <=", value, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061In(List<Integer> values) {
            addCriterion("BKA061 in", values, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061NotIn(List<Integer> values) {
            addCriterion("BKA061 not in", values, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061Between(Integer value1, Integer value2) {
            addCriterion("BKA061 between", value1, value2, "bka061");
            return (Criteria) this;
        }

        public Criteria andBka061NotBetween(Integer value1, Integer value2) {
            addCriterion("BKA061 not between", value1, value2, "bka061");
            return (Criteria) this;
        }

        public Criteria andAaz231IsNull() {
            addCriterion("AAZ231 is null");
            return (Criteria) this;
        }

        public Criteria andAaz231IsNotNull() {
            addCriterion("AAZ231 is not null");
            return (Criteria) this;
        }

        public Criteria andAaz231EqualTo(String value) {
            addCriterion("AAZ231 =", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231NotEqualTo(String value) {
            addCriterion("AAZ231 <>", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231GreaterThan(String value) {
            addCriterion("AAZ231 >", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231GreaterThanOrEqualTo(String value) {
            addCriterion("AAZ231 >=", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231LessThan(String value) {
            addCriterion("AAZ231 <", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231LessThanOrEqualTo(String value) {
            addCriterion("AAZ231 <=", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231Like(String value) {
            addCriterion("AAZ231 like", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231NotLike(String value) {
            addCriterion("AAZ231 not like", value, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231In(List<String> values) {
            addCriterion("AAZ231 in", values, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231NotIn(List<String> values) {
            addCriterion("AAZ231 not in", values, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231Between(String value1, String value2) {
            addCriterion("AAZ231 between", value1, value2, "aaz231");
            return (Criteria) this;
        }

        public Criteria andAaz231NotBetween(String value1, String value2) {
            addCriterion("AAZ231 not between", value1, value2, "aaz231");
            return (Criteria) this;
        }

        public Criteria andBka063IsNull() {
            addCriterion("BKA063 is null");
            return (Criteria) this;
        }

        public Criteria andBka063IsNotNull() {
            addCriterion("BKA063 is not null");
            return (Criteria) this;
        }

        public Criteria andBka063EqualTo(String value) {
            addCriterion("BKA063 =", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063NotEqualTo(String value) {
            addCriterion("BKA063 <>", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063GreaterThan(String value) {
            addCriterion("BKA063 >", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063GreaterThanOrEqualTo(String value) {
            addCriterion("BKA063 >=", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063LessThan(String value) {
            addCriterion("BKA063 <", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063LessThanOrEqualTo(String value) {
            addCriterion("BKA063 <=", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063Like(String value) {
            addCriterion("BKA063 like", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063NotLike(String value) {
            addCriterion("BKA063 not like", value, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063In(List<String> values) {
            addCriterion("BKA063 in", values, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063NotIn(List<String> values) {
            addCriterion("BKA063 not in", values, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063Between(String value1, String value2) {
            addCriterion("BKA063 between", value1, value2, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka063NotBetween(String value1, String value2) {
            addCriterion("BKA063 not between", value1, value2, "bka063");
            return (Criteria) this;
        }

        public Criteria andBka064IsNull() {
            addCriterion("BKA064 is null");
            return (Criteria) this;
        }

        public Criteria andBka064IsNotNull() {
            addCriterion("BKA064 is not null");
            return (Criteria) this;
        }

        public Criteria andBka064EqualTo(String value) {
            addCriterion("BKA064 =", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064NotEqualTo(String value) {
            addCriterion("BKA064 <>", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064GreaterThan(String value) {
            addCriterion("BKA064 >", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064GreaterThanOrEqualTo(String value) {
            addCriterion("BKA064 >=", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064LessThan(String value) {
            addCriterion("BKA064 <", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064LessThanOrEqualTo(String value) {
            addCriterion("BKA064 <=", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064Like(String value) {
            addCriterion("BKA064 like", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064NotLike(String value) {
            addCriterion("BKA064 not like", value, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064In(List<String> values) {
            addCriterion("BKA064 in", values, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064NotIn(List<String> values) {
            addCriterion("BKA064 not in", values, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064Between(String value1, String value2) {
            addCriterion("BKA064 between", value1, value2, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka064NotBetween(String value1, String value2) {
            addCriterion("BKA064 not between", value1, value2, "bka064");
            return (Criteria) this;
        }

        public Criteria andBka065IsNull() {
            addCriterion("BKA065 is null");
            return (Criteria) this;
        }

        public Criteria andBka065IsNotNull() {
            addCriterion("BKA065 is not null");
            return (Criteria) this;
        }

        public Criteria andBka065EqualTo(Date value) {
            addCriterionForJDBCDate("BKA065 =", value, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065NotEqualTo(Date value) {
            addCriterionForJDBCDate("BKA065 <>", value, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065GreaterThan(Date value) {
            addCriterionForJDBCDate("BKA065 >", value, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKA065 >=", value, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065LessThan(Date value) {
            addCriterionForJDBCDate("BKA065 <", value, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKA065 <=", value, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065In(List<Date> values) {
            addCriterionForJDBCDate("BKA065 in", values, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065NotIn(List<Date> values) {
            addCriterionForJDBCDate("BKA065 not in", values, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065Between(Date value1, Date value2) {
            addCriterionForJDBCDate("BKA065 between", value1, value2, "bka065");
            return (Criteria) this;
        }

        public Criteria andBka065NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BKA065 not between", value1, value2, "bka065");
            return (Criteria) this;
        }

        public Criteria andAae100IsNull() {
            addCriterion("AAE100 is null");
            return (Criteria) this;
        }

        public Criteria andAae100IsNotNull() {
            addCriterion("AAE100 is not null");
            return (Criteria) this;
        }

        public Criteria andAae100EqualTo(String value) {
            addCriterion("AAE100 =", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100NotEqualTo(String value) {
            addCriterion("AAE100 <>", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100GreaterThan(String value) {
            addCriterion("AAE100 >", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100GreaterThanOrEqualTo(String value) {
            addCriterion("AAE100 >=", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100LessThan(String value) {
            addCriterion("AAE100 <", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100LessThanOrEqualTo(String value) {
            addCriterion("AAE100 <=", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100Like(String value) {
            addCriterion("AAE100 like", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100NotLike(String value) {
            addCriterion("AAE100 not like", value, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100In(List<String> values) {
            addCriterion("AAE100 in", values, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100NotIn(List<String> values) {
            addCriterion("AAE100 not in", values, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100Between(String value1, String value2) {
            addCriterion("AAE100 between", value1, value2, "aae100");
            return (Criteria) this;
        }

        public Criteria andAae100NotBetween(String value1, String value2) {
            addCriterion("AAE100 not between", value1, value2, "aae100");
            return (Criteria) this;
        }

        public Criteria andBka051IsNull() {
            addCriterion("BKA051 is null");
            return (Criteria) this;
        }

        public Criteria andBka051IsNotNull() {
            addCriterion("BKA051 is not null");
            return (Criteria) this;
        }

        public Criteria andBka051EqualTo(Date value) {
            addCriterionForJDBCDate("BKA051 =", value, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051NotEqualTo(Date value) {
            addCriterionForJDBCDate("BKA051 <>", value, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051GreaterThan(Date value) {
            addCriterionForJDBCDate("BKA051 >", value, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKA051 >=", value, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051LessThan(Date value) {
            addCriterionForJDBCDate("BKA051 <", value, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKA051 <=", value, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051In(List<Date> values) {
            addCriterionForJDBCDate("BKA051 in", values, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051NotIn(List<Date> values) {
            addCriterionForJDBCDate("BKA051 not in", values, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051Between(Date value1, Date value2) {
            addCriterionForJDBCDate("BKA051 between", value1, value2, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka051NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BKA051 not between", value1, value2, "bka051");
            return (Criteria) this;
        }

        public Criteria andBka052IsNull() {
            addCriterion("BKA052 is null");
            return (Criteria) this;
        }

        public Criteria andBka052IsNotNull() {
            addCriterion("BKA052 is not null");
            return (Criteria) this;
        }

        public Criteria andBka052EqualTo(String value) {
            addCriterion("BKA052 =", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052NotEqualTo(String value) {
            addCriterion("BKA052 <>", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052GreaterThan(String value) {
            addCriterion("BKA052 >", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052GreaterThanOrEqualTo(String value) {
            addCriterion("BKA052 >=", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052LessThan(String value) {
            addCriterion("BKA052 <", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052LessThanOrEqualTo(String value) {
            addCriterion("BKA052 <=", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052Like(String value) {
            addCriterion("BKA052 like", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052NotLike(String value) {
            addCriterion("BKA052 not like", value, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052In(List<String> values) {
            addCriterion("BKA052 in", values, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052NotIn(List<String> values) {
            addCriterion("BKA052 not in", values, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052Between(String value1, String value2) {
            addCriterion("BKA052 between", value1, value2, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka052NotBetween(String value1, String value2) {
            addCriterion("BKA052 not between", value1, value2, "bka052");
            return (Criteria) this;
        }

        public Criteria andBka053IsNull() {
            addCriterion("BKA053 is null");
            return (Criteria) this;
        }

        public Criteria andBka053IsNotNull() {
            addCriterion("BKA053 is not null");
            return (Criteria) this;
        }

        public Criteria andBka053EqualTo(String value) {
            addCriterion("BKA053 =", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053NotEqualTo(String value) {
            addCriterion("BKA053 <>", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053GreaterThan(String value) {
            addCriterion("BKA053 >", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053GreaterThanOrEqualTo(String value) {
            addCriterion("BKA053 >=", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053LessThan(String value) {
            addCriterion("BKA053 <", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053LessThanOrEqualTo(String value) {
            addCriterion("BKA053 <=", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053Like(String value) {
            addCriterion("BKA053 like", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053NotLike(String value) {
            addCriterion("BKA053 not like", value, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053In(List<String> values) {
            addCriterion("BKA053 in", values, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053NotIn(List<String> values) {
            addCriterion("BKA053 not in", values, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053Between(String value1, String value2) {
            addCriterion("BKA053 between", value1, value2, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka053NotBetween(String value1, String value2) {
            addCriterion("BKA053 not between", value1, value2, "bka053");
            return (Criteria) this;
        }

        public Criteria andBka054IsNull() {
            addCriterion("BKA054 is null");
            return (Criteria) this;
        }

        public Criteria andBka054IsNotNull() {
            addCriterion("BKA054 is not null");
            return (Criteria) this;
        }

        public Criteria andBka054EqualTo(String value) {
            addCriterion("BKA054 =", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054NotEqualTo(String value) {
            addCriterion("BKA054 <>", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054GreaterThan(String value) {
            addCriterion("BKA054 >", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054GreaterThanOrEqualTo(String value) {
            addCriterion("BKA054 >=", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054LessThan(String value) {
            addCriterion("BKA054 <", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054LessThanOrEqualTo(String value) {
            addCriterion("BKA054 <=", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054Like(String value) {
            addCriterion("BKA054 like", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054NotLike(String value) {
            addCriterion("BKA054 not like", value, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054In(List<String> values) {
            addCriterion("BKA054 in", values, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054NotIn(List<String> values) {
            addCriterion("BKA054 not in", values, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054Between(String value1, String value2) {
            addCriterion("BKA054 between", value1, value2, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka054NotBetween(String value1, String value2) {
            addCriterion("BKA054 not between", value1, value2, "bka054");
            return (Criteria) this;
        }

        public Criteria andBka055IsNull() {
            addCriterion("BKA055 is null");
            return (Criteria) this;
        }

        public Criteria andBka055IsNotNull() {
            addCriterion("BKA055 is not null");
            return (Criteria) this;
        }

        public Criteria andBka055EqualTo(String value) {
            addCriterion("BKA055 =", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055NotEqualTo(String value) {
            addCriterion("BKA055 <>", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055GreaterThan(String value) {
            addCriterion("BKA055 >", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055GreaterThanOrEqualTo(String value) {
            addCriterion("BKA055 >=", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055LessThan(String value) {
            addCriterion("BKA055 <", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055LessThanOrEqualTo(String value) {
            addCriterion("BKA055 <=", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055Like(String value) {
            addCriterion("BKA055 like", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055NotLike(String value) {
            addCriterion("BKA055 not like", value, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055In(List<String> values) {
            addCriterion("BKA055 in", values, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055NotIn(List<String> values) {
            addCriterion("BKA055 not in", values, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055Between(String value1, String value2) {
            addCriterion("BKA055 between", value1, value2, "bka055");
            return (Criteria) this;
        }

        public Criteria andBka055NotBetween(String value1, String value2) {
            addCriterion("BKA055 not between", value1, value2, "bka055");
            return (Criteria) this;
        }

        public Criteria andAke005IsNull() {
            addCriterion("AKE005 is null");
            return (Criteria) this;
        }

        public Criteria andAke005IsNotNull() {
            addCriterion("AKE005 is not null");
            return (Criteria) this;
        }

        public Criteria andAke005EqualTo(String value) {
            addCriterion("AKE005 =", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005NotEqualTo(String value) {
            addCriterion("AKE005 <>", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005GreaterThan(String value) {
            addCriterion("AKE005 >", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005GreaterThanOrEqualTo(String value) {
            addCriterion("AKE005 >=", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005LessThan(String value) {
            addCriterion("AKE005 <", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005LessThanOrEqualTo(String value) {
            addCriterion("AKE005 <=", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005Like(String value) {
            addCriterion("AKE005 like", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005NotLike(String value) {
            addCriterion("AKE005 not like", value, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005In(List<String> values) {
            addCriterion("AKE005 in", values, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005NotIn(List<String> values) {
            addCriterion("AKE005 not in", values, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005Between(String value1, String value2) {
            addCriterion("AKE005 between", value1, value2, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke005NotBetween(String value1, String value2) {
            addCriterion("AKE005 not between", value1, value2, "ake005");
            return (Criteria) this;
        }

        public Criteria andAke006IsNull() {
            addCriterion("AKE006 is null");
            return (Criteria) this;
        }

        public Criteria andAke006IsNotNull() {
            addCriterion("AKE006 is not null");
            return (Criteria) this;
        }

        public Criteria andAke006EqualTo(String value) {
            addCriterion("AKE006 =", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006NotEqualTo(String value) {
            addCriterion("AKE006 <>", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006GreaterThan(String value) {
            addCriterion("AKE006 >", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006GreaterThanOrEqualTo(String value) {
            addCriterion("AKE006 >=", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006LessThan(String value) {
            addCriterion("AKE006 <", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006LessThanOrEqualTo(String value) {
            addCriterion("AKE006 <=", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006Like(String value) {
            addCriterion("AKE006 like", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006NotLike(String value) {
            addCriterion("AKE006 not like", value, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006In(List<String> values) {
            addCriterion("AKE006 in", values, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006NotIn(List<String> values) {
            addCriterion("AKE006 not in", values, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006Between(String value1, String value2) {
            addCriterion("AKE006 between", value1, value2, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke006NotBetween(String value1, String value2) {
            addCriterion("AKE006 not between", value1, value2, "ake006");
            return (Criteria) this;
        }

        public Criteria andAke003IsNull() {
            addCriterion("AKE003 is null");
            return (Criteria) this;
        }

        public Criteria andAke003IsNotNull() {
            addCriterion("AKE003 is not null");
            return (Criteria) this;
        }

        public Criteria andAke003EqualTo(String value) {
            addCriterion("AKE003 =", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003NotEqualTo(String value) {
            addCriterion("AKE003 <>", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003GreaterThan(String value) {
            addCriterion("AKE003 >", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003GreaterThanOrEqualTo(String value) {
            addCriterion("AKE003 >=", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003LessThan(String value) {
            addCriterion("AKE003 <", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003LessThanOrEqualTo(String value) {
            addCriterion("AKE003 <=", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003Like(String value) {
            addCriterion("AKE003 like", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003NotLike(String value) {
            addCriterion("AKE003 not like", value, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003In(List<String> values) {
            addCriterion("AKE003 in", values, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003NotIn(List<String> values) {
            addCriterion("AKE003 not in", values, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003Between(String value1, String value2) {
            addCriterion("AKE003 between", value1, value2, "ake003");
            return (Criteria) this;
        }

        public Criteria andAke003NotBetween(String value1, String value2) {
            addCriterion("AKE003 not between", value1, value2, "ake003");
            return (Criteria) this;
        }

        public Criteria andAka063IsNull() {
            addCriterion("AKA063 is null");
            return (Criteria) this;
        }

        public Criteria andAka063IsNotNull() {
            addCriterion("AKA063 is not null");
            return (Criteria) this;
        }

        public Criteria andAka063EqualTo(String value) {
            addCriterion("AKA063 =", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063NotEqualTo(String value) {
            addCriterion("AKA063 <>", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063GreaterThan(String value) {
            addCriterion("AKA063 >", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063GreaterThanOrEqualTo(String value) {
            addCriterion("AKA063 >=", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063LessThan(String value) {
            addCriterion("AKA063 <", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063LessThanOrEqualTo(String value) {
            addCriterion("AKA063 <=", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063Like(String value) {
            addCriterion("AKA063 like", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063NotLike(String value) {
            addCriterion("AKA063 not like", value, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063In(List<String> values) {
            addCriterion("AKA063 in", values, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063NotIn(List<String> values) {
            addCriterion("AKA063 not in", values, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063Between(String value1, String value2) {
            addCriterion("AKA063 between", value1, value2, "aka063");
            return (Criteria) this;
        }

        public Criteria andAka063NotBetween(String value1, String value2) {
            addCriterion("AKA063 not between", value1, value2, "aka063");
            return (Criteria) this;
        }

        public Criteria andBkc194IsNull() {
            addCriterion("bkc194 is null");
            return (Criteria) this;
        }

        public Criteria andBkc194IsNotNull() {
            addCriterion("bkc194 is not null");
            return (Criteria) this;
        }

        public Criteria andBkc194EqualTo(String value) {
            addCriterion("bkc194 =", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194NotEqualTo(String value) {
            addCriterion("bkc194 <>", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194GreaterThan(String value) {
            addCriterion("bkc194 >", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194GreaterThanOrEqualTo(String value) {
            addCriterion("bkc194 >=", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194LessThan(String value) {
            addCriterion("bkc194 <", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194LessThanOrEqualTo(String value) {
            addCriterion("bkc194 <=", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194Like(String value) {
            addCriterion("bkc194 like", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194NotLike(String value) {
            addCriterion("bkc194 not like", value, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194In(List<String> values) {
            addCriterion("bkc194 in", values, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194NotIn(List<String> values) {
            addCriterion("bkc194 not in", values, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194Between(String value1, String value2) {
            addCriterion("bkc194 between", value1, value2, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc194NotBetween(String value1, String value2) {
            addCriterion("bkc194 not between", value1, value2, "bkc194");
            return (Criteria) this;
        }

        public Criteria andBkc109IsNull() {
            addCriterion("bkc109 is null");
            return (Criteria) this;
        }

        public Criteria andBkc109IsNotNull() {
            addCriterion("bkc109 is not null");
            return (Criteria) this;
        }

        public Criteria andBkc109EqualTo(String value) {
            addCriterion("bkc109 =", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109NotEqualTo(String value) {
            addCriterion("bkc109 <>", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109GreaterThan(String value) {
            addCriterion("bkc109 >", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109GreaterThanOrEqualTo(String value) {
            addCriterion("bkc109 >=", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109LessThan(String value) {
            addCriterion("bkc109 <", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109LessThanOrEqualTo(String value) {
            addCriterion("bkc109 <=", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109Like(String value) {
            addCriterion("bkc109 like", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109NotLike(String value) {
            addCriterion("bkc109 not like", value, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109In(List<String> values) {
            addCriterion("bkc109 in", values, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109NotIn(List<String> values) {
            addCriterion("bkc109 not in", values, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109Between(String value1, String value2) {
            addCriterion("bkc109 between", value1, value2, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBkc109NotBetween(String value1, String value2) {
            addCriterion("bkc109 not between", value1, value2, "bkc109");
            return (Criteria) this;
        }

        public Criteria andBka080IsNull() {
            addCriterion("bka080 is null");
            return (Criteria) this;
        }

        public Criteria andBka080IsNotNull() {
            addCriterion("bka080 is not null");
            return (Criteria) this;
        }

        public Criteria andBka080EqualTo(BigDecimal value) {
            addCriterion("bka080 =", value, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080NotEqualTo(BigDecimal value) {
            addCriterion("bka080 <>", value, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080GreaterThan(BigDecimal value) {
            addCriterion("bka080 >", value, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bka080 >=", value, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080LessThan(BigDecimal value) {
            addCriterion("bka080 <", value, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080LessThanOrEqualTo(BigDecimal value) {
            addCriterion("bka080 <=", value, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080In(List<BigDecimal> values) {
            addCriterion("bka080 in", values, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080NotIn(List<BigDecimal> values) {
            addCriterion("bka080 not in", values, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("bka080 between", value1, value2, "bka080");
            return (Criteria) this;
        }

        public Criteria andBka080NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bka080 not between", value1, value2, "bka080");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion extends BaseBean{
    	private static final long serialVersionUID = 1L;
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