package by.teachmeskills.eshop.utils;

public enum RequestParamsEnum {
    LOGIN_PARAM("username"),
    SHOPPING_CART_PARAM("cart"),
    CATEGORY_PARAM("category"),
    PRODUCT_PARAM("product"),
    CATEGORIES_PARAM("categories"),
    LOGGED_IN_USER_PARAM("loggedInUser"),
    ORDER_ID_PARAM("order_id"),
    PRICE_ORDER_PARAM("priceOrder"),
    USER_ORDERS_PARAM("userOrders"),
    SEARCH_RESULT_PARAM("search_result"),
    PAGE_NUMBER_PARAM("page_number"),
    ERROR_PARAM("error");

    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}