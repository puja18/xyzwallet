package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.mysql.jdbc.BalanceStrategy;

public class AccountDAOImpl implements IAccountDao {

	EntityManager em;
	
	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try{
			
			this.em=JPAManager.createEntityManager();
			
			em.getTransaction().begin();
			
			em.persist(accountBean);
			
			em.getTransaction().commit();
		

			JPAManager.closeResources(em);
			return true;
			
		} catch(Exception e) {
			//return false;
			e.printStackTrace();
		}
		return false;
	
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.merge(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			return false;
		}
	
	}


	@Override
	public AccountBean findAccount(int accId) throws Exception {
		// TODO Auto-generated method stub
		try{
			em=JPAManager.createEntityManager();
			AccountBean accountBean2=em.find(AccountBean.class,accId);
			JPAManager.closeResources(em);
			return accountBean2;
			
		}catch(Exception e){
			return null;
		}
		

  }

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		// TODO Auto-generated method stub
		double totBalance = accountBean.getBalance()+depositAmount;
		accountBean.setBalance(totBalance);
		updateAccount(accountBean);
		return true;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		// TODO Auto-generated method stub
		double totBalance = accountBean.getBalance()-withdrawAmount;
		accountBean.setBalance(totBalance);
		updateAccount(accountBean);
		return true;
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount)
			throws Exception {
		// TODO Auto-generated method stub
		double totBalance = transferingAccountBean.getBalance()-transferAmount;
		transferingAccountBean.setBalance(totBalance);
		double totBalance1 = beneficiaryAccountBean.getBalance()+transferAmount;
		beneficiaryAccountBean.setBalance(totBalance1);
		updateAccount(transferingAccountBean);
		updateAccount(beneficiaryAccountBean);
		return true;
		
		
	}
}
