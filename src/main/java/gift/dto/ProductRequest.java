package dto;

public record ProductRequest(Long categoryId, String name, int price, String imageUrl) {}