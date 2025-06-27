const modal = document.getElementById('productModal');
const modalTitle = document.getElementById('modalTitle');
let modalMode = 'add';
let selectedProductId = null;

// 페이지 로드시 상품 목록 조회
document.addEventListener('DOMContentLoaded', loadProducts);

// 상품 추가 모달 열기
function showAddModal() {
  openModal('add');
}

// 상품 목록 조회
function loadProducts() {
  fetch('/api/products')
  .then(response => {
    if (!response.ok) throw new Error('상품 목록을 불러올 수 없습니다.');
    return response.json();
  })
  .then(products => {
    const tbody = document.querySelector('#productTable tbody');
    tbody.innerHTML = products.length === 0
        ? '<tr><td colspan="4" class="empty-state">등록된 상품이 없습니다.</td></tr>'
        : products.map(product => `
          <tr>
            <td>${product.name}</td>
            <td>${product.price.toLocaleString()}원</td>
            <td>${product.imageUrl}</td>
            <td>
              <button class="btn btn-edit" onclick="editProduct(${product.id})">✏️ 수정</button>
              <button class="btn btn-delete-row" onclick="deleteProduct(${product.id})">🗑️ 삭제</button>
            </td>
          </tr>
        `).join('');
  })
  .catch(error => {
    console.error('Error:', error);
    alert('상품 목록을 불러오는데 실패했습니다.');
  });
}

// 상품 수정을 위한 별도 함수
function editProduct(id) {
  fetch(`/api/products/${id}`)
  .then(response => {
    if (!response.ok) throw new Error('상품 정보를 불러올 수 없습니다.');
    return response.json();
  })
  .then(product => {
    openModal('update', product.id, product.name, product.price, product.imageUrl);
  })
  .catch(error => {
    console.error('Error:', error);
    alert('상품 정보를 불러오는데 실패했습니다.');
  });
}

// 모달 열기
function openModal(mode, id = null, name = '', price = '', imageUrl = '') {
  modalMode = mode;
  selectedProductId = id;
  modalTitle.textContent = mode === 'add' ? '상품 추가' : '상품 수정';
  document.getElementById('submitBtn').textContent = mode === 'add' ? '상품 추가' : '수정 완료';

  // 폼 초기화 후 값 설정
  document.getElementById('productForm').reset();

  if (mode === 'update') {
    document.getElementById('productName').value = name || '';
    document.getElementById('productPrice').value = price || '';
    document.getElementById('productImageUrl').value = imageUrl || '';
  }

  modal.style.display = 'block';
  document.getElementById('productName').focus();
}

// 모달 닫기
function closeModal() {
  modal.style.display = 'none';
  document.getElementById('productForm').reset();
}

// 폼 제출 처리
document.getElementById('productForm').addEventListener('submit', function(e) {
  e.preventDefault();

  const name = document.getElementById('productName').value.trim();
  const price = parseInt(document.getElementById('productPrice').value);
  const imageUrl = document.getElementById('productImageUrl').value.trim();

  // 유효성 검사
  if (!name) {
    alert('상품명을 입력해주세요.');
    return;
  }
  if (isNaN(price) || price < 0) {
    alert('올바른 가격을 입력해주세요.');
    return;
  }

  const data = { name, price, imageUrl };
  const url = modalMode === 'add' ? '/api/products' : `/api/products/${selectedProductId}`;
  const method = modalMode === 'add' ? 'POST' : 'PUT';

  fetch(url, {
    method: method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
  .then(response => {
    if (!response.ok) throw new Error('요청 처리에 실패했습니다.');
    return response.json();
  })
  .then(() => {
    alert(modalMode === 'add' ? '상품이 추가되었습니다.' : '상품이 수정되었습니다.');
    closeModal();
    loadProducts();
  })
  .catch(error => {
    console.error('Error:', error);
    alert(modalMode === 'add' ? '상품 추가에 실패했습니다.' : '상품 수정에 실패했습니다.');
  });
});

// 상품 삭제
function deleteProduct(id) {
  if (!confirm('정말 삭제하시겠습니까?')) return;

  fetch(`/api/products/${id}`, { method: 'DELETE' })
  .then(response => {
    if (!response.ok) throw new Error('삭제에 실패했습니다.');
    alert('상품이 삭제되었습니다.');
    loadProducts();
  })
  .catch(error => {
    console.error('Error:', error);
    alert('상품 삭제에 실패했습니다.');
  });
}


// 모달 외부 클릭시 닫기
window.onclick = function(event) {
  if (event.target === modal) {
    closeModal();
  }
}