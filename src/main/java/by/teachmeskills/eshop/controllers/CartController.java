package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.CartDto;
import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import java.util.Optional;

import static by.teachmeskills.eshop.utils.EshopConstants.PRODUCT_ID_PARAM;
import static by.teachmeskills.eshop.utils.EshopConstants.USER_ID;

@Tag(name = "cart", description = "The Cart API")
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final Cart cart = new Cart();

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(
            summary = "Add product to cart",
            description = "Cart page with product in eshop",
            tags = {"cart"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product was added to cart",
                    content = @Content(schema = @Schema(implementation = CartDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Product wasn't be added in cart- Bad request"
            )
    })

    @PostMapping("/add")
    public ResponseEntity<CartDto> addProductToCart(@Min(value = 1) @RequestParam(PRODUCT_ID_PARAM) int productId) throws RepositoryExceptions {
        CartDto cartDtoResponse = cartService.addProductToCart(productId, cart);
        if (Optional.ofNullable(cartDtoResponse).isPresent()) {
            return new ResponseEntity<>(cartDtoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(
            summary = "Remove product",
            description = "Remove product from cart",
            tags = {"cart"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product has been removed from cart",
                    content = @Content(schema = @Schema(implementation = CartDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Product wasn't be removed from cart- Bad request"
            )
    })

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<CartDto> deleteProductFromCart(@Min(value = 1) @PathVariable int productId) {
        CartDto cartDtoDeleteProductResponse = cartService.deleteProductFromCart(productId, cart);
        if (Optional.ofNullable(cartDtoDeleteProductResponse).isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Clear cart",
            description = "Remove all products from cart",
            tags = {"cart"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products has been removed from cart",
                    content = @Content(schema = @Schema(implementation = CartDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Products wasn't be removed from cart- Bad request"
            )
    })

    @DeleteMapping("/clear")
    public ResponseEntity<CartDto> deleteAllProducts() {
        CartDto cartDtoClearCart = cartService.deleteAllProductsFromCart(cart);
        if (Optional.ofNullable(cartDtoClearCart).isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Buy product",
            description = "Buy product in cart",
            tags = {"cart"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products has been purchased",
                    content = @Content(schema = @Schema(implementation = CartDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Products wasn't be purchased- Bad request"
            )
    })

    @PostMapping("/buy")
    public ResponseEntity<String> buyProducts(@RequestParam(USER_ID) int userId) {
        String message = cartService.orderProducts(userId, cart);
        if (message.equals("Products were successfully ordered")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}