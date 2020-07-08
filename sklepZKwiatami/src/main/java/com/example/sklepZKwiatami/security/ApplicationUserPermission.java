package com.example.sklepZKwiatami.security;

public enum ApplicationUserPermission {
    BUY("buy"),
    SELL("sell"),
    PRODUCT_MODIFY("product:modify"),
    USER_MODIFY("user:modify"),
    HISTORY("history"),
    USER_THEMSELF_MODIFY("user:themself:modify");

    private String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
