package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class OrderDetail {
    @EmbeddedId
    private OrderDetailsKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("itemCode")
    @JoinColumn(name = "item_code")
    private Item item;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Orders orders;
    private String product;

    private String fault;
    private double price;}
