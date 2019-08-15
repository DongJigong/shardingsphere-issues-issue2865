package komachi.sion;

import komachi.sion.entity.Order;
import komachi.sion.repository.OrderRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Try to reproduce issue, but failed.
 *
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args);
        try {
            process(applicationContext);

            OrderRepo repo = applicationContext.getBean(OrderRepo.class);
//            Order or = new Order();
//            or.setMonthTime("201908");
//            or.setOrderId(100);
//            ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withMatcher("monthTime",exact())
//                    .withMatcher("orderId",exact());
//            List<Order> data = repo.findAll(Example.of(or,exampleMatcher));
            List<Order> data = repo.findAll();
            for(Order order :data){
                order.setStatus("esdfsf");
            }
            data = repo.saveAll(data);
        }catch (Exception e){
            e.printStackTrace();
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
