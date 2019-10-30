package solutions.own

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class TransactionValidatorTest extends Specification {

    @Unroll
    def "When transaction #scenario, it is not considered valid"() {

        expect:
        !TransactionValidator.isValid(new Transaction(hash, dateTime, total))

        where:
        scenario                           | hash                           | dateTime                                   | total
        'total is undefined'               | '10d7ce2f43e35fa57d1bbf8b1e0'  | LocalDateTime.parse('2019-08-01T12:12:12') | null
        'timestamp is undefined'           | '10d7ce2f43e35fa57d1bbf8b1e0'  | null                                       | BigDecimal.valueOf(20)
        'hash has more than 27 characters' | '10d7ce2f43e35fa57d1bbf8b1e0x' | LocalDateTime.parse('2019-08-01T12:12:12') | BigDecimal.valueOf(20)
        'hash has less than 27 characters' | ''                             | LocalDateTime.parse('2019-08-01T12:12:12') | BigDecimal.valueOf(20)
        'hash is undefined'                | null                           | LocalDateTime.parse('2019-08-01T12:12:12') | BigDecimal.valueOf(20)

    }

    @Unroll
    def "Transaction with #hash, #dateTime and #total is considered valid"() {

        expect:
        TransactionValidator.isValid(new Transaction(hash, dateTime, total))

        where:
        hash                          | dateTime                                   | total
        '10d7ce2f43e35fa57d1bbf8b1e0' | LocalDateTime.parse('2019-08-01T12:12:12') | BigDecimal.valueOf(10)
        '10d7ce2f43e35fa57d1bbf8b1e0' | LocalDateTime.parse('2019-08-01T12:12:12') | BigDecimal.valueOf(0)
        '10d7ce2f43e35fa57d1bbf8b1e0' | LocalDateTime.parse('2019-08-01T12:12:12') | BigDecimal.valueOf(-10)
    }

    def "CSV entry validation basic validation test"() {
        expect:
        TransactionValidator.isValidCsv(csvRecord) == expectedOutcome

        where:
        csvRecord                                                     | expectedOutcome
        null                                                          | false
        ''                                                            | false
        ',,'                                                          | false
        '123456789012345678901234567,2000-02-20T00:00:00,'            | false
        '123456789012345678901234567,2000-02-20T00:00:00,0.00'        | true
        '123456789012345678901234567  , 2000-02-20T00:00:00   , 0.00' | true


    }
}
