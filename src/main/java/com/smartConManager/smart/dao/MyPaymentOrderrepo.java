package com.smartConManager.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartConManager.smart.model.MyOrder;

public interface MyPaymentOrderrepo extends JpaRepository<MyOrder, Long> {

}
