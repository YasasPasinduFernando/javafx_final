package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@ToString
@Entity

public class Item {
    @Id
    private String code;
    private String name;
    private String category;
    private String status;
    private String contact;

    public Item(String code,String name,String category,String status,String contact){
        this.code = code;
        this.name = name;
        this.category = category;
        this.status = status;
        this.contact = contact;

    }
}
