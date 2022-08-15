package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "registration", description = "The Registration API")
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Registration new user",
            description = "Registration new user",
            tags = {"registration"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User has been registered",
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User has not been registered - Bad request"
            )
    })

    @PostMapping
    public ResponseEntity<UserDto> registration(@RequestBody UserDto userDto) throws RepositoryExceptions, ServiceExceptions {
        UserDto addNewUserDto = userService.addNewUser(userDto);
        if (Optional.ofNullable(addNewUserDto).isPresent()) {
            return new ResponseEntity<>(addNewUserDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}