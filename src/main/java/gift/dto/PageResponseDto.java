package gift.dto;

import java.util.List;

public class PageResponseDto {
    private int currentPage;
    private int totalPages;
    private List<ProductResponseDto> pageProducts;

    public PageResponseDto(int currentPage, int totalPages, List<ProductResponseDto> pageProducts) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageProducts = pageProducts;
    }

    public int getCurrentPage() { return currentPage; }
    public int getTotalPages() { return totalPages; }
    public List<ProductResponseDto> getPageProducts() { return pageProducts; }

}
