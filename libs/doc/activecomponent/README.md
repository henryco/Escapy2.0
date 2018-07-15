# **Escapy Active Component**

Это рефлексивная библотека для сериализации и конфигурации компонентов с возможностью использования в скриптинге через внешние файлы.
На данный момент существует имплементация ореентированная на работу с **`XML`** файлами, однако ничего не мешает написать свою заточенную 
под **`JSON`**.

В основе концепции лежит разделение объектов на два вида: 

* **`Plain Java Objet`**
* **`Escapy Component`**

Первые как следует из названия это обыкновенные объекты java инстанцируемые как собственно обычные объекты через конструктор.
Вторые это компоненты библотеки которые конфигурируются через фабрики компонентов в коде с помощью callback' и аннотаций **`@EscapyComponent`**,
такие компоненты не обязательно должны быть объектами java, а могут быть и вовсе обыкновенными методами. В свою очередь 
фабрики компонентов **`@EscapyComponentFactory`** могут иметь внутреннее состояние изменяемое через те самые методы, что собственно и позволяет использовать
библиотеку для скриптинга (от сюда и название), а в связке с **`Dependency injection`** превращает библиотеку в убийственный комбайнер.

### **EscapyComponentParser**
Создание компонентов, как и вообще работа с библиотекой пользователем ограничивается в целом 
взаимодействием с интерфейсом **`EscapyComponentParser`** (его реализациями). Интерфейс описывает 3 метода
чья роль ясна из названий, это:

* **`void setComponentFactory(IEscapyComponentFactory factory);`**
* **`void setObjectFactory(IEscapyObjectFactory factory);`**
* **`<T> T parseComponent(String file);`**

Первые 2 метода нужны если потребуется расширить функциональность библиотеки и/или используемой
имплементации **`EscapyComponentParser`** посредством собственных реализаций под-фабрик парсера.

В 95% случаев, обычному пользователю данная функциональность не пригодится, а все взаимодействие с библиотекой
сведется к созданию стандартной имплементации интерфейса - **`XmlStreamComponentParser`**, конструктор которого
в качестве входных параметров получает массив Фабрик-компонентов 
и вызову метода **`<T> T parseComponent(String file);`**.


**Не следует путать `IEscapyComponentFactory` и фабрики компонентов отмеченные аннотацией `@EscapyComponentFactory`, это не одно и тоже!**

 ****

# **Escapy Component Factory**
Декларация и конфигурация инстанцируемых через внешний файл компонентов производится через фабрики компонентов которые могут иметь внутреннее состояние.
Для этого следуеть отметить класс аннотацией **`@EscapyComponentFactory(%name%)`** которая в качестве аргумента принимает
название фабрики. Так же фабрики могут содержать другие фложенные фабрики, однако в таком случае они должны иметь пустой публичный конструктор.

### **EscapyComponent**
За инстанцирование компонентов внутри фабрик отвечают методы отмеченные аннотацией **`@EscapyComponent(%name%)`** их входные параметры 
могут быть отмечены аннотацией **`@Arg(%name%)`** что бы можно было связать параметр из внешнего файла с параметром метода, в противном случае 
связывание будет происходить по порядковому номеру, так же параметрами могут быть варарги (varrags), их тоже можно именовать, но в таком случае
во внешнем файле придется передавать варарг явным способом через массив.

### **EscapyComponentFactoryListener**
Инстанцирование компонентов в фабрике можно перехватывать и контролировать через callback'и интерфейса **`EscapyComponentFactoryListener`**, 
для этого достаточно что бы фабрика просто реализовала 2 метода из этого интерфейса: 

* **`boolean enterComponent(String name);`**

* **`Object leaveComponent(String name, Object instance);`**

Первый метод вызывается перед созданием компонента - с его именем в качестве входного параметра, а от возвращаемого значения
зависит будет ли данный компонент создаваться вообще. 
Второй метод в качестве первого входного параметра принимает имя компонента, в свою очередь второй параметр это уже созданный компонент 
который по умолчанию возвращает метод.

### **UtilityCoreComponent**
Библиотека содержит некоторе число вспомагательных компонентов, среди которых:

* **`@EscapyComponent("debug") void debug(@Arg("args") Object ... args);`**

* **`@EscapyComponent("main")  void main(Object ... args);`**

* **`@EscapyComponent("array") Object newArrayInstance(@Arg("type") Class<?> type, @Arg("elements") Object ... args);`**

* **`@EscapyComponent("array-auto") Object newAutoArray(@Arg("elements") Object ... args);`**

* **`@EscapyComponent("array-list") List<?> newArrayList(@Arg("elements") Object ... args);`**

* **`@EscapyComponent("class") Class<?> findClass(@Arg("object") Object o);`**

* **`@EscapyComponent("class-by-name") Class<?> classByName(String name);`**

* **`@EscapyComponent("entry") Entry createEntry(@Arg("key") Object key, @Arg("value") Object value);`**

* **`@EscapyComponent("null") Object nullObject(Object ... args);`**

* **`@EscapyComponent("type") Class<?> primitiveType(@Arg("object") Object o);`**

C полным списком можно ознакомиться заглянув в класс-фабрику **```java UtilityCoreComponent```**.

### **Code example**

```java 
	
	@EscapyComponentFactory("some")
	public class SomeExampleFactory {
	
		private Object someState = new Integer(42);
			
		@EscapyComponent("mutate")
		public final void justVoidCall(Object arg) {
			if (someState instanceof Integer && arg instanceof Integer) 
				someState += arg;
			else someState = arg;
		}
		
		@EscapyComponent("state")
		public Object getState() {
			return someState;
		}
	
		@EscapyComponentFactory("nested-a") // Static class
		public static final class Nested_A implements EscapyComponentFactoryListener {
		
			@Override
			public boolean enterComponent(String name) {
				System.out.println("Create: " + name);
				return true;
			}

			@Override
			public Object leaveComponent(String name, Object instance) {
				System.out.println("Createed: " + name);
				System.out.println("Instance: " + instance);
				return instance;
			}
		
			@EscapyComponent("sum")
			public String sumToText(@Arg("a") float a, @Arg("b") float b) {
				return new Float(a + b).toString();
			}

		}
		
		@EscapyComponentFactory("second")
		public class SencondNestedFactory {
			
			@EscapyComponent("hello")
			public String hello(String ... varargs) {
				for (String s: varargs) 
					System.out.println(s);
				return "Hello world!";
			}
			
			@EscapyComponent("calculate")
			public int calc(@Arg("val") int val, Float ... args) {
				int r = val;
				for (Float f: args) 
					r += (int) f;
				return r;
			}
			
			@EscapyComponent("also-show-state") 
			public void showParentState() {
				System.out.println(someState);
			}
			
		}
		
	}
	
	
```


