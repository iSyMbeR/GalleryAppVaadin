package com.isymber.repository;

import com.isymber.model.CloudinaryCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryRepo extends JpaRepository<CloudinaryCredentials, Long> {
}
