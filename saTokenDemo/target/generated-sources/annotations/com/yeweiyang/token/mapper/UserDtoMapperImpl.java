package com.yeweiyang.token.mapper;

import com.yeweiyang.token.pojo.copy.Jay;

import com.yeweiyang.token.pojo.copy.JayDto;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.HashMap;

import java.util.Map;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2022-08-19T18:08:01+0800",

    comments = "version: 1.2.0.Beta2, compiler: javac, environment: Java 1.8.0_312 (Azul Systems, Inc.)"

)

@Component

public class UserDtoMapperImpl implements UserDtoMapper {

    @Override

    public JayDto jayToJayDto(Jay jay) throws Exception {

        if ( jay == null ) {

            return null;
        }

        JayDto jayDto = new JayDto();

        if ( jay.getCreateUser() != null ) {

            jayDto.setCreateUser( jay.getCreateUser() );
        }

        else {

            jayDto.setCreateUser( "周杰伦" );
        }

        jayDto.setUserName( jay.getName() );

        jayDto.setCreateTime( jay.getCreateTime() );

        jayDto.setId( jay.getId() );

        jayDto.setUpdateTime( jay.getUpdateTime() );

        jayDto.setUpdateUser( jay.getUpdateUser() );

        return jayDto;
    }

    @Override

    public void updateDeliveryAddressFromAddress(Jay jay, JayDto jayDto) {

        if ( jay == null ) {

            return;
        }

        jayDto.setId( jay.getId() );

        jayDto.setCreateUser( jay.getCreateUser() );

        jayDto.setCreateTime( jay.getCreateTime() );

        jayDto.setUpdateTime( jay.getUpdateTime() );

        jayDto.setUpdateUser( jay.getUpdateUser() );
    }

    @Override

    public Map<String, String> DateMapToStringMap(Map<String, Date> sourceMap) {

        if ( sourceMap == null ) {

            return null;
        }

        Map<String, String> map = new HashMap<String, String>();

        for ( java.util.Map.Entry<String, Date> entry : sourceMap.entrySet() ) {

            String key = entry.getKey();

            String value = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( entry.getValue() );

            map.put( key, value );
        }

        return map;
    }
}

