import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.bind.JAXB;

public class CreateSequentialFile {
    public static void main(String... args){
        //open client xml, write object to it then close file
        try(BufferedWriter output = Files.newBufferedWriter(Paths.get("clients.xml"))) {
            Scanner scanner = new Scanner(System.in);

            //stores the account before xml serialization
            Accounts accounts = new Accounts();

            System.out.printf("%s%n%s%n?", "Enter Account Number, first name, last name and balance.",
                              "Enter end-of-line indicator to end input.");

            while(scanner.hasNext()){ //loop until end of file indicator
                try {
                    //create new record
                    Account record = new Account(scanner.nextInt(), scanner.next(), scanner.next(), scanner.nextDouble());

                    //add to accountList
                    accounts.getAccounts().add(record);


                }catch (NoSuchElementException elementException){
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine();//discard input so user can try again
                }

                System.out.print("?");
            }

            //write accountList XML to output
            JAXB.martial(accounts, output);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error opening file. Terminating....");
        }
    }
}
