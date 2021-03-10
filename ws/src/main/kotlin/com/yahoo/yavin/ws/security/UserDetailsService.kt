/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */

package com.yahoo.yavin.ws.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService(private val userRepository: UserRepository) :
    UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findOneById(username) ?: throw UsernameNotFoundException("User Not Found!")
        return UserDetails(user)
    }
}
