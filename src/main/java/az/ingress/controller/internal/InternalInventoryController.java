package az.ingress.controller.internal;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.Valid;
import az.ingress.model.request.StockUpdateCallbackRequest;
import az.ingress.strategy.InventoryUpdateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/inventory")
@RequiredArgsConstructor
public class InternalInventoryController {

  private final InventoryUpdateStrategy inventoryUpdateStrategy;

  @PatchMapping
  @ResponseStatus(NO_CONTENT)
  public void updateStock(
      @RequestBody @Valid StockUpdateCallbackRequest stockUpdateCallbackRequest
  ) {
    inventoryUpdateStrategy.inventoryCallback(stockUpdateCallbackRequest);
  }


}
