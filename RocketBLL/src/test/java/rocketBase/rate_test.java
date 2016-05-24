package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void getRateTest() throws RateException {
		try{
			double r = RateBLL.getRate(620);
			assertEquals(r,5,0.1);
			
			r = RateBLL.getRate(820);
			//System.out.println(r);
			assertEquals(r,3.5,.01);
		}catch(Exception e){}
		
	}
	
	@Test
	public void getPaymentTest() throws RateException{
		double r = RateBLL.getRate(700)/100/12;
		double payment = RateBLL.getPayment(r, 400, 350000, 0, false);
		//System.out.println("Expected payment: "+payment);
		assertEquals(payment, 1690.0,0.01);
	}
	
	@Test(expected = RateException.class)
	public void RateExceptionTest() throws Exception{
		double r = RateBLL.getRate(1);
		System.out.println(r);
	}

}
