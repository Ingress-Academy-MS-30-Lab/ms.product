package az.ingress.strategy;

import java.util.Map;
import java.util.function.Consumer;
import az.ingress.model.enums.OrderStatus;
import az.ingress.model.request.StockUpdateCallbackRequest;
import az.ingress.service.abstracts.InventoryService;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdateStrategy {

  private final Map<OrderStatus, Consumer<StockUpdateCallbackRequest>> stockConsumerMap;

  public InventoryUpdateStrategy(InventoryService inventoryService) {
    stockConsumerMap = Map.of(
        OrderStatus.SUCCESS, inventoryService::deductStock,
        OrderStatus.FAILED, inventoryService::releaseStock
    );
  }

  public void inventoryCallback(StockUpdateCallbackRequest request) {
    stockConsumerMap.get(request.getStatus()).accept(request);
  }


}
