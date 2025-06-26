document.addEventListener('DOMContentLoaded', () => {
    const addProductBtn = document.getElementById('add-product-btn');
    const productModal = document.getElementById('product-modal');
    const closeModalBtn = document.querySelector('.close-button');
    const productForm = document.getElementById('product-form');
    const modalTitle = document.getElementById('modal-title');
    const productIdField = document.getElementById('product-id');

    const openModal = () => {
        productModal.style.display = 'block';
    };

    const closeModal = () => {
        productModal.style.display = 'none';
    };

    addProductBtn.addEventListener('click', () => {
        productForm.reset();
        modalTitle.textContent = '새 상품 추가';
        productIdField.value = '';
        openModal();
    });

    closeModalBtn.addEventListener('click', closeModal);

    window.addEventListener('click', (event) => {
        if (event.target === productModal) {
            closeModal();
        }
    });

    productForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const productData = {
            name: document.getElementById('name').value,
            price: parseInt(document.getElementById('price').value, 10),
            imageUrl: document.getElementById('imageUrl').value,
        };

        axios.post('/api/products', productData)
            .then(response => {
                if (response.status === 201) {
                    alert('상품이 성공적으로 추가되었습니다.');
                    closeModal();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                
                const errorMessage = error.response?.data?.message || '상품 추가에 실패했습니다. 입력값을 확인해주세요.';
                alert(errorMessage);
            });
    });
});
