#### element and TypeMirror  

```java
package com.example;	// PackageElement

public class Foo {		// TypeElement

	private int a;		// VariableElement
	private Foo other; 	// VariableElement

	public Foo () {} 	// ExecuteableElement

	public void setA ( 	// ExecuteableElement
	                 int newA	// TypeElement
	                 ) {}
}
```  

#### element  

```java
Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Easy.class);
        for (Element element:elements){
            element.getkind() //获取元素类型(type、method等)
        }
```
