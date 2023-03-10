package com.tibame.tga105.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tibame.tga105.user.entity.VerificationToken;

@Repository("verificationRepository")
public interface VerificationRepository extends JpaRepository<VerificationToken, Long>{
	VerificationToken findByConfirmationToken(String confirmationToken);
}
