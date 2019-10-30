package solutions.own;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionFraudDetector {

    public static List<String> detect(List<String> transactions, LocalDate date, BigDecimal totalThreshold) {

        List<Transaction> transactionList = transactions.stream()
                .filter(TransactionValidator::isValidCsv)   // Use of input to filter out records to detect fradulent
                .map(TransactionConverter::fromCsv)         // transactions is not advised to be used in this way but
                .filter(TransactionValidator::isValid)      // rather to identify them as fradulent immediately because
                .collect(Collectors.toList());              // such logic might be exploited. It is used here just for
                                                            // the purpose of showcasing data filtering and validation.

        return groupTransactionsByDate(transactionList).entrySet().stream()
                .filter(transactionEntry -> transactionEntry.getKey().getLocalDate().equals(date))
                .filter(transactionEntry -> transactionEntry.getValue().compareTo(totalThreshold) > 0)
                .map(transactionEntry -> transactionEntry.getKey().getCardHash())
                .collect(Collectors.toList());

    }

    private static Map<TransactionKey, BigDecimal> groupTransactionsByDate(List<Transaction> transactionList) {

        Map<TransactionKey, BigDecimal> transactionMap = new HashMap<>();
        for (Transaction transaction : transactionList) {
            TransactionKey transactionKey = TransactionKey.of(transaction.getCardHash(), transaction.getDateTime());

            BigDecimal totalValue = transactionMap.get(transactionKey);
            if (Objects.isNull(totalValue)) {
                transactionMap.put(transactionKey, transaction.getTotal());
            } else {
                transactionMap.put(transactionKey, totalValue.add(transaction.getTotal()));
            }
        }
        return transactionMap;
    }

}
