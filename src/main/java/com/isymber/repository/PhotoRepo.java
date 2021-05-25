package com.isymber.repository;

import com.isymber.model.Photo;
import com.isymber.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {
}
