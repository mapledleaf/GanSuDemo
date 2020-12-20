package com.powersi.biz.medicare.inhospital.service.db;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface Kc24EncapService extends Serializable {

	@SuppressWarnings("rawtypes")
	public List listDeclare = Arrays
			.asList(new String[] { "S01", "S02", "C000", "C001", "C002", "C003", "D001", "D002", "D003" });

	@SuppressWarnings("rawtypes")
	public List lisTreatmentType = Arrays.asList(new String[] { "110", "111", "11P", "11J", "120", "121", "122", "123",
			"124", "125", "126", "12M", "131", "140", "161", "162", "163", "170", "180", "128", "129", });

	@SuppressWarnings("rawtypes")
	public List<Map> loadKc24Info(List kc21List, List kc22List, List kc27List);

}