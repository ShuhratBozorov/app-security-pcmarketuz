package uz.pdp.appsecuritypcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appsecuritypcmarketuz.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
