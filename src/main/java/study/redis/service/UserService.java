package study.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import study.redis.dto.UserProfile;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private final ExternerApiService externerApiService;

    private final StringRedisTemplate redisTemplate;

    public UserService(ExternerApiService externerApiService, StringRedisTemplate redisTemplate) {
        this.externerApiService = externerApiService;
        this.redisTemplate = redisTemplate;
    }

    public UserProfile getUserProfile(String userId){
        String userName = null;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cachedName = ops.get("nameKey: " + userId);

        if (cachedName != null){
            userName = cachedName;
        }
        else {
            userName = externerApiService.getUserName(userId);
            ops.set("nameKey: " + userId, userName, 5, TimeUnit.SECONDS);
        }

        int userAge = externerApiService.getUserAge(userId);

        return new UserProfile(userName, userAge);
    }

}
