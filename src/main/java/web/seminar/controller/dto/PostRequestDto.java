package web.seminar.controller.dto;

import lombok.Getter;
import web.seminar.domain.entity.Post;
import web.seminar.domain.entity.User;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
//    private String image;

}
