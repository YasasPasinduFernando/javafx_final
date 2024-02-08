package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailTm extends RecursiveTreeObject<OrderDetailTm> {
    private String orderId;
    private String itemCode;
    private String product;
    private String fault;
    private double price;
}
