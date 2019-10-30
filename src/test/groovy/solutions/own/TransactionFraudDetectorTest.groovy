package solutions.own

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class TransactionFraudDetectorTest extends Specification {

    def transactions = [
            "10d7ce2f43e35fa57d1bbf8b1e0, 2014-04-28T13:15:54, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e1, 2014-04-29T13:15:54, 10.01",
            "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:55, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:56, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-29T13:15:56, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e4, 2014-04-29T13:15:56, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e3, 2014-04-30T13:15:56, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e5, 2014-04-30T13:15:55, 10.00",
            "10d7ce2f43e35fa57d1bbf8b1e5, 2014-04-30T13:15:56, 10.00"
    ]


    @Unroll
    def "Detect method should return #expectedFradulentCardHashes when looking for fradulent transactions on #date with total threshold of #totalThreshold"() {

        def fradulentCardHashes = TransactionFraudDetector.detect(transactions, LocalDate.parse(date), BigDecimal.valueOf(totalThreshold))

        expect:
        fradulentCardHashes.size() == expectedFradulentCardHashes.size()
        fradulentCardHashes
                .containsAll(expectedFradulentCardHashes)

        where:
        date         | totalThreshold | expectedFradulentCardHashes
        '2014-04-27' | 1              | []
        '2014-04-28' | 10             | []
        '2014-04-28' | 9              | ['10d7ce2f43e35fa57d1bbf8b1e0']
        '2014-04-29' | 10             | ['10d7ce2f43e35fa57d1bbf8b1e2', '10d7ce2f43e35fa57d1bbf8b1e1']
        '2014-04-29' | 20             | ['10d7ce2f43e35fa57d1bbf8b1e2']
        '2014-04-30' | 10             | ['10d7ce2f43e35fa57d1bbf8b1e5']

    }
}
