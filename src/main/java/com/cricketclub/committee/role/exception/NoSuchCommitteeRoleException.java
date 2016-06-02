package com.cricketclub.committee.role.exception;

import com.cricketclub.common.exception.ObjectNotFoundException;

public class NoSuchCommitteeRoleException extends ObjectNotFoundException {
    public NoSuchCommitteeRoleException() {
        super("Committee role not found");
    }

    public NoSuchCommitteeRoleException(Integer committeRoleId) {
        super("Committee role "+ committeRoleId + " not found");
    }
}
