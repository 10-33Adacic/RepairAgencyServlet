package ua.training.model.service;

import ua.training.model.dao.CommentDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Comment;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class CommentService {

    private DaoFactory daoFactory;
    private CommentDao commentDao;

    public CommentService() {
        this.daoFactory = DaoFactory.getInstance();
        this.commentDao = daoFactory.createCommentDao();
    }

    public Optional<List<Comment>> findAllComment(int page, int size){
        return Optional.ofNullable(commentDao.findAll(page, size));
    }

    public long findCount() {
            return commentDao.findCount();
    }
    public Comment addComment(String comment, User user) throws SQLException {
        Comment newComment = Comment.builder()
                .comment(comment)
                .date(LocalDate.now(Clock.system(ZoneId.of("Europe/Kiev"))))
                .user(user)
                .build();
       commentDao.add(newComment);
       return newComment;
    }
}
