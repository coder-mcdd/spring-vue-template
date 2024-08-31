package jzxy.mcdd.backend;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * BackendApplicationTests
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/31 20:46
 */
@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class BackendApplicationTests {

    @Resource
    ApplicationContext context;

    @Test
    void contextLoads() {
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
