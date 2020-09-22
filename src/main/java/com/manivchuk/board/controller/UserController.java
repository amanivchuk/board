package com.manivchuk.board.controller;

import com.manivchuk.board.service.user.UserService;
import com.manivchuk.board.transport.dto.user.UserCreateDto;
import com.manivchuk.board.transport.dto.user.UserOutcomeDto;
import com.manivchuk.board.transport.dto.user.UserUpdateDto;
import com.manivchuk.board.transport.dto.user.UserUpdateEmailDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Setter(onMethod_ = @Autowired)
@Api(value = "UserController", description = "REST API for User", tags = {"User"})
@RequestMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {

    private UserService userService;

    @ApiOperation(value = "Create a user", notes = "ADMIN, MANAGER", nickname = "createUser")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody @Valid UserCreateDto dto){
        return userService.create(dto).getId();
    }

    @ApiOperation(value = "Delete a user by id", notes = "ADMIN, MANAGER, USER", nickname = "deleteUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted successfully"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @ApiOperation(value = "Recovery a user by id", notes = "USER", nickname = "recoverUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recovered successfully"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    @PatchMapping("{id}")
    public Long recovery(@PathVariable Long id) {
        return userService.recovery(id);
    }

    @ApiOperation(value = "Update a user", notes = "USER", nickname = "updateUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update accepted"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER','USER')")
    @PutMapping("{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto){
        return userService.update(id, dto);
    }

    @ApiOperation(value = "Update a user's email", notes = "ADMIN, MANAGER, USER", nickname = "updateEmail")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update accepted"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
            @ApiResponse(code = 403, message = "User doesn't have permission"),
            @ApiResponse(code = 404, message = "Not correct data"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    @PatchMapping("email")
    public Long updateEmail(@RequestBody UserUpdateEmailDto dto) {
        return userService.update(dto);
    }

    @ApiOperation(value = "Finding users", notes = "ADMIN, MANAGER", nickname = "findAllUsers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 400, message = "Not valid dto"),
            @ApiResponse(code = 401, message = "Not correct token"),
    })
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping
    public List<UserOutcomeDto> getAll(){
        return userService.getAll();
    }
}
