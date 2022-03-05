package com.example.book.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// TODO 테스트 하위 패키지들은 무엇이 있고 각각은 어떻게 다른가. assertj와 jupiter는 각각 무엇인지 모르겠다.
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// TODO junit4랑은 무슨 어노테이션 차이가 왜 나는걸까?
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // TODO 커스텀으로 코드 자동완성 하는 방법 찾기
        //given
        String title = "테스트 게시글 제목";
        String content = "테스트 게시글 본문";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("testq@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("Auditing 테스팅")
    public void auditingTest() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0 , 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> all = postsRepository.findAll();

        //then
        Posts posts = all.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("posts.getCreatedTime() = " + posts.getCreatedTime());
        System.out.println("posts.getModifiedTime() = " + posts.getModifiedTime());
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedTime()).isAfter(now);
    }
}