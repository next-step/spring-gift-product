// 체크박스 클릭 후, 모두 삭제 버튼
document.getElementById('delete-button').addEventListener('click', () => {
    const selectKeys = document.querySelectorAll('input[name=selectedKey]:checked');
    const selectkeyList = Array.from(selectKeys).map(selectKeys => selectKeys.value);

    const promises = selectkeyList.map(key =>
        fetch(`/view/products/${key}`, {
                    method: 'DELETE'
                })
    );

    Promise.all(promises)
        .then(() => {
            alert('선택 물품 삭제 완료!');
            window.location.reload(); // 새로고침
        });


});