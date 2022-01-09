// Name: Ria Talwar
// Date: July 12, 2021

// A BankAccount keeps track of a user's money balance and ID
// and counts how many transactions (deposits/withdrawals) are made.
public class BankAccount
{
  private String id;
  private double balance;
  private int transactions;

  // Constructs a BankAccount object with the given id, and
  // 0 balance and transactions
  public BankAccount(String id)
  {
    id = id;
    balance = 0;
    transactions = 0;
  }

  // Returns the field values
  public double getBalance()
  {
    return balance;
  }

  public String getID()
  {
    return id;
  }

  public int getTransactions()
  {
    return transactions;
  }

  // Adds the amount to the balance if it between 0 - 500.
  // Also counts as 1 transaction.
  public void deposit(double amount)
  {
    transactions++;
    if (amount > 0 && amount < 500) balance += amount;
  }

  // Subtracts the amount from the balance if the user has enough money.
  // Also counts as 1 transaction.
  public void widthdraw(double amount)
  {
    transactions++;
    if (balance >= amount) balance -= amount;
  }

  public boolean transactionFee(double fee)
  {
    for (int i = 0; i <= transactions; i++)
    {
      if (i * fee > balance)
      {
        balance = 0;
        return false;
      }
      balance -= (i * fee);
    }
    return true;
  }
}
