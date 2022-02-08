package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Page<Comment>> findAllByCreatorName(String creatorName, Pageable page);

    Optional<Page<Comment>> findAllByEventId(long eventId,Pageable page);

    void deleteAllByEventId(long eventId);

    void deleteAllByCreatorName(String creatorName);
}
