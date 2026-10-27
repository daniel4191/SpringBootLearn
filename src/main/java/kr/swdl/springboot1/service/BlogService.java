package kr.swdl.springboot1.service;

import jakarta.transaction.Transactional;
import kr.swdl.springboot1.domain.Article;
import kr.swdl.springboot1.dto.AddArticleRequest;
import kr.swdl.springboot1.dto.UpdateArticleRequest;
import kr.swdl.springboot1.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    // 전체 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 글 한개 서치
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 삭제
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    // 트랜잭션 메서드
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
