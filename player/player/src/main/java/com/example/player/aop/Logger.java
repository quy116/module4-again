package com.example.player.aop;

import com.example.player.dto.PlaySoccerDto;
import com.example.player.service.IPlaySoccerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private static int playerCount = 0;

    @Autowired
    private IPlaySoccerService iPlaySoccerService;

    @Pointcut("execution(* com.example.player.service.impl.PlaySoccerService.signUpForSoccer(..))")
    public void signUpForSoccerMethod() {}

    @Before("signUpForSoccerMethod()")
    public void countAndLogPlayer(JoinPoint joinPoint) {
        if (playerCount >= 11) {
            throw new RuntimeException("Số lượng cầu thủ đăng ký đã vượt quá 11.");
        }

        playerCount++;
        System.out.println("Số lượng cầu thủ đăng ký đá: " + playerCount);
    }

    // Thêm một @AfterReturning advice để giảm số lượng cầu thủ khi gọi reserveRegistration
    @AfterReturning("execution(* com.example.player.service.impl.PlaySoccerService.reserveRegistration(..))")
    public void decreasePlayerCount() {
        playerCount--;
        System.out.println("Số lượng cầu thủ đăng ký đá sau khi hủy đăng ký: " + playerCount);
    }
}
