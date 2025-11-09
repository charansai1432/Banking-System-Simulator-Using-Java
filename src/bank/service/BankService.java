package bank.service;

import bank.model.Account;
import bank.exception.*;
import java.util.*;
import java.util.stream.Collectors;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();
    private Random random = new Random();

    public Account createAccount(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be empty!");
        }

        String initials = name.substring(0, 2).toUpperCase();
        int randomNum = 1000 + random.nextInt(9000);
        String accNo = initials + randomNum;

        Account account = new Account(accNo, name);
        accounts.put(accNo, account);
        return account;
    }

    public void deposit(String accNo, double amount) throws AccountNotFoundException, InvalidAmountException {
        Account account = accounts.get(accNo);
        if (account == null)
            throw new AccountNotFoundException("Account not found!");
        if (amount <= 0)
            throw new InvalidAmountException("Deposit amount must be positive!");

        account.deposit(amount);
    }

    public void withdraw(String accNo, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        Account account = accounts.get(accNo);
        if (account == null)
            throw new AccountNotFoundException("Account not found!");
        if (amount <= 0)
            throw new InvalidAmountException("Withdraw amount must be positive!");
        if (account.getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient balance!");

        account.withdraw(amount);
    }

    public void transfer(String fromAcc, String toAcc, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        if (!accounts.containsKey(fromAcc) || !accounts.containsKey(toAcc))
            throw new AccountNotFoundException("Invalid account number!");

        if (amount <= 0)
            throw new InvalidAmountException("Transfer amount must be positive!");

        Account src = accounts.get(fromAcc);
        Account dest = accounts.get(toAcc);

        synchronized (this) {
            if (src.getBalance() < amount)
                throw new InsufficientBalanceException("Insufficient balance!");

            src.withdraw(amount);
            dest.deposit(amount);
        }
    }

    public Account getAccount(String accNo) throws AccountNotFoundException {
        Account acc = accounts.get(accNo);
        if (acc == null)
            throw new AccountNotFoundException("Account not found!");
        return acc;
    }

    public List<Account> searchByName(String name) {
        return accounts.values().stream()
                .filter(a -> a.getHolderName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    // Demonstration of multithreading on the same account
public void simulateConcurrentTransactions(String accNo) {
    Thread t1 = new Thread(() -> {
        try {
            deposit(accNo, 1000);  // Deposit 1000
        } catch (Exception e) {
            System.out.println("Deposit error: " + e.getMessage());
        }
    });

    Thread t2 = new Thread(() -> {
        try {
            withdraw(accNo, 500);  // Withdraw 500
        } catch (Exception e) {
            System.out.println("Withdraw error: " + e.getMessage());
        }
    });

    t1.start();
    t2.start();

    try {
        t1.join();
        t2.join();
    } catch (InterruptedException e) {
        System.out.println("Thread interrupted: " + e.getMessage());
    }

    System.out.println("✅ Concurrent transactions completed safely for account: " + accNo);
}

 
}


