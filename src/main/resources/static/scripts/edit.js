document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('product-form');

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const id = form.dataset.id;

        const data = {
            name: document.getElementById('name').value,
            price: document.getElementById('price').value,
            imageURL: document.getElementById('imgURL').value
        };

        fetch(`/api/products/${id}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                alert('상품이 성공적으로 수정되었습니다.');
                location.href = '/';
            } else {
                alert('상품 수정에 실패하였습니다.');
            }
        })
        .catch(error => {
            alert('Error: ' + error.message);
        });
    });
});
