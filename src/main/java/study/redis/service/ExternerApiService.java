package study.redis.service;

import org.springframework.stereotype.Service;

@Service
public class ExternerApiService {

    public String getUserName(String userId){
        //외부 서비스나 DB 호출

        try {Thread.sleep(500);}
        catch (InterruptedException e){}

        System.out.println("이름 출력");
        if (userId.equals("A")){
            return "Adam";
        }if (userId.equals("B")){
            return "Bob";
        }
        return "";
    }
    public int getUserAge(String userId){
        //외부 서비스나 DB 호출

        try {Thread.sleep(500);}
        catch (InterruptedException e){}

        System.out.println("나이 출력");

        if (userId.equals("A")){
            return 28;
        }if (userId.equals("B")){
            return 35;
        }
        return 0;
    }

}
