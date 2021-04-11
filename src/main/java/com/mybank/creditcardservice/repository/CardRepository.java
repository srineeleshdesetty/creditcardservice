package com.mybank.creditcardservice.repository;

import com.mybank.creditcardservice.entity.CreditCardEntity;
import com.mybank.creditcardservice.model.CardDetail;
import com.mybank.creditcardservice.model.CustomerID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public interface CardRepository extends CrudRepository<CreditCardEntity, Long> {

    @Query("select c from CreditCardEntity c where c.customerId = ?1")
    List<CreditCardEntity> findByCustomerId(long customerId);
}
