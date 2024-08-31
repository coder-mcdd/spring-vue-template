package jzxy.mcdd.backend;

import org.springframework.boot.SpringApplication;

class TestBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(BackendApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
