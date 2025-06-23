package gift.repository;

import gift.dto.request.RequestGift;
import gift.dto.request.RequestModifyGift;
import gift.entity.Gift;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GiftRepository {
    // giftMap 필드를 처음 선언할 당시에, ConcurrentMap 을 설정할 수 있었으나,
    // 생성자 내용에 아무것도 들어가지 않으므로, ConcurrentMap 을 생성자 부분에서 할당하는 식으로 하였다.

    // 생성자 측에 ConcurrentMap 을 하면, 다른 Repository 에서 Repository 에 대한 인스턴스를 생성 시
    // 다른 Repository 가 생성된다고 생각하였다.
    // 하지만 생성자가 아닌 필드측에서 ConcurrentMap 을 생성시키면, GiftRepository 에 대해서 giftMap 에 대한
    // 접근을 할 수 있도록 하는 것이므로 데이터에 대한 통일을 이뤄낼 수 있다.
    private final Map<Long, Gift> giftMap = new ConcurrentHashMap<>();

    public GiftRepository() {}

    public void save(RequestGift requestGift) {
        giftMap.put(requestGift.id(), RequestGift.from(requestGift));
    }

    public Gift findById(Long id) {
        Gift gift = giftMap.get(id);
        if(gift == null){
            throw new RuntimeException("해당 상품이 없습니다.");
        }
        return giftMap.get(id);
    }

    public List<Gift> findAll(){
        return new ArrayList<>(giftMap.values());
    }

    public Gift modify(Long id, RequestModifyGift requestModifyGift) {
        Gift gift = giftMap.get(id);
        if(gift == null){
            throw new RuntimeException("해당 상품이 없습니다.");
        }
        gift.setPrice(requestModifyGift.price());
        gift.setName(requestModifyGift.name());
        gift.setImageUrl(requestModifyGift.imageUrl());
        giftMap.put(id, gift);

        return gift;
    }

    public void delete(Long id) {
        giftMap.remove(id);
    }
}
