package az.ingress.model.response;

public record ErrorResponse(String errorCode,
                            String message) {}