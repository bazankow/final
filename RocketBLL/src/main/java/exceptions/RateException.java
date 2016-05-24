package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	private RateDomainModel rate_model;

	public RateException(int score) {
		super();
		rate_model = new RateDomainModel();
		rate_model.setiMinCreditScore(score);
	}	
	
	public RateException() {
		super();
	}	

	public RateDomainModel getRate_model() {
		return rate_model;
	}
	
	
}
