async function requestCreateProduct() {
    const prodName = document.getElementById("prod-name-input").value;
    const prodPrice = document.getElementById("prod-price-input").value;
    const prodImageUrl = document.getElementById("prod-url-input").value;
    console.log("함수 호출됨");
    if (!validateProduct(prodName, prodPrice, prodImageUrl)) {
        return;
    }

    const product = {
        name: prodName,
        price: prodPrice,
        imageUrl: prodImageUrl
    };

    const res = await fetch("/api/products", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(product)
    });
    console.log("요청 보냄", res);
    if (res.ok) {
        alert("상품이 성공적으로 등록되었습니다!");
        window.location.href = "/admin/products";
    } else {
        const errorData = await res.json();
        alert(`상품 등록에 실패했습니다: ${errorData.message}`);
    }

}

function validateProduct(name, price, imageUrl) {
    if (name === "") {
        alert("이름을 입력해주세요!");
        return false;
    }
    if (price === "") {
        alert("가격을 입력해주세요!");
        return false;
    }
    if (price <= 0) {
        alert("가격은 0보다 커야 합니다!");
        return false;
    }
    if (imageUrl === "") {
        alert("이미지 URL을 입력해주세요!");
        return false;
    }
    return true;
}

