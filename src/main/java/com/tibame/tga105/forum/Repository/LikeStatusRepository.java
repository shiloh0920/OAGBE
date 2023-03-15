package com.tibame.tga105.forum.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tibame.tga105.forum.entity.LikeStatusEntity;

@Repository
public interface LikeStatusRepository extends JpaRepository<LikeStatusEntity, Integer> {

	@Query (value = "SELECT * FROM like_status_entity where article_id=?1 and user_id=?2  ", nativeQuery = true)
	public LikeStatusEntity findOne(Integer articleid,
									Integer userid) ;
}
