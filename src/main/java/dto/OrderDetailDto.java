package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDto {
    private String orderId;
    private String itemCode;
    private String product;
    private String fault;
    private double price;

}