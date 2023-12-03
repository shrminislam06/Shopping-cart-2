package com.spingBoot.shoppingCart.Repository;

import com.spingBoot.shoppingCart.Entity.Cart;
import com.spingBoot.shoppingCart.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    List<Cart> findByUserEntityId(long userId);

//    Cart findByUserEntityIdAndProductsId(long userId, Long productId);


    Cart findFirstByUserEntityIdAndProductsId(long userId,long productId);

  List <Cart> findByUserEntity_IdAndProducts_Id(long userId, long productId);
    Optional <Cart> findByUserEntityIdAndProductsId(long UserEntity_id,  long Products_Id);

    Optional<Cart> findByUserEntity_IdAndProducts_Id(long userId, Long productId);

    List<Cart> findByUserEntityIdAndId(long userId, long id);

    Optional<Cart> findByProductsIdAndPurchasedFalse(long productId);

    //Optional<Cart> findByProductsId(Long productId);
}
