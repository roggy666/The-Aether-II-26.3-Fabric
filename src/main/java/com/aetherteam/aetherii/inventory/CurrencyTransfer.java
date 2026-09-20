package com.aetherteam.aetherii.inventory;

/** Integer-only wallet arithmetic, shared with the regression checks. */
public final class CurrencyTransfer {
    private CurrencyTransfer() {}

    public static int withdraw(int balance, int button) {
        if (balance <= 0 || (button != 0 && button != 1)) return 0;
        int stack = Math.min(64, balance);
        return button == 0 ? stack : (stack + 1) / 2;
    }

    public static int deposit(int balance, int carried, int value, int button) {
        if (balance < 0 || carried <= 0 || value <= 0 || (button != 0 && button != 1)) return 0;
        int count = button == 0 ? carried : 1;
        return Math.min(count, (Integer.MAX_VALUE - balance) / value);
    }
}
