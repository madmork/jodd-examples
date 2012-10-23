package madvoc.item;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

/**
 * Item service.
 */
@PetiteBean
public class ItemService {

	@PetiteInject
	ItemManager itemManager;

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
}
