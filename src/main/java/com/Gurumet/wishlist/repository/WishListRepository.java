package com.Gurumet.wishlist.repository;

import com.Gurumet.db.MemoryDbRepositoryAbstract;
import com.Gurumet.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
