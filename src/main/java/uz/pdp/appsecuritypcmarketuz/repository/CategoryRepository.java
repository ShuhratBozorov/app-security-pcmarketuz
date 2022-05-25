package uz.pdp.appsecuritypcmarketuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appsecuritypcmarketuz.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
