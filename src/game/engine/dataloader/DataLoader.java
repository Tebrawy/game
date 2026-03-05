package game.engine.dataloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.engine.Role;
import game.engine.cards.*;
import game.engine.cells.*;
import game.engine.monsters.*;
import game.engine.exceptions.InvalidCSVFormat;

public class DataLoader {
	private static final String CARDS_FILE_NAME = "cards.csv";
	private static final String CELLS_FILE_NAME = "cells.csv";
	private static final String MONSTERS_FILE_NAME = "monsters.csv";
	
	public static ArrayList<Card> readCards() throws IOException{
		ArrayList<Card> cards = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_NAME))) {
			String line;
			while ((line = br.readLine()) != null){
				String[] data = line.split(",");
				if (data.length < 4) throw new InvalidCSVFormat("Card data incomplete", line);
			
				String type = data[0].toUpperCase();
				String name = data[1];
				String desc = data[2];
				try{
					int rarity = Integer.parseInt(data[3]);
					switch (type){
						case "SWAPPER":
							cards.add(new SwapperCard(name, desc, rarity));
							break;
						case "SHIELD":
							cards.add(new ShieldCard(name, desc, rarity));
							break;
						case "STARTOVER":
							if (data.length < 5) throw new InvalidCSVFormat("Lucky value missing", line);
							String luckyStr = data[4].trim().toLowerCase();
	                        if (!luckyStr.equals("true") && !luckyStr.equals("false")) {
	                        	throw new InvalidCSVFormat("Invalid lucky boolean format", line);
	                        }
							cards.add(new StartOverCard(name, desc, rarity, Boolean.parseBoolean(luckyStr)));
							break;
						case "ENERGYSTEAL":
							if (data.length < 5) throw new InvalidCSVFormat("Energy value missing", line);
							String energyStr = data[4].trim().toLowerCase();
							int energyAmount;
	                        try {
	                            energyAmount = Integer.parseInt(data[4].trim());
	                        } catch (NumberFormatException e) {
	                            throw new InvalidCSVFormat("Invalid energy format", line);
	                        }
							cards.add(new EnergyStealCard(name, desc, rarity, energyAmount));
							break;
						case "CONFUSION":
							if (data.length < 5) throw new InvalidCSVFormat("Duration missing", line);
							int duration;
	                        try {
	                            duration = Integer.parseInt(data[4].trim());
	                        } catch (NumberFormatException e) {
	                            throw new InvalidCSVFormat("Invalid energy format", line);
	                        }
							cards.add(new ConfusionCard(name, desc, rarity, duration));
							break;
						default:
							throw new InvalidCSVFormat("Unknown card type", line);
					}
				} catch(NumberFormatException e){
					throw new InvalidCSVFormat("Invalid number format in card data", line);
				}
			}
		}
		return cards;
	}
	
	public static ArrayList<Cell> readCells() throws IOException{
		ArrayList<Cell> cells = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME))) {
			String line;
			while ((line = br.readLine()) != null){
				String[] data = line.split(",");
				if (data.length < 2) throw new InvalidCSVFormat("Cell data incomplete", line);
			
				String name = data[0];
				if(data.length >= 3){
					try {
                        Role role = Role.valueOf(data[1].toUpperCase());
                        int energy = Integer.parseInt(data[2]);
                        cells.add(new DoorCell(name, role, energy));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidCSVFormat("Invalid DoorCell format", line);
                    }
				}
				else {
                    try {
                        int effect = Integer.parseInt(data[1]);
                        if (effect > 0)
                            cells.add(new ConveyorBelt(name, effect));
                        else
                            cells.add(new ContaminationSock(name, effect));
                    } catch (NumberFormatException e) {
                        throw new InvalidCSVFormat("Invalid TransportCell format", line);
                    }
				}
			}
			return cells;
		}
	}
	
	public static ArrayList<Monster> readMonsters() throws IOException {
        ArrayList<Monster> monsters = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(MONSTERS_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) throw new InvalidCSVFormat("Monster data incomplete", line);

                String type = data[0].toUpperCase();
                String name = data[1];
                String desc = data[2];
                Role role;
                int energy;
                try {
                    role = Role.valueOf(data[3].toUpperCase());
                    energy = Integer.parseInt(data[4]);
                } catch (IllegalArgumentException e) {
                    throw new InvalidCSVFormat("Invalid Role or Energy format", line);
                }

                switch (type) {
                    case "DASHER": 
                        monsters.add(new Dasher(name, desc, role, energy));
                        break;
                    case "DYNAMO": 
                        monsters.add(new Dynamo(name, desc, role, energy));
                        break;
                    case "MULTITASKER": 
                        monsters.add(new MultiTasker(name, desc, role, energy));
                        break;
                    case "SCHEMER": 
                        monsters.add(new Schemer(name, desc, role, energy));
                        break;
                    default: 
                        throw new InvalidCSVFormat("Unknown monster type", line);
                }
            }
        }
        return monsters;
    }
	
	
}
