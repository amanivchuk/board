package com.manivchuk.board.controller;

import com.manivchuk.board.service.board.BoardService;
import com.manivchuk.board.service.search.BoardSearchValues;
import com.manivchuk.board.transport.dto.board.BoardCreateDto;
import com.manivchuk.board.transport.dto.board.BoardOutcomeDto;
import com.manivchuk.board.transport.dto.board.BoardUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Setter(onMethod_ = @Autowired)
@Api(value = "BoardController", description = "REST API for Board", tags = {"Board"})
@RequestMapping(path = "board", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class BoardController {

    private BoardService boardService;

    @ApiOperation(value = "Create a board", notes = "ADMIN, MANAGER", nickname = "createBoard")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAMANGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody @Valid BoardCreateDto dto){
        return boardService.create(dto).getId();
    }

    @ApiOperation(value = "Delete a board by id", notes = "ADMIN, MANAGER", nickname = "deleteBoard")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted successfully"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }

    @ApiOperation(value = "Update a board", notes = "ADMIN, MANAGER", nickname = "updateBoard")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update accepted"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PutMapping()
    public Long update(@RequestBody @Valid BoardUpdateDto dto) {
        return boardService.update(dto);
    }

    @ApiOperation(value = "Finding boards", notes = "ADMIN, MANAGER", nickname = "findAllBoards")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
    })
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping
    public List<BoardOutcomeDto> getAll() {
        return boardService.getAll();
    }

    @ApiOperation(value = "Finding general employee by criteria", notes = "ADMIN, MANAGER", nickname = "searchAllGeneralEmployees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/search")
    public ResponseEntity<Page<BoardOutcomeDto>> searchEmployeeGeneral(@RequestBody BoardSearchValues boardSearchValues) {

        String title = boardSearchValues.getTitle();

        String sortColumn = boardSearchValues.getSortColumn();
        String sortDirection = boardSearchValues.getSortDirection();

        Integer pageNumber = boardSearchValues.getPageNumber();
        Integer pageSize = boardSearchValues.getPageSize();

        //объект постраничности
        Sort.Direction direction = sortDirection == null || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        //объект сортировки
        Sort sort = Sort.by(direction, sortColumn);

        // объект постраничности
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        //результат запроса с постраничным выводом
        Page result = boardService.findByParams(title, pageRequest);

        return ResponseEntity.ok(result);
    }
}

