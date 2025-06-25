const selectedProductIds = new Set();

const tbody = document.getElementById('product-list-body');
const selectAllCheckbox = document.getElementById('product-all-select');
const createButton = document.getElementById('product-create-btn');
const deleteAllButton = document.getElementById('product-delete-all-btn');

/**
 * 상품 목록을 서버에서 가져와 테이블에 렌더링
 */
function fetchProductList() {
  tbody.innerHTML = "";

  fetch("/api/products")
  .then(res => {
    if (!res.ok) {
      throw new Error("상품 목록 불러오기 실패");
    }
    return res.json();
  })
  .then(products => products.forEach(renderProductRow))
  .catch(err => {
    console.error(err);
    alert("상품 목록을 불러오는 데 실패했습니다.");
  });
}

/**
 * 상품 데이터를 테이블 행으로 렌더링
 * @param {{ id: number, imageUrl: string, name: string, price: number }} product
 */
function renderProductRow(product) {
  const tr = document.createElement('tr');
  tr.innerHTML = `
    <td><input type="checkbox" class="product-checkbox" data-id="${product.id}"></td>
    <td>${product.id}</td>
    <td><img src="${product.imageUrl}" alt="${product.name}" width="200" height="80"></td>
    <td>${product.name}</td>
    <td>${product.price}</td>
    <td>
      <button data-id="${product.id}" class="update-btn">수정</button>
      <button data-id="${product.id}" class="delete-btn">삭제</button>
    </td>
  `;
  tbody.appendChild(tr);
}

/**
 * 상품 하나를 삭제
 * @param {number} id
 */
function deleteProduct(id) {
  if (!confirm(`상품 ID ${id}를 삭제할까요?`)) {
    return;
  }

  fetch(`/api/products/${id}`, {method: 'DELETE'})
  .then(res => {
    if (!res.ok) {
      throw new Error("삭제 실패");
    }
    fetchProductList();
  })
  .catch(err => {
    console.error(err);
    alert("삭제에 실패했습니다.");
  });
}

/**
 * 선택된 상품들을 모두 삭제
 */
function deleteSelectedProducts() {
  if (selectedProductIds.size === 0) {
    alert("삭제할 상품을 선택하세요.");
    return;
  }

  if (!confirm(`선택한 ${selectedProductIds.size}개의 상품을 삭제할까요?`)) {
    return;
  }

  const deletePromises = Array.from(selectedProductIds).map(id =>
      fetch(`/api/products/${id}`, {method: 'DELETE'}).then(res => {
        if (!res.ok) {
          throw new Error(`상품 ${id} 삭제 실패`);
        }
      })
  );

  Promise.all(deletePromises)
  .then(() => {
    alert("선택한 상품을 모두 삭제했습니다.");
    selectedProductIds.clear();
    selectAllCheckbox.checked = false;
    fetchProductList();
  })
  .catch(err => {
    console.error(err);
    alert("상품 삭제 중 오류가 발생했습니다.");
    fetchProductList(); // 상태 동기화를 위해 갱신
  });
}

/**
 * 수정 모드로 행 전환
 * @param {HTMLTableRowElement} row
 * @param {number} id
 */
function enableEditMode(row, id) {
  const name = row.children[3].textContent;
  const price = row.children[4].textContent;

  row.children[3].innerHTML = `<input type="text" class="name-input" value="${name}" />`;
  row.children[4].innerHTML = `<input type="number" class="price-input" value="${price}" />`;
  row.children[5].innerHTML = `
    <button data-id="${id}" class="save-btn">저장</button>
    <button data-id="${id}" class="cancel-btn">취소</button>
  `;
}

/**
 * 상품 정보 저장 요청
 * @param {HTMLTableRowElement} row
 * @param {number} id
 */
function saveChanges(row, id) {
  const name = row.querySelector('.name-input').value.trim();
  const price = Number(row.querySelector('.price-input').value);
  const imageUrl = row.children[2].querySelector('img').src;

  if (!name || price <= 0) {
    alert("상품명과 가격을 올바르게 입력하세요.");
    return;
  }

  fetch(`/api/products/${id}`, {
    method: 'PUT',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({name, price, imageUrl}),
  })
  .then(res => {
    if (!res.ok) {
      throw new Error("수정 실패");
    }
    fetchProductList();
  })
  .catch(err => {
    console.error(err);
    alert("수정에 실패했습니다.");
  });
}

/**
 * 수정 모드 취소 후 다시 렌더링
 */
function cancelEditMode() {
  fetchProductList();
}

/**
 * 전체 선택 체크박스 핸들러
 * @param {Event} e
 */
function handleSelectAll(e) {
  const checked = e.target.checked;
  document.querySelectorAll('.product-checkbox').forEach(cb => {
    cb.checked = checked;
    const id = Number(cb.dataset.id);
    checked ? selectedProductIds.add(id) : selectedProductIds.delete(id);
  });
}

/**
 * 개별 체크박스 토글 처리
 * @param {HTMLInputElement} checkbox
 */
function toggleCheckbox(checkbox) {
  const id = Number(checkbox.dataset.id);
  checkbox.checked
      ? selectedProductIds.add(id)
      : selectedProductIds.delete(id);
}

/**
 * 테이블 클릭 이벤트 위임 핸들러
 * @param {MouseEvent} e
 */
function handleTableClick(e) {
  const target = e.target;
  const row = target.closest('tr');
  const id = Number(target.dataset.id);

  if (target.classList.contains('product-checkbox')) {
    toggleCheckbox(target);
  } else if (target.classList.contains('delete-btn')) {
    deleteProduct(id);
  } else if (target.classList.contains('update-btn')) {
    enableEditMode(row, id);
  } else if (target.classList.contains('save-btn')) {
    saveChanges(row, id);
  } else if (target.classList.contains('cancel-btn')) {
    cancelEditMode();
  }
}

/**
 * 등록 버튼 클릭 시 상품 등록 페이지로 이동
 */
function goToCreatePage() {
  window.location.href = '/admin/products/create';
}

document.addEventListener('DOMContentLoaded', () => {
  fetchProductList();
  tbody.addEventListener('click', handleTableClick);
  selectAllCheckbox.addEventListener('change', handleSelectAll);
  createButton.addEventListener('click', goToCreatePage);
  deleteAllButton.addEventListener('click', deleteSelectedProducts);
});
