package gift.repository;

import gift.dto.CreateItemDTO;
import gift.entity.Item;

public interface ItemRepository {
    Item saveItem(CreateItemDTO dto);
}
