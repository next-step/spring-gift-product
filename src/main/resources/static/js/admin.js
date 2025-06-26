$(document).ready(function () {
  $(".delete-btn").click(function () {
    const id = $(this).data("id");
    if (confirm("정말 삭제하시겠습니까?")) {
      $.ajax({
        url: `/api/products/${id}`,
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
