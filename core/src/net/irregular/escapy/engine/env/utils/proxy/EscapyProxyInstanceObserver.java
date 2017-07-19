package net.irregular.escapy.engine.env.utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 19/07/17.
 */
public class EscapyProxyInstanceObserver {


	private final Collection<EscapyProxyListener> proxyListeners;
	public EscapyProxyInstanceObserver() {
		this.proxyListeners = new LinkedList<>();
	}
	public EscapyProxyInstanceObserver(EscapyProxyListener... listeners) {
		this();
		addProxyListeners(listeners);
	}
	public EscapyProxyInstanceObserver(Collection<EscapyProxyListener> listeners) {
		this();
		addProxyListeners(listeners);
	}



	@SuppressWarnings("unchecked")
	public <T> T proxyObservedInstance(T instance) {

		final Class instanceClass = instance.getClass();
		final InvocationHandler handler = (proxy, method, args) -> {
			Object result = method.invoke(proxy, args);
			for (EscapyProxyListener listener: proxyListeners)
				listener.onProxyMethodInvoked(result, method.getName());
			return result;
		};

		return (T) Proxy.newProxyInstance(instanceClass.getClassLoader(), instanceClass.getInterfaces(), handler);
	}




	public void addProxyListeners(EscapyProxyListener... listeners) {
		proxyListeners.addAll(Arrays.asList(listeners));
	}

	public void addProxyListeners(Collection<EscapyProxyListener> listeners) {
		proxyListeners.addAll(listeners);
	}


	public void removeProxyListeners(EscapyProxyListener... listeners) {
		for (EscapyProxyListener listener: listeners) proxyListeners.remove(listener);
	}

	public void removeProxyListeners(Collection<EscapyProxyListener> listeners) {
		for (EscapyProxyListener listener: listeners) proxyListeners.remove(listener);
	}

	public void removeAllProxyListeners() {
		proxyListeners.clear();
	}
}