import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.java.frej.fuzzy.Fuzzy;


public class AnalyzeText {
	public static TreeMap<String, String[]> nutritionDetails(String s){
		
        TreeMap<String, String[]> t = new TreeMap<String, String[]>();     		//Creating treemap that will hold all values for organic quantities
        
        TextIO.readFile(s);    													//read in the text file.
        
        while(!TextIO.eof()) {    												//loop through the text file.
            String data = "";       											// will be the key for the map
            ArrayList<String> quantity = new ArrayList<String>();				//array list to hold quantities
            
            String tempLine = TextIO.getln();									//read in the current line of the text file	
            
            Pattern percentQuantities = Pattern.compile("\\d+%");				//regex to search for all numbers followed by a %
            Matcher percentMatcher = percentQuantities.matcher(tempLine);
            
            Pattern gramsQuantity = Pattern.compile("\\d+(g|mg)");				//regex to search for all numbers followed by a g or mg
            Matcher gramsMatcher = gramsQuantity.matcher(tempLine);
            
            Pattern calorieCounter = Pattern.compile("Cal.*\\s\\d+");			// regex to search for calories
            Matcher calorieMatcher = calorieCounter.matcher(tempLine);
            
            while (percentMatcher.find()) {										//add the values found by regex into the array list
            	quantity.add(percentMatcher.group());
            }
            
            while (gramsMatcher.find()) {
            	quantity.add(gramsMatcher.group());
            }
            
            while(calorieMatcher.find()) {
            	String temp = calorieMatcher.group().substring(9);
            	quantity.add(temp);
            }
           
            String[] quantityArray = new String[quantity.size()];				//convert array list to an array
            for(int i= 0; i < quantityArray.length; i ++) {
            	quantityArray[i] = quantity.get(i);
            }
             
            /**
             * fuzzy spelling search goes here to put values into data and amount. 
             */
            
        	if(Fuzzy.containsOneOf(tempLine, "Calorie")) {
        		data = "Calorie";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Protein")){
        		data = "Protein";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Total Fat")){
        		data = "Total Fat";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Carbs") || Fuzzy.containsOneOf(tempLine, "Carbohydrates")){
        		data = "Carbs";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Sodium")){
        		data = "Sodium";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Cholesterol")){
        		data = "Cholesterol";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Sugars")){
        		data = "Sugars";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Fiber")){
        		data = "Fiber";
        	} 
        	if (Fuzzy.containsOneOf(tempLine, "Potassium")){
        		data = "Potassium";
        	}
        	
        	if(data.length() > 0) { 											//checks that the key is not empty
        			t.put(data, quantityArray);
        	}
        }
        return t;
    }
	/**
	 * Calculates the daily calorie intake needed based on a person's profile.
	 * 
	 * @param gender
	 * @param exerciseAmount
	 * @param weight
	 * @param height
	 * @param age
	 * @return calories needed
	 */
    public int dailyCalories(String gender, double exerciseAmount, double weight, double height, int age){
        int bmr = 0;
        if(gender.equals("Male")){
            bmr = (int)((66.473 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * age)) * exerciseAmount);
        }else if(gender.equals("Female")){
            bmr = (int)((655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age)) * exerciseAmount);
        }
        return bmr;
    }
    
    /**
     * testing fuzzyregex with the map.
     */
    public static void main(String[] args){
    	TreeMap<String, String[]> tree = AnalyzeText.nutritionDetails("test.txt");
    	for(Map.Entry<String, String[]> entry : tree.entrySet())
    	{
    		System.out.println("Key: " + entry.getKey() + ". Value: " + Arrays.toString(entry.getValue()));
    	}
    }
}
