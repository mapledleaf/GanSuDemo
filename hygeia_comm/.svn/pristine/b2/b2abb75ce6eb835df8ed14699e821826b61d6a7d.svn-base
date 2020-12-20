package com.powersi.biz.medicare.hosp.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.powersi.comm.bean.BaseBean;
import com.powersi.comm.mybatis.Page;

public class Bkn2DtoExample extends Page{
	private static final long serialVersionUID = 1L;
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Bkn2DtoExample() {
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

    @SuppressWarnings("serial")
	protected abstract static class GeneratedCriteria extends BaseBean{
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAaa027IsNull() {
            addCriterion("aaa027 is null");
            return (Criteria) this;
        }

        public Criteria andAaa027IsNotNull() {
            addCriterion("aaa027 is not null");
            return (Criteria) this;
        }

        public Criteria andAaa027EqualTo(String value) {
            addCriterion("aaa027 =", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotEqualTo(String value) {
            addCriterion("aaa027 <>", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027GreaterThan(String value) {
            addCriterion("aaa027 >", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027GreaterThanOrEqualTo(String value) {
            addCriterion("aaa027 >=", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027LessThan(String value) {
            addCriterion("aaa027 <", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027LessThanOrEqualTo(String value) {
            addCriterion("aaa027 <=", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027Like(String value) {
            addCriterion("aaa027 like", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotLike(String value) {
            addCriterion("aaa027 not like", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027In(List<String> values) {
            addCriterion("aaa027 in", values, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotIn(List<String> values) {
            addCriterion("aaa027 not in", values, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027Between(String value1, String value2) {
            addCriterion("aaa027 between", value1, value2, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotBetween(String value1, String value2) {
            addCriterion("aaa027 not between", value1, value2, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaz217IsNull() {
            addCriterion("aaz217 is null");
            return (Criteria) this;
        }

        public Criteria andAaz217IsNotNull() {
            addCriterion("aaz217 is not null");
            return (Criteria) this;
        }

        public Criteria andAaz217EqualTo(String value) {
            addCriterion("aaz217 =", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217NotEqualTo(String value) {
            addCriterion("aaz217 <>", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217GreaterThan(String value) {
            addCriterion("aaz217 >", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217GreaterThanOrEqualTo(String value) {
            addCriterion("aaz217 >=", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217LessThan(String value) {
            addCriterion("aaz217 <", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217LessThanOrEqualTo(String value) {
            addCriterion("aaz217 <=", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217Like(String value) {
            addCriterion("aaz217 like", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217NotLike(String value) {
            addCriterion("aaz217 not like", value, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217In(List<String> values) {
            addCriterion("aaz217 in", values, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217NotIn(List<String> values) {
            addCriterion("aaz217 not in", values, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217Between(String value1, String value2) {
            addCriterion("aaz217 between", value1, value2, "aaz217");
            return (Criteria) this;
        }

        public Criteria andAaz217NotBetween(String value1, String value2) {
            addCriterion("aaz217 not between", value1, value2, "aaz217");
            return (Criteria) this;
        }

        public Criteria andPayNoIsNull() {
            addCriterion("pay_no is null");
            return (Criteria) this;
        }

        public Criteria andPayNoIsNotNull() {
            addCriterion("pay_no is not null");
            return (Criteria) this;
        }

        public Criteria andPayNoEqualTo(String value) {
            addCriterion("pay_no =", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotEqualTo(String value) {
            addCriterion("pay_no <>", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoGreaterThan(String value) {
            addCriterion("pay_no >", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_no >=", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoLessThan(String value) {
            addCriterion("pay_no <", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoLessThanOrEqualTo(String value) {
            addCriterion("pay_no <=", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoLike(String value) {
            addCriterion("pay_no like", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotLike(String value) {
            addCriterion("pay_no not like", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoIn(List<String> values) {
            addCriterion("pay_no in", values, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotIn(List<String> values) {
            addCriterion("pay_no not in", values, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoBetween(String value1, String value2) {
            addCriterion("pay_no between", value1, value2, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotBetween(String value1, String value2) {
            addCriterion("pay_no not between", value1, value2, "payNo");
            return (Criteria) this;
        }

        public Criteria andAke025IsNull() {
            addCriterion("ake025 is null");
            return (Criteria) this;
        }

        public Criteria andAke025IsNotNull() {
            addCriterion("ake025 is not null");
            return (Criteria) this;
        }

        public Criteria andAke025EqualTo(String value) {
            addCriterion("ake025 =", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025NotEqualTo(String value) {
            addCriterion("ake025 <>", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025GreaterThan(String value) {
            addCriterion("ake025 >", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025GreaterThanOrEqualTo(String value) {
            addCriterion("ake025 >=", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025LessThan(String value) {
            addCriterion("ake025 <", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025LessThanOrEqualTo(String value) {
            addCriterion("ake025 <=", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025Like(String value) {
            addCriterion("ake025 like", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025NotLike(String value) {
            addCriterion("ake025 not like", value, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025In(List<String> values) {
            addCriterion("ake025 in", values, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025NotIn(List<String> values) {
            addCriterion("ake025 not in", values, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025Between(String value1, String value2) {
            addCriterion("ake025 between", value1, value2, "ake025");
            return (Criteria) this;
        }

        public Criteria andAke025NotBetween(String value1, String value2) {
            addCriterion("ake025 not between", value1, value2, "ake025");
            return (Criteria) this;
        }

        public Criteria andAka130IsNull() {
            addCriterion("aka130 is null");
            return (Criteria) this;
        }

        public Criteria andAka130IsNotNull() {
            addCriterion("aka130 is not null");
            return (Criteria) this;
        }

        public Criteria andAka130EqualTo(String value) {
            addCriterion("aka130 =", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130NotEqualTo(String value) {
            addCriterion("aka130 <>", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130GreaterThan(String value) {
            addCriterion("aka130 >", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130GreaterThanOrEqualTo(String value) {
            addCriterion("aka130 >=", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130LessThan(String value) {
            addCriterion("aka130 <", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130LessThanOrEqualTo(String value) {
            addCriterion("aka130 <=", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130Like(String value) {
            addCriterion("aka130 like", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130NotLike(String value) {
            addCriterion("aka130 not like", value, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130In(List<String> values) {
            addCriterion("aka130 in", values, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130NotIn(List<String> values) {
            addCriterion("aka130 not in", values, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130Between(String value1, String value2) {
            addCriterion("aka130 between", value1, value2, "aka130");
            return (Criteria) this;
        }

        public Criteria andAka130NotBetween(String value1, String value2) {
            addCriterion("aka130 not between", value1, value2, "aka130");
            return (Criteria) this;
        }

        public Criteria andBka006IsNull() {
            addCriterion("bka006 is null");
            return (Criteria) this;
        }

        public Criteria andbka006IsNotNull() {
            addCriterion("bka006 is not null");
            return (Criteria) this;
        }

        public Criteria andbka006EqualTo(String value) {
            addCriterion("bka006 =", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006NotEqualTo(String value) {
            addCriterion("bka006 <>", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006GreaterThan(String value) {
            addCriterion("bka006 >", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006GreaterThanOrEqualTo(String value) {
            addCriterion("bka006 >=", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006LessThan(String value) {
            addCriterion("bka006 <", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006LessThanOrEqualTo(String value) {
            addCriterion("bka006 <=", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006Like(String value) {
            addCriterion("bka006 like", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006NotLike(String value) {
            addCriterion("bka006 not like", value, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006In(List<String> values) {
            addCriterion("bka006 in", values, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006NotIn(List<String> values) {
            addCriterion("bka006 not in", values, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006Between(String value1, String value2) {
            addCriterion("bka006 between", value1, value2, "bka006");
            return (Criteria) this;
        }

        public Criteria andbka006NotBetween(String value1, String value2) {
            addCriterion("bka006 not between", value1, value2, "bka006");
            return (Criteria) this;
        }

        public Criteria andBkn568IsNull() {
            addCriterion("bkn568 is null");
            return (Criteria) this;
        }

        public Criteria andBkn568IsNotNull() {
            addCriterion("bkn568 is not null");
            return (Criteria) this;
        }

        public Criteria andBkn568EqualTo(BigDecimal value) {
            addCriterion("bkn568 =", value, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568NotEqualTo(BigDecimal value) {
            addCriterion("bkn568 <>", value, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568GreaterThan(BigDecimal value) {
            addCriterion("bkn568 >", value, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bkn568 >=", value, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568LessThan(BigDecimal value) {
            addCriterion("bkn568 <", value, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568LessThanOrEqualTo(BigDecimal value) {
            addCriterion("bkn568 <=", value, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568In(List<BigDecimal> values) {
            addCriterion("bkn568 in", values, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568NotIn(List<BigDecimal> values) {
            addCriterion("bkn568 not in", values, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("bkn568 between", value1, value2, "bkn568");
            return (Criteria) this;
        }

        public Criteria andBkn568NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bkn568 not between", value1, value2, "bkn568");
            return (Criteria) this;
        }

        public Criteria andCurForegiftIsNull() {
            addCriterion("cur_foregift is null");
            return (Criteria) this;
        }

        public Criteria andCurForegiftIsNotNull() {
            addCriterion("cur_foregift is not null");
            return (Criteria) this;
        }

        public Criteria andCurForegiftEqualTo(BigDecimal value) {
            addCriterion("cur_foregift =", value, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftNotEqualTo(BigDecimal value) {
            addCriterion("cur_foregift <>", value, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftGreaterThan(BigDecimal value) {
            addCriterion("cur_foregift >", value, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cur_foregift >=", value, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftLessThan(BigDecimal value) {
            addCriterion("cur_foregift <", value, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cur_foregift <=", value, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftIn(List<BigDecimal> values) {
            addCriterion("cur_foregift in", values, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftNotIn(List<BigDecimal> values) {
            addCriterion("cur_foregift not in", values, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cur_foregift between", value1, value2, "curforegift");
            return (Criteria) this;
        }

        public Criteria andCurForegiftNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cur_foregift not between", value1, value2, "curforegift");
            return (Criteria) this;
        }

        public Criteria andBkn149IsNull() {
            addCriterion("bkn149 is null");
            return (Criteria) this;
        }

        public Criteria andBkn149IsNotNull() {
            addCriterion("bkn149 is not null");
            return (Criteria) this;
        }

        public Criteria andBkn149EqualTo(Date value) {
            addCriterionForJDBCDate("bkn149 =", value, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149NotEqualTo(Date value) {
            addCriterionForJDBCDate("bkn149 <>", value, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149GreaterThan(Date value) {
            addCriterionForJDBCDate("bkn149 >", value, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bkn149 >=", value, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149LessThan(Date value) {
            addCriterionForJDBCDate("bkn149 <", value, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bkn149 <=", value, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149In(List<Date> values) {
            addCriterionForJDBCDate("bkn149 in", values, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149NotIn(List<Date> values) {
            addCriterionForJDBCDate("bkn149 not in", values, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149Between(Date value1, Date value2) {
            addCriterionForJDBCDate("bkn149 between", value1, value2, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn149NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bkn149 not between", value1, value2, "bkn149");
            return (Criteria) this;
        }

        public Criteria andBkn565IsNull() {
            addCriterion("bkn565 is null");
            return (Criteria) this;
        }

        public Criteria andBkn565IsNotNull() {
            addCriterion("bkn565 is not null");
            return (Criteria) this;
        }

        public Criteria andBkn565EqualTo(String value) {
            addCriterion("bkn565 =", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565NotEqualTo(String value) {
            addCriterion("bkn565 <>", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565GreaterThan(String value) {
            addCriterion("bkn565 >", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565GreaterThanOrEqualTo(String value) {
            addCriterion("bkn565 >=", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565LessThan(String value) {
            addCriterion("bkn565 <", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565LessThanOrEqualTo(String value) {
            addCriterion("bkn565 <=", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565Like(String value) {
            addCriterion("bkn565 like", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565NotLike(String value) {
            addCriterion("bkn565 not like", value, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565In(List<String> values) {
            addCriterion("bkn565 in", values, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565NotIn(List<String> values) {
            addCriterion("bkn565 not in", values, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565Between(String value1, String value2) {
            addCriterion("bkn565 between", value1, value2, "bkn565");
            return (Criteria) this;
        }

        public Criteria andBkn565NotBetween(String value1, String value2) {
            addCriterion("bkn565 not between", value1, value2, "bkn565");
            return (Criteria) this;
        }

        public Criteria andAkb020IsNull() {
            addCriterion("akb020 is null");
            return (Criteria) this;
        }

        public Criteria andAkb020IsNotNull() {
            addCriterion("akb020 is not null");
            return (Criteria) this;
        }

        public Criteria andAkb020EqualTo(String value) {
            addCriterion("akb020 =", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotEqualTo(String value) {
            addCriterion("akb020 <>", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020GreaterThan(String value) {
            addCriterion("akb020 >", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020GreaterThanOrEqualTo(String value) {
            addCriterion("akb020 >=", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020LessThan(String value) {
            addCriterion("akb020 <", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020LessThanOrEqualTo(String value) {
            addCriterion("akb020 <=", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020Like(String value) {
            addCriterion("akb020 like", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotLike(String value) {
            addCriterion("akb020 not like", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020In(List<String> values) {
            addCriterion("akb020 in", values, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotIn(List<String> values) {
            addCriterion("akb020 not in", values, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020Between(String value1, String value2) {
            addCriterion("akb020 between", value1, value2, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotBetween(String value1, String value2) {
            addCriterion("akb020 not between", value1, value2, "akb020");
            return (Criteria) this;
        }

        public Criteria andBkn574IsNull() {
            addCriterion("bkn574 is null");
            return (Criteria) this;
        }

        public Criteria andBkn574IsNotNull() {
            addCriterion("bkn574 is not null");
            return (Criteria) this;
        }

        public Criteria andBkn574EqualTo(String value) {
            addCriterion("bkn574 =", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574NotEqualTo(String value) {
            addCriterion("bkn574 <>", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574GreaterThan(String value) {
            addCriterion("bkn574 >", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574GreaterThanOrEqualTo(String value) {
            addCriterion("bkn574 >=", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574LessThan(String value) {
            addCriterion("bkn574 <", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574LessThanOrEqualTo(String value) {
            addCriterion("bkn574 <=", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574Like(String value) {
            addCriterion("bkn574 like", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574NotLike(String value) {
            addCriterion("bkn574 not like", value, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574In(List<String> values) {
            addCriterion("bkn574 in", values, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574NotIn(List<String> values) {
            addCriterion("bkn574 not in", values, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574Between(String value1, String value2) {
            addCriterion("bkn574 between", value1, value2, "bkn574");
            return (Criteria) this;
        }

        public Criteria andBkn574NotBetween(String value1, String value2) {
            addCriterion("bkn574 not between", value1, value2, "bkn574");
            return (Criteria) this;
        }
    }

    @SuppressWarnings("serial")
	public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    @SuppressWarnings("serial")
	public static class Criterion extends BaseBean{
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