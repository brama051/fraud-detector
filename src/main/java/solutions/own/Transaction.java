package solutions.own;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final String cardHash;
    private final LocalDateTime dateTime;
    private final BigDecimal total;

    public Transaction(String cardHash, LocalDateTime dateTime, BigDecimal total) {

        this.cardHash = cardHash;
        this.dateTime = dateTime;
        this.total = total;
    }

    public String getCardHash() {

        return cardHash;
    }

    public LocalDateTime getDateTime() {

        return dateTime;
    }

    public BigDecimal getTotal() {

        return total;
    }


}
