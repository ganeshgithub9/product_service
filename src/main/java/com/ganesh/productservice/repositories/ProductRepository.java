package com.ganesh.productservice.repositories;

import com.ganesh.productservice.DTO.FindAllProductsDA0;
import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategory(Category c);

    //@Query(value = "select p,c,ps from products p inner join categories c on p.category=c inner join prices ps on p.price=ps")
    //@Query(value = "select  p.uuid,p.name,p.description,p.rating,pr.price,c.name category,pr.currency from products p left join categories c on p.category_uuid=c.uuid inner join prices pr on p.price_uuid=pr.uuid",nativeQuery = true)
    @Query("select p from products p")
    List<Product> findAllProducts();

    //@Query(value = "select  p.uuid,p.name,p.description,p.rating,pr.price,c.name category,pr.currency from (select * from products where category_uuid=:category_uuid) as p inner join categories c on p.category_uuid=c.uuid inner join prices pr on p.price_uuid=pr.uuid",nativeQuery = true)

    //List<FindAllProductsDA0> findAllByCategoryId(UUID category_uuid);
}
