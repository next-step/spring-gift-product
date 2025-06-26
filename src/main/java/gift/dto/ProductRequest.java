package gift.dto;

/*
 ProductRequest와 ProductResponse 현재 동일한 코드 구조를 가지고 있어 하나의 dto로 합쳐도 되지만,
 이후에 기능 확장, 필드 추가 등을 고려해 미리 분리해 놓는 게 낫다고 판단했습니다.
*/

public record ProductRequest(Long id, String name, Integer price, String imageUrl) {

}
