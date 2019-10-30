package solutions.own;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionKey {

    private final String cardHash;
    private final LocalDate localDate;

    private TransactionKey(String cardHash, LocalDate localDate) {

        this.cardHash = cardHash;
        this.localDate = localDate;
    }

    public static TransactionKey of(String cardHash, LocalDateTime dateTime) {

        return new TransactionKey(cardHash, dateTime.toLocalDate());
    }

    public String getCardHash() {
        return cardHash;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionKey that = (TransactionKey) o;
        return Objects.equals(cardHash, that.cardHash) &&
                Objects.equals(localDate, that.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardHash, localDate);
    }
}
