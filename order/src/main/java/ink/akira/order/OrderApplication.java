package ink.akira.order;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by akira on 2019/3/28.
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "ink.akira.order.service.impl")
@MapperScan("ink.akira.order.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
