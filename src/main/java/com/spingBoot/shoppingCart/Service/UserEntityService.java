package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.UserNameAlreadyExistException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.UpdateUserInfo;
import com.spingBoot.shoppingCart.Model.UserRegisterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.naming.NameAlreadyBoundException;
import java.util.List;

public interface UserEntityService {
    String saveUser(UserEntity userModel) throws UserNameAlreadyExistException;

    UserEntity getUserByEmail(String email) throws UserNotFoundException;

   Page <UserEntity> getAll(Pageable pageable);

    void updateUserInfo(UpdateUserInfo updateUserInfo) throws UserNotFoundException;

    void deleteUser(long userId);


}
