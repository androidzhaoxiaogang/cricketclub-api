package com.cricketclub.committee.role.dto;

import com.cricketclub.common.dto.BaseDomain;

import java.util.ArrayList;
import java.util.List;

public class CommitteeRoleList extends BaseDomain {

    private final List<CommitteeRole> committeeRoles;

    public CommitteeRoleList(List<CommitteeRole> committeeRoles) {
        this.committeeRoles = committeeRoles;
    }

    public List<CommitteeRole> getCommitteeRoles() {
        return committeeRoles;
    }
}
