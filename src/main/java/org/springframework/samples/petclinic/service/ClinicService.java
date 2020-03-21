/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.security.acl.Owner;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Request;
import org.springframework.samples.petclinic.model.Residence;
import org.springframework.samples.petclinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicService {

	private ClinicRepository clinicRepository;

	@Autowired
	public ClinicService(ClinicRepository clinicRepository) {
		this.clinicRepository = clinicRepository;
	}	
	
	@Transactional(readOnly = true)
	public Clinic findClinicById(int id) throws DataAccessException {
		return clinicRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Clinic findClinicByRequest(Request request) throws DataAccessException {
		Collection<Clinic> clinics = this.clinicRepository.findAll();
		for(Clinic c: clinics) {
			if(c.getRequestById(request.getId())!= null) return c;
		}
		return null;
	}
	
	@Transactional
	public Iterable<Clinic> findAll(){
		return clinicRepository.findAll();
	}

}
