package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PhoneBookEntity;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBookEntity, Long> {

	/**検索SQL*/
	@Query(value = "SELECT p.phone_book_id, p.name, p.phone_number, false as is_delete FROM phone_book p", nativeQuery = true)
	public List<PhoneBookEntity> findAll();

	/**削除SQL*/
	@Modifying
	@Transactional
	@Query(value = "DELETE from phonebook WHERE id = :id", nativeQuery = true)
	public void delete(int id);

	/**登録SQL*/
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO phonebook (name,phone_number,account_id) VALUES (:name,:phoneNumber,:accountId)", nativeQuery = true)
	public void regist(String name, String phoneNumber, String accountId);

	/**更新SQL*/
	@Modifying
	@Transactional
	@Query(value = "UPDATE phonebook SET name = :name, phone_number = :phoneNumber ,account_id = :accountId WHERE id = :id", nativeQuery = true)
	public void update(String name, String phoneNumber, int id, String accountId);
}