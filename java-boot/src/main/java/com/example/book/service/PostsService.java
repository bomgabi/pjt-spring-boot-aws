package com.example.book.service;

import com.example.book.domain.posts.Posts;
import com.example.book.domain.posts.PostsRepository;
import com.example.book.web.dto.PostsResponseDto;
import com.example.book.web.dto.PostsSaveRequestDto;
import com.example.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // TODO findby 조합식은?
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());

    }
}
