package view;

import model.Item;

public class DisplayableItem implements DisplayableObject {

	Item item;
	
	public DisplayableItem (Item i){
		item = i;
	}
	
	@Override
	public String display() {
		String result = item.toString()+'\n';
		result += "Purpose of Item:\t";
		result += "How to get a new item:\t";
		return result;
	}

}
