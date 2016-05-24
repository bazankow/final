package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();
		rates = RateDAL.getAllRates();
		double best_rate = -1;
		for(RateDomainModel rate : rates){
			if(rate.getiMinCreditScore() < GivenCreditScore){
				//System.out.println("Possible Rate is: " + rate.getdInterestRate());
				best_rate = rate.getdInterestRate();
			}
		}
		if(best_rate < 0)
		{
			throw new RateException(GivenCreditScore);
		}
		return best_rate;
		
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		double payment = FinanceLib.pmt(r, n, p, f, t)*-100;
		payment = Math.round(payment)/100;
		return payment;
	}
}
