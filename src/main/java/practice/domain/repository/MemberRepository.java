package practice.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findByEmail(String userEmail);
}
