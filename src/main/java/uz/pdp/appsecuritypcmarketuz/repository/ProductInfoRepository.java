package uz.pdp.appsecuritypcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appsecuritypcmarketuz.entity.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer> {
}
