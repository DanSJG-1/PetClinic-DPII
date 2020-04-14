package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Item;

public interface ItemRepository {
	
	Collection<Item> findItemByNameInService(String item);
	
	void save(Item item) throws DataAccessException;

}