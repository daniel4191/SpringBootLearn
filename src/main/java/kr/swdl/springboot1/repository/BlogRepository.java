package kr.swdl.springboot1.repository;

import kr.swdl.springboot1.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
