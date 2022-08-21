package by.teachmeskills.eshop.utils;

public enum PagesPathEnum {
    START_PAGE("home"),
    SIGN_IN_PAGE("login"),
    CATEGORY_PAGE("category"),
    CART_PAGE("cart"),
    PRODUCT_PAGE("product"),
    PROFILE_PAGE("profile"),
    REGISTRATION_PAGE("registration"),
    SEARCH_PAGE("search"),
    ORDER_PAGE("orders"),
    REGISTRATION_SUCCESS_PAGE("registration_success"),
    ERROR_PAGE("error");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}