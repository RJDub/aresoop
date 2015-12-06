package model;

import java.io.Serializable;

import enums.Task;

public abstract class Item implements Serializable {
	
	public Colonist owner;
	public Task task;
	public int bonus_amount;
	
	public Item(){
		
	}

	public String getOwner() {
		if (owner != null)
			return owner.getName();
		else 
			return "Unused";
	}

	public void setOwner(Colonist c) {
		owner = c;
	}

	public void reclaim(Item i) {
		owner.removeItem(i);
	}
	
	public abstract String getFunction();
	

	
}
