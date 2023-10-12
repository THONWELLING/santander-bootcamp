package com.thonwelling.service;

import com.thonwelling.exceptions.BusinessException;

import java.util.List;

public interface CrudService <ID, T>{

    List<T> findAll();
    T findById(ID id);
    T create(T entity) throws BusinessException;
    T update(ID id, T entity) throws BusinessException;
    void delete(ID id) throws BusinessException;
}
