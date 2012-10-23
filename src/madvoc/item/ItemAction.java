// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package madvoc.item;

import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.Out;
import jodd.madvoc.meta.InterceptedBy;
import jodd.madvoc.meta.Action;
import jodd.madvoc.interceptor.EchoInterceptor;
import jodd.madvoc.interceptor.DefaultWebAppInterceptors;
import jodd.petite.meta.PetiteInject;

import java.util.List;

@MadvocAction
@InterceptedBy({EchoInterceptor.class, DefaultWebAppInterceptors.class})
public class ItemAction {

	/**
	 * Inject item manager, session-scoped bean.
	 */
//	@PetiteInject
//	ItemManager itemManager;

	/**
	 * Example of mixed scopes. Injecting singleton with
	 * current item manager of this session.
	 */
	@PetiteInject
	ItemService itemService;

	@In
	Item item;

	@Action
	public String add() {
		ItemManager itemManager = itemService.getItemManager();

		System.out.println("ItemAction.add");
		System.out.println(itemManager);
		itemManager.add(item);
		prepare();
		return "#list.ok";
	}


	@Out
	List<Item> items;

	@Action
	public String list() {
		ItemManager itemManager = itemService.getItemManager();

		System.out.println("ItemAction.list");
		System.out.println(itemManager);
		prepare();
		return "ok";
	}

	private void prepare() {
		ItemManager itemManager = itemService.getItemManager();

		items = itemManager.getAllItems();
	}

}
