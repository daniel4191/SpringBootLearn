package kr.swdl.springboot1.dto;

import kr.swdl.springboot1.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewReponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewReponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
