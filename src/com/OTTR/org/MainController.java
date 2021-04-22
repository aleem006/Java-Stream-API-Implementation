package com.OTTR.org;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author DELL
 *
 */

public class MainController {

	private Collection<Minion> minions;

	public Collection<Minion> getMinions() {
		return minions;
	}

	public void setMinions(List<Minion> minions) {
		this.minions = minions;
	}

	public MainController() {
		minions = new ArrayList<Minion>();
	}

	public void readFile(String fileName) {
		
		try {
			// get file
			Stream<String> streamofStrings = Files.lines(Paths.get(fileName)); 

			// Remove header, Duplicate entries and sort in ascending order
			streamofStrings.skip(1).distinct().sorted().forEach(s -> {
					String[] record = s.split(",");
					minions.add(new Minion(record[0], record[1], record[2]));
			});
		} catch (Exception ex) {
			System.out.println("Error occured: " + ex.getMessage());
		}
		
	}

	public void printOnConsole() {
		System.out.println("- All Items with # of Eyes = 1 written on Console: ");
		for (Minion minion : minions) {
			if (Integer.parseInt(minion.getnEyes()) == 1) {
				System.out.println(minion.toString());
			}
		}
	}

	public Boolean saveFile(String fileName) {
		
		Comparator<Minion> listSortedByName = Comparator.comparing(Minion::getName).reversed();
		//Collection<Minion> listSortedByName = new ArrayList<Minion>();
		//Stream<String> streamOfCollectionByName =	listSortedByName.stream().map(Minion::getName).sorted(Collections.reverseOrder());
		
		Comparator<Minion> listSortedBynEyes = Comparator.comparing(Minion::getnEyes).reversed();
		//Collection<Minion> listSortedBynEyes = new ArrayList<Minion>();
		//Stream<String> streamOfCollectionBynEyes =	listSortedBynEyes.stream().map(Minion::getName).sorted(Collections.reverseOrder());
		
		// Descending Alphabets and Number order 
		Comparator<Minion> listSortedByOrder = listSortedBynEyes.thenComparing(listSortedByName); 

		Boolean flag = true;
		try {
			
			List<String> sortedMinion = minions.stream().sorted(listSortedByOrder).map(Minion::toStringFileFormate).collect(Collectors.toList());
			//sortedMinion.forEach(e -> System.out.println(e));
			// Add Header
			//sortedMinion.add("Name, # of Eyes, Item");
			sortedMinion.add(0,"Name, # of Eyes, Item");
			Files.write(Paths.get(fileName), sortedMinion);

		} catch (Exception ex) {
			System.out.println("Error occured: " + ex.getMessage());
			flag = false;
		}
		
		return flag;
	}
	
	public static void main(String arg[]) {
		
		MainController controls = new MainController();
		controls.readFile("minions.csv");
		controls.printOnConsole();
		Boolean flagCheck = controls.saveFile("result.csv");
		if(flagCheck) {
			System.out.println("- Duplicate Entity Removed.");
			System.out.println("- Saved all Items in a File.");
		} else {
			System.out.println("Error while saving a file");
		}
			
	}
}
