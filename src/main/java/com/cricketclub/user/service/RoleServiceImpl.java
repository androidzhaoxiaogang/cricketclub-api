package com.cricketclub.user.service;

import com.cricketclub.user.dto.Role;
import com.cricketclub.user.dto.RoleList;
import com.cricketclub.user.domain.RoleBO;
import com.cricketclub.user.exception.NoSuchRoleException;
import com.cricketclub.user.repository.RoleRepository;
import com.cricketclub.common.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
class RoleServiceImpl extends RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Mapper<RoleBO, Role, RoleList> mapper;

    public Optional<RoleList> findActiveRoles() {
        LOGGER.debug("Find all active service roles");
        List<RoleBO> roleBOList = roleRepository.findBySelectable(true);
        if(roleBOList.isEmpty()) {
            return Optional.empty();
        }

        RoleList roleList = mapper.transformToList(mapper.transform(roleBOList));
        return Optional.of(roleList);
    }

    @Transactional
    public void updateRole(final Integer roleId, final Boolean selectable) throws NoSuchRoleException {
        LOGGER.debug("Update role {} to visible: {}", roleId, selectable);
        RoleBO roleBO = roleRepository.findById(roleId)
                .orElseThrow(() -> new NoSuchRoleException(roleId));

        roleBO.setSelectable(selectable);
        roleRepository.save(roleBO);
    }

    @Override
    public Optional<RoleBO> findById(final Integer id) throws NoSuchRoleException {
        return roleRepository.findById(id);
    }


    public Optional<RoleBO> findByName(final RoleBO.Role role) throws NoSuchRoleException {
        return roleRepository.findByName(role);
    }
}



