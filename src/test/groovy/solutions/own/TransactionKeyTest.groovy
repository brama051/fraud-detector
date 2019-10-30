package solutions.own

import spock.lang.Specification

import java.time.LocalDateTime

class TransactionKeyTest extends Specification {

    def "Two transaction keys are considered to be equal only when they have the same hash and the same date part of datetime"() {

        expect:
        (TransactionKey.of(hash1, dateTime1) == TransactionKey.of(hash2, dateTime2)) == expectedOutcome

        where:
        hash1  | dateTime1                                  | hash2  | dateTime2                                  | expectedOutcome
        'hash' | LocalDateTime.parse('2014-04-29T13:15:54') | 'hash' | LocalDateTime.parse('2014-04-29T13:15:54') | true
        'hash' | LocalDateTime.parse('2014-04-29T13:15:54') | 'hash' | LocalDateTime.parse('2014-04-29T00:15:54') | true
        'hash' | LocalDateTime.parse('2014-04-29T13:15:54') | 'ahhs' | LocalDateTime.parse('2014-04-29T00:15:54') | false
        'hash' | LocalDateTime.parse('2014-04-29T13:15:54') | 'hash' | LocalDateTime.parse('2014-04-30T00:15:54') | false

    }


}
