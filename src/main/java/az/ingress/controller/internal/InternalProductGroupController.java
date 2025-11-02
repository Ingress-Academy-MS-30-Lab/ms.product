package az.ingress.controller.internal;

import az.ingress.service.abstraction.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/product-groups")
@RequiredArgsConstructor
public class InternalProductGroupController {

  private final ProductGroupService productGroupService;

  @GetMapping("{id}/exists")
  public void checkProductExists(@PathVariable Long id) {
    productGroupService.checkProductExists(id);
  }

}
