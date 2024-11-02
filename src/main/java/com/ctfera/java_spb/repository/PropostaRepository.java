package com.ctfera.java_spb.repository;

import com.ctfera.java_spb.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {


    List<Proposta> findAllByIntegradaIsFalse();

}
