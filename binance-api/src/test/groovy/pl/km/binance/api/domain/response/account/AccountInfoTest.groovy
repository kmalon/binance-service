package pl.km.binance.api.domain.response.account

import spock.lang.Specification

class AccountInfoTest extends Specification {

    def "should fillter out and set only not zero balances"() {
        given: "zero and not zero balances"
        def zeroBigDecimal = BigDecimal.ZERO.setScale(8)
        def notZeroBigDecimal = new BigDecimal(10).setScale(8)
        def zeroBalance = Balance.builder().asset("zeroBalance").free(zeroBigDecimal).locked(zeroBigDecimal).build()
        def notZeroBalanceWithLocked = Balance.builder().asset("notZeroBalanceWithLocked").free(zeroBigDecimal).locked(notZeroBigDecimal).build()
        def notZeroBalanceWithFree = Balance.builder().asset("notZeroBalanceWithFree").free(notZeroBigDecimal).locked(zeroBigDecimal).build()
        when: "setting AccountInfo balance - non zero Balances should be filltered out"
        def info = new AccountInfo()
        info.setBalances(Arrays.asList(zeroBalance, notZeroBalanceWithFree, notZeroBalanceWithLocked))
        then: "should filler out zero balance but not with locked or free"
        info.getBalances().size() == 2
        info.getBalances().contains(notZeroBalanceWithLocked)
        info.getBalances().contains(notZeroBalanceWithFree)
    }
}