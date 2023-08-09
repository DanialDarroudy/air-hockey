import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class File {
    public static void log (String error) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./log.txt", true));
            writer.write(error + "\n");
            writer.close();
        }
        catch(Exception e) {
            System.out.println("Error");
        }
    }
    public static void getLog() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./log.txt"));
            String input = reader.readLine();
            int i = 0;
            while(input != null) {
                Main.row[i][0] = input.split("/")[0];
                Main.row[i][1] = input.split("/")[1];
                Main.row[i][2] = input.split("/")[2];
                Main.row[i][3] = input.split("/")[3];
                Main.row[i][4] = input.split("/")[4];
                Main.row[i][5] = input.split("/")[5];
                input = reader.readLine();
                i++;
            }
            reader.close();
        }
        catch(Exception e) {
            System.out.println("error");
        }
    }
}
