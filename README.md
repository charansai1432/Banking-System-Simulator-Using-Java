<h1><centre>Banking System Simulator (Core Java & OOPs)</centre>
</h1>
Overview :-  

The Banking System Simulator is a beginner-friendly Java console application that simulates the core functionalities of a real-world banking system.
It demonstrates Object-Oriented Programming (OOPs) principles such as Encapsulation, Abstraction, and Inheritance, along with important Java concepts like Collections, Exception Handling, Functional Programming, and Multithreading.


How to Run the Project (in VS Code or Command Line)

Step 1 — Clone the Repository

git clone  

        https://github.com/charansai1432/Banking-System-Simulator-Using-Java



Navigate to respective after cloning the repo

         cd banking-system/src



Step 2 — Compile the Code

(Important command to compile all java files at once)

        javac bank/model/*.java bank/exception/*.java bank/service/*.java bank/main.java



Step 3 — Run the Application

        java bank.main


You’ll see:

==== Banking System ====
1. Create Account
2. Perform Account Operations
3. Exit


The system allows users to:

    Create new bank accounts

    Deposit and withdraw money

    Transfer funds between accounts

    Check balances

    Handle simultaneous operations safely using synchronization

    Core Features & Functionality

    
Account Creation

    The user can create a new account by entering their name.

    A unique Account Number is generated automatically using:

    initials + random 4-digit number


Banking Operations

Deposit

    Adds money to the account.
  
    Validates that the deposit amount is positive.

    Throws InvalidAmountException for invalid input.

Withdraw

    Deducts funds if there’s sufficient balance.

    Throws InsufficientBalanceException if balance is low.

    Uses try-catch-finally to ensure the balance remains consistent.

Transfer

    Transfers money between two valid accounts.

    Validates both source and destination accounts.

    Throws AccountNotFoundException if an account number is invalid.

    Uses synchronized blocks to prevent data conflicts during simultaneous operations.

Show Balance

  - Displays:

  - Account Number

  - Holder Name

  - Current Balance
  
  - Handles invalid account lookups using exceptions.

Menu-Driven Interface

Interactive console menu with options for:

==== Banking System ====
1. Create Account
2. Perform Account Operations
3. Exit


Sub-menu:

1. Deposit
2. Withdraw
3. Transfer
4. Show Balance
5. Back to Main Menu


Input validation:

Catches InputMismatchException when users enter invalid data types. (i.e user has to enter only the either alphabets or numbers According to the case)

Custom exceptions:

  -  Which provides the better error handling for the input validataion which makes the clear user experience

  -  Ensure smooth and clear user experience.


