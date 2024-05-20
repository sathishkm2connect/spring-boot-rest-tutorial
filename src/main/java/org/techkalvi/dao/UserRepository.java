package org.techkalvi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.techkalvi.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
