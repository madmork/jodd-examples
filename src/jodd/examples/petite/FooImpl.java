// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.petite;

import jodd.petite.meta.PetiteInject;
import jodd.petite.meta.PetiteBean;

@PetiteBean("foo")
public class FooImpl implements Foo {

	@PetiteInject
	public Boo boo;

	public void foo() {
		if (boo != null) {
			boo.boo();
		}
		System.out.println("foo: " + toString());
	}
}
