// Admin's products management script

const modal = document.getElementById('modal');
const modalTitle = document.getElementById('modal-title');
let modalMode = 'none';
let selectedProductId = null;

// Functions of Open and close modal
function openModal(mode, productId = null) {
    modalMode = mode;
    modalTitle.innerText = (mode === 'add') ? '상품 추가' : '상품 수정';
    if (mode === 'update') selectedProductId = productId;
    modal.style.display = 'block';
}
function closeModal() {
    modal.style.display = 'none';
}

document.getElementById('infoForm').addEventListener('submit', function(event) {
    event.preventDefault();
    if (modalMode === 'add') {
        addProduct();
    } else if (modalMode === 'update') {
        updateProduct(selectedProductId);
    }
    closeModal();
});

// Functions communicate with the backend
function addProduct() {
    sendProductData('http://localhost:8080/api/products', 'POST');
}

function updateProduct(id) {
    sendProductData(`http://localhost:8080/api/products/${id}`, 'PUT');
}

function deleteProduct(productId) {
    if (productId) {
        fetch(`http://localhost:8080/api/products/${productId}`, {
            method: 'DELETE'
        }).then(() => {
            location.reload();
        });
    }
}

function sendProductData(url, method) {
    const formData = new FormData(document.getElementById("infoForm"));
    const data = Object.fromEntries(formData.entries());

    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    }).then(() => {
        location.reload();
    });
}