package gift.item.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long itemId) {
        super("Item not found with id: " + itemId);
    }

}