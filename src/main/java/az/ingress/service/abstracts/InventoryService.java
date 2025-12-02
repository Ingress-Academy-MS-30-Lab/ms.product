package az.ingress.service.abstracts;

import az.ingress.model.request.StockUpdateCallbackRequest;

public interface InventoryService {

  void releaseStock(StockUpdateCallbackRequest stockUpdateCallbackRequest);

  void deductStock(StockUpdateCallbackRequest stockUpdateCallbackRequest);

}
