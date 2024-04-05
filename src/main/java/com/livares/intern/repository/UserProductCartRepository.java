package com.livares.intern.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.livares.intern.DTO.CartDTO;
import com.livares.intern.entity.UserProductCart;

@Repository
public interface UserProductCartRepository extends JpaRepository<UserProductCart, Long> {

	@Query("select new com.livares.intern.DTO.CartDTO(p.name,p.description,p.img,p.price,p.categoryId.category,u.username)  "
			+ "from UserProductCart c join Product p on c.productId.id=p.id join Users u on c.userId.id=u.id where c.userId.id=:userId ")
	List<CartDTO> fetchData(long userId);

	@Query("select count(c) from UserProductCart c where c.productId.id=:productId and c.userId.id=:userId ")
	int countOfProducts(long userId, long productId);

//

	@Query("select count(c) from UserProductCart c where c.userId.id=:userId")
	int countByUserId(long userId);

	@Query("select c from UserProductCart c where c.userId.id=:userId and c.productId.id=:productId")
	UserProductCart findByUserId(long userId, long productId);

}
