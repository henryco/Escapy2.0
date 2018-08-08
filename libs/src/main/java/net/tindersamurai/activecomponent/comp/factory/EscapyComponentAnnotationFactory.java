package net.tindersamurai.activecomponent.comp.factory;

import lombok.Getter;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.core.FilesCoreComponent;
import net.tindersamurai.activecomponent.core.UtilityCoreComponent;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Function;


public class EscapyComponentAnnotationFactory implements IEscapyComponentFactory {

	private final Map<String, Function<Map<String, Object>, Object>> constructors;
	private final Map<String, Object> factories;
	private @Getter final String nameSpaceSeparator;

	public EscapyComponentAnnotationFactory(String nameSpaceSeparator, Collection<Object> componentFactories) {
		this(nameSpaceSeparator, componentFactories.toArray());
	}

	public EscapyComponentAnnotationFactory(String nameSpaceSeparator, Object ... componentFactories) {
		this.nameSpaceSeparator = nameSpaceSeparator;
		this.constructors = new HashMap<>();
		this.factories = new HashMap<>();

		Object[] factories = new Object[componentFactories.length + 2];
		System.arraycopy(componentFactories, 0, factories, 2, componentFactories.length);
		factories[0] = new UtilityCoreComponent();
		factories[1] = new FilesCoreComponent();

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
		this.factories.put(namespace, factory);

		for (val method : factory.getClass().getDeclaredMethods()) {

			val esc = method.getDeclaredAnnotation(EscapyComponent.class);
			if (esc == null) continue;

			val annotationName = esc.value().trim();
			val componentName = namespace.trim() + nameSpaceSeparator + annotationName;
			val parameters = method.getParameters();
			val args = new Map.Entry[parameters.length];

			for (int i = 0; i < parameters.length; i++) {
				val param = parameters[i];
				val arg = param.getDeclaredAnnotation(Arg.class);

				val pName = arg == null ? Integer.toString(i) : arg.value();
				args[i] = new AbstractMap.SimpleEntry<>(pName, param);
			}

			constructors.put(componentName, (Map<String, Object> arguments) -> {

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

						int len = Math.max(0, arguments.size() - i);
						Object vars = Array.newInstance(parameter.getType().getComponentType(), len);
						for (int v = 0; v < len; v++) {
							try {
								Array.set(vars, v, arguments.get(Integer.toString(v + i)));
							} catch (IllegalArgumentException e) {
								throw new RuntimeException("Component factory argument type mismatch:\n" +
										"\tREQUIRED: " + parameter.getType().getComponentType() + "\n" +
										"\tFOUND: " + arguments.get(Integer.toString(v + i)).getClass() + "\n" +
										"\tnote: IF YOU PASS ARRAY TO VARARG, YOU SHOULD DO IT BY NAME!"
										, e);
							}
						}
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
					return method.invoke(factory, arr);

				} catch (Exception e) {
					String msg = "Cannot create component: " + componentName
							+ "\nMETHOD: " + method
							+ "\nARGS: " + Arrays.toString(arr) + "\n";
					throw new RuntimeException(msg, e);
				}
			});

		}
	}

	@Override
	public <T> T createComponent(String name, Map<String, Object> arguments) {
		try {
			//noinspection unchecked
			return (T) constructors.get(name).apply(arguments);
		} catch (NullPointerException e) {
			val msg= "NULL_POINTER COMPONENT: " + name
					+ "\nCONSTRUCTOR: " + constructors.get(name)
					+ "\nARGS: " + arguments.toString();
			throw new RuntimeException(msg, e);
		}
	}

	@Override
	public Object getFactory(String name) {
		val f = factories.get(name);
		if (f != null) return f;
		return factories.get(name.substring(0, name.lastIndexOf(nameSpaceSeparator)));
	}
}
