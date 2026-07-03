import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactions = new ArrayList<>();

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        transactions.add(time() + " : Account created with balance ₹" + balance);
    }

    private String time() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(time() + " : Deposited ₹" + amount);
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        transactions.add(time() + " : Withdrawn ₹" + amount);
        System.out.println("Withdrawal successful.");
    }

    public void showBalance() {
        System.out.println("Current Balance : ₹" + balance);
    }

    public void displayDetails() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Balance : ₹" + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction History");
        for (String t : transactions) {
            System.out.println(t);
        }
    }
}

public class BankingInformationSystem {
    static ArrayList<BankAccount> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static BankAccount search(int acc) {
        for (BankAccount b : accounts)
            if (b.getAccountNumber() == acc)
                return b;
        return null;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== BANKING INFORMATION SYSTEM ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Account Details");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Account Number: ");
                    int acc = sc.nextInt();
                    if (search(acc) != null) {
                        System.out.println("Account already exists.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Account Holder: ");
                    String name = sc.nextLine();
                    System.out.print("Initial Deposit: ");
                    double bal = sc.nextDouble();
                    accounts.add(new BankAccount(acc, name, bal));
                    System.out.println("Account created successfully.");
                    break;

                case 2:
                    System.out.print("Account Number: ");
                    BankAccount d = search(sc.nextInt());
                    if (d != null) {
                        System.out.print("Amount: ");
                        d.deposit(sc.nextDouble());
                    } else System.out.println("Account not found.");
                    break;

                case 3:
                    System.out.print("Account Number: ");
                    BankAccount w = search(sc.nextInt());
                    if (w != null) {
                        System.out.print("Amount: ");
                        w.withdraw(sc.nextDouble());
                    } else System.out.println("Account not found.");
                    break;

                case 4:
                    System.out.print("Account Number: ");
                    BankAccount b = search(sc.nextInt());
                    if (b != null) b.showBalance();
                    else System.out.println("Account not found.");
                    break;

                case 5:
                    System.out.print("Account Number: ");
                    BankAccount a = search(sc.nextInt());
                    if (a != null) a.displayDetails();
                    else System.out.println("Account not found.");
                    break;

                case 6:
                    System.out.print("Account Number: ");
                    BankAccount t = search(sc.nextInt());
                    if (t != null) t.showTransactions();
                    else System.out.println("Account not found.");
                    break;

                case 7:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
