function openCreateModal(titleText, file) {
    fetch(file)
        .then(response => response.text())
        .then(html => {
            const modalEl = document.getElementById('product-modal');

            document.querySelector('.product-modal-title').textContent = titleText;
            document.getElementById('product-modal').style.display = 'flex';
            document.querySelector('.product-modal-body').innerHTML = html;

            modalEl.dispatchEvent(new Event('modalready'));
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

// 테이블 내의 수정 버튼 클릭 이벤트
document.querySelectorAll('.update-button').forEach(button => {
    button.addEventListener('click', event => {
        const row = event.currentTarget.closest('tr');
        const cells = Array.from(row.cells);

        const productId = cells[1].textContent.trim();
        const id        = cells[2].textContent.trim();
        const name      = cells[3].textContent.trim();
        const price     = cells[4].textContent.trim();
        const imageUrl  = cells[5].textContent.trim();

        openCreateModal('물품 수정 창', 'update-product.html');

        const modalEl = document.getElementById('product-modal');
        modalEl.addEventListener('modalready', () => {
          document.getElementById('productId').value = productId;
          document.getElementById('id').value         = id;
          document.getElementById('name').value       = name;
          document.getElementById('price').value      = price;
          document.getElementById('imageUrl').value   = imageUrl;
        }, { once: true });
    });
});

// 테이블 내의 삭제 버튼 클릭 이벤트
document.querySelectorAll('.delete-button').forEach(button => {
    button.addEventListener('click', event => {
        const row = event.currentTarget.closest('tr');
        const cells = Array.from(row.cells);

        const productId = cells[1].textContent.trim();

        fetch(`/view/products/${productId}`, {
            method: 'DELETE'
        })
        .then (response => {
            if (response.ok) {
                window.location.reload();
            } else {
                console.error('삭제 실패:', res.status);
            }
        });
    });
});