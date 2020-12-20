/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.message.ctrl;

import java.util.List;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.sys.util.MessageHelper;

public class CtSYS000020QueryMessage extends BusiService {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.hygeia.framework.IBusiService#execute(com.powersi.hygeia.
	 * framework.IParameterObj, com.powersi.hygeia.framework.IResultObj)
	 */
	public int execute(IParameterObj param, IResultObj result) throws Exception {
		List lst = MessageHelper.queryMessageList("1".equals(getParameter("login_flag")));
		
		if (CollectionHelper.isNotEmpty(lst)) {
			result.setResultSet("messageinfo", lst);
		}

		return SUCCESS;
	}
}
