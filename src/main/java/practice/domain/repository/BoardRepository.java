package practice.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	List<BoardEntity> findByTitleContaining(String keyword);
}
