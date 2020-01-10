import java.util.Random;
import java.util.Scanner;

public class WarfareLosses{
    public static void main(String args[]) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Is there still troops to roll ?");
            String userVerify = scan.nextLine();
            if (userVerify.toLowerCase() == "yes") {
                Scanner scan1 = new Scanner(System.in);
                int fighter1 = scan1.nextInt();
                Scanner scan2 = new Scanner(System.in);
                int fighter2 = scan2.nextInt();
            }
            else {
                break;
            }
        }
    }
}