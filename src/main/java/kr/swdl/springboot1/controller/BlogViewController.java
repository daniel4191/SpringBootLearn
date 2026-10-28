package kr.swdl.springboot1.controller;

import kr.swdl.springboot1.domain.Article;
import kr.swdl.springboot1.dto.ArticleListViewReponse;
import kr.swdl.springboot1.dto.ArticleViewResponse;
import kr.swdl.springboot1.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewReponse> articles = blogService.findAll().stream()
                .map(ArticleListViewReponse::new)
                .toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if (id == null){ // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id 가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
