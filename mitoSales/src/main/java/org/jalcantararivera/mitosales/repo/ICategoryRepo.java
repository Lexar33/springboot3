package org.jalcantararivera.mitosales.repo;

import org.jalcantararivera.mitosales.model.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.function.Function;

public interface ICategoryRepo extends IGenericRepo<Category,Integer> {
    //QUERIES DERIVADO (CONSULTAS SENCILLAS)
    //DerivedQueries
    // select * from category c where c.name=''
    List<Category> findByName(String name);
    List<Category> findByNameAndDescription(String name,String description);
    List<Category> findByNameLike(String name);
    List<Category> findByNameContains(String name); // %XYZ%
    List<Category> findByNameStartsWith(String name); // %XYZ
    List<Category> findByNameEndsWith(String name); // XYZ%
    List<Category> findByNameAndEnabled(String name,boolean enabled);
    List<Category> findByNameOrEnabled(String name,boolean enabled);
    List<Category> findByEnabled(boolean enabled);
    List<Category> findByEnabledTrue();
    Category findOneByName(String name);
    List<Category> findTop3ByName(String name);
    List<Category> findByNameIs(String name);
    List<Category> findByNameIsNotNull();
    List<Category> findByNameEqualsIgnoreCase(String name);
    List<Category> findByIdCategoryGreaterThanEqual(Integer id);
    List<Category> findByIdCategoryBetween(Integer id1, Integer id2);
    List<Category> findByNameOrderByDescription(String name);

    //JPQL: Java Persistence Query Language
    //@Query("FROM Product p WHERE p.category.name= :name")
    @Query("FROM Category c where c.name = :name AND c.description LIKE %:description%")
    List<Category> getNameAndDescription1(@Param("name") String name,@Param("description") String description);

    @Query("SELECT new Category (c.name,c.enabled) FROM Category c where c.name = :name AND c.description LIKE %:description%")
    List<Category> getNameAndDescription2(@Param("name") String name,@Param("description") String description);



    //SQL: NativeQuery

    @Query(value="SELECT * FROM category c WHERE c.name= :name", nativeQuery = true)
    List<Category> getNameSQL(@Param("name") String name);

    @Modifying
    @Query(value="UPDATE category SET name =:name", nativeQuery = true)
    Integer updateNames(@Param("name")String name);


}
