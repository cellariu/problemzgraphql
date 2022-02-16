package com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Userz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserzRepository extends CrudRepository<Userz, UUID> {

    Optional<Userz> findByUsernameIgnoreCase(String username);

    // select * from userz u inner join userz_token ut on u.id = ut.user_id where ut.auth_token = ? and ut.expiry_timestamp > current_timestamp

    @Query(nativeQuery = true,
            value = "select * from userz u inner join userz_token ut on u.id = ut.user_id " +
                    "where ut.auth_token = ? and ut.expiry_timestamp > current_timestamp")
    Optional<Userz> findUserByToken(String authToken);

}
