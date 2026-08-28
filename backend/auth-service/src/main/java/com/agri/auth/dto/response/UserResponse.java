package com.agri.auth.dto.response;

import com.agri.auth.enums.Role;

public record UserResponse(

        Long id,

        String email,

        String firstName,

        String lastName,

        String phone,

        Role role,

        boolean enabled,

        boolean accountLocked

) {
}