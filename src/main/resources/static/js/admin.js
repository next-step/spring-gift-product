document.getElementById("create-product-form").addEventListener("submit", async function (e) {
    e.preventDefault();

    const data = {
        name: document.getElementById("create-name").value,
        price: parseFloat(document.getElementById("create-price").value),
        imageUrl: document.getElementById("create-imageUrl").value
    };

    const response = await fetch("/api/products", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        alert("상품이 등록되었습니다!");
        window.location.reload();
    } else {
        const errorMessage = await response.text(); // 응답 본문 받아오기
        alert("등록에 실패했습니다: " + errorMessage); // 서버 메시지 표시
    }
});

document.getElementById("patch-product-form").addEventListener("submit", async function (e) {
    e.preventDefault();

    const productId = document.getElementById("patch-id").value

    const data = {
        id: document.getElementById("patch-id").value,
        name: document.getElementById("patch-name").value,
        price: parseFloat(document.getElementById("patch-price").value),
        imageUrl: document.getElementById("patch-imageUrl").value
    };

    const response = await fetch(`/api/products/${productId}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        alert("상품이 수정되었습니다");
        window.location.reload();
    } else {
        alert("수정에 실패했습니다.");
    }
});

document.getElementById("delete-btn").addEventListener('click', async function (event) {

    const productId = event.target.dataset.productId;
    if (!confirm(`정말로 ${productId}번 상품을 삭제하시겠습니까?`)) {
        return
    }
    const response = await fetch(`/api/products/${productId}`, {
        method: "DELETE",
    })

    if (response.ok) {
        alert("상품이 삭제되었습니다.");
        window.location.reload();
    } else {
        alert("삭제를 실패했습니다.");
    }

});

document.querySelectorAll('.patch-btn').forEach(button => {
    button.addEventListener('click', function (event) {
        const patchButton = event.target;

        const productId = patchButton.getAttribute('data-product-id');
        const productName = patchButton.getAttribute('data-product-name');
        const productPrice = patchButton.getAttribute('data-product-price');
        const productImageUrl = patchButton.getAttribute('data-product-image-url');

        document.getElementById('patch-id').value = productId;
        document.getElementById('patch-name').value = productName;
        document.getElementById('patch-price').value = productPrice;
        document.getElementById('patch-imageUrl').value = productImageUrl;

        document.getElementById('patch-product-form').style.display = 'block';
    });
});
