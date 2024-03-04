package Practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// User class to represent each user of the ATM
class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

// ATM class to encapsulate ATM functionalities
class ATM {
    private Map<String, User> users;
    private User currentUser;

    public ATM() {
        users = new HashMap<>();
        // Add some dummy users
        users.put("user1", new User("user1", "1234", 1000.0));
        users.put("user2", new User("user2", "5678", 2000.0));
    }

    public boolean authenticateUser(String userID, String userPIN) {
        User user = users.get(userID);
        if (user != null && user.getUserPIN().equals(userPIN)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return currentUser.getAccountBalance();
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
        }
    }
}

public class ATMInterface {
    private static ATM atm = new ATM();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM interface!");
        System.out.println("Please enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.println("Please enter your PIN: ");
        String userPIN = scanner.nextLine();

        if (atm.authenticateUser(userID, userPIN)) {
            System.out.println("Authentication successful!");
            showMainMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.println("Please select an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM interface. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        double balance = atm.checkBalance();
        System.out.println("Your current balance is: $" + balance);
    }

    private static void withdrawMoney() {
        System.out.println("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (atm.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed. Please check your balance and try again.");
        }
    }

    private static void depositMoney() {
        System.out.println("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        atm.deposit(amount);
        System.out.println("Deposit successful.");
    }
}
