package by.tms.sportseventplanner.rest;

import by.tms.sportseventplanner.dto.comment.RequestCommentDto;
import by.tms.sportseventplanner.dto.comment.SentCommentDto;
import by.tms.sportseventplanner.entity.Comment;
import by.tms.sportseventplanner.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create/{commentId}")
    public ResponseEntity<?> createComment(@PathVariable long commentId, @RequestBody RequestCommentDto requestCommentDto) {
        Comment created = commentService.save(commentId,mapper.map(requestCommentDto, Comment.class));
        return new ResponseEntity<>(mapper.map(created, SentCommentDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/byId/{commentId}")
    public ResponseEntity<?> getCommentByCommentId(@PathVariable long commentId) {
        Comment found = commentService.getCommentByCommentId(commentId);
        return new ResponseEntity<>(mapper.map(found, SentCommentDto.class), HttpStatus.OK);
    }

    @GetMapping("/byEvent/{eventId}")
    public ResponseEntity<?> getAllCommentsByEventId(@PathVariable long eventId) {
        List<SentCommentDto> commentDtoList = commentService.getListCommentByEventId(eventId).stream()
                .map(comment -> mapper.map(comment, SentCommentDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @GetMapping("/byOrganization/{creatorName}")
    public ResponseEntity<?>getAllCommentsByCreatorName(@PathVariable String creatorName){
        List<SentCommentDto> commentDtoList = commentService.getListCommentByCreatorName(creatorName).stream()
                .map(comment -> mapper.map(comment, SentCommentDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteByCommentId(@PathVariable long commentId){
        Comment deleted = commentService.deleteById(commentId);
        return new ResponseEntity<>(mapper.map(deleted, SentCommentDto.class), HttpStatus.OK);
    }
}
