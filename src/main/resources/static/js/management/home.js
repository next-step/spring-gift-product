var selectedProductIds = new Set();

function handleCreateButtonClick() {
    window.location.href = "/products/management/create";
}

function handleDeleteButtonClick() {
    if (selectedProductIds.size == 0) {
        alert("삭제할 상품을 선택하세요.");
        return;
    }

    if (!confirm(`${selectedProductIds.size}개의 상품을 삭제하시겠습니까?`)) {
        return;
    }

    let index = 0;
    const total = selectedProductIds.size;

    for (let id of selectedProductIds) {
        index++;

        fetch(`/api/products/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                console.error(`Product ${id} 삭제 실패`);
            }
        })
        .catch(error => {
            console.error("삭제 에러: ", error);
            alert("에러 발생");
        })
        .finally(() => {
            if (index === total) {
                location.reload();
            }
        })
    }
}

function handleCheckboxClick(id, checked) {
    if (checked) {
        selectedProductIds.add(id);
    }
    else {
        selectedProductIds.delete(id);
    }
}

function handleSingleUpdateClick(id) {
    window.location.href = `/products/management/${id}`;
}

function handleSingleDeleteClick(id) {
    fetch(`/api/products/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        }
        else {
            console.error(`Product ${id} 삭제 실패`);
            alert("삭제 실패");
        }
    })
    .catch(error => {
        console.error("삭제 에러: ", error);
        alert("에러 발생");
    })
}
