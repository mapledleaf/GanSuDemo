/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.test;

import java.util.Date;

import com.powersi.hygeia.framework.scheduling.Trigger;
import com.powersi.hygeia.framework.scheduling.TriggerContext;
import com.powersi.hygeia.framework.util.DateFunc;

public class TestTrigger implements Trigger {

	/* (non-Javadoc)
	 * @see com.powersi.hygeia.framework.scheduling.Trigger#nextExecutionTime(com.powersi.hygeia.framework.scheduling.TriggerContext)
	 */
	public Date nextExecutionTime(TriggerContext triggerContext) {
		return DateFunc.addSeconds(new java.util.Date(), 15);
	}

}
