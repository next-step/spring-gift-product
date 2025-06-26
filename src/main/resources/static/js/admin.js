// 삭제 기능은 AJAX요청을 사용해보도록 함
$(document).ready(function () {
  $(".delete-btn").click(function () { // 삭제 버튼을 클릭하면
    const id = $(this).data("id");
    if (confirm("정말 삭제하시겠습니까?")) { // 한번 더 확인해보고
      $.ajax({
        url: `/api/products/${id}`,  // Step1에서 만들어둔 상품 삭제 API를 활용함
        type: "DELETE",
        success: function () {
          alert("삭제 완료");
          location.reload();
        },
        error: function () {
          alert("삭제 실패");
        }
      });
    }
  });
});
