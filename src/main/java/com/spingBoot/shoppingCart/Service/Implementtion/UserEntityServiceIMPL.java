package com.spingBoot.shoppingCart.Service.Implementtion;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.UserNameAlreadyExistException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.UpdateUserInfo;
import com.spingBoot.shoppingCart.Model.UserRegisterModel;

import com.spingBoot.shoppingCart.Repository.UserRepository;
import com.spingBoot.shoppingCart.Service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceIMPL implements UserEntityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    public UserEntityServiceIMPL(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;


        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String saveUser(UserEntity user) throws UserNameAlreadyExistException{
//       Optional<UserEntity> optionalUserEntity=userRepository.findByEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity= userRepository.save(user);
        if (userEntity.getId()>0) {
//            UserEntity userEntity = new UserEntity();
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            userEntity.setId(user.getId());
//            userEntity.setName(user.getName());
//            userEntity.setEmail(user.getEmail());
//            userEntity.setPhone(user.getPhone());
//            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
//            userEntity.setRoles(authentication.get);
            return "user added";

        } else {
            throw new UserNameAlreadyExistException();
        }

    }


    @Override
    public UserEntity getUserByEmail(String email) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }else {
            return user.get();
        }

    }

    @Override
    public Page<UserEntity> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public void updateUserInfo(UpdateUserInfo updateUserInfo) throws UserNotFoundException {
        UserEntity user = userRepository.findById(updateUserInfo.getId()).orElseThrow(UserNotFoundException::new);
        user.setName(updateUserInfo.getUserName());
        user.setPassword(updateUserInfo.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }


}
