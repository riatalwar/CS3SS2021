public class BankTest
{
  public static void main(String[] args)
  {
    BankAccount savings = new BankAccount("Jimmy");
    savings.deposit(10.00);
    savings.deposit(50.00);
    savings.deposit(10.00);
    savings.deposit(70.00);

    System.out.println(savings.transactionFee(5.00));//true
    System.out.println(savings.getBalance() == 90);
    System.out.println(savings.transactionFee(10.00));//false
    System.out.println(savings.getBalance() == 0);
  }
}
