package net.irregular.escapy.env.utils.proxy;

/**
 * @author Henry on 19/07/17.
 */
public interface EscapyProxyListener {

	void onProxyMethodInvoked(Object methodResult, String methodName, Object[] args);

}