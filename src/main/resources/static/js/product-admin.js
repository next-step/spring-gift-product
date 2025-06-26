const modal = document.getElementById('productModal');
const modalTitle = document.getElementById('modalTitle');
let modalMode = 'add';
let selectedProductId = null;

// 페이지 로드시 상품 목록 조회
document.addEventListener('DOMContentLoaded', loadProducts);

// 상품 목록 조회
function loadProducts() {
  fetch('/api/products')
  .then(response => response.json())
  .then(products => {
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = products.length === 0
        ? '<tr><td colspan="4" class="empty-state">등록된 상품이 없습니다.</td></tr>'
        : products.map(product => `
          <tr>
            <td>${product.name}</td>
            <td>${product.price.toLocaleString()}원</td>
            <td>${product.imageUrl || '-'}</td>
            <td>
              <button class="btn btn-edit" onclick="openModal('update', ${product.id}, '${product.name}', ${product.price}, '${product.imageUrl || ''}')">✏️ 수정</button>
              <button class="btn btn-delete-row" onclick="deleteProduct(${product.id})">🗑️ 삭제</button>
            </td>
          </tr>
        `).join('');
  });
}

// 모달 열기/닫기
function openModal(mode, id = null, name = '', price = '', imageUrl = '') {
  modalMode = mode;
  selectedProductId = id;
  modalTitle.textContent = mode === 'add' ? '상품 추가' : '상품 수정';
  document.getElementById('submitBtn').textContent = mode === 'add' ? '상품 추가' : '수정 완료';

  if (mode === 'update') {
    document.getElementById('productName').value = name;
    document.getElementById('productPrice').value = price;
    document.getElementById('productImageUrl').value = imageUrl;
  } else {
    document.getElementById('productForm').reset();
  }
  modal.style.display = 'block';
}

function closeModal() {
  modal.style.display = 'none';
}

// 폼 제출
document.getElementById('productForm').addEventListener('submit', function(e) {
  e.preventDefault();
  const formData = new FormData(this);
  const data = Object.fromEntries(formData.entries());
  data.price = parseInt(data.price);

  const url = modalMode === 'add' ? '/api/products' : `/api/products/${selectedProductId}`;
  const method = modalMode === 'add' ? 'POST' : 'PUT';

  fetch(url, {
    method: method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
  .then(() => {
    closeModal();
    loadProducts();
  });
});

// 상품 삭제
function deleteProduct(id) {
  if (confirm('정말 삭제하시겠습니까?')) {
    fetch(`/api/products/${id}`, { method: 'DELETE' })
    .then(() => loadProducts());
  }
}

// 모달 외부 클릭시 닫기
window.onclick = function(event) {
  if (event.target === modal) closeModal();
}