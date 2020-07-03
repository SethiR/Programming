"""
Author : Me
This script will post csv content as json to kafka topic.
"""

import sys
import json
import time

from pykafka import KafkaClient


class FileHandler:

    def __init__(self, file):
        self.file = file
        self.header = None

    def __iter__(self):
        with open(self.file) as f:
            self.header = f.readline()
            line = True
            while (line):
                line = f.readline()
                yield line

    def lines(self):
        return iter(self)


def main(file_name, topic, file_offset, time_interval):
    client = KafkaClient(hosts="localhost:9092", zookeeper_hosts="localhost:2181")
    topic = client.topics[topic]
    file = FileHandler(file_name)
    with topic.get_sync_producer() as producer:
        for line in file.lines():
            data = json.dumps(dict(zip(file.header.split(","), line.split(","))))
            print(data)
            producer.produce(bytes(data, encoding = 'utf-8'))


if __name__ == '__main__':
    file_name = sys.argv[1]
    topic = sys.argv[2]
    file_offset = sys.argv[3]
    time_interval = sys.argv[4]
    main(file_name, topic, file_offset, time_interval)
