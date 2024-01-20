import java.util.*;
class shoppingCart {
    public void shop() {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> items = new ArrayList<>(Arrays.asList("Bread", "Butter", "Jam", "Cheese", "Milk"));
        ArrayList<Integer> prices = new ArrayList<>(Arrays.asList(15, 30, 45, 50, 60));

        String[][] cart = new String[5][3];
        System.out.println("Welcome!");
        System.out.println("Please enter your full name: ");
        String name = sc.nextLine();
        System.out.println("Please enter your address for delivery: ");
        String address = sc.nextLine();
        Map<String, Integer> itemPricesMap = convertToHashMap(items, prices);

        while (true) {
            System.out.println(name + "                   " + address);
            System.out.println(itemPricesMap);
            System.out.println("1. Add Bread.");
            System.out.println("2. Add Butter.");
            System.out.println("3. Add Jam");
            System.out.println("4. Add Cheese");
            System.out.println("5. Add Milk");
            System.out.println("6. Remove Bread.");
            System.out.println("7. Remove Butter.");
            System.out.println("8. Remove Jam");
            System.out.println("9. Remove Cheese");
            System.out.println("10. Remove Milk");
            System.out.println("11. Checkout");

            int choice = sc.nextInt();

            if (choice >= 1 && choice <= 5) {
                int index = choice - 1;
                if (cart[index][0] != null && cart[index][1] != null && cart[index][2] != null) {
                    cart[index][1] = Integer.toString(Integer.parseInt(cart[index][1]) + 1);
                    cart[index][2] = Integer.toString(Integer.parseInt(cart[index][2]) + prices.get(index));
                } else {
                    cart[index][0] = items.get(index);
                    cart[index][1] = "1";
                    cart[index][2] = Integer.toString(prices.get(index));
                }
            } else if (choice >= 6 && choice <= 10) {
                int index = choice - 6;
                if (cart[index][0] != null && cart[index][1] != null && cart[index][2] != null) {
                    cart[index][1] = Integer.toString(Integer.parseInt(cart[index][1]) - 1);
                    cart[index][2] = Integer.toString(Integer.parseInt(cart[index][2]) - prices.get(index));
                    if (cart[index][1].equals("0")) {
                        cart[index][0] = null;
                    }
                }
            } else if (choice == 11) {
                int total = 0;
                for (String[] item : cart) {
                    if (item[0] != null) {
                        System.out.println("Item: " + item[0] + ", Quantity: " + item[1] + " Price: " + item[2]);
                        total += Integer.parseInt(item[2]);
                    }
                }
                System.out.println("Total Price: " + total);
                break;
            } else {
                System.out.println("Invalid Choice.");
            }
        }
    }

    public static <K, V> Map<K, V> convertToHashMap(ArrayList<K> keys, ArrayList<V> values) {
        Map<K, V> hashMap = new HashMap<>();
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                hashMap.put(keys.get(i), values.get(i));
            }
        } else {
            System.out.println("Please map the sizes to match the number of items.");
        }

        return hashMap;
    }
}

interface RBI {
    void calculateInterest();
}

class BankAccount {
    Scanner sc = new Scanner(System.in);
    int accNum;
    String name, mobile;
    int balance;

    public BankAccount() {
        balance = 1000;
    }

    public void inputData() {
        System.out.print("Enter the name of the person: ");
        name = sc.next();
        System.out.print("Enter the phone number of the person: ");
        mobile = sc.next();
        System.out.print("Enter the account number of the person: ");
        accNum = sc.nextInt();
        System.out.print("Enter the balance of the account: ");
        balance = sc.nextInt();
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Mobile Number: " + mobile);
        System.out.println("Account Number: " + accNum);
        System.out.println("Balance: " + balance);
    }

    public void deposit() {
        System.out.println("Enter the amount you want to deposit: ");
        int depo = sc.nextInt();
        balance = balance + depo;
        System.out.println("Updated bank balance: " + balance);
    }

    public void withdraw() {
        System.out.println("Enter the amount to withdraw: ");
        int amount = sc.nextInt();
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println("The balance left after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }
}

class SavingAccount extends BankAccount implements RBI {
    public void withdraw() {
        if (balance >= 1000) {
            super.withdraw();
        } else {
            System.out.println("The balance is less than 1000. Withdrawal not allowed.");
        }
    }

    public void calculateInterest() {
        double interest = 0.04 * balance;
        System.out.println("Interest earned per year: " + interest);
    }
}

class CurrentAccount extends BankAccount implements RBI {
    public void calculateInterest() {
        double interest = 0.02 * balance;
        System.out.println("Interest earned per year: " + interest);
    }
}

class BankOp {
    public static void banking(){
        SavingAccount sa = new SavingAccount();
        CurrentAccount ca = new CurrentAccount();
        RBI rbiSA = sa;
        RBI rbiCA = ca;

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean saving = false;
        boolean current = false;

        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int accountType = sc.nextInt();

        if (accountType == 1) {
            saving = true;
        } else if (accountType == 2) {
            current = true;
        } else {
            System.out.println("Invalid choice. Exiting.");
            System.exit(0);
        }

        if (saving) {
            while (choice != 5) {
                System.out.println("1. Input details for Saving Account");
                System.out.println("2. Show details for Saving Account");
                System.out.println("3. Withdraw from Saving Account");
                System.out.println("4. Deposit into Saving Account");
                System.out.println("5. Exit");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sa.inputData();
                        break;
                    case 2:
                        sa.display();
                        break;
                    case 3:
                        sa.withdraw();
                        break;
                    case 4:
                        sa.deposit();
                        break;
                    case 5:
                        System.out.println("Exiting the System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

            System.out.println("Calculating interest for Saving Account:");
            rbiSA.calculateInterest();
        }

        choice = 0;

        if (current) {
            while (choice != 5) {
                System.out.println("1. Input details for Current Account");
                System.out.println("2. Show details for Current Account");
                System.out.println("3. Withdraw from Current Account");
                System.out.println("4. Deposit into Current Account");
                System.out.println("5. Exit");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ca.inputData();
                        break;
                    case 2:
                        ca.display();
                        break;
                    case 3:
                        ca.withdraw();
                        break;
                    case 4:
                        ca.deposit();
                        break;
                    case 5:
                        System.out.println("Exiting the System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

            System.out.println("Calculating interest for Current Account:");
            rbiCA.calculateInterest();
        }
    }
}
public class finall{    
    public static void main(String [] args){
        BankOp bank = new BankOp ();
        shoppingCart shoping =new shoppingCart();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("1. Banking System.");
        System.out.println("2. Shopping Cart");
        int ch = sc.nextInt();
        if (ch ==1){
            bank.banking();
        }else if (ch ==2) {
            shoping.shop();
        }
    }
}
