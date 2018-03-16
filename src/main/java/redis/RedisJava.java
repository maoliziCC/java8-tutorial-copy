package redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @package:redis
 * @author: lizhang
 * @date: 2018-03-09 14:39
 */

public class RedisJava {

    //连接到 redis 服务
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //1. 查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());

        //2.  设置 redis 字符串数据
        jedis.set("w3ckey", "Redis tutorial");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));

        //3. List(列表) 实例
        //存储数据到列表中
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.expire("tutorial-list",50000);
        jedis.lpush("tutorial-list", "Mysql");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: " + list.get(i));
        }

        //4. Redis Java Keys 实例
        Set<String> set = jedis.keys("*");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println("List of stored keys:: "+ str );
        }

        System.out.println("0=-------------");
        jedis.set("key","keykeykeyssv");
        System.out.println("判断key是否存在："+ jedis.exists("key"));
        // 设置 key001的过期时间
        System.out.println("设置 key的过期时间为5秒:"+jedis.expire("key", 5));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1   当 key 不存在时，返回 -2
        System.out.println("查看key的剩余生存时间："+jedis.ttl("key"));
        // 移除某个key的生存时间
        System.out.println("移除key的生存时间："+jedis.persist("key"));
        System.out.println("查看key的剩余生存时间："+jedis.ttl("key"));
        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型："+jedis.type("key"));


    }
}
