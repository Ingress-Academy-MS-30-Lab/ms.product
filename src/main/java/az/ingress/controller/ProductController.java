package az.ingress.controller;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public PageResponse<ProductPreviewResponse> getProductsByIds(
      @RequestParam @NotEmpty List<Long> ids,
      @RequestParam int page,
      @RequestParam int size
  ) {
    return productService.getProductsByIds(ids, page, size);
  }

}
