const selectedProductIds = new Set();
let currentPage = 1; // 현재 페이지
const pageSize = 5; // 한 페이지당 아이템 수

const tbody = document.getElementById('product-list-body');
const selectAllCheckbox = document.getElementById('product-all-select');
const createButton = document.getElementById('product-create-btn');
const deleteAllButton = document.getElementById('product-delete-all-btn');

const paginationDiv = document.createElement('div');
paginationDiv.id = 'pagination';

const prevPageBtn = document.createElement('button');
prevPageBtn.id = 'prev-page-btn';
prevPageBtn.textContent = '이전';

const nextPageBtn = document.createElement('button');
nextPageBtn.id = 'next-page-btn';
nextPageBtn.textContent = '다음';

paginationDiv.appendChild(prevPageBtn);
paginationDiv.appendChild(nextPageBtn);

// 테이블 아래에 페이지네이션 UI 삽입
tbody.parentNode.after(paginationDiv);

/**
 * 상품 목록을 서버에서 가져와 테이블에 렌더링
 * pageNumber, pageSize를 받아서 서버에 쿼리 파라미터로 전달
 */
function fetchProductList(pageNumber = 1, pageSize = 10) {
  tbody.innerHTML = "";

  fetch(`/api/products?pageNumber=${pageNumber}&pageSize=${pageSize}`)
  .then(res => {
    if (!res.ok) {
      throw new Error("상품 목록 불러오기 실패");
    }
    return res.json();
  })
  .then(pageData => {
    // 서버에서 받은 Page<Product> 구조에 맞게 처리
    pageData.content.forEach(renderProductRow);
    renderPagination(pageData.page, pageData.totalPages);
  })
  .catch(err => {
    console.error(err);
    alert("상품 목록을 불러오는 데 실패했습니다.");
  });
}

/**
 * 페이지네이션 UI 업데이트
 * @param {number} current 현재 페이지 번호
 * @param {number} totalPages 총 페이지 수
 */
function renderPagination(current, totalPages) {
  prevPageBtn.disabled = current <= 1;
  nextPageBtn.disabled = current >= totalPages;
  currentPage = current;

  // 기존 숫자 버튼 제거
  const oldButtons = document.querySelectorAll('.page-number-btn');
  oldButtons.forEach(btn => btn.remove());

  // 페이지 범위 계산
  const maxVisible = 5;
  let startPage = Math.max(1, current - Math.floor(maxVisible / 2));
  let endPage = startPage + maxVisible - 1;
  if (endPage > totalPages) {
    endPage = totalPages;
    startPage = Math.max(1, endPage - maxVisible + 1);
  }

  // 숫자 버튼을 prev/next 사이에 삽입
  const paginationDiv = document.getElementById('pagination');

  for (let i = startPage; i <= endPage; i++) {
    const pageBtn = document.createElement('button');
    pageBtn.textContent = i;
    pageBtn.classList.add('page-number-btn');

    if (i === current) {
      pageBtn.style.fontWeight = 'bold';
      pageBtn.style.backgroundColor = '#e2e8f0';
    }

    pageBtn.addEventListener('click', () => {
      fetchProductList(i, pageSize);
    });

    paginationDiv.insertBefore(pageBtn, nextPageBtn); // nextBtn 앞에 삽입
  }
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
    fetchProductList(currentPage, pageSize);
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
  fetch('/api/products', {
    method: 'DELETE',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(Array.from(selectedProductIds)),
  })
  .then(res => {
    if (!res.ok) {
      throw new Error("상품 일괄 삭제 실패");
    }
    alert("선택한 상품을 모두 삭제했습니다.");
    selectedProductIds.clear();
    selectAllCheckbox.checked = false;
    fetchProductList(currentPage, pageSize);
  })
  .catch(err => {
    console.error(err);
    alert("상품 삭제 중 오류가 발생했습니다.");
    fetchProductList(currentPage, pageSize);
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
    fetchProductList(currentPage, pageSize);
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
  fetchProductList(currentPage, pageSize);
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

// 페이지네이션 버튼 이벤트 리스너 등록
prevPageBtn.addEventListener('click', () => {
  if (currentPage > 1) {
    currentPage--;
    fetchProductList(currentPage, pageSize);
  }
});
nextPageBtn.addEventListener('click', () => {
  currentPage++;
  fetchProductList(currentPage, pageSize);
});

document.addEventListener('DOMContentLoaded', () => {
  fetchProductList(currentPage, pageSize);
  tbody.addEventListener('click', handleTableClick);
  selectAllCheckbox.addEventListener('change', handleSelectAll);
  createButton.addEventListener('click', goToCreatePage);
  deleteAllButton.addEventListener('click', deleteSelectedProducts);
});
