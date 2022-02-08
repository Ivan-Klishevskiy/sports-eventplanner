package by.tms.sportseventplanner.controller;

import by.tms.sportseventplanner.dto.comment.RequestCommentDto;
import by.tms.sportseventplanner.dto.comment.SentCommentDto;
import by.tms.sportseventplanner.entity.Comment;
import by.tms.sportseventplanner.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create/{eventId}")
    @Operation(summary = "Create new comment for event")
    public ResponseEntity<?> createComment(@PathVariable long eventId,
                                           @Valid @RequestBody RequestCommentDto requestCommentDto) {
        Comment created = commentService.save(eventId, mapper.map(requestCommentDto, Comment.class));
        return new ResponseEntity<>(mapper.map(created, SentCommentDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/byId/{commentId}")
    @Operation(summary = "Find comment by commentId")
    public ResponseEntity<?> getCommentByCommentId(@PathVariable long commentId) {
        Comment found = commentService.getCommentByCommentId(commentId);
        return new ResponseEntity<>(mapper.map(found, SentCommentDto.class), HttpStatus.OK);
    }

    @GetMapping("/byEvent/{eventId}")
    @Operation(summary = "Find comment by eventId")
    public ResponseEntity<?> getAllCommentsByEventId(@PathVariable long eventId,
                                                     @RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<Integer> size,
                                                     @RequestParam Optional<String> sortBy) {
        List<SentCommentDto> commentDtoList = commentService.getListCommentByEventId(eventId,(PageRequest.of(page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id")))).stream()
                .map(comment -> mapper.map(comment, SentCommentDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @GetMapping("/byOrganization/{creatorName}")
    @Operation(summary = "Find all comment by organization name")
    public ResponseEntity<?> getAllCommentsByCreatorName(@PathVariable @Length(min = 1, max = 255) String creatorName,
                                                         @RequestParam Optional<Integer> page,
                                                         @RequestParam Optional<Integer> size,
                                                         @RequestParam Optional<String> sortBy) {
        List<SentCommentDto> commentDtoList = commentService.getListCommentByCreatorName(creatorName,(PageRequest.of(page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id")))).stream()
                .map(comment -> mapper.map(comment, SentCommentDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete comment by commentId")
    public ResponseEntity<?> deleteByCommentId(@PathVariable long commentId) {
        Comment deleted = commentService.deleteById(commentId);
        return new ResponseEntity<>(mapper.map(deleted, SentCommentDto.class), HttpStatus.OK);
    }
}
