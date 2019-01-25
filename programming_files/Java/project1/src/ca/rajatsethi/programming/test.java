package ca.rajatsethi.programming;

import java.io.BufferedReader;
import java.io.FileReader;

public class test {
    public static void main(String[] args) {

        BufferedReader reader = null;
        int total = 0;

        try {
            reader = new BufferedReader(new FileReader("C:\\a.txt"));
            String line = null;

            while((line = reader.readLine()) != null){
                System.out.println(line);
                total += Integer.valueOf(line);
                System.out.println("Total = " + total);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (reader != null)
                    reader.close();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
