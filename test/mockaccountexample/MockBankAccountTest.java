package mockaccountexample;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;
 
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
 
@RunWith(JMock.class)
public class MockBankAccountTest 
{
    public static junit.framework.Test suite() 
    {
        return new JUnit4TestAdapter(MockBankAccountTest.class);
    }
 
    CreditAccount creditAcc;
    Mockery context = new JUnit4Mockery();
 
    BankAccount accountHandler;
 
    @Before
    public void initialize() 
    {
        accountHandler = context.mock(BankAccount.class);
        creditAcc = new CreditAccount(accountHandler);
    }
 
    @Test
    public void failExecutionIfBalanceInadequate() 
    {
        double  result = 0;
        context.checking(new Expectations() 
        {
            {
                oneOf(accountHandler).getBalance();
                will(returnValue(0.00));
            }
        });
 
        boolean exceptionThrown = false;
 
        try 
        {
            result = creditAcc.getBalance();
        } 
        catch (InsufficientFunds e)
        {
            // success
            exceptionThrown = true;
        }
        
        assertEquals(0.0, result, 0.0);
        assertTrue("Could have thrown: " + InsufficientFunds.class, !exceptionThrown);
    }
 
    @Test
    public void testDebitAccontWithSeveralGetBalanceCalls() 
    {
        double  balance = 0;
        
        context.checking(new Expectations() 
        {
            {
                /*
                 * using this sequence is not good, it states that there will only ever be
                 * 3 calls to getBalance against the accountHandler, each returning the same value
                 *
                 *   exactly(3).of(accountHandler).getBalance();
                 *   will( returnValue(2500.00));
                 *
                 * Using consecutive calls to will( returnValue(...) ) doesn't work
                 * It simply states the return value for the operation to be called 
                 * will be last will(...) value
                 *
                 * So
                 *   exactly(3).of(accountHandler).getBalance();
                 *   will( returnValue(2500.00));
                 *   will( returnValue(1000.00));
                 * Would cause a value of 1000.00 to be returned from each getBalance()
                 */
                
                oneOf(accountHandler).getBalance();
                will(returnValue(2500.00));
                
                oneOf(accountHandler).getBalance();
                will( returnValue(2500.00));
                oneOf(accountHandler).debit(with(1500.00));
                will( returnValue( 1000.00 ));
                oneOf(accountHandler).getBalance();
                will( returnValue(1000.00));
            }
        });
 
        balance = creditAcc.getBalance();
        assertEquals( 2500.00, balance, 0.0 );
        balance = creditAcc.debit(1500.00);
        assertEquals( 1000.00, balance, 0.0 );
 
        assertEquals(true, creditAcc.isExecuted());
    }
 
    @Test
    public void methodThatWeExpectWillThrowAnException() 
    {
        boolean expectedThrown = false;
 
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            expectedThrown = true;
        }
 
        assertTrue(expectedThrown);
    }
}
