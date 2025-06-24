package gift.repository;

import gift.dto.CreateItemDto;
import gift.entity.Item;

public interface ItemRepository {
    Item saveItem(CreateItemDto dto);
}

