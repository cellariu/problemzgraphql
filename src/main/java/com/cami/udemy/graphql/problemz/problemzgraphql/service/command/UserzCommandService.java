package com.cami.udemy.graphql.problemz.problemzgraphql.service.command;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.UserzToken;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.UserzRepository;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.UserzTokenRepository;
import com.cami.udemy.graphql.problemz.problemzgraphql.util.HashUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserzCommandService {

    @Autowired
    private UserzRepository userzRepository;

    @Autowired
    private UserzTokenRepository userzTokenRepository;

    public UserzToken login(String username, String password) {

        var userz = userzRepository.findByUsernameIgnoreCase(username);

        if (userz.isEmpty() || !HashUtil.isBcryptMatch(password, userz.get().getHashedPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var randomAuthToken = RandomStringUtils.randomAlphanumeric(40);

        var userEntity = userz.get();
        UserzToken newlyCreatedUserzToken = refreshToken(userEntity.getId(), randomAuthToken);
        return newlyCreatedUserzToken;
    }

    private UserzToken refreshToken(UUID userId, String authToken) {
        var userzToken = new UserzToken();

        userzToken.setUserId(userId);
        userzToken.setAuthToken(authToken);

        var now = LocalDateTime.now();
        userzToken.setCreateTimestamp(now);
        userzToken.setExpiryTimestamp(now.plusHours(2));

        return userzTokenRepository.save(userzToken);
    }
}
