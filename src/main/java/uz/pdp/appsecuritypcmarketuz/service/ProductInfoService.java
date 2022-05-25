package uz.pdp.appsecuritypcmarketuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appsecuritypcmarketuz.entity.ProductInfo;
import uz.pdp.appsecuritypcmarketuz.payload.ProductInfoDto;
import uz.pdp.appsecuritypcmarketuz.payload.Result;
import uz.pdp.appsecuritypcmarketuz.repository.ProductInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    public List<ProductInfo> getProductInfos() {
        return productInfoRepository.findAll();
    }

    public ProductInfo getProductInfoById(Integer id) {
        Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(id);
        return optionalProductInfo.orElse(null);
    }

    public Result addProductInfo(ProductInfoDto productInfoDto) {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setKey(productInfoDto.getKey());
        productInfo.setValue(productInfoDto.getValue());
        productInfoRepository.save(productInfo);
        return new Result("ProductInfo added", true);
    }

    public Result editProductInfo(ProductInfoDto productInfoDto, Integer id) {

        Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(id);
        if (!optionalProductInfo.isPresent())
            return new Result("Bunday productInfo mavjud emas", false);

        ProductInfo productInfo = optionalProductInfo.get();
        productInfo.setKey(productInfoDto.getKey());
        productInfo.setValue(productInfoDto.getValue());
        productInfoRepository.save(productInfo);
        return new Result("ProductInfo edited", true);
    }

    public Result deleteProductInfo(Integer id) {
        try {
            productInfoRepository.deleteById(id);
            return new Result("ProductInfo deleted", true);
        } catch (Exception e) {
            return new Result("Error!!!", false);
        }
    }
}
