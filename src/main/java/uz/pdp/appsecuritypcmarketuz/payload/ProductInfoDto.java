package uz.pdp.appsecuritypcmarketuz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto {
    @NotNull(message = "key bo'sh bo'lmasligi kerak!")
    private String key;

    @NotNull(message = "value bo'sh bo'lmasligi kerak!")
    private String value;
}
