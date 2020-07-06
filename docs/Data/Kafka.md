# Kafka

A simple kafka producer.

- Run kafka 
- Create topic named "test"
- Run console consumer to confirm msg received.

```
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

```java
// SampleProducer.java
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SampleProducer {
    public SampleProducer(){
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        ProducerRecord producerRecord = new ProducerRecord("test","name","sam");

        KafkaProducer kafkaProducer = new KafkaProducer(props);

        kafkaProducer.send(producerRecord);

        kafkaProducer.close();
    }
}
```

```java
// Runner.java
public class Runner {
    public static void main(String[] args) {

        SampleProducer sampleProducer = new SampleProducer();

    }
}
```

```xml
<!-- Maven -->
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
        <dependency>
            <groupId>org.apache.kafka</groupId>
            <artifactId>kafka-clients</artifactId>
            <version>2.5.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.30</version>
        </dependency>
</dependencies>
```

---

Hortonworks connect to kafka broker from within the same machine CentOS. (Same machine no ssh)

```py
from pykafka import KafkaClient

client = KafkaClient(hosts="localhost:9092", zookeeper_hosts="localhost:2181")

topic = client.topics['weatherData']

with topic.get_sync_producer() as producer:
    for i in range(4):
        producer.produce(b"test message")
```

You can consume using the console consumer.
```
/usr/hdp/current/kafka-broker/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic weatherData --from-beginning
```


---

```py
"""
Simple script to upload a json data to Kafka in Python
"""
from pykafka import KafkaClient
import json

client = KafkaClient(hosts="localhost:9092", zookeeper_hosts="localhost:2181")

# print(client.topics)

topic = client.topics['weatherData']

data = {
	"name" : "Sam",
	"age" : 10,
	"hobbies" : [
			"football",
			"basketball"
		]
}

print(json.dumps(data))


with topic.get_sync_producer() as producer:
	producer.produce(bytes(json.dumps(data), encoding = 'utf-8'))
```


A simple Java produer -- coded by me for a test check on my laptop. Uses the weather data with 
header where it forms the csv.

```java
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
            Thread.sleep(2000);
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
```