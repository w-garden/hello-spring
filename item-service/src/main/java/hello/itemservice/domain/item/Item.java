package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
//아래 방법은 추천하지 않음 -> 자바 코드로 구현 추천(ValidationItemControllerV3)
//@ScriptAssert(lang = "javascript", script="_this.price * _this.quantity >= 10000", message="총합이 10,000원이 넘게 입력 해 주세요")
public class Item {
    
//    @NotNull(groups = UpdateCheck.class) //수정시에만 적용
    private Long id;

//    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class}) //범위 안의 값이어야 함
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class) //등록시에만 적용
    private Integer quantity;

    private Boolean open; //판매여부
    private List<String> regions; //등록 지역
    private ItemType itemType; //상품 종류
    private String deliveryCode; //배송 방식

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
