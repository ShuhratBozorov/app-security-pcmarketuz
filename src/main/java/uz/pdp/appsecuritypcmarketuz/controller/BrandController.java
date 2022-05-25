package uz.pdp.appsecuritypcmarketuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appsecuritypcmarketuz.entity.Brand;
import uz.pdp.appsecuritypcmarketuz.payload.BrandDto;
import uz.pdp.appsecuritypcmarketuz.payload.Result;
import uz.pdp.appsecuritypcmarketuz.service.BrandService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getBrands() {
        List<Brand> brands = brandService.getBrands();
        return ResponseEntity.ok(brands);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer id) {
        Brand brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public HttpEntity<?> addBrand(@RequestBody BrandDto brandDto) {
        Result result = brandService.addBrand(brandDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public HttpEntity<?> editBrand(@Valid @RequestBody BrandDto brandDto, @PathVariable Integer id) {
        Result result = brandService.editBrand(brandDto, id);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBrand(@PathVariable Integer id) {
        Result result = brandService.deleteBrand(id);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
