package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.OrderDetailDto;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrdersTm extends RecursiveTreeObject<OrdersTm> {
    private String orderId;
    private String date;
    private String custId;
    private String itmCode;
    private double price;
    private List<OrderDetailDto> list;

    public OrdersTm(String orderId, String date, String custId, String itmCode, Double price) {
        this.orderId = orderId;
        this.date = date;
        this.custId = custId;
        this.itmCode = itmCode;
        this.price = price;
    }
}
