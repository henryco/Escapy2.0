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

* **`void setComponentFactory(IEscapyComponentFactory nodeFactory);`**
* **`void setObjectFactory(IEscapyObjectFactory nodeFactory);`**
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

```JAVA 
	
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

****
 
# **External configuration file**
В конечном итоге, главная цель библиотеки заключается в использовании внешнего файла конфигурации,
по умолчанию в библиотеке присутствует поддержка **`XML`** формата.

Для работы используются 3 зарезервированных префикса и один ключевой тэг: 

* **`<c: ... `** - префикс применимый к компонентам
* **`<o: ... `** - префикс применимый к объектам java
* **`<m: ... `** - префикс применимый к методам объектов
* **`<new> `** - тэг применимый к конструкторам объектов java


### **Components**
Каждый файл конфигурации должен иметь хотя бы один (и только один) корневой компонент, который 
будет создан и возвращен при вызове метода **`<T> T parseComponent(String file);`** 
из интерфейса **`EscapyComponentParser`**.

Создание компонента в файле конфигурации объявляется через префикс **`<c: ... `** и последующим за 
ним названием компонента из фабрики компонентов разделенных (по умолчанию) точкой.

Например: 
```XML 
	<c:some-comp-nodeFactory.nested-nodeFactory.component-name />
```

* **`some-comp-nodeFactory`**, **`nested-nodeFactory`** - это фабрики компонентов отмеченные в коде аннотацией **`@EscapyComponentFactory(%name%)`**
* **`component-name`** - это компонент, метод из фабрики отмеченный аннотацией **`@EscapyComponent(%name%)`**


В случае с корневым компонентом, следует так же явно объявить используемые префиксы. 
Ниже показан полный пример демонстрационного корневого компонента:


```XML

	<c:u.main 
		xmlns:c="https://henryco.net/escapy"
		xmlns:o="https://henryco.net/escapy"
        xmlns:m="https://henryco.net/escapy"
	>
		<!-- Some components and objects here -->
	</с:u.main>
	
```
Так же, любой входной параметр компонента, не важно будь то другой компонент или же объект, 
может быть именован и в дальнейшем связан с входным параметром метода создающего компонента, для этого к тегу компонента следует добавить аттрибут **`name`**


```XML
	<c:nodeFactory.component>
		<o:float name="someFloat"> 42 </o:float>
		
		<c:nodeFactory-2.other name="other">
			<o:string> test </o:string>
		</c:nodeFactory-2.other>
	</c:nodeFactory.component>
```

### **Objects**
С объектами дело обстоит немного интереснее, как и в случае с компонентами, декларация объектов начинается с префикса, но на этом
сходства заканчиваются.

На все теги объектов распространяется несколько важных правил: 

1) По умолчанию тип объекта следует из названия к которому добавляется префикс `java.lang.`, а первая буква заменяется заглавной:

* **`<o:string />`** --- **`java.lang.String`**
* **`<o:float />`** --- **`java.lang.Float`**
* **`<o:object />`** --- **`java.lang.Object`**


2) Тип объекта можно указать самому используя атрибут **`class`**:

* **`<o:object class="java.util.List" />`** --- **`java.lang.Object`** --- **`java.util.List`**
* **`<o:number class="java.lang.Integer" />`** --- **`java.lang.Number`** --- **`java.lang.Integer`**


3) По умолчанию, для входного параметра конструктора отдается прдепочтение типу **`String`** если не указанно иначе: 

```XML
	<!-- new String( new String( "hello" ) ) -->
	<!-- String into string -->
	<o:string> hello </o:string>


	<!-- new Short( new String( "42" ) ) -->
	<o:short> 42 </o:short>


	<!-- new Short( new String ( "42" ) ) -->
	<o:short>
		<o:string> 42 </o:string>
	</o:short>


	<!-- new Float( new Float( new Stirng( "50" ) ) ) -->
	<o:float>
		<o:float> 50 </o:float>
	</o:float>
	
```


4) Конструктор автоматически определяется из типа или же в ручную из тега **`<new>`**:

```XML 
	<!-- Constructor: java.lang.Float -->
	<o:float> 10 </o:float> 


	<!-- Constructor: java.lang.Float -->
	<o:float> 
		<new> 10 </new>
	</o:float>


	<!-- Constructor: java.lang.Integer -->
	<o:object> 
		<new class="java.lang.Integer"> 10 </new>
	</o:object>


	<!-- Constructor: java.util.ArrayList -->
	<!-- Type: java.util.List -->
	<o:object class="java.util.List"> 
		<new class="java.util.ArrayList" /> 
	</o:object>
	
```

5) У объектов можно вызывать методы используя тэг **`<m: ...>`**, вызов произойдет сразу после создания объекта и перед его возращением дальше во флоу: 

```XML 
	<o:object class="java.util.List">
		
		<!-- empty constructor -->
		<new class="java.util.ArrayList"/>
		
		<!-- this is method statements -->
		<m:add>
			<o:integer>10</o:integer>
		</m:add>
		
		<m:add>
			<o:object>
				<new class="java.lang.Integer">22</new>
			</o:object>
		</m:add>
	</o:object>
```

.