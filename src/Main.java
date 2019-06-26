import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.io.BufferedReader;
public class Main {
	private String[] args;
	
	private String ideal;
	private int length;
	private Map<String, Double> population;
	private Map<Character, Integer> trueCountMap;
	private String allowed = "abcdefghijklmnopqrstuvqxyz !@#$%^&*()_+-=[] {}|/?.>,<`~";
	public static void main(String[] args) throws IOException {
		
		new Main(args).run();
	}
	
	 private Main(String[] args) {
		 trueCountMap = new HashMap<Character, Integer>();
		 population = new HashMap<String, Double>();
		    this.args = args;
	}
	 
	 public void run() throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String line;
		 
		
		 while ((line = br.readLine()) != null) {
			 
			 this.ideal = line;
			 this.length = line.length();
			 for (int i = 0; i < this.length; i++) {
				 if (trueCountMap.containsKey(this.ideal.toCharArray()[i])) {
					 trueCountMap.put(this.ideal.toCharArray()[i], trueCountMap.get(this.ideal.toCharArray()[i]) + 1);
				 } else {
					 trueCountMap.put(this.ideal.toCharArray()[i], 1);
				 }
			 }
			 Random r = new Random();
			 for (int i = 0; i < 100; i++) {
				 // Populate with random strings and their fitnesses
				 String newString = "";
				 
				 for (int j = 0; j < this.length; j++) {
					 newString += this.allowed.charAt(r.nextInt(this.allowed.length()));
				 }
				 population.put(newString, this.fitness(newString));
				 
			 }
			 
//			 Iterator<Entry<String, Double>> it = population.entrySet().iterator();
//			 
//			 while(it.hasNext()) {
//				 Entry<String, Double> ent = it.next();
//				 System.out.println("STRING: " + ent.getKey() + " , VALUE: " + ent.getValue());
//			 }
			 // Sort the map!
			 
			 // Print the best and its fitness
			 
			 // Breed the best pair 100 times. Each time, half its digits will be from each parent, with 5% of mutation
			 // to random character.
			 
			 // REPEAT OVER GENERATIONS!
			 int count = 0;
			 while (true) {
//			 for (int n = 0; n < 5; n++) {
				 List<Map.Entry<String, Double> > list = 
			               new LinkedList<Map.Entry<String, Double>>(population.entrySet()); 
				 Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
			            public int compare(Map.Entry<String, Double> o1,  
			                               Map.Entry<String, Double> o2) 
			            { 
			                return (o2.getValue()).compareTo(o1.getValue()); 
			            } 
			        }); 
				 for (Entry<String, Double> ent : list) {
					 System.out.println(ent.getKey() + " , " + ent.getValue());
				 }
				 if (list.get(0).getValue() > 99999999) {
					 break;
				 }
				 List<String> children = new ArrayList<String>();
				 List<Entry<String, Double>> theBest = list.subList(0, 20);
				 System.out.println("BEST: " + theBest.size());
				 List<Entry<String, Double>> theRest = list.subList(20, list.size());
				 System.out.println("REST: " + theRest.size());
				 for (int i = 0; i < theBest.size() - 1; i+= 2) {
					 char[] p1 = theBest.get(i).getKey().toCharArray();
					 char[] p2 = theBest.get(i+1).getKey().toCharArray();
					 for (int k = 0; k < 6; k++) {
						 String child = "";
						 int crossover = r.nextInt(this.length);
						 
						 for (int j = 0; j < this.length; j++) {
							 int parent = r.nextInt(2);
							 
//							 int crossover = r.nextInt(this.length);
							 int mutation = r.nextInt(20);
							 if (mutation == 1) {
								 child += this.allowed.charAt(r.nextInt(this.allowed.length()));
							 } else {
//								 if (parent == 0) {
//									 child += p1[j];
//								 } else {
//									 child += p2[j];
//								 }
								 if (j <= crossover) {
									 child += p1[j];
								 } else {
									 child += p2[j];
								 }
//								 int l;
//								 for (l = 0; l < crossover; l++) {
//									 child += p1[l];
//								 }
//								 System.out.println("AT THIS STAGE: " + child);
//								 while (child.length() < this.length) {
//									 child += p2[l];
//									 l++;
//								 }
							 }
							 
						 }
						 children.add(child);
					 }
					 
				 }
				 System.out.println("AFTER BEST: " + children.size());
				 for (int i = 0; i < theRest.size() - 1; i+=2) {
					 char[] p1 = theRest.get(i).getKey().toCharArray();
					 char[] p2 = theRest.get(i+1).getKey().toCharArray();
					 int crossover = r.nextInt(this.length);
					 String child = "";
					 for (int j = 0; j < this.length; j++) {
						 int parent = r.nextInt(2);
//						 int crossover = r.nextInt(this.length);
						 int mutation = r.nextInt(20);
						 if (mutation == 1) {
							 child += this.allowed.charAt(r.nextInt(this.allowed.length()));
						 } else {
//							 if (parent == 0) {
//								 child += p1[j];
//							 } else {
//								 child += p2[j];
//							 }
//							 int l;
//							 for (l = 0; l < crossover; l++) {
//								 child += p1[l];
//							 }
//							 while (child.length() < this.length) {
//								 child += p2[l];
//								 l++;
//							 }
							 if (j <= crossover) {
								 child += p1[j];
							 } else {
								 child += p2[j];
							 }
						 }
						 
					 }
					 if (child.equals("hello world")) {
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 System.out.println("FOUND!!!!!!");
						 
					 }
					 children.add(child);
				 }
				 
				 System.out.println(children.size());
				 assert(children.size() == 100);
				 population = new HashMap<String, Double>();
				 for (int i = 0; i < 100; i++) {
					 population.put(children.get(i), this.fitness(children.get(i)));
				 }
				 int s = population.size();
				 while (s < 100) {
					 String d = "";
					 for (int m = 0; m < this.length; m++) {
						 d += this.allowed.charAt(r.nextInt(this.allowed.length()));
					 }
					 population.put(d, this.fitness(d));
					 s++;
				 }
				 System.out.println("ONE GENERATION OVER! This generation: " + count);
				 System.out.println("POP SIZE: " + population.size());
				 System.out.println();
				 count++;
			 }
			 
			 
		 }
	 }
	 
	 private double fitness(String subject) {
		 if (subject.equals(this.ideal)) {
			 return 1000000000;
		 }
		 double fitness = 0.0;
		 Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		 char[] charArr = subject.toCharArray();
		 char[] trueArr = this.ideal.toCharArray();
		 for (int i = 0; i < this.length; i++) {
			 if (charArr[i] == trueArr[i]) {
				 fitness += 100.0;
			 }
			 if (countMap.containsKey(charArr[i])) {
				 countMap.put(charArr[i], countMap.get(charArr[i]) + 1);
			 } else {
				 countMap.put(charArr[i], 1);
			 }
		 }
		 
		 for (int i = 0; i < this.length; i++) {
			 char c = charArr[i];
			 if (trueCountMap.containsKey(c)) {
				 fitness += (1/(Math.abs((trueCountMap.get(c) - countMap.get(c))) + .1));
//				 System.out.println("Hie! " +(1/(Math.abs((trueCountMap.get(c) - countMap.get(c))) + .1)));
			 }
		 }
		 return fitness;
	 }
	 
}
