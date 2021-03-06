package com.cricketclub.user.service;

import com.cricketclub.user.domain.UserBO;
import com.cricketclub.user.dto.User;
import com.cricketclub.user.dto.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UserConverter implements Converter<UserBO, User> {

    private final RoleConverter roleConverter;

    @Autowired
    public UserConverter(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public User convert(UserBO source) {
        return new User(
                source.getId(),
                source.getUsername(),
                source.getUsername(),
                null,
                null,//source.getUserProfile().getFirstName(),
                null,//source.getUserProfile().getLastName(),
                null,//source.getUserProfile().getHomeNumber(),
                null,//source.getUserProfile().getMobileNumber(),
                roleConverter.convert(source.getRoles()));
    }

    public UserBO convert(User source) {
        UserBO userBO = new UserBO();
        userBO.setUsername(source.getUsername());
        userBO.setPassword(source.getPassword());
        return userBO;
    }

    public UserList convert(List<UserBO> source) {
        return new UserList(source.stream()
                .map(this::convert)
                .collect(Collectors.toList()));
    }
}
