package com.xxl.job.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * job handler
 *
 * @author xuxueli 2015-12-19 19:06:38
 */
public abstract class IJobHandler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * execute handler, invoked when executor receives a scheduling request
	 *
	 * @throws Exception
	 */
	public abstract void execute() throws Exception;


	/*@Deprecated
	public abstract ReturnT<String> execute(String param) throws Exception;*/

	/**
	 * init handler, invoked when JobThread init
	 */
	public void init() throws Exception {
		// do something
	}


	/**
	 * destroy handler, invoked when JobThread destroy
	 */
	public void destroy() throws Exception {
		// do something
	}


}
