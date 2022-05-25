package uz.pdp.appsecuritypcmarketuz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotNull(message = "productName bo'sh bo'lmasligi kerak!")
    private String name;

    private Integer brandId;

    private Integer categoryId;

    private Integer productInfoId;
}
