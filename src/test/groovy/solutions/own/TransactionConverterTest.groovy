package solutions.own

import spock.lang.Specification

import java.time.LocalDateTime

class TransactionConverterTest extends Specification {

    def "csv string should be successfully parsed"() {

        given:
        def csvTransaction = '10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00'

        when:
        def transaction = TransactionConverter.fromCsv(csvTransaction)

        then:
        transaction.cardHash == '10d7ce2f43e35fa57d1bbf8b1e2'
        transaction.dateTime == LocalDateTime.of(2014, 04, 29, 13, 15, 54)
        transaction.total == 10
    }
}
