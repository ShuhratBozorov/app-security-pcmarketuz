package uz.pdp.appsecuritypcmarketuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appsecuritypcmarketuz.entity.Brand;
import uz.pdp.appsecuritypcmarketuz.payload.BrandDto;
import uz.pdp.appsecuritypcmarketuz.payload.Result;
import uz.pdp.appsecuritypcmarketuz.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Integer id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.orElse(null);
    }

    public Result addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        return new Result("Brand added", true);
    }

    public Result editBrand(BrandDto brandDto, Integer id) {

        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent())
            return new Result("Bunday brand mavjud emas", false);

        Brand brand = optionalBrand.get();
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        return new Result("Brand edited", true);
    }

    public Result deleteBrand(Integer id) {
        try {
            brandRepository.deleteById(id);
            return new Result("Brand deleted", true);
        } catch (Exception e) {
            return new Result("Error!!!", false);
        }
    }

}
