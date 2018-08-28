package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Food extends Item {

	// Used to tell if this is the cutter (i.e the one that we select on the table)
	private boolean template = false;

	
	// Nutrition values
	private double amount;
	private double proteins;
	private double carbohydrates;
	private double fats;
	// Calculated from proteins, carbohydrates and fats
	private double calories;
	private double quantity;
	
	// Used to tell if this food was imported from the database or if we added it
	// ourselves
	private boolean custom;

	// static values
	private final double ogAmount;
	private final double ogCalories, ogProteins, ogCarbohydrates, ogFats;

	public Food(String name, double[] values, boolean temp) {
		super(name);
		
		// Make sure there is enough values, if there isn't then throw an exception
		if(values.length < 5) throw new IllegalArgumentException("Invalid array size");
		
		
		
		

		// Needs validation
		this.template = temp;
		
		
		this.carbohydrates = values[0];
		this.fats = values[1];
		this.proteins = values[2];
		this.calories = (this.proteins * 4) + (this.carbohydrates * 4) + (this.fats * 9);

		this.amount = values[3];

		// Validate quantity values
		if (this.template) {
			this.quantity = 1;
		} else {
			this.quantity = values[4];
		}

		// Store static values (that will never change)
		
		ogCarbohydrates = values[0];
		ogFats = values[1];
		ogProteins = values[2];
		ogAmount = values[3];
		ogCalories = (proteins * 4) + (carbohydrates * 4) + (fats * 9);
	}

	// Copy constructor
	public Food(String name, Food food) {
		super(name);

		this.calories = food.getCalories();
		this.template = food.getTemplate();

		if (this.template) {
			this.quantity = 1;
		} else {
			this.quantity = food.getQuantity();
		}

		this.carbohydrates = food.getCarbohydrates();
		this.fats = food.getFats();
		this.proteins = food.getProteins();
		this.amount = food.getAmount();

		this.custom = food.getCustom();

		this.ogAmount = food.getOgAmount();
		this.ogCarbohydrates = food.getOgCarbohydrates();
		this.ogFats = food.getOgFats();
		this.ogProteins = food.getOgProteins();
		this.ogCalories = (ogProteins * 4) + (ogCarbohydrates * 4) + (ogFats * 9);
	}

	public boolean getTemplate() {
		return this.template;
	}

	private void setTemplate(boolean val) {
		this.template = val;
	}

	public double getOgAmount() {
		return ogAmount;
	}

	public double getOgCalories() {
		return ogCalories;
	}

	public double getOgProteins() {
		return ogProteins;
	}

	public double getOgCarbohydrates() {
		return ogCarbohydrates;
	}

	public double getOgFats() {
		return ogFats;
	}

	public double getCalories() {
		return this.calories;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;

		// Multiply everything else by quantity automatically
		this.amount = ogAmount * quantity;
		this.calories = ogCalories * quantity;
		this.carbohydrates = ogCarbohydrates * quantity;
		this.fats = ogFats * quantity;
		this.proteins = ogProteins * quantity;
	}
	
	public double getQuantity() {
		return this.quantity;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
		// This also means we should change the name to have (custom) in brackets
		this.setName(this.getName() + " (custom) ");
	}

	public boolean getCustom() {
		return this.custom;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}


	/*
	 * 
	 * String methods (for viewing on the table, but not their direct values)
	 * 
	 */
	public StringProperty getStrFood() {
		return new SimpleStringProperty(this.getName());
	}

	public StringProperty getStrAmount() {
		return new SimpleStringProperty(Double.toString(amount));
	}

	public StringProperty getStrCalories() {
		return new SimpleStringProperty(Double.toString(Helper.round(calories, 2)));
	}

	public StringProperty getStrCarbs() {
		return new SimpleStringProperty(Double.toString(Helper.round(carbohydrates, 2)));
	}

	public StringProperty getStrFats() {
		return new SimpleStringProperty(Double.toString(Helper.round(fats, 2)));
	}

	public StringProperty getStrProts() {
		return new SimpleStringProperty(Double.toString(Helper.round(proteins, 2)));
	}

	public StringProperty getStrQuantity() {
		return new SimpleStringProperty(Double.toString(quantity));
	}
}
