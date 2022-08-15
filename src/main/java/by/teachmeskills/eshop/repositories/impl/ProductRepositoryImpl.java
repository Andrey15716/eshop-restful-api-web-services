package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.CATEGORY_ID;
import static by.teachmeskills.eshop.utils.EshopConstants.PAGE_SIZE;
import static by.teachmeskills.eshop.utils.EshopConstants.SEARCH_RESULT;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product create(Product entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public List<Product> read() throws RepositoryExceptions {
        return entityManager.createQuery("select p from Product p").getResultList();
    }

    @Override
    public Product update(Product entity) throws RepositoryExceptions {
        Product product = entityManager.find(Product.class, entity.getId());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setPrice(entity.getPrice());
        product.setCategory(entity.getCategory());
        product.setImageName(entity.getImageName());
        product.setId(entity.getId());
        entityManager.persist(product);
        return product;
    }

    @Override
    public void delete(int id) throws RepositoryExceptions {
        Product product = entityManager.find(Product.class, id);

        entityManager.remove(product);
    }

    @Override
    public List<Product> getProductsBySearchRequest(String param) {
        Query query = entityManager.createQuery("select p from Product p where p.name like :searchResult or p.description like: searchResult");
        String searchResult = '%' + param + '%';
        query.setParameter(SEARCH_RESULT, searchResult);
        return query.getResultList();
    }

    @Override
    public Product getProductById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getAllProductsByCategoryIdPaging(int categoryId, int pageReq) {
        int firstResult;
        if (pageReq > 1) {
            firstResult = (pageReq - 1) * PAGE_SIZE;
        } else {
            firstResult = 0;
        }
        Query query = entityManager.createQuery("select p from Product p where p.category.id=:categoryId order by p.name asc");
        query.setParameter(CATEGORY_ID, categoryId);
        query.setFirstResult(firstResult);
        query.setMaxResults(PAGE_SIZE);
        return query.getResultList();
    }
}