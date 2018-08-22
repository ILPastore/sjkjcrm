package com.sjkjcrm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @author: xianyunpeng
 */


@NoRepositoryBean
public interface BaseRepository<T,I extends Serializable> extends PagingAndSortingRepository<T,I>,JpaSpecificationExecutor<T> {

}


