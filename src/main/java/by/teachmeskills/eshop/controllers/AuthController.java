package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.config.JwtProvider;
import by.teachmeskills.eshop.dto.AuthResponse;
import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@Tag(name = "authentication", description = "The Authentication API")
@RestController
@RequestMapping("/login")
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Operation(
            summary = "Authenticate",
            description = "Opening page after authenticating user",
            tags = {"authentication"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "User is authenticated",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated - Unauthorized operation"
            )
    })

    @PostMapping
    public AuthResponse login(@Valid @RequestBody UserDto userDto) throws RepositoryExceptions {
        User user = userService.getUserByNameAndPassword(userDto.getName(), userDto.getPassword());
        String token = jwtProvider.generateToken(user.getName());
        return new AuthResponse(token);
    }

    @Operation(
            summary = "Profile Data",
            description = "Opening page with user information",
            tags = {"profile"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User data were provided",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request - bad request"
            )
    })

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDto> getProfileData(@Min(value = 1) @PathVariable int userId,
                                                  @RequestParam int pageNumber,
                                                  @RequestParam int pageSize) throws RepositoryExceptions {
        UserDto userDto = userService.getUserData(userId, pageNumber, pageSize);
        if (Optional.ofNullable(userDto).isPresent()) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}