import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StockTradingPlatform {

    static String[] stocks = {
            "TCS",
            "Infosys",
            "Reliance",
            "HDFC Bank"
    };

    static double[] prices = {
            3500,
            1600,
            2900,
            1800
    };

    static int[] quantity = new int[4];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.println("\n===== MARKET DATA =====");

                for (int i = 0; i < stocks.length; i++) {

                    System.out.println(
                            (i + 1) + ". " +
                                    stocks[i] +
                                    " - Rs. " +
                                    prices[i]);
                }
            }

            else if (choice == 2) {

                System.out.println("\n===== BUY STOCK =====");

                for (int i = 0; i < stocks.length; i++) {

                    System.out.println(
                            (i + 1) + ". " +
                                    stocks[i] +
                                    " - Rs. " +
                                    prices[i]);
                }

                System.out.print("Select Stock: ");
                int stockChoice = sc.nextInt();

                System.out.print("Quantity: ");
                int qty = sc.nextInt();

                quantity[stockChoice - 1] += qty;

                double totalAmount =
                        prices[stockChoice - 1] * qty;

                System.out.println(
                        "Purchase Successful!");
                System.out.println(
                        "Total Amount = Rs. " +
                                totalAmount);

                try {

                    FileWriter fw =
                            new FileWriter(
                                    "portfolio.txt",
                                    true);

                    fw.write(
                            stocks[stockChoice - 1]
                                    + " | Shares: "
                                    + qty
                                    + " | Status: Purchased\n");

                    fw.close();

                } catch (IOException e) {

                    System.out.println(
                            "Error Saving Data!");
                }
            }

            else if (choice == 3) {

                System.out.println(
                        "\n===== SELL STOCK =====");

                for (int i = 0; i < stocks.length; i++) {

                    System.out.println(
                            (i + 1) + ". "
                                    + stocks[i]
                                    + " | Owned: "
                                    + quantity[i]);
                }

                System.out.print(
                        "Select Stock: ");
                int stockChoice =
                        sc.nextInt();

                System.out.print(
                        "Quantity To Sell: ");
                int qty =
                        sc.nextInt();

                if (quantity[stockChoice - 1]
                        >= qty) {

                    quantity[stockChoice - 1]
                            -= qty;

                    double sellAmount =
                            prices[stockChoice - 1]
                                    * qty;

                    System.out.println(
                            "Stock Sold Successfully!");

                    System.out.println(
                            "Amount Received = Rs. "
                                    + sellAmount);

                } else {

                    System.out.println(
                            "Insufficient Shares!");
                }
            }

            else if (choice == 4) {

                System.out.println(
                        "\n===== PORTFOLIO =====");

                double portfolioValue = 0;

                boolean found = false;

                for (int i = 0; i < stocks.length; i++) {

                    if (quantity[i] > 0) {

                        found = true;

                        double stockValue =
                                quantity[i]
                                        * prices[i];

                        portfolioValue +=
                                stockValue;

                        System.out.println(
                                stocks[i]
                                        + " | Shares: "
                                        + quantity[i]
                                        + " | Value: Rs. "
                                        + stockValue);
                    }
                }

                if (!found) {

                    System.out.println(
                            "Portfolio Empty!");
                }

                System.out.println(
                        "Total Portfolio Value = Rs. "
                                + portfolioValue);
            }

            else if (choice == 5) {

                System.out.println(
                        "Thank You!");
                break;
            }

            else {

                System.out.println(
                        "Invalid Choice!");
            }
        }

        sc.close();
    }
}