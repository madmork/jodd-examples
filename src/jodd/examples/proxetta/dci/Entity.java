// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.proxetta.dci;

/**
 * An entity, POJO.
 */
public class Entity implements MyEntity {

	private String foo = "Hello from the Entity!";

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public void doEntityStuff() {
		System.out.println("foo = " + foo);
	}
}
