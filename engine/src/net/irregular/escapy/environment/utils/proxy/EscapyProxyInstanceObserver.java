package net.irregular.escapy.environment.utils.proxy;

import net.irregular.escapy.environment.utils.EscapyLogger;

import java.lang.reflect.InvocationTargetException;
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
	public <T> T create(T instance) {

		final Class instanceClass = instance.getClass();
		return (T) Proxy.newProxyInstance(instanceClass.getClassLoader(), instanceClass.getInterfaces(),
				(Object proxy, Method method, Object[] args) -> {

					try {

						Object result = method.invoke(instance, args);
						for (EscapyProxyListener listener : proxyListeners)
							listener.onProxyMethodInvoked(result, method.getName(), args);
						return result;

					} catch (Exception e) {
						EscapyLogger logger = new EscapyLogger("ProxyObservedInstance").fileJava();
						if (e instanceof InvocationTargetException) {
							Throwable exception = ((InvocationTargetException) e).getTargetException();
							logger.log(exception, true);
						} else logger.log(e, true);

						System.exit(4);
					}

					throw new RuntimeException("proxyObservedInstanceError");
				}
		);
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