package madvoc;

import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.InterceptedBy;
import jodd.madvoc.meta.MadvocAction;

/**
 * Example of configurable interceptors.
 */
@MadvocAction
@InterceptedBy(ConfigurableActionInterceptorStack.class)
public class ConfigurableAction {

	/**
	 * Echo will be executed twice.
	 */
	@Action
	public void view() {
		System.out.println("ConfigurableAction.view");
	}
}
