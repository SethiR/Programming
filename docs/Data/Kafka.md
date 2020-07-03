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