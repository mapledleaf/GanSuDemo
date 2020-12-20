/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.ssm.biz.medicare.common.service;

import java.io.Closeable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.cache.DBCacheManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 代码表服务实现.
 */
public class CodetableServiceImpl implements CodetableService, Closeable {

	/** 代码表缓存. */
	private Map<String, Map<String, Object>> codetableCache = new ConcurrentHashMap<String, Map<String, Object>>();

	/** sql缓存. */
	private Map<String, String> sqlCache = new ConcurrentHashMap<String, String>();

	/** db缓存. */
	private Map<String, List<String>> dbCache = new ConcurrentHashMap<String, List<String>>();

	/** The size. */
	private int size = 0;

	/** The times. */
	private int times = 0;

	/** The lock. */
	private Object lock = new Object();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.hygeia.framework.service.CodetableService#contains(java.lang.
	 * String)
	 */
	@Override
	public boolean contains(final String codeType) {
		try {
			init();

			if (!codetableCache.containsKey(codeType)) {
				return init(codeType);
			} else {
				return true;
			}
		} catch (Exception ex) {
			LogHelper.getLogger().warn("判断代码缓存" + codeType + "是否存在出错", ex);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.hygeia.framework.service.CodetableService#get(java.lang.
	 * String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map get(final String codeType) {
		return get(codeType, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.hygeia.framework.service.CodetableService#get(java.lang.
	 * String, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map get(final String codeType, final String filter) {
		return get(codeType, filter, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.hygeia.framework.service.CodetableService#get(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map get(final String codeType, final String filter, final String sort) {
		init();

		boolean isCache = checkDBCache(codeType);

		if ((filter == null || filter.length() == 0) && (sort == null || sort.length() == 0)) {
			return getCache(codeType);
		}

		String code = String.format("%1$s$%2$s$%3$s", codeType, filter, sort);
		Map map = getCache(code);
		if (map != null) {
			return map;
		}

		map = new LinkedHashMap();

		String sql = sqlCache.get(codeType);
		if (UtilFunc.hasLength(sql)) {
			StringBuffer sb = new StringBuffer();

			sb.append(" SELECT * ");
			sb.append("   FROM (").append(sql).append(") ");
			if (UtilFunc.hasLength(filter)) {
				sb.append("  WHERE ").append(filter);
			}

			if (UtilFunc.hasLength(sort)) {
				sb.append("  ORDER BY ").append(sort);
			}

			// 加载主数据源
			List<Object[]> lst = DBFunc.executeArrayList(DBHelper.getDefaultConnection(), sb.toString(), filter);

			for (Object[] a : lst) {
				map.put(String.valueOf(a[0]), a[1]);
			}
		}

		codetableCache.put(code, map);

		if (isCache) {
			List<String> lst = dbCache.get(codeType);
			if (lst != null) {
				lst.add(code);
			}
		}

		return map;
	}

	/**
	 * 根据代码类型和值获取显示值.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param value
	 *            码表值
	 * @return the display value
	 */
	public String getDisplayValue(final String codeType, final Object value) {
		return getDisplayValue(codeType, value, null);
	}

	/**
	 * 根据代码类型和值获取显示值.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param value
	 *            码表值
	 * @param defaultValue
	 *            缺省值
	 * @return 代码表
	 */
	public String getDisplayValue(final String codeType, final Object value, final String defaultValue) {
		init();

		if (value == null) {
			return defaultValue;
		}

		return UtilFunc.getString(getCache(codeType), String.valueOf(value), defaultValue);
	}

	/**
	 * Check db cache.
	 * 
	 * @param codeType
	 *            the code type
	 * @return true, if successful
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean checkDBCache(final String codeType) {
		if (!dbCache.containsKey(codeType)) {
			return false;
		}

		if (DBCacheManager.getInstance().checkRefresh("codetable_" + codeType)) {
			synchronized (CodetableMapping.class) {
				List<String> lst = dbCache.get(codeType);
				for (String str : lst) {
					codetableCache.remove(str);
				}
				dbCache.put(codeType, new ArrayList<String>());

				Map tree = new LinkedHashMap();

				String sql = sqlCache.get(codeType);
				if (UtilFunc.hasLength(sql)) {
					List<Object[]> dat = DBFunc.executeArrayList(DBHelper.getDefaultConnection(), sql);
					for (Object[] a : dat) {
						tree.put(String.valueOf(a[0]), a[1]);
					}
				}

				codetableCache.put(codeType, tree);
			}
		}

		return true;
	}

	/**
	 * 判断代码字典是否数据库缓存.
	 * 
	 * @param codeType
	 *            the code type
	 * @return true, if is dB cache
	 */
	public boolean isDBCache(final String codeType) {
		if (!contains(codeType)) {
			return false;
		}

		return dbCache.containsKey(codeType);
	}

	/**
	 * 刷新码表.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void refresh() {
		Connection conn = null;
		try {
			synchronized (codetableCache) {
				Set codetableSet = new HashSet(codetableCache.keySet());

				conn = DBFunc.getConnection();

				String codetableKey = null;

				if (DBFunc.existColumn(conn, "SYS_CODE_TABLE", "CODE_TYPE")) {
					boolean hasValid = DBFunc.existColumn(conn, "SYS_CODE_TABLE", "VALID_FLAG");

					String localId = ParameterMapping.getLocalId();
					boolean hasLocal = DBFunc.existColumn(conn, "SYS_CODE_TABLE_DETAIL", "LOCAL_ID");
					Map<String, String> localMap = new HashMap<String, String>();
					if (hasLocal) {
						List<Object[]> localList = DBFunc.executeArrayList(conn,
								"SELECT DISTINCT CODE_TYPE FROM SYS_CODE_TABLE_DETAIL WHERE LOCAL_ID = ? AND VALID_FLAG = '1'",
								new Object[] { localId });
						for (Object[] a : localList) {
							localMap.put((String) a[0], (String) a[0]);
						}
					}

					String sql = null;
					if (hasValid) {
						sql = "SELECT CODE_TYPE, CODE_NAME, CODE_SQL FROM SYS_CODE_TABLE WHERE VALID_FLAG = '1'";
					} else {
						sql = "SELECT CODE_TYPE, CODE_NAME, CODE_SQL FROM SYS_CODE_TABLE";
					}

					List<Object[]> codetableList = DBFunc.executeArrayList(conn, sql);

					String detailSql = null;
					if (hasValid) {
						if (hasLocal) {
							detailSql = "SELECT CODE_TYPE, DATA_VALUE, DISPLAY_VALUE, LOCAL_ID FROM SYS_CODE_TABLE_DETAIL WHERE VALID_FLAG = '1' ORDER BY CODE_TYPE, DIS_ORDER, DATA_VALUE";
						} else {
							detailSql = "SELECT CODE_TYPE, DATA_VALUE, DISPLAY_VALUE FROM SYS_CODE_TABLE_DETAIL WHERE VALID_FLAG = '1' ORDER BY CODE_TYPE, DIS_ORDER, DATA_VALUE";
						}
					} else {
						detailSql = "SELECT CODE_TYPE, DATA_VALUE, DISPLAY_VALUE FROM SYS_CODE_TABLE_DETAIL ORDER BY CODE_TYPE, DIS_ORDER, DATA_VALUE";
					}

					List<Object[]> detailList = DBFunc.executeArrayList(conn, detailSql);

					String codeSql = null;
					String detailLocalId = "";
					for (int i = 0; i < codetableList.size(); i++) {
						Object[] values = codetableList.get(i);
						codetableKey = values[0].toString();

						try {
							Map tree = new LinkedHashMap();
							if (!UtilFunc.isEmptyString(values[2])) {
								List<Object[]> lst = DBFunc.executeArrayList(conn, values[2].toString());
								for (Object[] a : lst) {
									tree.put(String.valueOf(a[0]), a[1]);
								}

								codeSql = values[2].toString();
							} else {
								if (hasLocal) {
									if (localMap.containsKey(codetableKey)) {
										detailLocalId = localId;
									} else {
										detailLocalId = "0";
									}

									for (Object[] a : detailList) {
										if (codetableKey.equals(a[0]) && detailLocalId.equals(a[3])) {
											tree.put(String.valueOf(a[1]), a[2]);
										}
									}
								} else {
									for (Object[] a : detailList) {
										if (codetableKey.equals(a[0])) {
											tree.put(String.valueOf(a[1]), a[2]);
										}
									}
								}

								if (hasValid) {
									if (hasLocal) {
										codeSql = String.format(
												"SELECT DATA_VALUE, DISPLAY_VALUE, DIS_ORDER FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = '%1$s' AND VALID_FLAG = '1' AND LOCAL_ID = '%2$s' ORDER BY DIS_ORDER",
												codetableKey, detailLocalId);
									} else {
										codeSql = String.format(
												"SELECT DATA_VALUE, DISPLAY_VALUE, DIS_ORDER FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = '%1$s' AND VALID_FLAG = '1' ORDER BY DIS_ORDER",
												codetableKey);
									}

								} else {
									codeSql = String.format(
											"SELECT DATA_VALUE, DISPLAY_VALUE, DIS_ORDER FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = '%1$s' ORDER BY DIS_ORDER",
											codetableKey);
								}
							}

							sqlCache.put(codetableKey, codeSql);

							codetableSet.remove(codetableKey);
							codetableCache.put(codetableKey, tree);
						} catch (Exception e) {
							LogHelper.getLogger().warn("加载代码缓存[" + codetableKey + "]出错", e);
						}
					}
				}

				for (Iterator it = codetableSet.iterator(); it.hasNext();) {
					codetableKey = it.next().toString();

					codetableCache.remove(codetableKey);
					sqlCache.remove(codetableKey);
				}

				dbCache.clear();
				if (DBFunc.existTable(conn, "SYS_CACHE_CONFIG")) {
					List<Object[]> lst = DBFunc.executeArrayList(conn,
							"select cache_name from sys_cache_config where cache_name like 'codetable_%'");
					String name = null;
					if (lst != null && lst.size() > 0) {
						for (Object[] a : lst) {
							name = a[0].toString().substring(10);
							if (codetableCache.containsKey(name)) {
								dbCache.put(name, new ArrayList<String>());
							}
						}
					}
				}

				// 记录数
				size = codetableCache.size();
				times++;
			}
		} catch (HygeiaException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HygeiaException("刷新代码缓存信息出错", ex);
		} finally {
			DBFunc.closeConnection(conn);
			conn = null;
		}
	}

	/**
	 * 刷新缓存.
	 * 
	 * @param codeType
	 *            码表类型
	 */
	public void refresh(final String codeType) {
		try {
			synchronized (codetableCache) {
				if (dbCache.containsKey(codeType)) {
					dbCache.remove(codeType);
				}

				Set<String> keySet = new HashSet<String>();
				if (codetableCache.containsKey(codeType)) {
					keySet.add(codeType);
				}

				String codetableKey = codeType + "$";
				for (String key : codetableCache.keySet()) {
					if (key.startsWith(codetableKey)) {
						keySet.add(key);
					}
				}

				if (!keySet.isEmpty()) {
					for (String key : keySet) {
						codetableCache.remove(key);
					}
				}
			}
		} catch (Exception ex) {
			LogHelper.getLogger().warn("刷新代码缓存" + codeType + "出错", ex);
		}
	}

	/**
	 * Inits the.
	 * 
	 * @param codeType
	 *            the code type
	 * @return true, if successful
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean init(final String codeType) {
		Connection conn = null;
		boolean hasCode = false;
		try {
			synchronized (codetableCache) {
				conn = DBFunc.getConnection();
				if (DBFunc.existColumn(conn, "SYS_CODE_TABLE", "CODE_TYPE")) {
					boolean hasValid = DBFunc.existColumn(conn, "SYS_CODE_TABLE", "VALID_FLAG");

					String sql = null;
					if (hasValid) {
						sql = "SELECT CODE_TYPE, CODE_SQL FROM SYS_CODE_TABLE WHERE CODE_TYPE = ? AND VALID_FLAG = '1'";
					} else {
						sql = "SELECT CODE_TYPE, CODE_SQL FROM SYS_CODE_TABLE WHERE CODE_TYPE = ?";
					}
					Map map = DBFunc.executeMap(conn, sql, new Object[] { codeType });
					if (map != null && map.size() > 0) {
						String codeSql = (String) map.get("code_sql");
						if (!UtilFunc.hasText(codeSql)) {
							if (hasValid) {
								codeSql = String.format(
										"SELECT DATA_VALUE, DISPLAY_VALUE, DIS_ORDER FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = '%1$s' AND VALID_FLAG = '1' ORDER BY DIS_ORDER",
										codeType);
							} else {
								codeSql = String.format(
										"SELECT DATA_VALUE, DISPLAY_VALUE, DIS_ORDER FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = '%1$s' ORDER BY DIS_ORDER",
										codeType);
							}

						}

						Map tree = new LinkedHashMap();
						List<Object[]> lst = DBFunc.executeArrayList(conn, codeSql);
						for (Object[] a : lst) {
							tree.put(String.valueOf(a[0]), a[1]);
						}

						sqlCache.put(codeType, codeSql);
						codetableCache.put(codeType, tree);

						hasCode = true;
					}
				}

				if (DBFunc.existTable(conn, "SYS_CACHE_CONFIG")) {
					dbCache.remove(codeType);
					if (codetableCache.containsKey(codeType)) {
						if (DBFunc.executeInt(conn, "SELECT COUNT(1) AS CNT FROM SYS_CACHE_CONFIG WHERE CACHE_NAME = ?",
								new Object[] { "codetable_" + codeType }) > 0) {
							dbCache.put(codeType, new ArrayList<String>());
						}
					}
				}

				return hasCode;
			}
		} catch (Exception ex) {
			LogHelper.getLogger().warn("初始化代码缓存" + codeType + "出错", ex);
			return false;
		} finally {
			DBFunc.closeConnection(conn);
		}
	}

	/**
	 * Gets the code.
	 * 
	 * @param codeType
	 *            the code type
	 * @return the code
	 */
	@SuppressWarnings("rawtypes")
	private Map getCache(final String codeType) {
		Map map = codetableCache.get(codeType);
		if (map == null) {
			if (init(codeType)) {
				map = codetableCache.get(codeType);
			}
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.hygeia.framework.service.CodetableService#getSize()
	 */
	public int getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.hygeia.framework.service.CodetableService#getTimes()
	 */
	public int getTimes() {
		return times;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.hygeia.framework.service.CodetableService#init()
	 */
	public void init() {
		if (times == 0) {
			synchronized (lock) {
				if (times == 0) {
					refresh();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Closeable#close()
	 */
	public void close() {
		codetableCache.clear();
		sqlCache.clear();
		dbCache.clear();
		times = 0;
	}

}
