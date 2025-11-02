package az.ingress.model.event;

import az.ingress.model.enums.CategoryChangeEventType;
import lombok.Data;

@Data
public class CategoryUpdateEvent {

  private CategoryChangeEventType eventType;
  private Long id;
  private String name;
  private Long parentId;
  private String oldPath;
  private String newPath;

}
