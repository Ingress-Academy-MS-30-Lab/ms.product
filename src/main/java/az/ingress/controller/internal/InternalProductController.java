package az.ingress.controller.internal;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import az.ingress.model.request.OrderItemsValidationRequest;
import az.ingress.model.request.ReservationRequest;
import az.ingress.model.response.OrderValidationResponse;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.model.response.ReservationResponse;
import az.ingress.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/products")
@RequiredArgsConstructor
public class InternalProductController {

  private final ProductService productService;

  @GetMapping
  public PageResponse<ProductPreviewResponse> getProductsByIds(
      @RequestParam @NotEmpty List<Long> ids,
      @RequestParam int page,
      @RequestParam int size
  ) {
    return productService.getProductsByIds(ids, page, size);
  }

  @GetMapping("{id}/exists")
  public void checkProductExists(@PathVariable Long id) {
    productService.checkProductExists(id);
  }

  @PostMapping("/validate")
  public OrderValidationResponse validateProduct(
      @RequestBody @Valid OrderItemsValidationRequest validationRequest
  ) {
    return productService.validateProducts(validationRequest);
  }

  @PostMapping("/reserve")
  public ReservationResponse reserveProduct(
      @RequestBody @Valid ReservationRequest reservationRequest
  ) {
    return productService.reserveProducts(reservationRequest);
  }

}
