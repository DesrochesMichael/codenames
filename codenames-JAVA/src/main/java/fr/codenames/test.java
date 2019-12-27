package fr.codenames;

public class test {

	public static void main(String[] args) {
		
		System.out.println("\u001B31;1mhello world!");
		
		
		System.out.println(ANSI_RED + " test du rouge bro " + ANSI_RED);
	
	}
	public static final String ANSI_RED = "\u001B[31m";
}