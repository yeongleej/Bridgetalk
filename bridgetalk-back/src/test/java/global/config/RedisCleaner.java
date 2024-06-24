package global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCleaner {
    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public RedisCleaner(RedisTemplate<String, Object> redisTemplate,
                        RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    public void flushAll() {
        redisConnectionFactory.getConnection().flushAll();
    }
}
