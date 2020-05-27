import redis

SERVER = 'localhost'
PORT = 6379

# Connecting to Redis
r = redis.Redis(host=SERVER, port=PORT)

# Setting single value
r.set('foo','bar')
print(r.get('foo'))

# Setting and incrementing integers
r.set('a',100)
r.incr('a',10)

print(r.get('a'))

# Setting multiple values
r.mset({'name': 'Sam', 'age': 10})

# Getting multiple values
print(r.mget(['name', 'age']))

# A quick word about keys --> binary

# del
r.delete('name')

# Expiry in 20 seconds.
r.set('name','Sam',ex=20)


# -----------  LIST

r.rpush('grocery','apple')
r.rpush('grocery','banana')
r.rpush('grocery','grapes', 'oranges')

print(r.rpop('grocery'))
print(r.lpop('grocery'))

# only remember the last x elements in the list
r.ltrim('grocery',0, 3)

print(r.llen('grocery'))

# ------------- HASHES

r.hmset('user:1001', {'name':'sam', 'age':20})

"""
Thus you can store objects, but if its nested and not
serializable by JSON you might have to pickle it.
"""

# ------------ SETS

r.sadd('myset', 'value1','value2')
print(r.sismember('myset', 'value1'))
print(r.sismember('myset', 'value3'))


# intersection and other operations

#

# ------------ SORTED SETS

r.zadd('hackers', {"A": 1})
r.zadd('hackers', {"B": 2})
r.zadd('hackers', {"D": 4})
r.zadd('hackers', {"C": 3})

print(r.zrange('hackers', 0, -1))


# ----------- HyperLogLogs

# ----------- PUB/SUB Server