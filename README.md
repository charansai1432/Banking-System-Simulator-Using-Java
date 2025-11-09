<h1><centre>Banking System Simulator (Core Java & OOPs)</centre>
</h1>

<h4>Overview :- </h4> 

<h5>
The Banking System Simulator is a beginner-friendly Java console application that simulates the core functionalities of a real-world banking system.
It demonstrates Object-Oriented Programming (OOPs) principles such as Encapsulation, Abstraction, and Inheritance, along with important Java concepts like Collections, Exception Handling, Functional Programming, and Multithreading.
</h5>

<h1>
How to Run the Project (in VS Code or Command Line)
</h1>


Step 1 — <h2>Clone the Repository<h2>

<h4>Git Clone</h4>  

        https://github.com/charansai1432/Banking-System-Simulator-Using-Java



<h4>Navigate to respective after cloning the repo</h4>

         cd banking-system/src



<h4>Step 2 — Compile the Code<h4>

(Important command to compile all java files at once)

        javac bank/model/*.java bank/exception/*.java bank/service/*.java bank/main.java



<h4>Step 3 — Run the Application

    Make sure your at cd/src   (file path)

        java bank.main

</h4>


<centre>
<h4>

You’ll see:

==== Banking System ====
1. Create Account
2. Perform Account Operations
3. Exit

</h4>
</centre>
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


