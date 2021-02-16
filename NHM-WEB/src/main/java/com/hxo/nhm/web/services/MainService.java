package com.hxo.nhm.web.services;

import java.util.List;
import java.io.Serializable;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

public interface MainService <T extends Serializable> {
	
	public abstract void save (T t);

	public abstract void update (T t);
	
	public abstract void delete (T t);
	
	public abstract T findByID (String id);
	
	public abstract List<T> findAll ();
	
	public abstract long findAllCount ();
	
	public abstract void verify (T t);
	
}
