package com.persistencetier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.to.BillTO;
import com.to.SchemeTO;

public class BillService {

	public List<String> getSchemeList(String mobileOperator) throws Exception{
		System.out.println("Mobile in ser : "+mobileOperator);
		List<String> schemeList = null;
		EntityManager em = null;
		EntityTransaction et = null;
		EntityManagerFactory emf = null;
		try 
		{
			emf = Persistence.createEntityManagerFactory("MysoreOne");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			Query query = em
					.createQuery("SELECT s.schemeId from SchemeEntity s WHERE s.mobileOperator=?1");
			query.setParameter(1, mobileOperator);
			schemeList = query.getResultList();
			for (String string : schemeList) {
				System.out.println(string+" :: in service");
			}
			et.commit();			
		}
		catch (Exception ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw new Exception("Service.TECHNICAL_ERROR");
		}
		finally
		{
			emf.close();
		}
		return schemeList;
	}
	public SchemeTO getSchemeDetails(String schemeId) throws Exception
	{
		SchemeTO sto = new SchemeTO();
		EntityManager em = null;
		EntityTransaction et = null;
		EntityManagerFactory emf = null;
		try 
		{
			emf = Persistence.createEntityManagerFactory("MysoreOne");
			em = emf.createEntityManager();
			SchemeEntity se = em.find(SchemeEntity.class, schemeId);
			if(se == null)
			{
				throw new Exception("Service.INVALID_SCHEMEID");
			}
			sto.setMobileOperator(se.getMobileOperator());
			sto.setRechargeAmount(se.getRechargeAmount());
			sto.setSchemeId(se.getSchemeId());						
		}
		catch (PersistenceException ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw new Exception("Service.TECHNICAL_ERROR");
		}
		catch (Exception ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally
		{
			emf.close();
		}
		return sto;
	}
	
	public Integer saveBill(BillTO billTO) throws Exception
	{
		EntityManager em = null;
		EntityTransaction et = null;
		EntityManagerFactory emf = null;
		try 
		{
			emf = Persistence.createEntityManagerFactory("MysoreOne");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
				BillEntity billEntity = new BillEntity();
				billEntity.setName(billTO.getName());
				billEntity.setAmount(billTO.getAmount());
				billEntity.setBillId(billTO.getBillId());
				billEntity.setDateOfPayment(billTO.getDateOfPayment());
				billEntity.setMobileNumber(billTO.getMobileNumber());
				billEntity.setMobileOperator(billTO.getMobileOperator());
				billEntity.setTypeOfConnection(billTO.getTypeOfConnection());
			em.persist(billEntity);
			et.commit();
			return billEntity.getBillId();
		}
		catch (PersistenceException ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw new Exception("Service.TECHNICAL_ERROR");
		}
		catch (Exception ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally
		{
			emf.close();
		}
	}
	public List<BillTO> getBillReport(Calendar fromDate, Calendar toDate) throws Exception
	{
		List<BillTO> billToList = new ArrayList<BillTO>();
		EntityManager em = null;
		EntityTransaction et = null;
		EntityManagerFactory emf = null;
		try 
		{
			emf = Persistence.createEntityManagerFactory("MysoreOne");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
				//BillEntity billEn = new BillEntity();
				Query query = em
						.createQuery("SELECT b from BillEntity b where b.dateOfPayment BETWEEN ?1 and ?2");
				query.setParameter(1, fromDate);
				query.setParameter(2, toDate);
			List<BillEntity> billEntityList = query.getResultList();
			for (BillEntity billEntity : billEntityList) {
				BillTO billTO = new BillTO();
				billTO.setName(billEntity.getName());
				billTO.setAmount(billEntity.getAmount());
				billTO.setBillId(billEntity.getBillId());
				billTO.setDateOfPayment(billEntity.getDateOfPayment());
				billTO.setMobileNumber(billEntity.getMobileNumber());
				billTO.setMobileOperator(billEntity.getMobileOperator());
				billTO.setTypeOfConnection(billEntity.getTypeOfConnection());
				billToList.add(billTO);
			}			
			et.commit();
			return billToList;
		}
		catch (PersistenceException ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw new Exception("Service.TECHNICAL_ERROR");
		}
		catch (Exception ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally
		{
			emf.close();
		}
	}
	
	public List<String> getAllSchemes() throws Exception
	{
		List<String> schemeList = null;
		EntityManager em = null;
		EntityTransaction et = null;
		EntityManagerFactory emf = null;
		try 
		{
			emf = Persistence.createEntityManagerFactory("MysoreOne");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
				Query query = em
						.createQuery("SELECT s.schemeId from SchemeEntity s");
				schemeList = query.getResultList();
				if(schemeList == null)
					throw new Exception("Service.NO_SCHEMES");				
			et.commit();		
			return schemeList;
		}
		catch (PersistenceException ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw new Exception("Service.TECHNICAL_ERROR");
		}
		catch (Exception ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally
		{
			emf.close();
		}
	}
	
	public String updateScheme(SchemeTO sto) throws Exception
	{
		String schemeId = null;
		EntityManager em = null;
		EntityManagerFactory emf = null;
		try 
		{
			emf = Persistence.createEntityManagerFactory("MysoreOne");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			SchemeEntity se = em.find(SchemeEntity.class, sto.getSchemeId());
			if(se == null)
			{
				throw new Exception("Service.INVALID_SCHEMEID");
			}
			se.setRechargeAmount(sto.getRechargeAmount());
			schemeId = se.getSchemeId();			
			em.getTransaction().commit();
		}
		catch (PersistenceException ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw new Exception("Service.TECHNICAL_ERROR");
		}
		catch (Exception ex) 
		{
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally
		{
			emf.close();
		}
		return schemeId;
	}

}
