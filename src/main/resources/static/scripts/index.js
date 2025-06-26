document.querySelectorAll('.delete').forEach(btn => {
    btn.addEventListener('click', function () {
        const id = this.getAttribute('product-id');
        if (confirm('정말 삭제하시겠습니까?')) {
            fetch(`/api/products/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    alert('상품이 삭제되었습니다');
                    location.reload();
                } else {
                    alert('삭제에 실패하였습니다.');
                }
            })
            .catch(error => {
                alert('Error: ' + error);
            });
        }
    });
});