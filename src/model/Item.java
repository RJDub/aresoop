package model;

import java.io.Serializable;

import enums.Task;

public abstract class Item implements Serializable {
	
	Colonist owner;
	public Task task;
	public int bonus_amount;
	
	public Item(){
		
	}
	
	
}
