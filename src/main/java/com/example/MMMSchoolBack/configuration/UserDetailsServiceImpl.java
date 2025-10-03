package com.example.MMMSchoolBack.configuration;

import com.example.MMMSchoolBack.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return this.userRepo.findByEmail(userEmail)
                .orElseThrow(()->new UsernameNotFoundException("user not found!!"));
    }
}
