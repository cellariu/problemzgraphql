package com.cami.udemy.graphql.problemz.problemzgraphql.component.problemz;

import com.cami.udemy.graphql.problemz.problemzgraphql.types.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {

    @DgsData(parentType = "Query", field = "me")
    public User accountInfo(@RequestHeader(name = "authToken", required = true) String authToken) {
        return null;
    }

    @DgsData(parentType = "Mutation", field = "userCreate")
    public UserResponse createUser(@InputArgument(name = "user") UserCreateInput userCreateInput) {
        return null;
    }

    @DgsData(parentType = "Mutation", field = "userLogin")
    public UserResponse loginUser(@InputArgument(name = "user") UserLoginInput userLoginInput) {
        return null;
    }

    @DgsData(parentType = "Mutation", field = "userActivation")
    public UserActivationResponse activateUser(@InputArgument(name = "user") UserActivationInput userActivationInput) {
        return null;
    }
}
