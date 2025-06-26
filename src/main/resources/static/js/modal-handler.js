document.addEventListener('DOMContentLoaded', () => {

    // 상품 추가 모달

    const modal = document.getElementById('addProductModal');
    const openBtn = document.getElementById('openModalBtn');
    const closeBtn = document.getElementById('closeModalBtn');

    if (!modal || !openBtn || !closeBtn) return;

    openBtn.onclick = () => {
        modal.style.display = 'flex';
    };

    closeBtn.onclick = () => {
        modal.style.display = 'none';
    };

    // 상품 수정 모달

    const editModal = document.getElementById('editProductModal');
    const closeEditBtn = document.getElementById('closeEditModalBtn');

    if (editModal && closeEditBtn) {
        closeEditBtn.onclick = () => {
            editModal.style.display = 'none';
        };
        window.onclick = (event) => {
            if (event.target === editModal) {
                editModal.style.display = 'none';
            }
        };
    }

    // 수정 모달 열기 함수
    window.openEditModal = function(button) {
        const product = {
            id: button.getAttribute('data-id'),
            name: button.getAttribute('data-name'),
            price: button.getAttribute('data-price'),
            imageUrl: button.getAttribute('data-imageurl')
        };

        const editModal = document.getElementById('editProductModal');
        if (!editModal) return;

        editModal.style.display = 'flex';
        document.getElementById('editProductId').value = product.id;
        document.getElementById('editProductName').value = product.name;
        document.getElementById('editProductPrice').value = product.price;
        document.getElementById('editProductImageUrl').value = product.imageUrl;

        const form = document.getElementById('editProductForm');
        form.action = `/admin/products/${product.id}`;
    };

    // 상품 삭제 모달

    const deleteModal = document.getElementById('deleteProductModal');
    const cancelBtn = document.getElementById('cancelDeleteBtn');
    const deleteForm = document.getElementById('deleteProductForm'); // form 캐싱
    let deleteId = null;

    document.querySelectorAll('.deleteBtn').forEach(button => {
        button.addEventListener('click', () => {
            deleteId = button.getAttribute('data-id');  // let deleteId → 전역에 할당
            deleteForm.action = `/admin/products/${deleteId}`;
            deleteModal.style.display = 'flex';
        });
    });

    cancelBtn.addEventListener('click', () => {
        deleteModal.style.display = 'none';
        deleteId = null;
    });

    window.addEventListener('click', (event) => {
        if (event.target === deleteModal) {
            deleteModal.style.display = 'none';
            deleteId = null;
        }
    });
});
