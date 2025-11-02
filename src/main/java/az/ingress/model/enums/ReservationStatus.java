package az.ingress.model.enums;

public enum ReservationStatus {
  ACTIVE,       // Reservation is holding inventory
  COMPLETED,    // Payment successful, converted to order
  EXPIRED,      // Time limit exceeded, inventory released
  CANCELLED,    // User cancelled or removed items
  FAILED,
  RELEASED      // Released due to payment failure or other reasons
}