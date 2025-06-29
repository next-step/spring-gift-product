package gift.common.dto;

public record PageRequest(
    int offset,
    int pageSize,
    SortInfo sortInfo
) {
  public PageRequest{
    if(offset<0){
      throw new IllegalArgumentException("page offset값은 음수일 수 없습니다.");
    }
    if(pageSize<=0){
      throw new IllegalArgumentException("page size값은 0이거나 음수일 수 없습니다");
    }
    if(sortInfo==null){
      throw new IllegalArgumentException("정렬 정보는 null일 수 없습니다.");
    }
  }
}
