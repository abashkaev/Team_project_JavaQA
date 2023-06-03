package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {//добавление на счет корректного значения
        CreditAccount account = new CreditAccount(1_000, 5_000,15);

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }
    @Test
    public void createAccountWithNegativeRate () {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {CreditAccount account = new CreditAccount(0, 5_000,-15);} );
    }

    @Test
    public void byeIfAmountIsLessZero () {
        CreditAccount account = new CreditAccount(0, 5_000,15);

        Assertions.assertEquals(false, account.pay(-300));
    }

    @Test
    public void byeIfAmountIsZero () {
        CreditAccount account = new CreditAccount(0, 5_000,15);

        Assertions.assertEquals(false, account.pay(0));
    }

    @Test
    public void byeIfAmountIsGreaterZero () {
        CreditAccount account = new CreditAccount(1000, 5_000,15);

        account.pay(500);

        Assertions.assertEquals(500, account.getBalance());
    }
    @Test
    public void byeUnderCreditLimit() {
        CreditAccount account = new CreditAccount(0, 5_000,15);

        Assertions.assertEquals(false, account.pay(6000));
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void creditPercent () {
        CreditAccount account = new CreditAccount(-1000, 5_000,15);

        Assertions.assertEquals(-150, account.yearChange());
    }
    @Test
    public void creditPercentIfPositiveBalance () {
        CreditAccount account = new CreditAccount(1000, 5_000,15);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test public void creditPercentIfBalanceIsZero () {
        CreditAccount account = new CreditAccount(0, 5_000,15);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void createAccountWithRateIsZero () {
        CreditAccount account = new CreditAccount(0, 5_000,0);

        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    public void payIfCreditLimitOver () {
        CreditAccount account = new CreditAccount(0, 5_000,15);

        Assertions.assertEquals(false, account.pay(6000));
    }
    @Test
    public void  balanceAfterPayIfBalanceUnderCreditLimit() { //Тест на баланс, при выходе за кредитный лимит
        CreditAccount account = new CreditAccount(0, 5_000,15);

        account.pay(6000);

        Assertions.assertEquals(0, account.getBalance());
    }
    @Test
    public void balanceAfterPay () {// Тест на изменение баланса, после pay
        CreditAccount account = new CreditAccount(1500, 5_000, 15);

        account.pay(500);

        Assertions.assertEquals(1000, account.getBalance());
    }


}

