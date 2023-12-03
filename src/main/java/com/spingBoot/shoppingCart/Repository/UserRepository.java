package com.spingBoot.shoppingCart.Repository;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
  UserEntity findUserEntityByUsername(String username);

  UserEntity findByUsername(String username);
}
