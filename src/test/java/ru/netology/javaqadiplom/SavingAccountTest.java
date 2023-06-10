package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldTestWhenRateLess0() { //накопительная ставка не может быть отрецательная
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            1_000, 1_000, 10_000, -15);
                });

    }

    @Test
    public void shouldTestMinBalanceMoreMaxBalance() { //мин баланс  не может быть больше макс.
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            1_000, 10_000, 1_000, 15);
                });
    }

    @Test
    public void shouldTestMaxBalanceLess0() { //макс. баланс не может быть меньше нуля
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            1_000, 1_000, -1_000, 15);
                });
    }

    @Test
    public void shouldTestMinBalanceLess0() { //мин. баланс не может быть меньше нуля
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            1_000, -10_000, 1_000, 15);
                });
    }

    @Test
    public void shouldTestInitialBalanceLessMinBalance() { //нач. баланс не может быть меньше минимального
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            1_000, 2_000, 10_000, 15);
                });
    }

    @Test
    public void shouldTestInitialBalanceMoreMaxBalance() { //нач. баланс не может быть больше максимального
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            11_000, 2_000, 10_000, 15);
                });
    }

    @Test
    public void shouldTestInitialBalanceLess0() { //начальный баланс не может быть меньше 0
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    SavingAccount account = new SavingAccount(
                            -100, 1_000, 10_000, 15);
                });
    }

    @Test
    public void shouldAddLessThanMaxBalance() { //когда пополнение меньше макс.баланса
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        savingAccount.add(3_000);
        int expected = 5_000;
        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddMoreThanMaxBalance() { //когда при пополнение баланс становитья больше макс.баланса
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                5_000,
                5
        );

        savingAccount.add(7_000);
        int expected = 2_000;
        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestWhenAddAmountLess0() { //когда пополнение карты отрецательное
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        savingAccount.add(-300);
        int expected = (2_000);
        int actual = savingAccount.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestWhenAddAmountEqual0() { //когда пополнение =0
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        savingAccount.add(0);
        int expected = (2_000);
        int actual = savingAccount.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTestWhenBalanceNotGoBeyondTheMinBalance() { //при покупке баланс не выходит за границы мин. баланса
        SavingAccount savingAccount = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        savingAccount.pay(300);
        int expected = 2700;
        int actual = savingAccount.getBalance();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestWhenBalanceGoBeyondTheMinBalance() { //при покупке баланс выходит за границы мин. баланса
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        savingAccount.pay(1_500);
        int expected = (2_000);
        int actual = savingAccount.getBalance();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestWhenAmountLess0() { //когда сумма покупки отрецательная
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        savingAccount.pay(-300);
        int expected = (2_000);
        int actual = savingAccount.getBalance();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestWhenBalanceNotChange() {// расчет % на остаток
        SavingAccount savingAccount = new SavingAccount(
                200,
                200,
                10_000,
                15
        );
        int expected = 30;
        int actual = savingAccount.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}
