
public class PersonalProfile {
	private String gender;
	private double exerciseAmount, weight, height;
	private int age;
	
	/**
	 * profile constructor.
	 */
	public PersonalProfile(String gender, double exerciseAmount, double weight, double height, int age){
		this.gender = gender;
		this.exerciseAmount = exerciseAmount;
		this.weight = weight;
		this.height = height;
		this.age = age;
	}
	/**
	 * setters
	 */
	public void setGender(String gender){
		this.gender = gender;
	}
	public void exerciseAmount(double exerciseAmount) {
		this.exerciseAmount = exerciseAmount;
	}
	public void setWeight(double weight){
		this.weight = weight;
	}
	public void setHeight(double height){
		this.height = height;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * getters
	 */
	public String getGender() {
		return this.gender;
	}
	public double getExerciseAmount() {
		return this.exerciseAmount;
	}
	public double getWeight() {
		return this.weight;
	}
	public double height() {
		return this.height;
	}
	public int age() {
		return this.age;
	}
	
	/**
	 * conversions
	 */
	public double convertWeightToKilogram(double weight) {
		this.weight = this.weight * 0.453592;
		return this.weight;
	}
	public double convertHeightToCentimeter(double height) {
		this.height = this.height * 2.54;
		return this.height;
	}
}
