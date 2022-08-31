package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Page<Product> findAllByCategoryId(int id, Pageable pageable) throws RepositoryExceptions;

    Product getProductById(int id) throws RepositoryExceptions;

    void deleteProductById(int id) throws RepositoryExceptions;

    @Query("select p from Product p where p.name like ?1 order by p.id asc")
    List<Product> findAllByNameContaining(String param) throws RepositoryExceptions;
}