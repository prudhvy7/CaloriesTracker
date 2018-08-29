package model;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Food extends Item {

	private String customTag = " (custom) ";
	// Used to tell if this is the cutter (i.e the one that we select on the table)
	private boolean template = false;

	// Nutrition values
	private double amount = 0;
	private double carbohydrates = 0;
	private double proteins = 0;
	private double fats = 0;
	// Calculated from proteins, carbohydrates and fats
	private double calories = 0;
	private double quantity = 1;

	// Used to tell if this food was imported from the database or if we added it
	// ourselves
	private boolean custom = false;

	// static values
	private double ogAmount;
	private double ogCalories, ogCarbohydrates, ogProteins, ogFats;

	/**
	 * 
	 * @param name
	 * @param values
	 *            {amount, carbs, protein, fats}
	 * @param temp
	 *            {tells us if this is a template}
	 */
	public Food(String name, double[] values) {
		super(name);

		validateArray(values);
		storeOg(values);
		calories();
	}

	private final void storeOg(double[] values) {
		// Store static values (that will never change)
		ogAmount = values[0];
		ogCarbohydrates = values[1];
		ogProteins = values[2];
		ogFats = values[3];
		ogCalories = (ogCarbohydrates * 4) + (ogProteins * 4) + (ogFats * 9);
	}

	/**
	 * Constructor which accepts the template boolean value
	 * 
	 * @param name
	 * @param values
	 * @param template
	 */
	public Food(String name, double[] values, boolean template) {
		super(name);

		validateArray(values);
		storeOg(values);
		calories();

		this.template = template;
	}

	/**
	 * Calculate calories based off values
	 */
	private void calories() {
		this.calories = (this.carbohydrates * 4) + (this.proteins * 4) + (this.fats * 9);
	}

	/**
	 * Validates array checks:{ size == 4, negatives, amount > 0}
	 * 
	 * @param values
	 */
	private void validateArray(double[] values) {

		// Make sure there is enough values, if there isn't then throw an exception
		if (values.length != 4)
			throw new IllegalArgumentException("Invalid array size");

		// We have enough values so now check if they are positive
		for (int i = 0; i < values.length; i++) {
			if (values[i] < 0)
				throw new IllegalArgumentException("Negative array values");
		}

		// Check if the amount if 0
		if (values[0] == 0)
			throw new IllegalArgumentException("Amount cannot be 0");

		// Else accept the values given
		this.amount = values[0];
		this.carbohydrates = values[1];
		this.proteins = values[2];
		this.fats = values[3];
	}

	// Copy constructor
	public Food(String name, Food food) {
		super(name);

		validateFood(food);

		this.amount = food.getAmount();
		this.carbohydrates = food.getCarbohydrates();
		this.proteins = food.getProteins();
		this.fats = food.getFats();

		this.calories = food.getCalories();

		this.ogAmount = food.getOgAmount();
		this.ogCarbohydrates = food.getOgCarbohydrates();
		this.ogProteins = food.getOgProteins();
		this.ogFats = food.getOgFats();
		this.ogCalories = food.getOgCalories();
	}

	private void validateFood(Food food) {
		// Validates the food object that was passed in by checking its macros
		ArrayList<Double> vals = new ArrayList<Double>();
		vals.addAll(Arrays.asList(food.getAmount(), food.getCarbohydrates(), food.getProteins(), food.getFats()));

		int i, len = vals.size();
		for (i = 0; i < len; i++) {
			// Check for negatives
			if(vals.get(i) < 0) throw new IllegalArgumentException("Negative array values");
		}

		// Make sure amount is not 0
		if(food.getAmount() == 0) throw new IllegalArgumentException("Amount cannot be 0");


		// Make sure the calories that are set is correct
		double sum = (food.getCarbohydrates() * 4) + (food.getProteins() * 4) + (food.getFats() * 9);
		
		if(sum != food.getCalories()) throw new IllegalArgumentException("Invalid calories given for macros");
	}

	public String getCustomTag() {
		return this.customTag;
	}

	public boolean getTemplate() {
		return this.template;
	}

	public void setTemplate(boolean val) {
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
		// We cannot set the quantity if we are a cutter Food
		if (!this.template) {
			this.quantity = quantity;
			// Multiply everything else by quantity automatically
			this.amount = ogAmount * quantity;
			this.calories = ogCalories * quantity;
			this.carbohydrates = ogCarbohydrates * quantity;
			this.fats = ogFats * quantity;
			this.proteins = ogProteins * quantity;
		} else {
			System.out.println("You tried to edit a template food!");
		}
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
		// This also means we should change the name to have (custom) in brackets
		this.setName(this.getName() + this.customTag);
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
