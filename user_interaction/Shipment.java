package user_interaction;
import java.util.Scanner;

public class Shipment {
    public static void run() {

        //this class was made to leave applications for shipping
        Scanner my_scan = new Scanner(System.in);

        while (true) {
            System.out.println("How many items will be in the shipment?");
            System.out.println();

            int count = my_scan.nextInt();

            for(int i = 0; i < count; i++) {


                System.out.println("Enter product's id: ");
                String input = my_scan.nextLine();
            }

            System.out.println("Are there more applications to leave?");
            System.out.println("[ yes / no ]");
            String input = my_scan.nextLine();

            if(input.equals("no")) {
                break;
            }
        }

        System.out.println("nothing here now :)");

    }
}
