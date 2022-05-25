package uz.pdp.appsecuritypcmarketuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appsecuritypcmarketuz.entity.Product;
import uz.pdp.appsecuritypcmarketuz.payload.ProductDto;
import uz.pdp.appsecuritypcmarketuz.payload.Result;
import uz.pdp.appsecuritypcmarketuz.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<?> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        Result result = productService.addProduct(productDto);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Integer id) {
        Result result = productService.editProduct(productDto, id);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id) {
        Result result = productService.deleteProduct(id);
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
