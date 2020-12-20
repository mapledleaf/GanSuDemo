package com.powersi.biz.medicare.inhospital.service.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class DateServiceImpl implements DateService {
	private static final long serialVersionUID = 1L;

	@Override
	public Date toDate(String date, String format) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String dateToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	@Override
	public boolean isValidDate(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return false;
		}
		if (StringUtils.isBlank(format)) {
			return false;
		}
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return date.equals(simpleDateFormat.format(simpleDateFormat.parse(date)));
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String formatStringDate(String date, String division) {
		if (StringUtils.isNotBlank(date)) {
			String dateTemp = null;
			dateTemp = date.trim();
			if (dateTemp.length() <= 6) {
				return date;
			}
			if (dateTemp.length() > 10) {
				dateTemp = dateTemp.substring(0, 10);
			}
			String year = "";
			String month = "";
			String day = "";
			if (dateTemp.indexOf(division) != -1) {
				int index1 = -1;
				int index2 = -1;
				index1 = dateTemp.indexOf(division);
				if (index1 == 2) {
					year = "20" + dateTemp.substring(0, index1);
					index2 = dateTemp.indexOf(division, index1 + 1);
					if (index2 != -1) {
						month = dateTemp.substring(index1 + 1, index2);
						if (month != null && month.trim().length() == 2) {
							month = month.trim();
						} else if (month != null && month.trim().length() == 1) {
							month = "0" + month.trim();
						} else {
							return date;
						}

						int len = dateTemp.length() > index2 + 3 ? index2 + 3 : dateTemp.length();
						if (len > index2 + 1) {
							day = dateTemp.substring(index2 + 1, len);
							if (day != null && day.trim().length() == 2) {
								day = day.trim();
							} else if (day != null && day.trim().length() == 1) {
								day = "0" + day.trim();
							} else {
								return date;
							}
						} else {
							return date;
						}
					} else {
						return date;
					}
				} else if (index1 == 4) {
					year = dateTemp.substring(0, index1);
					index2 = dateTemp.indexOf(division, index1 + 1);
					if (index2 != -1) {
						month = dateTemp.substring(index1 + 1, index2);
						if (month != null && month.trim().length() == 2) {
							month = month.trim();
						} else if (month != null && month.trim().length() == 1) {
							month = "0" + month.trim();
						} else {
							return date;
						}

						int len = dateTemp.length() > index2 + 3 ? index2 + 3 : dateTemp.length();
						if (len > index2 + 1) {
							day = dateTemp.substring(index2 + 1, len);
							if (day != null && day.trim().length() == 2) {
								day = day.trim();
							} else if (day != null && day.trim().length() == 1) {
								day = "0" + day.trim();
							} else {
								return date;
							}
						} else {
							return date;
						}
					} else {
						return date;
					}
				} else {
					return date;
				}
			} else {
				return date;
			}
			return year + month + day;
		}
		return date;
	}

	@Override
	public String formatStringDate(String date) {
		if (StringUtils.isNotBlank(date)) {
			String strTemp = null;
			if (date.indexOf("-") != -1) {
				strTemp = formatStringDate(date, "-");
			} else if (date.indexOf("/") != -1) {
				strTemp = formatStringDate(date, "/");
			} else {
				if (date.trim().length() > 8) {
					strTemp = date.trim().substring(0, 8);
				} else if (date.trim().length() == 8) {
					strTemp = date.trim();
				} else if (date.trim().length() == 6) {
					strTemp = "20" + date.trim();
				} else {
					strTemp = date;
				}
			}
			if (isValidDate(strTemp, "yyyyMMdd")) {
				return strTemp;
			} else {
				return date;
			}
		}
		return date;
	}

	@Override
	public String formatDate(String date) {
		String strTemp = null;
		strTemp = date.replaceAll("/", "-");
		if (isValidDate(strTemp, "yyyy-MM-dd HH:mm:ss")) {
			return strTemp.substring(0, 10);
		}
		if (isValidDate(strTemp, "yyyy-MM-dd")) {
			return strTemp.substring(0, 10);
		}
		strTemp = formatStringDate(strTemp);
		if (isValidDate(strTemp, "yyyyMMdd")) {
			return strTemp.substring(0, 4) + "-" + strTemp.substring(4, 6) + "-" + strTemp.substring(6, 8);
		}
		return date;
	}

	@Override
	public String formatDateTime(String dateTime) {
		String strTemp1 = null;
		strTemp1 = dateTime.replaceAll("/", "-");
		if (isValidDate(strTemp1, "yyyy-MM-dd HH:mm:ss")) {
			return strTemp1;
		}
		if (isValidDate(strTemp1, "yyyy-MM-dd")) {
			return strTemp1 + " 00:00:00";
		}
		String strTemp2 = null;
		strTemp2 = formatStringTime(strTemp1);
		if (isValidDate(strTemp2, "yyyyMMddHHmmss")) {
			return strTemp2.substring(0, 4) + "-" + strTemp2.substring(4, 6) + "-" + strTemp2.substring(6, 8) + " "
					+ strTemp2.substring(8, 10) + ":" + strTemp2.substring(10, 12) + ":" + strTemp2.substring(12, 14);
		}
		strTemp2 = formatStringDate(strTemp1);
		if (isValidDate(strTemp2, "yyyyMMdd")) {
			return strTemp2.substring(0, 4) + "-" + strTemp2.substring(4, 6) + "-" + strTemp2.substring(6, 8)
					+ " 00:00:00";
		}
		return dateTime;
	}

	@Override
	public String formatStringTime(String dateTime, String division) {
		if (StringUtils.isNotBlank(dateTime)) {
			String timeTemp = null;
			timeTemp = dateTime.trim();
			if (timeTemp.length() < 14) {
				if (timeTemp.length() == 8) {
					return timeTemp.length() + "000000";
				}
				return dateTime;
			}
			if (timeTemp.length() > 19) {
				timeTemp = timeTemp.substring(0, 19);
			}
			if (timeTemp.replaceAll(" ", "").length() == 14) {
				return timeTemp.replaceAll(" ", "");
			}
			String year = "";
			String month = "";
			String day = "";
			String hour = "";
			String minute = "";
			String second = "";
			if (timeTemp.indexOf(division) != -1) {
				int index1 = -1;
				int index2 = -1;
				index1 = timeTemp.indexOf(division);
				if (index1 == 2) {
					year = "20" + timeTemp.substring(0, index1);
					index2 = timeTemp.indexOf(division, index1 + 1);
					if (index2 != -1) {
						month = timeTemp.substring(index1 + 1, index2);
						if (month != null && month.trim().length() == 2) {
							month = month.trim();
						} else if (month != null && month.trim().length() == 1) {
							month = "0" + month.trim();
						} else {
							return dateTime;
						}
						int len = timeTemp.length() > index2 + 3 ? index2 + 3 : timeTemp.length();
						if (len > index2 + 1) {
							day = timeTemp.substring(index2 + 1, len);
							if (day != null && day.trim().length() == 2) {
								day = day.trim();
							} else if (day != null && day.trim().length() == 1) {
								day = "0" + day.trim();
							} else {
								return dateTime;
							}
						} else {
							return dateTime;
						}
					} else {
						return dateTime;
					}
				} else if (index1 == 4) {
					year = timeTemp.substring(0, index1);
					index2 = timeTemp.indexOf(division, index1 + 1);
					if (index2 != -1) {
						month = timeTemp.substring(index1 + 1, index2);
						if (month != null && month.trim().length() == 2) {
							month = month.trim();
						} else if (month != null && month.trim().length() == 1) {
							month = "0" + month.trim();
						} else {
							return dateTime;
						}
						int len = timeTemp.length() > index2 + 3 ? index2 + 3 : timeTemp.length();
						if (len > index2 + 1) {
							day = timeTemp.substring(index2 + 1, len);
							if (day != null && day.trim().length() == 2) {
								day = day.trim();
							} else if (day != null && day.trim().length() == 1) {
								day = "0" + day.trim();
							} else {
								return dateTime;
							}
						} else {
							return dateTime;
						}
					} else {
						return dateTime;
					}
				} else {
					return dateTime;
				}
				index1 = -1;
				index2 = -1;
				if (timeTemp.indexOf(":") != -1) {
					index1 = timeTemp.indexOf(":");
					if (index1 - 2 >= 0) {
						hour = timeTemp.substring(index1 - 2, index1);
						if (hour != null && hour.trim().length() == 2) {
							hour = hour.trim();
						} else if (hour != null && hour.trim().length() == 1) {
							hour = "0" + hour.trim();
						} else {
							return dateTime;
						}
						index2 = timeTemp.indexOf(":", index1 + 1);
						if (index2 != -1) {
							minute = timeTemp.substring(index1 + 1, index2);
							if (minute != null && minute.trim().length() == 2) {
								minute = minute.trim();
							} else if (minute != null && minute.trim().length() == 1) {
								minute = "0" + minute.trim();
							} else {
								return dateTime;
							}
							int len = timeTemp.length() > index2 + 3 ? index2 + 3 : timeTemp.length();
							if (len > index2 + 1) {
								second = timeTemp.substring(index2 + 1, len);
								if (second != null && second.trim().length() == 2) {
									second = second.trim();
								} else if (second != null && second.trim().length() == 1) {
									second = "0" + second.trim();
								} else {
									return dateTime;
								}
							} else {
								return dateTime;
							}
						} else {
							return dateTime;
						}
					} else {
						return dateTime;
					}
				} else {
					return year + month + day + "000000";
				}
			} else {
				return dateTime;
			}
			return year + month + day + hour + minute + second;
		}
		return dateTime;
	}

	@Override
	public String formatStringTime(String dateTime) {
		if (StringUtils.isNotBlank(dateTime)) {
			String strTemp = null;
			if (dateTime.indexOf("-") != -1) {
				strTemp = formatStringTime(dateTime, "-");
			} else if (dateTime.indexOf("/") != -1) {
				strTemp = formatStringTime(dateTime, "/");
			} else {
				if (dateTime.trim().length() > 14) {
					dateTime = dateTime.trim().replaceAll(" ", "");
					if (dateTime.length() > 14) {
						strTemp = dateTime.substring(0, 14);
					}
				} else if (dateTime.trim().length() == 8) {
					strTemp = dateTime.trim() + "000000";
				}
			}
			if (isValidDate(strTemp, "yyyyMMddHHmmss")) {
				return strTemp;
			} else {
				return dateTime;
			}
		}
		return dateTime;
	}
}
