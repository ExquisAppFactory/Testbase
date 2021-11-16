package com.exquis.app.user.service;

import com.exquis.app.user.dto.RegisterUserRequestDto;
import com.exquis.app.user.dto.UserWalletResponseDto;
import com.exquis.app.user.entity.Role;
import com.exquis.app.user.entity.User;
import com.exquis.app.user.enums.RoleType;
import com.exquis.app.user.enums.StatusType;
import com.exquis.app.user.exception.HttpBadGatewayException;
import com.exquis.app.user.exception.HttpBadRequestException;
import com.exquis.app.user.exception.HttpNotFoundException;
import com.exquis.app.user.dto.Wallet;
import com.exquis.app.user.repository.UserRepository;
import com.exquis.app.user.service.contract.RecordStatusServiceContract;
import com.exquis.app.user.service.contract.RoleServiceContract;
import com.exquis.app.user.service.contract.UserServiceContract;
import com.exquis.app.user.utility.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author chukwuebuka
 */

@Slf4j
@Service
public class UserService implements UserServiceContract {
    @Autowired private UserRepository userRepository;
    @Autowired private RoleServiceContract roleService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private RecordStatusServiceContract recordStatusService;
    @Autowired private RestTemplate restTemplate;

    @Override
    @Transactional
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(()-> new HttpNotFoundException());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserWalletResponseDto findUserWithWallet(UUID userId) {
        log.info("findUserWithWallet()");
        User user = userRepository.findById(userId).orElseThrow(()-> new HttpNotFoundException("User not found"));
        try{
            Wallet wallet = restTemplate.getForObject("http://WALLET-SERVICE/wallets/" + user.getId(), Wallet.class);

            UserWalletResponseDto responseDto = UserWalletResponseDto
                    .builder()
                    .userId(user.getId().toString())
                    .email(user.getEmail())
                    .contactPhone(user.getContactPhone())
                    .walletNumber(wallet.getNumber())
                    .walletAmount(wallet.getAmount())
                    .build();

            return responseDto;
        }
        catch(Exception ex)
        {
            throw new HttpBadGatewayException();
        }
    }

    @Override
    public User createUserAccount(RegisterUserRequestDto registerUserRequestDto) {
        //set user role
        Set<Role> roles = new HashSet<>();

        Role userRole = roleService.findByRole(RoleType.USER);
        if(Helper.isEmpty(userRole)) { // it means user as a role is not setup in the system
            throw new HttpBadRequestException("Make sure user roles and privileges are setup first.");
        }
        // we hope role is not deleted
        if(userRole.getRecordStatus().getStatus() == StatusType.DELETED){
            throw new HttpBadRequestException("User role is deleted and can't be added to user account. \n Suggestion: Find out why user role is not active.");
        }
        roles.add(userRole); // add to role set

        User user = new User();
        user.setRoles(roles);
        user.setEmail(registerUserRequestDto.getEmail());
        user.setLastName(registerUserRequestDto.getLastName());
        user.setFirstName(registerUserRequestDto.getFirstName());
        user.setContactPhone(registerUserRequestDto.getContactPhone());
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        user.setRecordStatus(recordStatusService.create(StatusType.ACTIVE));

        return saveOrUpdate(user); // create
    }
}
