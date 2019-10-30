package solutions.own;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionConverter {

    private static final String CSV_DELIMITER = ",";

    /**
     * @param transactionCsv eg. '10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00'
     */
    public static Transaction fromCsv(String transactionCsv) {

        String[] transactionStringArray = transactionCsv.split(CSV_DELIMITER);

        return new Transaction(
                transactionStringArray[0].trim(),
                LocalDateTime.parse(transactionStringArray[1].trim()),
                new BigDecimal(transactionStringArray[2].trim())
        );

    }

}
