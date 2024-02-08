package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Orders {
    @Id
    private String orderId;
    private String date;
    private String custId;
//    private String itmCode;
//    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetails = new ArrayList<>();


    public Orders(String orderId, String date,String custId) {
        this.orderId = orderId;
        this.date = date;
        this.custId = custId;
//        this.itmCode = itmCode;
//        this.price = price;
    }
}
