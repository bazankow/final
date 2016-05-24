package rocketBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import rocketDomain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {

	public static ArrayList<RateDomainModel> getAllRates()
	{		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		ArrayList<RateDomainModel> alRates = new ArrayList<RateDomainModel>();		
		
		try {
			tx = session.beginTransaction();	
			
			//TODO - RocketDALRateDAL.getAllRates
			//			probably not a bad idea to sort the results...  Add an OrderBy
			//			example can be found here:
			//  		http://www.tutorialspoint.com/hibernate/hibernate_query_language.htm			
			List lstRates = session.createQuery("FROM RateDomainModel ORDER BY iRateID ASC").list();
			//System.out.println("lstRates size: "+lstRates.size());
			for (Iterator iterator = lstRates.iterator(); iterator.hasNext();) {
				RateDomainModel rte = (RateDomainModel) iterator.next();
				alRates.add(rte);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println(alRates.size());
		return alRates;
	}

}