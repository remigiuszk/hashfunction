package pl.kostrzynkamien.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kostrzynkamien.lab.repo.UserRepo;

@Service
@Primary
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepo.findAllByUsername(userName);
    }
}
