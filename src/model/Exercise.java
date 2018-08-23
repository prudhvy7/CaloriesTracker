package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Exercise extends Item {
	
	private int reps;
	private int sets;
	private double weight;
	private double caloriesBurned;
	
	public Exercise(String name) {
		super(name);
	}
	
	// TableView Getters

	public StringProperty getStrExercise() {
		return new SimpleStringProperty(this.getName());
	}
	
	public StringProperty getStrSets() {
		return new SimpleStringProperty(Integer.toString(sets));
	}
	
	public StringProperty getStrReps() {
		return new SimpleStringProperty(Integer.toString(reps));
	}
	
	public StringProperty getStrWeight() {
		return new SimpleStringProperty(Double.toString(weight));
	}
	
	public StringProperty getStrCaloriesBurned() {
		return new SimpleStringProperty(Double.toString(caloriesBurned));
	}
	
	// Getters & Setters
	
	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

}
