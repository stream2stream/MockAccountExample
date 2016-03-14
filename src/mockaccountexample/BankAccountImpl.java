/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mockaccountexample;

/**
 *
 * @author Selvyn
 */
public class BankAccountImpl implements BankAccount
{
    private double  itsBalance;
    
    public  BankAccountImpl( double balance )
    {
        itsBalance = balance;
    }
    
    public  double  getBalance()
    {
        return itsBalance;
    }
    
    public  double  debit( double amount )
    {
        itsBalance -= amount;
        return itsBalance;
    }
    
    public  double  credit( double  amount )
    {
        itsBalance += amount;
        return itsBalance;
    }
}
