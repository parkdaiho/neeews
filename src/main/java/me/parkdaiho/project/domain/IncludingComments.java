package me.parkdaiho.project.domain;

import java.util.List;

public interface IncludingComments {

    List<Comment> getComments();
    Long getCommentsSize();
    void addComment(Comment comment);
}
