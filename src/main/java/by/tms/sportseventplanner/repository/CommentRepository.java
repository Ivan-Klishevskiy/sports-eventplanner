package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<List<Comment>> findAllByCreatorName(String creatorName);

    Optional<List<Comment>> findAllByEventId(long eventId);

    void deleteAllByEventId(long eventId);

    void deleteAllByCreatorName(String creatorName);
}
