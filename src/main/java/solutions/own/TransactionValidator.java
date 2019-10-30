package solutions.own;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionValidator {

    public static boolean isValid(Transaction transaction) {

        if (Objects.isNull(transaction)) {
            return false;
        }

        if (Objects.isNull(transaction.getTotal())) {
            return false;
        }

        if (Objects.isNull(transaction.getCardHash())) {
            return false;
        }

        if (transaction.getCardHash().length() != 27) {
            return false;
        }


        if (Objects.isNull(transaction.getDateTime())) {
            return false;
        }

        return true;
    }

    public static boolean isValidCsv(String transaction) {

        if (Objects.isNull(transaction)) {
            return false;
        }

        String[] strings = transaction.split(",");
        if (strings.length != 3) {
            return false;
        }

        if (strings[0].trim().length() != 27) {
            return false;
        }

        {
            // string is ISO date (e.g. 2019-12-31T24:59:59)
            Pattern pattern = Pattern.compile("(19|20)[0-9][0-9]-(0[0-9]|1[0-2])-(0[1-9]|([12][0-9]|3[01]))T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
            Matcher matcher = pattern.matcher(strings[1].trim());
            if (!matcher.find()) {
                return false;
            }
        }

        {
            // string is numeric, integer either positive or negative
            Pattern pattern = Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)");
            Matcher matcher = pattern.matcher(strings[2].trim());
            if (!matcher.find()) {
                return false;
            }
        }

        return true;
    }

}
