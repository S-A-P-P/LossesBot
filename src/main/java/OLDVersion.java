import com.sun.istack.internal.NotNull;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OLDVersion {

    static boolean isSecondTurn = false;

    static Integer personALosses = 0;
    static Integer personBLosses = 0;

    static Integer vehicleCost;

    static Integer personALossesCost;
    static Integer personBLossesCost;

    enum VehicleType {
        /*PAGE 1 of shop*/ ARMORED_CAR, SELF_PROPELLED_ARTILLERY, COMBAT_TANK, FRIGATE, FIGHTER_AIRCRAFT, ATTACK_AIRCRAFT, MINESWEEPER, PATROL_SHIP
        /*PAGE 2 of shop*/ //add it later
    }

    static Integer[] costs = {
            145000,
            3900000,
            6810000,
            80000000,
            85000000,
            152000000,
            277000000,
            400000000,
    };

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        if(isSecondTurn) {
            scanner.nextLine();
        }

        List<VehicleType> vehicles = new ArrayList<>();
        Random r = new Random();

        vehicles = initList(vehicles);


        while (true) {

            System.out.println("Enter type of troop to enter (use the number next to the vehicle type, not the name itself): \n" +
                    "\t1). Armored Car \n" +
                    "\t2). Self Propelled Artillery \n" +
                    "\t3). Combat Tank \n" +
                    "\t4). Frigate \n" +
                    "\t5). Fighter Aircraft \n" +
                    "\t6). Attack Aircraft \n" +
                    "\t7). Minesweeper \n" +
                    "\t8). Patrol Ship");

            //This variable is placed here so it can be accessed anywhere
            VehicleType userVehicle;

            //initializing userVehicle. Ensuring that it isn't a number that's not on the list
            while (true) {
                int index = Integer.parseInt(scanner.nextLine());
                userVehicle = selectFromEnum(index, vehicles);
                if (userVehicle != null) {
                    vehicleCost = setVehicleCost(index);
                    break;
                }
                System.out.println("That index exceeds the list! Please enter a number from  1 and " + vehicles.size() + "!");
            }

            System.out.println("How many " + userVehicle.toString().toLowerCase() + "'s does \"Person A\" have?");
            System.out.println("Hit enter");
            scanner.nextLine();
            System.out.println("Now enter your value");
            int fighter1 = Integer.parseInt(scanner.nextLine());
            System.out.println("How many " + userVehicle.toString().toLowerCase() + "'s does \"Person B\" have?");
            System.out.println(
                    "hit enter"
            );
            scanner.nextLine();
            System.out.println("Now enter the value!");
            int fighter2 = Integer.parseInt(scanner.nextLine());
            System.out.println("Value entered!");
            int diceRoll1;
            int diceRoll2;

            int count = 1;

            while(true) {
                for (int i = 0; i < Integer.min(fighter1, fighter2); i++) {
                    diceRoll1 = diceRoll(r);
                    diceRoll2 = diceRoll(r);


                    if (Math.max(diceRoll1, diceRoll2) == diceRoll1) {
                        fighter2--;
                        personBLosses++;
                    }
                    else if (Math.max(diceRoll1, diceRoll2) == diceRoll2){
                        fighter1--;
                        personALosses++;
                    }
                    else {
                        System.out.println("Neither");
                    }
                }
                if((fighter1 == 0) || (fighter2 == 0))
                    break;
                System.out.println("Rolling dice... (Round " + count + ")");
                count++;
            }

            personALossesCost = personALosses * vehicleCost;
            personBLossesCost = personBLosses * vehicleCost;

            System.out.println(Util.line);
            System.out.println("End Result: ");
            System.out.println("\u001B[34m" + "\t\"Person A\" Losses: " + personALosses + " " + userVehicle.toString().toLowerCase() + "'s, or $" + personALossesCost + "\u001B[0m");
            System.out.println("\u001B[31m" + "\t\"Person B\" Losses: " + personBLosses + " " + userVehicle.toString().toLowerCase() + "'s, or $" + personBLossesCost + "\u001B[0m");
            System.out.println();
            System.out.println("\u001B[34m" + "\t\"Person A\" has " + fighter1 + " " + userVehicle.toString().toLowerCase() + "'s left" + "\u001B[0m");
            System.out.println("\u001B[31m" + "\t\"Person B\" has " + fighter2 + " " + userVehicle.toString().toLowerCase() + "'s left" + "\u001B[0m");

            if(fighter1 == 0)
                System.out.println("\u001B[31m" + "\"Person B\" wins!" + "\u001B[0m");
            if(fighter2 == 0)
                System.out.println("\u001B[34m" + "\"Person A\" wins!" + "\u001B[0m");
            System.out.println(Util.line + "\n");

            System.out.println("Would you like to roll for another vehicle?");
            if(isSecondTurn)
                scanner.nextLine();
            if(!responseChecker(scanner.nextLine()))
                break;
            isSecondTurn = true;
            fighter1 = 0;
            fighter2 = 0;
            personALosses = 0;
            personBLosses = 0;
        }
    }

    public static Integer diceRoll(Random r) {
        int phase1 = 0;
        int phase2 = 0;
        int phase3 = 0;
        int phase4 = 0;
        int phase5 = 0;
        int phase6 = 0;

        boolean isPhase1 = false;
        boolean isPhase2 = false;
        boolean isPhase3 = false;
        boolean isPhase4 = false;
        boolean isPhase5 = false;
        boolean isPhase6 = false;

        int realFinalRoll = 0;

        for(int i = 0; i < 1; i++) {
            int roll = 1 + r.nextInt(600);

            if (roll >= 1  && roll <= 100)
                isPhase1 = true;
            if (roll >= 101 && roll <= 200)
                isPhase2 = true;
            if (roll >= 201 && roll <= 300)
                isPhase3 = true;
            if (roll >= 301 && roll <= 400)
                isPhase4 = true;
            if (roll >= 401 && roll <= 500)
                isPhase5 = true;
            if (roll >= 501 && roll <= 600)
                isPhase6 = true;
            
        }

        boolean checked = false;

        if(isPhase1 && !(isPhase2 ||  isPhase3 || isPhase4 || isPhase5 || isPhase6)) {
            realFinalRoll = 1;
            checked =  true;
        }
        if(isPhase2 && !(isPhase1 ||  isPhase3 || isPhase4 || isPhase5 || isPhase6)) {
            realFinalRoll = 2;
            checked =  true;
        }
        if(isPhase3 && !(isPhase2 ||  isPhase1 || isPhase4 || isPhase5 || isPhase6)) {
            realFinalRoll = 3;
            checked =  true;
        }
        if(isPhase4 && !(isPhase2 ||  isPhase3 || isPhase1 || isPhase5 || isPhase6)) {
            realFinalRoll = 4;
            checked =  true;
        }
        if(isPhase5 && !(isPhase2 ||  isPhase3 || isPhase1 || isPhase4 || isPhase6)) {
            realFinalRoll = 5;
            checked =  true;
        }
        if(isPhase6 && !(isPhase2 ||  isPhase3 || isPhase1 || isPhase5 || isPhase4)) {
            realFinalRoll = 6;
            checked =  true;
        }
        if(!checked)
            System.out.println("something is wrong with \"DICE\" method");
        return realFinalRoll;

    }

    public static boolean responseChecker(String str) {
        str = str.toLowerCase();
        return str.contains("ye");
    }

    public static List<VehicleType> initList(List list) {
        list.add(VehicleType.ARMORED_CAR);
        list.add(VehicleType.SELF_PROPELLED_ARTILLERY);
        list.add(VehicleType.COMBAT_TANK);
        list.add(VehicleType.FRIGATE);
        list.add(VehicleType.FIGHTER_AIRCRAFT);
        list.add(VehicleType.ATTACK_AIRCRAFT);
        list.add(VehicleType.MINESWEEPER);
        list.add(VehicleType.PATROL_SHIP);

        return list;
    }

    @NotNull
    public static VehicleType selectFromEnum(int choice, List<VehicleType> list) {
        if(choice > list.size() || choice < 0)
            return null;
        return list.get(choice - 1);
    }

    public static Integer setVehicleCost(int index) {
        if(index < 0 || index >= costs.length)
            return null;
        return costs[index - 1];
    }
}