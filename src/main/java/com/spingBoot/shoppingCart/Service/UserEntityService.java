package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.UpdateUserInfo;
import com.spingBoot.shoppingCart.Model.UserRegisterModel;

import java.util.List;

public interface UserEntityService {
    void saveUser(UserRegisterModel userModel);

    UserEntity getUserByUsername(String username) throws UserNotFoundException;

   List <UserEntity> getAll();

    void updateUserInfo(UpdateUserInfo updateUserInfo) throws UserNotFoundException;

    void deleteUser(long userId);


}
