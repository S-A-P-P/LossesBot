import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Util {
    public static Random r = new Random();
    public static final String path = "/Users/pranavpolakam/Desktop/Programs/CSA/raids/LossesBot/src/main/java/Files_(*IMPORTANT*)/UserInputFile";
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
            scanner = new Scanner(file);
            List<String> lines = new ArrayList<>();
            while(scanner.hasNextLine())
                lines.add(scanner.nextLine());
            return lines;
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

    }

    public static boolean check() {
        return scanner.nextLine().equalsIgnoreCase("0011748527");
    }

    public static int diceRoll() {
        return 1 + r.nextInt(1000000);
    }
}