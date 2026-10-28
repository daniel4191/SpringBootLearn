package kr.swdl.springboot1.controller;

import kr.swdl.springboot1.dto.ArticleListViewReponse;
import kr.swdl.springboot1.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
