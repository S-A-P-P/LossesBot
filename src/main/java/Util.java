import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Util {
    public static Random r = new Random();
    public static final String path = ""
    public static final String line = "===================================================";
    public static Integer cost;
    public static Scanner scanner = new Scanner(System.in);


    public static File file = new File(path);

    public static boolean fileExists;
    public final Integer[] costs = {
            145000
    };

    public static List intro() {
        Scanner scanner;
        try {
            if(check()) {
                fileExists = true;
                scanner = new Scanner(file);
                List<String> lines = new ArrayList<>();
                while(scanner.hasNextLine())
                    lines.add(scanner.nextLine());
                return lines;
            }
            else
                System.out.println("Not correct format!");
            fileExists = false;
        }
        catch (FileNotFoundException e) {
            System.out.println("File doesn't exist. Check path!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFile() {
        Path p1 = Paths.get(path + "/template") //<- change as necessary
        String template = "0011748527\n" +
                "(Do Not Delete ^! Delete this and replace phrases below with information)\n" +
                "(Type of Vehicle)\n" +
                "(Cost of Vehicle)\n" +
                "(Number of Vehicles for player1)\n" +
                "(Number of Vehicles for player2)\n" +
                "(Player 1 name)\n" +
                "(Player 2 name)\n" +
                "(Amt of oil used for one instance of the vehicle)";

        try {
            Files.write(p1, template.getBytes(), StandardOpenOption.CREATE);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean check() {
        return scanner.nextLine().equalsIgnoreCase("0011748527");
    }

    public static int diceRoll() {
        return 1 + r.nextInt(1000000);
    }
}