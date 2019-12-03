package ua.training.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Comment {

    private Long id;

    private LocalDate date;
    private String comment;

    private User user;

    public Comment(){}
    public Comment(LocalDate date, String comment, User user) {
        this.date = date;
        this.comment = comment;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public static Comment.Builder builder() {
        return new Comment().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Comment.Builder id(Long id) {
            Comment.this.id = id;
            return this;
        }

        public Comment.Builder date(LocalDate date) {
            Comment.this.date = date;
            return this;
        }

        public Comment.Builder comment(String comment) {
            Comment.this.comment = comment;
            return this;
        }

        public Comment.Builder user(User user) {
            Comment.this.user = user;
            return this;
        }
        public Comment build() {
            return Comment.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
