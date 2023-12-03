package com.spingBoot.shoppingCart.Service.Implementtion;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.UserNameAlreadyExistException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.UpdateUserInfo;
import com.spingBoot.shoppingCart.Model.UserRegisterModel;

import com.spingBoot.shoppingCart.Repository.UserRepository;
import com.spingBoot.shoppingCart.Service.UserEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityServiceIMPL implements UserEntityService {
    private final UserRepository userRepository;



    public UserEntityServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;


    }


    @Override
    public void saveUser(UserRegisterModel user) throws UserNameAlreadyExistException {
        UserEntity optionalUserEntity=userRepository.findByUsername(user.getUsername());
        if (optionalUserEntity==null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(user.getId());
            userEntity.setUsername(user.getUsername());
            userEntity.setEmail(user.getEmail());
            userEntity.setPhone(user.getPhone());
            userEntity.setPassword(user.getPassword());
            userEntity.setRoles(user.getRoles());
            userRepository.save(userEntity);
        } else {
            throw new UserNameAlreadyExistException();
        }

    }


    @Override
    public UserEntity getUserByUsername(String username) throws UserNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException();
        }else {
            return user;
        }

    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }


    @Override
    public void updateUserInfo(UpdateUserInfo updateUserInfo) throws UserNotFoundException {
        UserEntity user = userRepository.findById(updateUserInfo.getId()).orElseThrow(UserNotFoundException::new);
        user.setUsername(updateUserInfo.getUserName());
        user.setPassword(updateUserInfo.getPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }


}
