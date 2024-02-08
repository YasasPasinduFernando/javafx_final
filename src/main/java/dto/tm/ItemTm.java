package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String code;
    private String name;
    private String category;
    private String status;
    private JFXButton btn;
    private String contact;
}
