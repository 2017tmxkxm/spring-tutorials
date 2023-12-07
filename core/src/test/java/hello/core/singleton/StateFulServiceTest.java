package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        // ThreadA : A 사용자 10000원 주문
        int userAPrice = stateFulService1.order("userA", 10000);
        // ThreadB : B 사용자 20000원 주문
        int userBPrice = stateFulService2.order("userB", 20000);
        
        // ThreadA: 사용자A 주문 금액 조회
        //int price = stateFulService1.getPrice();
        //System.out.println("price = " + price);

        //assertThat(stateFulService1.getPrice()).isEqualTo(20000);

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);
    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}