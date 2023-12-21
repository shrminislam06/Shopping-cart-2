package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.UserNameAlreadyExistException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.UpdateUserInfo;
import com.spingBoot.shoppingCart.Model.UserRegisterModel;
import com.spingBoot.shoppingCart.Service.UserEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameAlreadyBoundException;
import java.util.List;

@RestController
@RequestMapping
public class UserController {
    private UserEntityService userEntityService;


    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/public/register")
    public ResponseEntity<String> save(@RequestBody UserEntity user) throws UserNameAlreadyExistException {

        return ResponseEntity.ok(userEntityService.saveUser(user));
    }

    @GetMapping("/getUserByUsername")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.getUserByEmail(email));
    }

    @GetMapping("/userList")
   @PreAuthorize("hasAuthority('ADMIN')")
    public Page<UserEntity> getAll(@RequestParam(defaultValue = "0")int pageNo,@RequestParam(defaultValue = "10")int size) {
        PageRequest pageRequest=PageRequest.of(pageNo, size);
    return userEntityService.getAll(pageRequest);
   }

    @PutMapping("/update-user-info")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateUserInfo(@RequestBody UpdateUserInfo updateUserInfo) throws UserNotFoundException {
        userEntityService.updateUserInfo(updateUserInfo);
        return ResponseEntity.ok("your information updated successfully");
    }
    @DeleteMapping("/delete-userby-userId")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestParam long userId) {
        userEntityService.deleteUser(userId);
        return ResponseEntity.ok("deleted id :" + userId);
    }

}
