import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileAndDirectoryInfo {
    public static void main(String... args) throws IOException {

        System.out.println("Enter file or directory name:");

        Scanner scanner = new Scanner(System.in);

        //create path object based on user input
        Path path = Paths.get(scanner.nextLine());

        if(Files.exists(path)){ // if file exists, output info about it
            //Display file or directory info
            System.out.printf("%n%s Exist%n", path.getFileName());
            System.out.printf("%s a directory %n", Files.isDirectory(path) ? "Is" : "Is Not");
            System.out.printf("%s an absolute path", path.isAbsolute() ? "Is" : "Is Not");
            System.out.printf("Last modified %s%n", Files.getLastModifiedTime(path));
            System.out.printf("Size %s%n", Files.size(path));
            System.out.printf("Path %s%n", path);
            System.out.printf("Absolute Path", path.toAbsolutePath());

            if (Files.isDirectory(path)){ // out put directory listing
                System.out.println("%n Directory content:%n");

                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);

                //Object for iterating directory's contents
                for(Path p : directoryStream){
                    System.out.println(p);
                }
            } else { //not file or directory out put directory message
                System.out.printf("%nDoes Not Exist%n", path);
            }
        }
    } //End main
} //End class
