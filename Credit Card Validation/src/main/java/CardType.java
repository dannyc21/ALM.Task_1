import java.util.ArrayList;
import java.util.List;

public enum CardType {
  VISA("VISA", "4"),
  JCB("JCB", "35"),
  DISCOVER("DISCOVER", "6011", "65"),
  MASTERCARD("MASTERCARD", "51", "52", "53", "54", "55"),
  AMERICAN_EXPRESS("AMERICAN EXPRESS", "34", "37");

  private final String paymentSystemName;
  private final List<String> prefixs;

  CardType(String paymentName, String ...prefix) {
    this.paymentSystemName = paymentName;
    this.prefixs = List.of(prefix);
  }

  public List<String> getPrefixs() {
    return this.prefixs;
  }
  public String getPaymentSystemName() {
    return this.paymentSystemName;
  }
}
