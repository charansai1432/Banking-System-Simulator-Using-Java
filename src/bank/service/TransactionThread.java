package bank.service;

public class TransactionThread extends Thread {
    private BankService bankService;
    private String accountNumber;
    private double amount;
    private boolean deposit;

    public TransactionThread(BankService bankService, String accountNumber, double amount, boolean deposit) {
        this.bankService = bankService;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.deposit = deposit;
    }
// ///

    public void run() {
        try {
            if (deposit) {
                bankService.deposit(accountNumber, amount);
                System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
            } else {
                bankService.withdraw(accountNumber, amount);
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
            }
        } catch (Exception e) {
            System.out.println("Error in thread: " + e.getMessage());
        }
    }
}
