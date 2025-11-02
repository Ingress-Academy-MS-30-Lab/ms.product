package az.ingress.controller;

import java.util.Optional;
import az.ingress.model.request.ProductFilterRequest;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductDetailsResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public PageResponse<ProductPreviewResponse> getProducts(
      @RequestParam Optional<Long> categoryId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id,asc") String sort,
      @RequestBody ProductFilterRequest filterRequest
  ) {
    return productService.getProducts(categoryId, page, size, sort, filterRequest);
  }

  @GetMapping("/{id}")
  public ProductDetailsResponse getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

}
