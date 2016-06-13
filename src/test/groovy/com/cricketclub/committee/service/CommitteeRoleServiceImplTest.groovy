package com.cricketclub.committee.service

import com.cricketclub.committee.service.CommitteeRoleConverter
import com.cricketclub.committee.service.CommitteeRoleService
import com.cricketclub.committee.service.CommitteeRoleServiceImpl
import com.cricketclub.committee.domain.CommitteeRoleBO
import com.cricketclub.committee.dto.CommitteeRole
import com.cricketclub.committee.dto.CommitteeRoleList
import spock.lang.Specification

class CommitteeRoleServiceImplTest extends Specification {

    private CommitteeRoleService committeeRoleService
    private CommitteeRoleConverter committeeRoleMapper

    private CommitteeRoleBO committeeRoleBO
    private CommitteeRole committeeRole
    private CommitteeRoleList committeeRoleList

    private CommitteeRoleServiceImpl underTest

    def setup() {
        committeeRoleService = Mock(CommitteeRoleService)
        committeeRoleMapper = Mock(CommitteeRoleConverter)

        committeeRoleBO = Mock(CommitteeRoleBO)
        committeeRole = Mock(CommitteeRole)
        committeeRoleList = Mock(CommitteeRoleList)

        underTest = new CommitteeRoleServiceImpl(
                committeeRoleRepository:committeeRoleService,
                mapper:committeeRoleMapper
        )
    }

    def "test getActiveCommitteRole success"() {
        given:
            List<CommitteeRoleBO> committeeRoleBOList = new ArrayList<>()
            List<CommitteeRole> committeeRoles = new ArrayList<>()
            committeeRoleBOList.add(committeeRoleBO)
            committeeRoles.add(committeeRole)
        when:
            Optional<CommitteeRoleList> result = underTest.getActiveCommitteRole()
        then:
            1 * committeeRoleService.getActiveCommitteeRoles() >> committeeRoleBOList
            1 * committeeRoleMapper.transform(committeeRoleBOList) >> committeeRoles
            1 * committeeRoleMapper.transformToList(committeeRoles) >> committeeRoleList
            result.isPresent()
            result.get() == committeeRoleList
    }

    def "test getActiveCommitteRole when committee role is empty"() {
        when:
            Optional<CommitteeRoleList> result = underTest.getActiveCommitteRole()
        then:
            1 * committeeRoleService.getActiveCommitteeRoles() >> new ArrayList<CommitteeRoleBO>()
            0 * committeeRoleMapper.transform(_)
            0 * committeeRoleMapper.transformToList(_)
            !result.isPresent()
    }
}