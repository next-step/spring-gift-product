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