package com.powersi.biz.medicare.hosp.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.powersi.comm.bean.BaseBean;

public class Kzh04DtoExample extends BaseBean{
	private static final long serialVersionUID = 1L;
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
	private int startRow;
	private int pageSize;
    
	 public int getStartRow() {
			return startRow;
		}

		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

   
	
    
    public Kzh04DtoExample() {
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

        public Criteria andBkh015IsNull() {
            addCriterion("BKH015 is null");
            return (Criteria) this;
        }

        public Criteria andBkh015IsNotNull() {
            addCriterion("BKH015 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh015EqualTo(Integer value) {
            addCriterion("BKH015 =", value, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015NotEqualTo(Integer value) {
            addCriterion("BKH015 <>", value, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015GreaterThan(Integer value) {
            addCriterion("BKH015 >", value, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015GreaterThanOrEqualTo(Integer value) {
            addCriterion("BKH015 >=", value, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015LessThan(Integer value) {
            addCriterion("BKH015 <", value, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015LessThanOrEqualTo(Integer value) {
            addCriterion("BKH015 <=", value, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015In(List<Integer> values) {
            addCriterion("BKH015 in", values, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015NotIn(List<Integer> values) {
            addCriterion("BKH015 not in", values, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015Between(Integer value1, Integer value2) {
            addCriterion("BKH015 between", value1, value2, "bkh015");
            return (Criteria) this;
        }

        public Criteria andBkh015NotBetween(Integer value1, Integer value2) {
            addCriterion("BKH015 not between", value1, value2, "bkh015");
            return (Criteria) this;
        }

        public Criteria andAaa027IsNull() {
            addCriterion("AAA027 is null");
            return (Criteria) this;
        }

        public Criteria andAaa027IsNotNull() {
            addCriterion("AAA027 is not null");
            return (Criteria) this;
        }

        public Criteria andAaa027EqualTo(String value) {
            addCriterion("AAA027 =", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotEqualTo(String value) {
            addCriterion("AAA027 <>", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027GreaterThan(String value) {
            addCriterion("AAA027 >", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027GreaterThanOrEqualTo(String value) {
            addCriterion("AAA027 >=", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027LessThan(String value) {
            addCriterion("AAA027 <", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027LessThanOrEqualTo(String value) {
            addCriterion("AAA027 <=", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027Like(String value) {
            addCriterion("AAA027 like", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotLike(String value) {
            addCriterion("AAA027 not like", value, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027In(List<String> values) {
            addCriterion("AAA027 in", values, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotIn(List<String> values) {
            addCriterion("AAA027 not in", values, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027Between(String value1, String value2) {
            addCriterion("AAA027 between", value1, value2, "aaa027");
            return (Criteria) this;
        }

        public Criteria andAaa027NotBetween(String value1, String value2) {
            addCriterion("AAA027 not between", value1, value2, "aaa027");
            return (Criteria) this;
        }

        public Criteria andBkh059IsNull() {
            addCriterion("BKH059 is null");
            return (Criteria) this;
        }

        public Criteria andBkh059IsNotNull() {
            addCriterion("BKH059 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh059EqualTo(String value) {
            addCriterion("BKH059 =", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059NotEqualTo(String value) {
            addCriterion("BKH059 <>", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059GreaterThan(String value) {
            addCriterion("BKH059 >", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059GreaterThanOrEqualTo(String value) {
            addCriterion("BKH059 >=", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059LessThan(String value) {
            addCriterion("BKH059 <", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059LessThanOrEqualTo(String value) {
            addCriterion("BKH059 <=", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059Like(String value) {
            addCriterion("BKH059 like", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059NotLike(String value) {
            addCriterion("BKH059 not like", value, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059In(List<String> values) {
            addCriterion("BKH059 in", values, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059NotIn(List<String> values) {
            addCriterion("BKH059 not in", values, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059Between(String value1, String value2) {
            addCriterion("BKH059 between", value1, value2, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh059NotBetween(String value1, String value2) {
            addCriterion("BKH059 not between", value1, value2, "bkh059");
            return (Criteria) this;
        }

        public Criteria andBkh060IsNull() {
            addCriterion("BKH060 is null");
            return (Criteria) this;
        }

        public Criteria andBkh060IsNotNull() {
            addCriterion("BKH060 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh060EqualTo(String value) {
            addCriterion("BKH060 =", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060NotEqualTo(String value) {
            addCriterion("BKH060 <>", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060GreaterThan(String value) {
            addCriterion("BKH060 >", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060GreaterThanOrEqualTo(String value) {
            addCriterion("BKH060 >=", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060LessThan(String value) {
            addCriterion("BKH060 <", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060LessThanOrEqualTo(String value) {
            addCriterion("BKH060 <=", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060Like(String value) {
            addCriterion("BKH060 like", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060NotLike(String value) {
            addCriterion("BKH060 not like", value, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060In(List<String> values) {
            addCriterion("BKH060 in", values, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060NotIn(List<String> values) {
            addCriterion("BKH060 not in", values, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060Between(String value1, String value2) {
            addCriterion("BKH060 between", value1, value2, "bkh060");
            return (Criteria) this;
        }

        public Criteria andBkh060NotBetween(String value1, String value2) {
            addCriterion("BKH060 not between", value1, value2, "bkh060");
            return (Criteria) this;
        }

        public Criteria andAac004IsNull() {
            addCriterion("AAC004 is null");
            return (Criteria) this;
        }

        public Criteria andAac004IsNotNull() {
            addCriterion("AAC004 is not null");
            return (Criteria) this;
        }

        public Criteria andAac004EqualTo(String value) {
            addCriterion("AAC004 =", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004NotEqualTo(String value) {
            addCriterion("AAC004 <>", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004GreaterThan(String value) {
            addCriterion("AAC004 >", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004GreaterThanOrEqualTo(String value) {
            addCriterion("AAC004 >=", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004LessThan(String value) {
            addCriterion("AAC004 <", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004LessThanOrEqualTo(String value) {
            addCriterion("AAC004 <=", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004Like(String value) {
            addCriterion("AAC004 like", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004NotLike(String value) {
            addCriterion("AAC004 not like", value, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004In(List<String> values) {
            addCriterion("AAC004 in", values, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004NotIn(List<String> values) {
            addCriterion("AAC004 not in", values, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004Between(String value1, String value2) {
            addCriterion("AAC004 between", value1, value2, "aac004");
            return (Criteria) this;
        }

        public Criteria andAac004NotBetween(String value1, String value2) {
            addCriterion("AAC004 not between", value1, value2, "aac004");
            return (Criteria) this;
        }

        public Criteria andBac001IsNull() {
            addCriterion("BAC001 is null");
            return (Criteria) this;
        }

        public Criteria andBac001IsNotNull() {
            addCriterion("BAC001 is not null");
            return (Criteria) this;
        }

        public Criteria andBac001EqualTo(String value) {
            addCriterion("BAC001 =", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001NotEqualTo(String value) {
            addCriterion("BAC001 <>", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001GreaterThan(String value) {
            addCriterion("BAC001 >", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001GreaterThanOrEqualTo(String value) {
            addCriterion("BAC001 >=", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001LessThan(String value) {
            addCriterion("BAC001 <", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001LessThanOrEqualTo(String value) {
            addCriterion("BAC001 <=", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001Like(String value) {
            addCriterion("BAC001 like", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001NotLike(String value) {
            addCriterion("BAC001 not like", value, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001In(List<String> values) {
            addCriterion("BAC001 in", values, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001NotIn(List<String> values) {
            addCriterion("BAC001 not in", values, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001Between(String value1, String value2) {
            addCriterion("BAC001 between", value1, value2, "bac001");
            return (Criteria) this;
        }

        public Criteria andBac001NotBetween(String value1, String value2) {
            addCriterion("BAC001 not between", value1, value2, "bac001");
            return (Criteria) this;
        }

        public Criteria andBkh061IsNull() {
            addCriterion("BKH061 is null");
            return (Criteria) this;
        }

        public Criteria andBkh061IsNotNull() {
            addCriterion("BKH061 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh061EqualTo(Date value) {
            addCriterionForJDBCDate("BKH061 =", value, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061NotEqualTo(Date value) {
            addCriterionForJDBCDate("BKH061 <>", value, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061GreaterThan(Date value) {
            addCriterionForJDBCDate("BKH061 >", value, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKH061 >=", value, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061LessThan(Date value) {
            addCriterionForJDBCDate("BKH061 <", value, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKH061 <=", value, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061In(List<Date> values) {
            addCriterionForJDBCDate("BKH061 in", values, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061NotIn(List<Date> values) {
            addCriterionForJDBCDate("BKH061 not in", values, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061Between(Date value1, Date value2) {
            addCriterionForJDBCDate("BKH061 between", value1, value2, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh061NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BKH061 not between", value1, value2, "bkh061");
            return (Criteria) this;
        }

        public Criteria andBkh062IsNull() {
            addCriterion("BKH062 is null");
            return (Criteria) this;
        }

        public Criteria andBkh062IsNotNull() {
            addCriterion("BKH062 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh062EqualTo(Date value) {
            addCriterionForJDBCDate("BKH062 =", value, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062NotEqualTo(Date value) {
            addCriterionForJDBCDate("BKH062 <>", value, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062GreaterThan(Date value) {
            addCriterionForJDBCDate("BKH062 >", value, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKH062 >=", value, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062LessThan(Date value) {
            addCriterionForJDBCDate("BKH062 <", value, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKH062 <=", value, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062In(List<Date> values) {
            addCriterionForJDBCDate("BKH062 in", values, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062NotIn(List<Date> values) {
            addCriterionForJDBCDate("BKH062 not in", values, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062Between(Date value1, Date value2) {
            addCriterionForJDBCDate("BKH062 between", value1, value2, "bkh062");
            return (Criteria) this;
        }

        public Criteria andBkh062NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BKH062 not between", value1, value2, "bkh062");
            return (Criteria) this;
        }

        public Criteria andAac066IsNull() {
            addCriterion("AAC066 is null");
            return (Criteria) this;
        }

        public Criteria andAac066IsNotNull() {
            addCriterion("AAC066 is not null");
            return (Criteria) this;
        }

        public Criteria andAac066EqualTo(String value) {
            addCriterion("AAC066 =", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066NotEqualTo(String value) {
            addCriterion("AAC066 <>", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066GreaterThan(String value) {
            addCriterion("AAC066 >", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066GreaterThanOrEqualTo(String value) {
            addCriterion("AAC066 >=", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066LessThan(String value) {
            addCriterion("AAC066 <", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066LessThanOrEqualTo(String value) {
            addCriterion("AAC066 <=", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066Like(String value) {
            addCriterion("AAC066 like", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066NotLike(String value) {
            addCriterion("AAC066 not like", value, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066In(List<String> values) {
            addCriterion("AAC066 in", values, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066NotIn(List<String> values) {
            addCriterion("AAC066 not in", values, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066Between(String value1, String value2) {
            addCriterion("AAC066 between", value1, value2, "aac066");
            return (Criteria) this;
        }

        public Criteria andAac066NotBetween(String value1, String value2) {
            addCriterion("AAC066 not between", value1, value2, "aac066");
            return (Criteria) this;
        }

        public Criteria andBka035IsNull() {
            addCriterion("BKA035 is null");
            return (Criteria) this;
        }

        public Criteria andBka035IsNotNull() {
            addCriterion("BKA035 is not null");
            return (Criteria) this;
        }

        public Criteria andBka035EqualTo(String value) {
            addCriterion("BKA035 =", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035NotEqualTo(String value) {
            addCriterion("BKA035 <>", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035GreaterThan(String value) {
            addCriterion("BKA035 >", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035GreaterThanOrEqualTo(String value) {
            addCriterion("BKA035 >=", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035LessThan(String value) {
            addCriterion("BKA035 <", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035LessThanOrEqualTo(String value) {
            addCriterion("BKA035 <=", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035Like(String value) {
            addCriterion("BKA035 like", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035NotLike(String value) {
            addCriterion("BKA035 not like", value, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035In(List<String> values) {
            addCriterion("BKA035 in", values, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035NotIn(List<String> values) {
            addCriterion("BKA035 not in", values, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035Between(String value1, String value2) {
            addCriterion("BKA035 between", value1, value2, "bka035");
            return (Criteria) this;
        }

        public Criteria andBka035NotBetween(String value1, String value2) {
            addCriterion("BKA035 not between", value1, value2, "bka035");
            return (Criteria) this;
        }

        public Criteria andBkh063IsNull() {
            addCriterion("BKH063 is null");
            return (Criteria) this;
        }

        public Criteria andBkh063IsNotNull() {
            addCriterion("BKH063 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh063EqualTo(String value) {
            addCriterion("BKH063 =", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063NotEqualTo(String value) {
            addCriterion("BKH063 <>", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063GreaterThan(String value) {
            addCriterion("BKH063 >", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063GreaterThanOrEqualTo(String value) {
            addCriterion("BKH063 >=", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063LessThan(String value) {
            addCriterion("BKH063 <", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063LessThanOrEqualTo(String value) {
            addCriterion("BKH063 <=", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063Like(String value) {
            addCriterion("BKH063 like", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063NotLike(String value) {
            addCriterion("BKH063 not like", value, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063In(List<String> values) {
            addCriterion("BKH063 in", values, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063NotIn(List<String> values) {
            addCriterion("BKH063 not in", values, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063Between(String value1, String value2) {
            addCriterion("BKH063 between", value1, value2, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBkh063NotBetween(String value1, String value2) {
            addCriterion("BKH063 not between", value1, value2, "bkh063");
            return (Criteria) this;
        }

        public Criteria andBke204IsNull() {
            addCriterion("BKE204 is null");
            return (Criteria) this;
        }

        public Criteria andBke204IsNotNull() {
            addCriterion("BKE204 is not null");
            return (Criteria) this;
        }

        public Criteria andBke204EqualTo(Date value) {
            addCriterionForJDBCDate("BKE204 =", value, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204NotEqualTo(Date value) {
            addCriterionForJDBCDate("BKE204 <>", value, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204GreaterThan(Date value) {
            addCriterionForJDBCDate("BKE204 >", value, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKE204 >=", value, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204LessThan(Date value) {
            addCriterionForJDBCDate("BKE204 <", value, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BKE204 <=", value, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204In(List<Date> values) {
            addCriterionForJDBCDate("BKE204 in", values, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204NotIn(List<Date> values) {
            addCriterionForJDBCDate("BKE204 not in", values, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204Between(Date value1, Date value2) {
            addCriterionForJDBCDate("BKE204 between", value1, value2, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke204NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BKE204 not between", value1, value2, "bke204");
            return (Criteria) this;
        }

        public Criteria andBke205IsNull() {
            addCriterion("BKE205 is null");
            return (Criteria) this;
        }

        public Criteria andBke205IsNotNull() {
            addCriterion("BKE205 is not null");
            return (Criteria) this;
        }

        public Criteria andBke205EqualTo(String value) {
            addCriterion("BKE205 =", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205NotEqualTo(String value) {
            addCriterion("BKE205 <>", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205GreaterThan(String value) {
            addCriterion("BKE205 >", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205GreaterThanOrEqualTo(String value) {
            addCriterion("BKE205 >=", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205LessThan(String value) {
            addCriterion("BKE205 <", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205LessThanOrEqualTo(String value) {
            addCriterion("BKE205 <=", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205Like(String value) {
            addCriterion("BKE205 like", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205NotLike(String value) {
            addCriterion("BKE205 not like", value, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205In(List<String> values) {
            addCriterion("BKE205 in", values, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205NotIn(List<String> values) {
            addCriterion("BKE205 not in", values, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205Between(String value1, String value2) {
            addCriterion("BKE205 between", value1, value2, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke205NotBetween(String value1, String value2) {
            addCriterion("BKE205 not between", value1, value2, "bke205");
            return (Criteria) this;
        }

        public Criteria andBke206IsNull() {
            addCriterion("BKE206 is null");
            return (Criteria) this;
        }

        public Criteria andBke206IsNotNull() {
            addCriterion("BKE206 is not null");
            return (Criteria) this;
        }

        public Criteria andBke206EqualTo(String value) {
            addCriterion("BKE206 =", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206NotEqualTo(String value) {
            addCriterion("BKE206 <>", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206GreaterThan(String value) {
            addCriterion("BKE206 >", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206GreaterThanOrEqualTo(String value) {
            addCriterion("BKE206 >=", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206LessThan(String value) {
            addCriterion("BKE206 <", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206LessThanOrEqualTo(String value) {
            addCriterion("BKE206 <=", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206Like(String value) {
            addCriterion("BKE206 like", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206NotLike(String value) {
            addCriterion("BKE206 not like", value, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206In(List<String> values) {
            addCriterion("BKE206 in", values, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206NotIn(List<String> values) {
            addCriterion("BKE206 not in", values, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206Between(String value1, String value2) {
            addCriterion("BKE206 between", value1, value2, "bke206");
            return (Criteria) this;
        }

        public Criteria andBke206NotBetween(String value1, String value2) {
            addCriterion("BKE206 not between", value1, value2, "bke206");
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

        public Criteria andAkb020IsNull() {
            addCriterion("AKB020 is null");
            return (Criteria) this;
        }

        public Criteria andAkb020IsNotNull() {
            addCriterion("AKB020 is not null");
            return (Criteria) this;
        }

        public Criteria andAkb020EqualTo(String value) {
            addCriterion("AKB020 =", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotEqualTo(String value) {
            addCriterion("AKB020 <>", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020GreaterThan(String value) {
            addCriterion("AKB020 >", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020GreaterThanOrEqualTo(String value) {
            addCriterion("AKB020 >=", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020LessThan(String value) {
            addCriterion("AKB020 <", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020LessThanOrEqualTo(String value) {
            addCriterion("AKB020 <=", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020Like(String value) {
            addCriterion("AKB020 like", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotLike(String value) {
            addCriterion("AKB020 not like", value, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020In(List<String> values) {
            addCriterion("AKB020 in", values, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotIn(List<String> values) {
            addCriterion("AKB020 not in", values, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020Between(String value1, String value2) {
            addCriterion("AKB020 between", value1, value2, "akb020");
            return (Criteria) this;
        }

        public Criteria andAkb020NotBetween(String value1, String value2) {
            addCriterion("AKB020 not between", value1, value2, "akb020");
            return (Criteria) this;
        }

        public Criteria andAka020IsNull() {
            addCriterion("AKA020 is null");
            return (Criteria) this;
        }

        public Criteria andAka020IsNotNull() {
            addCriterion("AKA020 is not null");
            return (Criteria) this;
        }

        public Criteria andAka020EqualTo(String value) {
            addCriterion("AKA020 =", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020NotEqualTo(String value) {
            addCriterion("AKA020 <>", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020GreaterThan(String value) {
            addCriterion("AKA020 >", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020GreaterThanOrEqualTo(String value) {
            addCriterion("AKA020 >=", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020LessThan(String value) {
            addCriterion("AKA020 <", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020LessThanOrEqualTo(String value) {
            addCriterion("AKA020 <=", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020Like(String value) {
            addCriterion("AKA020 like", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020NotLike(String value) {
            addCriterion("AKA020 not like", value, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020In(List<String> values) {
            addCriterion("AKA020 in", values, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020NotIn(List<String> values) {
            addCriterion("AKA020 not in", values, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020Between(String value1, String value2) {
            addCriterion("AKA020 between", value1, value2, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka020NotBetween(String value1, String value2) {
            addCriterion("AKA020 not between", value1, value2, "aka020");
            return (Criteria) this;
        }

        public Criteria andAka021IsNull() {
            addCriterion("AKA021 is null");
            return (Criteria) this;
        }

        public Criteria andAka021IsNotNull() {
            addCriterion("AKA021 is not null");
            return (Criteria) this;
        }

        public Criteria andAka021EqualTo(String value) {
            addCriterion("AKA021 =", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021NotEqualTo(String value) {
            addCriterion("AKA021 <>", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021GreaterThan(String value) {
            addCriterion("AKA021 >", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021GreaterThanOrEqualTo(String value) {
            addCriterion("AKA021 >=", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021LessThan(String value) {
            addCriterion("AKA021 <", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021LessThanOrEqualTo(String value) {
            addCriterion("AKA021 <=", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021Like(String value) {
            addCriterion("AKA021 like", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021NotLike(String value) {
            addCriterion("AKA021 not like", value, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021In(List<String> values) {
            addCriterion("AKA021 in", values, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021NotIn(List<String> values) {
            addCriterion("AKA021 not in", values, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021Between(String value1, String value2) {
            addCriterion("AKA021 between", value1, value2, "aka021");
            return (Criteria) this;
        }

        public Criteria andAka021NotBetween(String value1, String value2) {
            addCriterion("AKA021 not between", value1, value2, "aka021");
            return (Criteria) this;
        }

        public Criteria andBkh058IsNull() {
            addCriterion("bkh058 is null");
            return (Criteria) this;
        }

        public Criteria andBkh058IsNotNull() {
            addCriterion("bkh058 is not null");
            return (Criteria) this;
        }

        public Criteria andBkh058EqualTo(String value) {
            addCriterion("bkh058 =", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058NotEqualTo(String value) {
            addCriterion("bkh058 <>", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058GreaterThan(String value) {
            addCriterion("bkh058 >", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058GreaterThanOrEqualTo(String value) {
            addCriterion("bkh058 >=", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058LessThan(String value) {
            addCriterion("bkh058 <", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058LessThanOrEqualTo(String value) {
            addCriterion("bkh058 <=", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058Like(String value) {
            addCriterion("bkh058 like", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058NotLike(String value) {
            addCriterion("bkh058 not like", value, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058In(List<String> values) {
            addCriterion("bkh058 in", values, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058NotIn(List<String> values) {
            addCriterion("bkh058 not in", values, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058Between(String value1, String value2) {
            addCriterion("bkh058 between", value1, value2, "bkh058");
            return (Criteria) this;
        }

        public Criteria andBkh058NotBetween(String value1, String value2) {
            addCriterion("bkh058 not between", value1, value2, "bkh058");
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