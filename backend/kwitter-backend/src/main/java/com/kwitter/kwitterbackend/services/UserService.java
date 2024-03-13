package com.kwitter.kwitterbackend.services;

import com.kwitter.kwitterbackend.exceptions.EmailAlreadyTakenException;
import com.kwitter.kwitterbackend.models.ApplicationUser;
import com.kwitter.kwitterbackend.models.RegistrationObject;
import com.kwitter.kwitterbackend.models.Role;
import com.kwitter.kwitterbackend.repositories.RoleRepository;
import com.kwitter.kwitterbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public ApplicationUser registerUser(RegistrationObject ro) {

        ApplicationUser user = new ApplicationUser();

        user.setFirstName(ro.getFirstName());
        user.setLastName(ro.getLastName());
        user.setEmail(ro.getEmail());
        user.setDateOfBirth(ro.getDob());

        String name = user.getFirstName() + user.getLastName();

        boolean nameTaken = true;

        String tempName = "";
        while (nameTaken) {
            tempName = generateUsername(name);
            if (userRepo.findByUsername(tempName).isEmpty()) {
                nameTaken = false;
            }
        }

        user.setUsername(tempName);

        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepo.findByAuthority("USER").get());
        user.setAuthorities(roles);

        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException("Email already taken");
        }
    }

    private String generateUsername(String name) {
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000L);
        return name + generatedNumber;
    }
}
