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
public class MockAccountExample
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        MockBankAccountTest test = new MockBankAccountTest();
        
        test.initialize();
        test.failExecutionIfBalanceInadequate();
        test.testDebitAccontWithSeveralGetBalanceCalls();
        test.methodThatWeExpectWillThrowAnException();
    }
    
}
