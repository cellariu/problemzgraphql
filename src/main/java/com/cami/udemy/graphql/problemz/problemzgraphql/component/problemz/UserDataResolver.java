package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Userz;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.command.UserzCommandService;
import com.cami.udemy.graphql.problemz.problemzgraphql.service.query.UserzQueryService;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.*;
import com.cami.udemy.graphql.problemz.problemzgraphql.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@DgsComponent
public class UserDataResolver {

    @Autowired
    private UserzCommandService userzCommandService;

    @Autowired
    private UserzQueryService userzQueryService;


    @DgsData(parentType = "Query", field = "me")
    public User accountInfo(@RequestHeader(name = "authToken", required = true) String authToken) {
        var userz = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(DgsEntityNotFoundException::new);
        return GraphqlBeanMapper.mapToGrapghql(userz);
    }

    @DgsData(parentType = "Mutation", field = "userCreate")
    public UserResponse createUser(@InputArgument(name = "user") UserCreateInput userCreateInput) {

        var userz = GraphqlBeanMapper.mapToEntity(userCreateInput);

        var createdUserz =  userzCommandService.createUserz(userz);

        var userResponse = UserResponse.builder()
                .user(GraphqlBeanMapper.mapToGrapghql(createdUserz))
                .build();

        return userResponse;
    }

    @DgsData(parentType = "Mutation", field = "userLogin")
    public UserResponse loginUser(@InputArgument(name = "user") UserLoginInput userLoginInput) {

        var generatedToken = userzCommandService.login(userLoginInput.getUsername(), userLoginInput.getPassword());

        var userAuthToken = GraphqlBeanMapper.mapToGrapghql(generatedToken);

        var userInfo = accountInfo(userAuthToken.getAuthToken());

        var userResponse = UserResponse.builder()
                .user(userInfo)
                .authToken(userAuthToken)
                .build();
        return userResponse;
    }

    @DgsData(parentType = "Mutation", field = "userActivation")
    public UserActivationResponse activateUser(@InputArgument(name = "user") UserActivationInput userActivationInput) {

        Optional<Userz> userz = userzCommandService.activateUser(
                userActivationInput.getUsername(), userActivationInput.getActive());

        if (userz.isEmpty()) {
            throw new DgsEntityNotFoundException("User " + userActivationInput.getUsername() + " could not be found in DB!");
        }

        var userActivationResponse = UserActivationResponse.builder()
                .isActive(userz.get().getActive())
                .build();

        return userActivationResponse;
    }
}
