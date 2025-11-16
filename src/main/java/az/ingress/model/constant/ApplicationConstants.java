package az.ingress.model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConstants {

  public static final String MIN_PRICE = "0.00";
  public static final String LOG_START = "start";
  public static final String LOG_END = "end";
  public static final String LOG_ERROR = "error";
  public static final String ACTION_LOG = "ActionLog.{}.{}: {}";

}