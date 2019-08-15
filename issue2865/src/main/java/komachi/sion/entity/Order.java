package komachi.sion.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_order")
@Getter
@Setter
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "month_time")
    private String monthTime;
    
    @Column
    private String status;
}
