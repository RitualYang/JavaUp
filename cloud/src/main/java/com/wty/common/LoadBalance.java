package com.wty.common;

/**
 * 负载均衡接口定义
 * @author 用户
 * @version
 * @see
 *
 */
public interface LoadBalance {
	
	/**
	 * 获取访问服务器
	 * @author 用户
	 * @version
	 * @return
	 */
	String getServer();
}
