package komachi.sion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import komachi.sion.entity.Order;
import komachi.sion.repository.OrderRepo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Try to reproduce issue, but failed.
 *
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);

            OrderRepo repo = applicationContext.getBean(OrderRepo.class);
            List<Order> data = repo.findAll();
            for(Order order :data){
                order.setStatus("esdfsf");
            }
            repo.saveAll(data);
        }
    }


    public static void process(ConfigurableApplicationContext applicationContext) {
        OrderRepo repo = applicationContext.getBean(OrderRepo.class);
        List<Order> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Order order = new Order();
            order.setOrderId(100);
            order.setMonthTime("201908");
            order.setStatus("TEST");
            list.add(order);
        }
        repo.saveAll(list);
    }
}
