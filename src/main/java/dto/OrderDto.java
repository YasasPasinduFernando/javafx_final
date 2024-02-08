package dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDto {
    private String orderId;
    private String date;
    private String custId;
//    private String itmCode;
//    private double price;
    private List<OrderDetailDto> list;
}
