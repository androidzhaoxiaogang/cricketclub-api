package com.cricketclub.user.dto;


import com.cricketclub.common.dto.BaseDomain;
import com.cricketclub.user.domain.RoleBO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Role extends BaseDomain {

    private Integer roleId;

    @NotNull(message = "username is compulsory")
    private final RoleBO.Role name;

    @Size(min = 5, max = 50, message = "description is wrong size")
    @NotBlank(message = "description is compulsory")
    private final String description;

    @NotNull(message = "presedenceOrder is compulsory")
    private final Integer presedenceOrder;

    public Role(Integer roleId, RoleBO.Role name, String description, Integer presedenceOrder) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.presedenceOrder = presedenceOrder;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public RoleBO.Role getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPresedenceOrder() {
        return presedenceOrder;
    }
}
