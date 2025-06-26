function openCreateModal(titleText, file) {
    fetch(file)
        .then(response => response.text())
        .then(html => {
            document.querySelector('.product-modal-title').textContent = titleText;
            document.getElementById('product-modal').style.display = 'flex';
            document.querySelector('.product-modal-body').innerHTML = html;
        });
}

function closeModal() {
    document.querySelector('.product-modal-title').textContent = '';
    document.getElementById('product-modal').style.display = 'none';
    document.querySelector('.product-modal-body').innerHTML = '';
}

// 체크박스 클릭 후, 모두 삭제 버튼
document.getElementById('delete-button').addEventListener('click', () => {
    const selectKeys = document.querySelectorAll('input[name=selectedKey]:checked');
    const selectkeyList = Array.from(selectKeys).map(selectKeys => selectKeys.value);
    const selectkeyLength = selectkeyList.length;

    if (!selectkeyLength) {
        alert('선택된 물품이 없습니다!')
    }
    else {
        const promises = selectkeyList.map(key =>
            fetch(`/view/products/${key}`, {
                        method: 'DELETE'
            })
        );

        Promise.all(promises)
            .then(() => {
                alert('선택 물품 삭제 완료!');
                window.location.reload();
            });
    }
});