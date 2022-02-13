package com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Userz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserzRepository extends CrudRepository<Userz, UUID> {

}