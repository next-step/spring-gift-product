document.getElementById('product-form').addEventListener('submit',
    function (event) {
        event.preventDefault();

        const data = {
            name: document.getElementById('name').value,
            price: document.getElementById('price').value,
            imageURL: document.getElementById('imgURL').value
        };

        fetch('/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                alert('상품이 성공적으로 추가되었습니다.');
                location.href = '/';
            } else {
                alert('상품 추가에 실패하였습니다.');
            }
        })
        .catch(error => {
            alert('Error: ' + error);
        });
    }
);