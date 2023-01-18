package study.redis.Controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final StringRedisTemplate redisTemplate;

    public HelloController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello-World";
    }

    @GetMapping("setFruit")
    public String setFruit(@RequestParam String name){
        ValueOperations<String, String> ops = redisTemplate.opsForValue(); // ops를 통해 set, get을 실행
        ops.set("fruit", name);
        return "saved";
    }
    @GetMapping("getFruit")
    public String getFruit(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get("fruit");
    }


}
