package net.irregular.escapy.map.data.comp.factory;

import lombok.val;
import net.irregular.escapy.map.data.comp.annotation.Arg;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponent;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponentFactory;

import java.util.*;
import java.util.function.Function;


public class EscapyComponentAnnotationFactory implements IEscapyComponentFactory {

	private final Map<String, Function<Map<String, Object>, Object>> constructors;


	public EscapyComponentAnnotationFactory(Collection<Object> componentFactories) {
		this(componentFactories.toArray());
	}

	public EscapyComponentAnnotationFactory(Object ... componentFactories) {
		this.constructors = new HashMap<>();
		initialize(componentFactories);
	}

	private void initialize(Object[] factories) {

		for (val factory : factories) {

			val cfa = factory.getClass().getDeclaredAnnotation(EscapyComponentFactory.class);
			if (cfa == null) continue;

			val namespace = EscapyComponentFactory.Helper.getName(cfa, factory.getClass());
			for (val method : factory.getClass().getDeclaredMethods()) {

				val esc = method.getDeclaredAnnotation(EscapyComponent.class);
				if (esc == null) continue;

				val componentName = namespace.trim() + ":" + esc.value().trim();
				val parameters = method.getParameters();
				val args = new Map.Entry[parameters.length];

				for (int i = 0; i < parameters.length; i++) {
					val param = parameters[i];
					val arg = param.getDeclaredAnnotation(Arg.class);

					val pName = arg == null ? Integer.toString(i) : arg.value();
					val pType = param.getType();
					args[i] = new AbstractMap.SimpleEntry<>(pName, pType);
				}

				constructors.put(componentName, (Map<String, Object> arguments) -> {

					Object[] arr = new Object[args.length];
					for (int i = 0; i < args.length; i++) {
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
						e.printStackTrace();
					}

					throw new RuntimeException("Cannot create component: " + componentName);
				});

			}

		}
	}

	@Override
	public <T> T createComponent(String name, Map<String, Object> arguments) {
		//noinspection unchecked
		return (T) constructors.get(name).apply(arguments);
	}

}
