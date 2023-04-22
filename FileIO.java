
import java.io.*;

public class FileIO {

    public static String[] parseCsv(String filename) {
        String line;
        int numEntries = 0; // how many lines there are in the file

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while ((line = reader.readLine()) != null) {
                numEntries += 1; // determines the size of the String[] array
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] dataArray = new String[numEntries];
        try {
            // second pass to parse the items into the array
            BufferedReader br = new BufferedReader(new FileReader(filename));
            for (int ii = 0; ii < numEntries; ii++) {
                line = br.readLine();
                if (line != null) {
                    dataArray[ii] = line;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataArray;
    }
}
