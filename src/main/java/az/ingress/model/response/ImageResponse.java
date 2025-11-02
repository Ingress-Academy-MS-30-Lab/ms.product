package az.ingress.model.response;

import lombok.Data;

@Data
public class ImageResponse {

  private Long id;
  private String imageUrl;
  private Boolean isPrimary;
  private int displayOrder;

}
