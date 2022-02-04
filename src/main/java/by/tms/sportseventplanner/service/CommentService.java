package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Comment;
import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.repository.CommentRepository;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public Comment save(long eventId, Comment comment) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException(String.format("Event id %s is not exist!", comment.getEventId())));

        Organization organization = organizationRepository
                .findByName(comment.getCreatorName())
                .orElseThrow(() -> new RuntimeException(String.format("Organization %s is not exist!", comment.getCreatorName())));

        comment.setEventId(event.getId());
        return commentRepository.save(comment);
    }

    public Comment getCommentByCommentId(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException(String.format("Comment with id %s is not exist!", commentId)));
    }

    public List<Comment> getListCommentByEventId(long eventId) {
        return commentRepository.findAllByEventId(eventId)
                .orElseThrow(() -> new RuntimeException(String.format("Comment with eventId %s is not exist!", eventId)));
    }

    public List<Comment> getListCommentByCreatorName(String creatorName) {
        return commentRepository.findAllByCreatorName(creatorName)
                .orElseThrow(() -> new RuntimeException(String.format("Comment with creatorName %s is not exist!", creatorName)));
    }

    public Comment deleteById(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException(String.format("Comment with id %s is not exist!", commentId)));
        commentRepository.delete(comment);
        return comment;
    }
}
