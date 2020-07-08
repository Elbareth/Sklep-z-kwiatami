package com.example.sklepZKwiatami.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    CUSTOMER(Sets.newHashSet(ApplicationUserPermission.BUY, ApplicationUserPermission.HISTORY, ApplicationUserPermission.USER_THEMSELF_MODIFY)),
    SELLER(Sets.newHashSet(ApplicationUserPermission.SELL)),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.SELL,
            ApplicationUserPermission.PRODUCT_MODIFY,
            ApplicationUserPermission.USER_MODIFY));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permission = getPermission().stream()
                .map(it->new SimpleGrantedAuthority(it.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permission;
    }
}
