package com.sandbox.repositorys;

import com.sandbox.entities.Person;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("personRepository")
public interface PersonRepository extends CrudRepository<Person, Long> {//extends, not implements!!!
    //can extend interfaces Repository, CrudRepository, or PagingAndSortingRepository

    List<Person> findAll();
    List<Person> findByFirstName(String firstName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

//  List<Student> findByLastName(String lastName);

//    @Query("select * from student where last_name = :lName")
//    List<Student> findByLastName(@Param("lName") String lastName);

    @Modifying
    @Query("UPDATE person SET first_name = :name WHERE id = :id")
    boolean updateByFirstName(@Param("id") Long id, @Param("name") String name);

// CrudRepository give us:
//
//    <S extends T> S save(S entity);
//
//    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
//
//    Optional<T> findById(ID id);
//
//    boolean existsById(ID id);
//
//    Iterable<T> findAll();
//
//    Iterable<T> findAllById(Iterable<ID> ids);
//
//    long count();
//
//    void deleteById(ID id);
//
//    void delete(T entity);
//
//    void deleteAllById(Iterable<? extends ID> ids);
//
//    void deleteAll(Iterable<? extends T> entities);
//
//    void deleteAll();
}