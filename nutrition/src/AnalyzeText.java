import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.java.frej.fuzzy.Fuzzy;


public class AnalyzeText {
	public static TreeMap<String, String[]> nutritionDetails(String s){
		
		int count = 0;
		
        TreeMap<String, String[]> t = new TreeMap<String, String[]>();
        
        TextIO.readFile(s);
        
        while(!TextIO.eof()) {
        	count++;
            String data = "";
            ArrayList<String> quantity = new ArrayList<String>();
            
            String tempLine = TextIO.getln();
            
            Pattern percentQuantities = Pattern.compile("\\d+%");
            Matcher percentMatcher = percentQuantities.matcher(tempLine);
            
            Pattern gramsQuantity = Pattern.compile("\\d+(g|mg)");
            Matcher gramsMatcher = gramsQuantity.matcher(tempLine);
            
            Pattern calorieCounter = Pattern.compile("Cal.*\\s\\d+");
            Matcher calorieMatcher = calorieCounter.matcher(tempLine);
            
            while (percentMatcher.find()) {
            	quantity.add(percentMatcher.group());
            }
            
            while (gramsMatcher.find()) {
            	quantity.add(gramsMatcher.group());
            }
            
            while(calorieMatcher.find()) {
            	quantity.add(calorieMatcher.group());
            }
           
            String[] quantityArray = new String[quantity.size()];
            for(int i= 0; i < quantityArray.length; i ++) {
            	quantityArray[i] = quantity.get(i);
            }
             
            /**
             * fuzzy spelling search goes here to put values into data and amount. 
             */
            
        	if(Fuzzy.containsOneOf(tempLine, "Calorie")) {
        		data = "Calorie";
        	} else if (Fuzzy.containsOneOf(tempLine, "Protein")){
        		data = "Protein";
        	} else if (Fuzzy.containsOneOf(tempLine, "Total Fat")){
        		data = "Total Fat";
        	} else if (Fuzzy.containsOneOf(tempLine, "Carbs") || Fuzzy.containsOneOf(tempLine, "Carbohydrates")){
        		data = "Carbs";
        	} else if (Fuzzy.containsOneOf(tempLine, "Sodium")){
        		data = "Sodium";
        	} else if (Fuzzy.containsOneOf(tempLine, "Cholesterol")){
        		data = "Cholesterol";
        	} else if (Fuzzy.containsOneOf(tempLine, "Sugars")){
        		data = "Sugars";
        	} else if (Fuzzy.containsOneOf(tempLine, "Fiber")){
        		data = "Fiber";
        	} else if (Fuzzy.containsOneOf(tempLine, "Potassium")){
        		data = "Potassium";
        	}
        	if(data.length() > 0) {
        		if(t.get(data) == null) {
        			t.put(data, quantityArray);
        		}
        		else {
        			t.put(data + count, quantityArray);
        		}
        	}
        }
        return t;
    }
    public int dailyCalories(String gender, double exerciseAmount, double weight, double height, int age){
        int bmr = 0;
        if(gender.equals("Male")){
            bmr = (int)((66.473 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * age)) * exerciseAmount);
        }else if(gender.equals("Female")){
            bmr = (int)((655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age)) * exerciseAmount);
        }
        return bmr;
    }
    public static void main(String[] args){
    	TreeMap<String, String[]> tree = AnalyzeText.nutritionDetails("test.txt");
    	for(Map.Entry<String, String[]> entry : tree.entrySet())
    	{
    		System.out.println("Key: " + entry.getKey() + ". Value: " + Arrays.toString(entry.getValue()));
    	}
    }
}
