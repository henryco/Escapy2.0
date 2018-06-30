package net.tindersamurai.activecomponent.comp.factory;

import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.core.UtilityCoreComponent;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Function;


public class EscapyComponentAnnotationFactory implements IEscapyComponentFactory {

	private final Map<String, Function<Map<String, Object>, Object>> constructors;
	private final String nameSpaceSeparator;

	public EscapyComponentAnnotationFactory(String nameSpaceSeparator, Collection<Object> componentFactories) {
		this(nameSpaceSeparator, componentFactories.toArray());
	}

	public EscapyComponentAnnotationFactory(String nameSpaceSeparator, Object ... componentFactories) {
		this.nameSpaceSeparator = nameSpaceSeparator;
		this.constructors = new HashMap<>();

		Object[] factories = new Object[componentFactories.length + 1];
		System.arraycopy(componentFactories, 0, factories, 1, componentFactories.length);
		factories[0] = new UtilityCoreComponent();

		initialize(factories, "");
	}

	public EscapyComponentAnnotationFactory(Collection<Object> componentFactories) {
		this(".", componentFactories.toArray());
	}

	public EscapyComponentAnnotationFactory(Object ... componentFactories) {
		this(".", componentFactories);
	}

	private void initialize(Object[] factories, String rootNamespace) {
		for (val factory : factories) {

			val cfa = factory.getClass().getDeclaredAnnotation(EscapyComponentFactory.class);
			if (cfa == null) continue;

			val namespace = EscapyComponentFactory.Helper.getName(cfa, factory.getClass());
			processFactoryInstance(factory, rootNamespace + namespace);

			for (val nested : factory.getClass().getDeclaredClasses()) {
				val nestedFactory = newNested(nested, factory);
				initialize(new Object[]{nestedFactory}, rootNamespace + namespace + nameSpaceSeparator);
			}
		}
	}

	private static Object newNested(Class<?> aClass, Object parent) {

		val cons = aClass.getDeclaredConstructors();
		if (cons.length != 1)
			throw new RuntimeException("Nested component factory has more then one constructor");
		cons[0].setAccessible(true);

		try {
			switch (cons[0].getParameterCount()) {
				case 0: return cons[0].newInstance();
				case 1: return cons[0].newInstance(parent);
				default: throw new RuntimeException("Nested component factory constructor has arguments");
			}
		} catch (Exception e) {
			throw new RuntimeException("Cannot create instance of nested component factory: " + aClass);
		}
	}

	private void processFactoryInstance(Object factory, String namespace) {

		final EscapyComponentFactoryListener listener;
		if (factory instanceof EscapyComponentFactoryListener)
			listener = (EscapyComponentFactoryListener) factory;
		else listener = null;

		for (val method : factory.getClass().getDeclaredMethods()) {

			val esc = method.getDeclaredAnnotation(EscapyComponent.class);
			if (esc == null) continue;

			val componentName = namespace.trim() + nameSpaceSeparator + esc.value().trim();
			val parameters = method.getParameters();
			val args = new Map.Entry[parameters.length];

			for (int i = 0; i < parameters.length; i++) {
				val param = parameters[i];
				val arg = param.getDeclaredAnnotation(Arg.class);

				val pName = arg == null ? Integer.toString(i) : arg.value();
				args[i] = new AbstractMap.SimpleEntry<>(pName, param);
			}

			constructors.put(componentName, (Map<String, Object> arguments) -> {

				if (listener != null && !listener.enterComponent(componentName))
					return null;

				Object[] arr = new Object[args.length];
				for (int i = 0; i < args.length; i++) {

					val parameter = (Parameter) args[i].getValue();
					if (parameter.isVarArgs()) {

						Arg ann = parameter.getDeclaredAnnotation(Arg.class);
						if (ann != null) {
							val paramName = ann.value();
							val varargObject = arguments.get(paramName);
							if (varargObject != null) {
								arr[i] = varargObject;
								break;
							}
						}

						int len = arguments.size() - i;
						Object vars = Array.newInstance(parameter.getType().getComponentType(), len);

						for (int v = 0; v < len; v++)
							Array.set(vars, v, arguments.get(Integer.toString(v + i)));
						arr[i] = vars;

						break;
					}

					String argName = (String) args[i].getKey();
					arr[i] = arguments.get(argName);

					if (arr[i] == null)
						arr[i] = arguments.get(Integer.toString(i));

					if (arr[i] != null && arr[i].toString().equalsIgnoreCase("null"))
						arr[i] = null;
				}

				try {
					method.setAccessible(true);
					Object o = method.invoke(factory, arr);

					if (listener != null)
						return listener.leaveComponent(componentName, o);
					return o;

				} catch (Exception e) {
					throw new RuntimeException("Cannot create component: " + componentName, e);
				}
			});

		}
	}

	@Override
	public <T> T createComponent(String name, Map<String, Object> arguments) {
		//noinspection unchecked
		return (T) constructors.get(name).apply(arguments);
	}

}
