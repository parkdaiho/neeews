package me.parkdaiho.project.domain;

import java.util.List;

public interface Commentable {

    List<Comment> getComments();
    Long getCommentsSize();
    void addComment(Comment comment);
}
