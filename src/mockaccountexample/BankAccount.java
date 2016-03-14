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
public interface BankAccount
{
    public  double  getBalance();
    public  double  debit( double amount );
    public  double  credit( double  amount );
}
