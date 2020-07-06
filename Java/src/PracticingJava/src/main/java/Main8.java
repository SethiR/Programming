/*
A simple example where we pick data from file (csv), the file has a header.
The file is read and we create a map where key is the header and value
is each row in the file, this map can then be converted to JSON easily.
 */

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

class FileProcessor{

    String fileName;

    public FileProcessor(String fileName){
        this.fileName = fileName;
    }

    static Map<String, String> toMap(String[] keys, String[] values){
        Map<String, String> map = new HashMap<>();
        for (int i=0; i< keys.length -1 ; i++){
            map.put(keys[i], values[i]);
        }   
        return map;
    }

    boolean run() throws InterruptedException {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If no lines in file.
        if (lines.size() == 0){
            return true;
        }


        String[] header = lines.get(0).split(",");
        lines = lines.subList(1,lines.size());

        for (String line : lines){
            String[] word = line.split(",");
            Gson gson = new Gson();
            Map<String, String> dataMap = toMap(header, word);
            String dataJSON = gson.toJson(dataMap);
            String key = dataMap.get("Site Num") + "-" + dataMap.get("");
            boolean result = postToKafka(key, dataJSON);
            Thread.sleep(20);
        }
        return true;
    }

    private boolean postToKafka(String key, String data){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        producer.send(new ProducerRecord<String, String>("weatherData", key, data));
        producer.close();

        return true;
    }

}


public class Main8 {
    public static void main(String[] args){
        String fileName = args[0];
        FileProcessor fp = new FileProcessor(fileName);
        try {
            fp.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}