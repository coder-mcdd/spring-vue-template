package jzxy.mcdd.backend;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest
class BackendApplicationTests {

    @Resource
    ApplicationContext context;

    @Test
    void contextLoads() {
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
