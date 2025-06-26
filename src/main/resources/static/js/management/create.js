function handleExitButtonClick() {
    window.location.href("/products/management/home");
}

function handleSubmit(event) {
    event.preventDefault();

    const form = event.target;

    const productData = {
        name: form.name.value,
        price: parseInt(form.price.value),
        imageUrl: form.imageUrl.value
    };

    fetch("/api/products", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/products/management/home";
        }
        else {
            alert("생성 실패");
        }
    })
    .catch(error => {
        console.error("에러 발생: ", error);
        alert("에러 발생");
    })
}