package view.displayables;

import model.Item;

public class DisplayableItem implements DisplayableObject {

	Item item;
	
	public DisplayableItem (Item i){
		item = i;
	}
	
	@Override
	public String display() {
		String result = item.toString() + "\n";
		result += "Purpose of Item:\t" + item.getFunction() + "\n\n";
		result += "How to assign an item to colonist:\nSelect a colonist, then select an item.\nDouble click on the Item.\n\n";
		result += "How to reclaim an item:\nUnselect colonist, then select an item.\nDouble click on the item.\n\n";
		result += "How to get a new item:\nUnselect colonist and item,\nthen double click on the blank item panel.";
		return result;
	}

}
