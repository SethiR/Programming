/*
A simple example where we pick data from file (csv), the file has a header.
The file is read and we create a map where key is the header and value
is each row in the file, this map can then be converted to JSON easily.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main8 {

    static Map toMap(String[] keys, String[] values){
        Map<String, String> map = new HashMap<>();
        for (int i=0; i< keys.length -1 ; i++){
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("/home/rs/MEGA/techprojects/proj1/uspollution_pollution_us_2000_2016_1.csv"));
        String[] header = lines.get(0).split(",");

        lines = lines.subList(1,lines.size());

        for (String line : lines){
            String[] word = line.split(",");
            System.out.println(toMap(header, word));
            break;
        }
    }
}