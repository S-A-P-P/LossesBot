import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;

public class WarfareLosses2 {

    private static Integer[] victors = {0, 1, 2};

    private static Scanner scanner = new Scanner(System.in);

    private static Integer player1Amt;
    private static Integer player1StartAmt;
    private static Integer player2Amt;
    private static Integer player2StartAmt;

    private static Integer player1Losses = 0;
    private static Integer player2Losses = 0;

    private static Integer player1Roll = Util.diceRoll();
    private static Integer player2Roll = Util.diceRoll();

    private static Integer oilCost = 0;

    public static void main(String[] args) {
        List<String> scannedLines = Util.intro();

        player1Amt = Integer.parseInt(scannedLines.get(3));
        player2Amt = Integer.parseInt(scannedLines.get(4));
        System.out.println(player1Amt);
        System.out.println(player2Amt);
        player1StartAmt = player1Amt;
        player2StartAmt = player2Amt;
        oilCost = Integer.parseInt(scannedLines.get(7));
        int round = 0;

        while (true) {
            round++;
            System.out.printf("Round %s: %n", round);
            if (Math.max(player1Roll, player2Roll) == player1Roll) {
                player2Amt--;
                player2Losses++;
            } else if (player1Roll == player2Roll) {
                System.out.println("Yo");
                main(args);
            } else {
                player1Amt--;
                player1Losses++;
            }
            System.out.println("Player 1: " + player1Amt + ", Player 2: " + player2Amt);
            System.out.println("Player 1 roll: " + player1Roll + " Player 2 roll: " + player2Roll);
            player1Roll = Util.diceRoll();
            player2Roll = Util.diceRoll();
            System.out.println("Player 1 roll: " + player1Roll + " Player 2 roll: " + player2Roll);
            if(player1Amt <= 0 || player2Amt <= 0)
                break;
        }
        System.out.print("Name the report: ");
        String name = scanner.nextLine();
        String path = ""
        try {
            Path p1 = Paths.get(path);
            String report1 = "Report " + name + ": \n" +
                    Util.line + "\n" +
                    scannedLines.get(5) + "'s Report: \n" +
                    scannedLines.get(5) + " Lost " + (player1StartAmt - player1Amt) + " " + scannedLines.get(1) + "(s). \n" +
                    scannedLines.get(5) + " now has " + player1Amt + " " + scannedLines.get(1) + "(s) \n" +
                    scannedLines.get(5) + " lost " + (oilCost * player1Losses) + " oil \n" +
                    Util.line;

            String report2 = "\n" + Util.line + "\n" +
                    scannedLines.get(6) + "'s Report: \n" +
                    scannedLines.get(6) + " Lost " + (player2StartAmt - player2Amt) + " " + scannedLines.get(1) + "(s). \n" +
                    scannedLines.get(6) + " now has " + player2Amt + " " + scannedLines.get(1) + "(s) \n" +
                    scannedLines.get(6) + " lost " + (oilCost * player2Losses) + " oil \n" +
                    Util.line;
            Files.write(p1, (report1 + report2).getBytes(), StandardOpenOption.CREATE);
            System.out.println("File Created at Path " + path + "!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
