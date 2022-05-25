package uz.pdp.appsecuritypcmarketuz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    @NotNull(message = "brandName bo'sh bo'lmasligi kerak!")
    private String name;
}
