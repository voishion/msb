# Redis研究记录

- ## Redis监听事件

[Redis监听事件配置参考文档](https://www.cnblogs.com/qlqwjy/p/15132798.html)

```config
#监控所有事件，感觉有性能问题，没有测试过
notify-keyspace-events KEA

#监控过期事件
notify-keyspace-events Ex

#监控过期、删除事件
notify-keyspace-events Eg
```

```
#事件列表
__keyevent@0__:del
__keyevent@0__:expire
__keyevent@0__:set
```

```
#官网提供的事件类型如下
K     Keyspace events, published with __keyspace@<db>__ prefix.
E     Keyevent events, published with __keyevent@<db>__ prefix.
g     Generic commands (non-type specific) like DEL, EXPIRE, RENAME, ...
$     String commands
l     List commands
s     Set commands
h     Hash commands
z     Sorted set commands
t     Stream commands
d     Module key type events
x     Expired events (events generated every time a key expires)
e     Evicted events (events generated when a key is evicted for maxmemory)
m     Key miss events (events generated when a key that doesn't exist is accessed)
A     Alias for "g$lshztxed", so that the "AKE" string means all the events except "m".
```

