package com.cricketclub.committee.role.controller;

import com.cricketclub.committee.CommitteeLinksFactory;
import com.cricketclub.committee.role.exception.NoSuchCommitteeRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class CommitteeRoleControllerHateoasBuilder {

    @Autowired
    private CommitteeLinksFactory committeeLinksFactory;

    List<Link> buildLinksForGetCommitteeRoles() throws NoSuchCommitteeRoleException {
        List<Link> links = new ArrayList<Link>();
        links.add(committeeLinksFactory.getCommitteeRoleLink(Link.REL_SELF));
        return links;
    }
}

