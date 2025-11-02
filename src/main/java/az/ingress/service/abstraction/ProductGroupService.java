package az.ingress.service.abstraction;

import org.springframework.stereotype.Service;

@Service
public interface ProductGroupService {

  void checkProductExists(Long id);

}
