package com.yeweiyang.token.mapperDto;

import com.yeweiyang.token.pojo.saToken.User;

import com.yeweiyang.token.response.UserResp;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2022-08-19T18:08:01+0800",

    comments = "version: 1.2.0.Beta2, compiler: javac, environment: Java 1.8.0_312 (Azul Systems, Inc.)"

)

@Component

public class UserDtoToMapperImpl implements UserDtoToMapper {

    @Override

    public List<UserResp> userToUserResp(List<User> usersjays) {

        if ( usersjays == null ) {

            return null;
        }

        List<UserResp> list = new ArrayList<UserResp>();

        for ( User user : usersjays ) {

            list.add( userToUserResp( user ) );
        }

        return list;
    }

    protected UserResp userToUserResp(User user) {

        if ( user == null ) {

            return null;
        }

        UserResp userResp = new UserResp();

        userResp.setUsername( user.getUsername() );

        userResp.setPassword( user.getPassword() );

        return userResp;
    }
}

