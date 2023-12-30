package mainPackage;

import gui.CalcGUI;

public class MainClass {
	public static void main(String args[]) {
		System.out.println("Hello, World!");
		
		@SuppressWarnings("unused")
		CalcGUI gui = new CalcGUI(400, 400);
	}
}
