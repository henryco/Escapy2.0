package net.irregular.escapy.engine.env.utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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


	@SuppressWarnings("unchecked")
	public <T> T proxyObservedInstance(T instance) {

		final Class instanceClass = instance.getClass();

		return (T) Proxy.newProxyInstance(
				instanceClass.getClassLoader(),
				instanceClass.getInterfaces(),
				new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

				try {

					Object result = method.invoke(instance, args);
					for (EscapyProxyListener listener : proxyListeners)
						listener.onProxyMethodInvoked(result, method.getName());
					return result;

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(4);
				}

				throw new RuntimeException("proxyObservedInstanceError");
			}
		});
	}


	@Override
	public String toString() {
		return "EscapyProxyInstanceObserver{" +
				"proxyListeners=" + proxyListeners +
				'}';
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