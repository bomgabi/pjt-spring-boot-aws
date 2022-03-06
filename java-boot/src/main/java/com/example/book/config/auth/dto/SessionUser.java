package com.example.book.config.auth.dto;

import com.example.book.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * Entity 클래스에 직접 직렬화를 구현할 경우 모든 하위 클래스가 직렬화가 되어야 하므로, 필요한 것만 빼서 DTO를 만든다.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
