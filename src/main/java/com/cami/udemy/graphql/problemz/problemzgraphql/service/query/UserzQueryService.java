package com.cami.udemy.graphql.problemz.problemzgraphql.service.query;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Userz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.UserzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserzQueryService {

    @Autowired
    private UserzRepository userzRepository;

    public Optional<Userz> findUserzByAuthToken(String authToken) {
        return userzRepository.findUserByToken(authToken);
    }
}
