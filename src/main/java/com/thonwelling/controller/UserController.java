package com.thonwelling.controller;

import com.thonwelling.exceptions.BusinessException;
import com.thonwelling.models.dtos.UserDto;
import com.thonwelling.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "RESTful API To Manage Users.")
public record UserController(UserService userService) {

  @GetMapping
  @Operation(summary = "Get The User List", description = "Retrieve a List Of Registered Users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Operation successful")
  })
  public ResponseEntity<List<UserDto>> findAll() {
    var userList = userService.findAll();
    var userListDto = userList.stream().map(UserDto::new).collect(Collectors.toList());
    return ResponseEntity.ok(userListDto);
  }
  @GetMapping("/{userId}")
  @Operation(summary = "Get User by ID", description = "Retrieve a Specific User By ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Operation successful"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  public ResponseEntity<UserDto> findUserById(@PathVariable Long userId) {
    var user = userService.findById(userId);
    return ResponseEntity.ok(new UserDto(user));
  }

  @PostMapping
  @Operation(summary = "Create a New User", description = "Create a New User And Return The Created User's Data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created successfully"),
      @ApiResponse(responseCode = "422", description = "Invalid user data provided")
  })
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws BusinessException {
    var newUser = userService.create(userDto.toModel());
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newUser.getUserId())
        .toUri();
    return ResponseEntity.created(location).body(new UserDto(newUser));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a User", description = "Update The Data Of An User By ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "422", description = "Invalid user data provided")
  })
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws BusinessException {
    var user = userService.update(id, userDto.toModel());
    return ResponseEntity.ok(new UserDto(user));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a User", description = "Delete an User By ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "User deleted successfully"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws BusinessException {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}