package com.nagarro.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.compare.DurationCompare;
import com.nagarro.compare.FareCompare;
import com.nagarro.display.FlightView;
import com.nagarro.entities.FlightDetails;

public class FlightController {
	private List <FlightDetails> flightList = new ArrayList<>();
	

	public void searchFlight(String fileName/*, String deptLocation, String arrivalLocation, String date,
			String fileClass*/) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		
		List<String> array;
		while (sc.hasNext()) {
			String line = sc.nextLine().toUpperCase();

			if (!line.isEmpty()) {
				StringTokenizer token = new StringTokenizer(line, "|");
				array = new ArrayList<>(line.length());
				while (token.hasMoreTokens()) {

					array.add(token.nextToken());
				}
				
				Object[] objArray = array.toArray();
				
				String[] str=Arrays.copyOf(objArray, objArray.length, String[].class);
				FlightDetails fd=new FlightDetails();
				
				
				Configuration cfg= new Configuration();
		        SessionFactory sfactory=cfg.configure().buildSessionFactory();
		      //  System.out.println(sfactory);
		        
		        //creating students 
		        
		        
		    //    FlightDetails fd =new FlightDetails();
		        fd.setFlightNum(str[0]);
				fd.setDepartLoc(str[1]);
				fd.setArrivalLoc(str[2]);
				fd.setDate(str[3]);
				fd.setTime(Integer.parseInt(str[4]));
				fd.setDuration(Float.parseFloat(str[5]));
				fd.setFare(Integer.parseInt(str[6]));
				fd.setSeat(str[7]);
				fd.setfClass(str[8]);
		        
		       // System.out.println(st);
		        
		        Session s=   sfactory.openSession();
		        s.beginTransaction();
		        s.save(fd);
		        
		        s.getTransaction().commit();
		        
				
				
				
				
				
				

//				if (array.get(1).equals(deptLocation) && array.get(2).equals(arrivalLocation) && array.get(3).equals(date)
//						&& array.get(8).equals(fileClass)) {
//					FlightDetails details = new FlightDetails(array.get(0), array.get(1), array.get(2), array.get(3),
//							Integer.parseInt(array.get(4)), Float.parseFloat(array.get(5)),
//							Integer.parseInt(array.get(6)), array.get(7), array.get(8));
//
//					flightList.add(details);
//				}
			}
		}
		//return flightList;
	}

	public void updateView(int choiceCode, List<FlightDetails> flightList) {
		if (choiceCode == 1) {
			FareCompare fc =new FareCompare();
			fc.compareFare(flightList);
			
		} 
		else if (choiceCode == 2) {
			DurationCompare fc =new DurationCompare();
			fc.durationCompare(flightList);
			
		} else {
			System.out.println("Wrong Choice.");
			return;
		}
		
		FlightView view = new FlightView();
		
		view.viewFlights(flightList);

	}
}