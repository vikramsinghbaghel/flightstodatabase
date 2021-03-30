package com.nagarro.storetodatabase;

import java.util.Scanner;

import com.nagarro.validation.Validate;

public class App {
	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//
//		String deptLocation;
//		String arrivalLocation;
//		String date;
//		String flightClass;
//		int choiceCode;
//		
//		Validate validate = new Validate();
//		do {
//			System.out.print("Enter Departure Location  : ");
//			deptLocation = scan.nextLine().toUpperCase();
//		} while (!validate.departLoc(deptLocation));
//
//		do {
//			System.out.print("Enter Arrival Location  : ");
//			arrivalLocation = scan.nextLine().toUpperCase();
//		} while (!validate.arrivalLoc(arrivalLocation));
//
//		do {
//			System.out.print("Enter Date  (DD-MM-YYYY) : ");
//			date = scan.nextLine().toUpperCase();
//		} while (!validate.flightDate(date));
//
//		do {
//			System.out.print("Enter Class: (E or B or EB) : ");
//			flightClass = scan.nextLine().toUpperCase();
//		} while (!validate.flightClass(flightClass));
//
//		do {
//			System.out.print(
//					"Enter Output Preference :   1. Fare \t 2. Flight Duration \nEnter Preference Choice Code : ");
//			choiceCode = scan.nextInt();
//		} while (choiceCode <1 || choiceCode > 2);

		CsvReader read = new CsvReader(/*deptLocation, arrivalLocation, date, flightClass, choiceCode*/);
		Thread firstThread = new Thread(read);
		FolderWatcherService watch = new FolderWatcherService();

		Thread watcher = new Thread(watch);

		watcher.start();
		firstThread.start();

	//	scan.close();

	}
}

