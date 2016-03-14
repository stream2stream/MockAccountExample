/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mockaccountexample;

public class CreditAccount implements BankAccount
{
    private BankAccount itsParent;
    public boolean executed;    // use this function to prove that certain methods have been called
 
    CreditAccount( BankAccount parent )
    {
        itsParent = parent;
    }
 
    public void setAccount(BankAccount parent) 
    {
        itsParent = parent;
    }
 
    @Override
    public double getBalance()
    {
        return itsParent.getBalance();
    }
 
    @Override
    public  double   debit( double amt ) 
    {
        double bal = itsParent.getBalance();
        if (bal - amt < 0) 
        {
            throw new InsufficientFunds();
        }
        itsParent.debit(amt);
        executed = true;
        
        return itsParent.getBalance();
    }
    
    @Override
    public  double  credit( double amt )
    {
        itsParent.credit(amt);
        executed = true;
        
        return itsParent.getBalance();
    }
 
    public boolean isExecuted() 
    {
        return executed;
    }
}
