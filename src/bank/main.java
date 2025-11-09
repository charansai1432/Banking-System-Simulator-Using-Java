package bank;

import bank.service.BankService;
import bank.model.Account;
import bank.exception.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();

        while (true) {
            System.out.println("\n==== Banking System ====");
            System.out.println("1. Create Account");
            System.out.println("2. Perform Account Operations");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = readInt(sc);
                validateMainChoice(choice);

                switch (choice) {
                    case 1:
                        createAccountFlow(sc, bankService);
                        break;

                    case 2:
                        performAccountOperationsFlow(sc, bankService);
                        break;

                    case 3:
                        System.out.println("Thank you for banking with us!");
                        sc.close();
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input type. Please enter numbers only.");
                sc.nextLine(); // clear invalid input
            } catch (InvalidChoiceException ice) {
                System.out.println("Invalid choice: " + ice.getMessage());
            } catch (Exception e) {
                // Catch unexpected exceptions so the program doesn't crash during evaluation
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // readInt: reads an int and throws InputMismatchException if not an int
    private static int readInt(Scanner sc) throws InputMismatchException {
        try {
            int val = sc.nextInt();
            sc.nextLine(); // consume newline
            return val;
        } catch (InputMismatchException ime) {
            throw ime;
        }
    }

    private static double readDouble(Scanner sc) throws InputMismatchException {
        try {
            double val = sc.nextDouble();
            sc.nextLine(); // consume newline
            return val;
        } catch (InputMismatchException ime) {
            throw ime;
        }
    }

    // Validate main menu choices 1..3
    private static void validateMainChoice(int choice) throws InvalidChoiceException {
        if (choice < 1 || choice > 3) {
            throw new InvalidChoiceException("Please choose 1, 2, or 3.");
        }
    }

    // Validate account operations menu choices 1..5
    private static void validateAccountChoice(int choice) throws InvalidChoiceException {
        if (choice < 1 || choice > 5) {
            throw new InvalidChoiceException("Please choose between 1 and 5.");
        }
    }

    // Flow to create account (with InvalidNameException handling)
    private static void createAccountFlow(Scanner sc, BankService bankService) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        try {
            Account acc = bankService.createAccount(name);
            System.out.println("Account created! Account Number: " + acc.getAccountNumber());

            // optional: demonstrate concurrent transactions automatically (comment out if not wanted)
            // bankService.simulateConcurrentTransactions(acc.getAccountNumber());

        } catch (InvalidNameException ine) {
            System.out.println("Invalid name: " + ine.getMessage());
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    // Flow to perform operations on an existing account
    private static void performAccountOperationsFlow(Scanner sc, BankService bankService) {
        System.out.print("Enter account number: ");
        String accNo = sc.nextLine();

        try {
            // Validate account existence (this will throw AccountNotFoundException if not found)
            Account acc = bankService.getAccount(accNo);

            boolean back = false;
            while (!back) {
                System.out.println("\n--- Account Menu ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Show Balance");
                System.out.println("5. Back to Main Menu");
                System.out.print("Enter choice: ");

                try {
                    int op = readInt(sc);
                    validateAccountChoice(op);

                    switch (op) {
                        case 1: // Deposit
                            System.out.print("Enter amount to deposit: ");
                            try {
                                double amt = readDouble(sc);
                                bankService.deposit(accNo, amt);
                                System.out.println("Deposit successful!");
                            } catch (InputMismatchException ime) {
                                System.out.println("Invalid amount. Enter a numeric value.");
                                sc.nextLine();
                            }
                            break;

                        case 2: // Withdraw
                            System.out.print("Enter amount to withdraw: ");
                            try {
                                double wamt = readDouble(sc);
                                bankService.withdraw(accNo, wamt);
                                System.out.println("Withdrawal successful!");
                            } catch (InputMismatchException ime) {
                                System.out.println("Invalid amount. Enter a numeric value.");
                                sc.nextLine();
                            } catch (InsufficientBalanceException ibe) {
                                System.out.println("Withdrawal failed: " + ibe.getMessage());
                            }
                            break;

                        case 3: // Transfer
                            System.out.print("Enter destination account number: ");
                            String dest = sc.nextLine();
                            System.out.print("Enter amount to transfer: ");
                            try {
                                double tamt = readDouble(sc);
                                bankService.transfer(accNo, dest, tamt);
                                System.out.println("Transfer successful!");
                            } catch (InputMismatchException ime) {
                                System.out.println("Invalid amount. Enter a numeric value.");
                                sc.nextLine();
                            } catch (AccountNotFoundException anf) {
                                System.out.println("Transfer failed: " + anf.getMessage());
                            } catch (InsufficientBalanceException ibe) {
                                System.out.println("Transfer failed: " + ibe.getMessage());
                            } catch (InvalidAmountException iam) {
                                System.out.println("Transfer failed: " + iam.getMessage());
                            }
                            break;

                        case 4: // Show Balance
                            Account updated = bankService.getAccount(accNo);
                            System.out.println("Account Number: " + updated.getAccountNumber());
                            System.out.println("Holder Name: " + updated.getHolderName());
                            System.out.println("Balance: " + updated.getBalance());
                            break;

                        case 5:
                            back = true;
                            break;
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("Invalid input. Please enter numbers only.");
                    sc.nextLine();
                } catch (InvalidChoiceException ice) {
                    System.out.println("Invalid menu choice: " + ice.getMessage());
                } catch (AccountNotFoundException anf) {
                    // Shouldn't normally happen here because we validated earlier, but handle just in case
                    System.out.println("Account error: " + anf.getMessage());
                    back = true;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        } catch (AccountNotFoundException anf) {
            System.out.println("Account not found: " + anf.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
