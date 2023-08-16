package com.onurbas.graphql.mutation;

import com.onurbas.excepiton.ElasticManagerException;
import com.onurbas.excepiton.ErrorType;
import com.onurbas.graphql.model.UserProfileInput;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver  implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;


    public Boolean createUserProfile(UserProfileInput userProfileInput){
        try {
            userProfileService.save(IUserMapper.INSTANCE.toUserProfile(userProfileInput));
            return  true;
        }catch (Exception e){
         throw    new ElasticManagerException(ErrorType.USER_NOT_CREATED);
        }

    }
}
