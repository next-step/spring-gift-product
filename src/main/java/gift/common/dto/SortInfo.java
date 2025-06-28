package gift.common.dto;

public record SortInfo(String field, boolean isAscending) {

  public SortInfo {
    if(field==null){
      throw new IllegalArgumentException("정렬 필드값은 null일 수 없습니다");
    }
    if (field.isEmpty()) {
      throw new IllegalArgumentException("정렬 필드값은 빈 값일 수 없습니다");
    }
  }

}