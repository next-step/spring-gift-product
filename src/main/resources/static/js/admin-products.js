document.addEventListener('DOMContentLoaded', () => {
    const addProductBtn = document.getElementById('add-product-btn');
    const productModal = document.getElementById('product-modal');
    const closeModalBtn = document.querySelector('.close-button');
    const productForm = document.getElementById('product-form');
    const modalTitle = document.getElementById('modal-title');
    const productIdField = document.getElementById('product-id');
    const tableBody = document.querySelector("#product-table tbody");
    getProducts();

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
            const id = document.getElementById('product-id').value;

            if (id) {
                updateProduct(id, productData);
            } else {
                addNewProduct(productData);
            }
        }
    );

    tableBody.addEventListener('click', (event) => {
        const target = event.target;
        if (target.classList.contains('edit-btn')) {
            const productId = target.dataset.id;
            openEditModal(productId);
        }
        if (target.classList.contains('delete-btn')) {
            const productId = target.dataset.id;
            deleteProduct(productId);
        }
    });
});


function getProducts(page = 0, size = 5) {
    axios.get(`/api/products?page=${page}&size=${size}`)
        .then(response => {
            const pageData = response.data;
            renderTable(pageData.content);
            renderPagination(pageData);
        })
}

function renderTable(products) {
    const tableBody = document.querySelector("#product-table tbody");
    tableBody.innerHTML = '';

    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td><img src="${product.imageUrl}" alt="${product.name}" width="80"></td>
            <td>
                <button class="edit-btn" data-id="${product.id}">수정</button>
                <button class="delete-btn" data-id="${product.id}">삭제</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function renderPagination(pageData) {
    const paginationControls = document.getElementById('pagination-controls');
    paginationControls.innerHTML = '';

    if (pageData.totalPages === 0) return;

    if (pageData.totalPages === 1) {
        const pageButton = document.createElement('button');
        pageButton.innerText = '1';
        pageButton.classList.add('current');
        paginationControls.appendChild(pageButton);
        return;
    }

    const currentPageNumber = pageData.number;

    if (pageData.hasPrevious) {
        const prevButton = document.createElement('button');
        prevButton.innerText = '이전';
        prevButton.onclick = () => getProducts(currentPageNumber - 1);
        paginationControls.appendChild(prevButton);
    }

    for (let i = 0; i < pageData.totalPages; i++) {
        const pageButton = document.createElement('button');
        pageButton.innerText = i + 1;
        if (i === currentPageNumber) {
            pageButton.classList.add('current');
        }
        pageButton.onclick = () => getProducts(i);
        paginationControls.appendChild(pageButton);
    }

    if (pageData.hasNext) {
        const nextButton = document.createElement('button');
        nextButton.innerText = '다음';
        nextButton.onclick = () => getProducts(currentPageNumber + 1);
        paginationControls.appendChild(nextButton);
    }
}

function addNewProduct(productData) {
    axios.post('/api/products', productData)
        .then(response => {
            if (response.status === 201) {
                alert('상품이 성공적으로 추가되었습니다.');
                document.getElementById('product-modal').style.display = 'none';
                getProducts();
            }
        })
        .catch(error => {
            console.error('상품 추가 실패:', error);
            const errorMessage = error.response?.data?.message || '상품 추가에 실패했습니다.';
            alert(errorMessage);
        });
}

function openEditModal(id) {
    axios.get(`/api/products/${id}`)
        .then(response => {
            const product = response.data;
            const productModal = document.getElementById('product-modal');
            const productForm = document.getElementById('product-form');

            productForm.querySelector('#modal-title').textContent = '상품 수정';
            productForm.querySelector('#product-id').value = product.id;
            productForm.querySelector('#name').value = product.name;
            productForm.querySelector('#price').value = product.price;
            productForm.querySelector('#imageUrl').value = product.imageUrl;

            productModal.style.display = 'block';
        })
}

function updateProduct(id, productData) {
    axios.put(`/api/products/${id}`, productData)
        .then(response => {
            if (response.status === 204) {
                alert('상품이 성공적으로 수정되었습니다.');
                document.getElementById('product-modal').style.display = 'none';
                getProducts();
            }
        })
}

function deleteProduct(id) {
    if (confirm(`정말로 이 상품(ID: ${id})을 삭제하시겠습니까?`)) {
        axios.delete(`/api/products/${id}`)
            .then(response => {
                if (response.status === 204) {
                    alert('상품이 성공적으로 삭제되었습니다.');
                    getProducts();
                }
            })
    }
}
