package in.CodeWithShubham.moneyManager.service;

import in.CodeWithShubham.moneyManager.entity.ProfileEntity;
import in.CodeWithShubham.moneyManager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ProfileEntity existProfile = profileRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Profile not found with email" + email));
//        System.out.println("this is existProfile " + existProfile.getPassword());
        return User.builder()
                .username(existProfile.getEmail())
                .password(existProfile.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
