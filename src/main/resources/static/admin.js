document.getElementById("create-product-form").addEventListener("submit", async function(e) {
    e.preventDefault(); // 폼 기본 제출 막기

    const data = {
        name: document.getElementById("name").value,
        price: parseFloat(document.getElementById("price").value),
        imageUrl: document.getElementById("imageUrl").value
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
        alert("등록에 실패했습니다.");
    }
});

document.getElementById("delete-btn").addEventListener('click', async function (event) {

    const productId = event.target.dataset.productId;
    confirm(`정말로 ${productId}번 상품을 삭제하시겠습니까?`);

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