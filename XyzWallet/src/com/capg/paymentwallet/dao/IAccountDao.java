package com.capg.paymentwallet.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;

public interface IAccountDao {


    public boolean createAccount(AccountBean accountBean) throws Exception ;
    public boolean deposit(AccountBean accountBean,double depositAmount) throws Exception ;
    public boolean withdraw(AccountBean accountBean,double withdrawAmount) throws Exception;
    public boolean fundTransfer(AccountBean transferingAccountBean, AccountBean beneficiaryAccountBean, double transferAmount) throws Exception ;
    public boolean updateAccount(AccountBean accountBean) throws Exception;
    public AccountBean findAccount(int accId) throws Exception;
  
  
	
	 
    
}
