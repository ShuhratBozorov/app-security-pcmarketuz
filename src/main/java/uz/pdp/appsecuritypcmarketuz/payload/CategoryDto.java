package uz.pdp.appsecuritypcmarketuz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotNull(message = "categoryName bo'sh bo'lmasligi kerak!")
    private String name;

    private Integer parentCategoryId;
}
