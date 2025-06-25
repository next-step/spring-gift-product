function openUpdateModal(id, name, price, imageUrl) {
    document.getElementById('update-id').value = id;
    document.getElementById('update-name').value = name;
    document.getElementById('update-price').value = price;
    document.getElementById('update-image-url').value = imageUrl;
    document.getElementById('updateModal').style.display = 'block';
}

function closeModal() {
    document.querySelectorAll('.modal').forEach(modal => {
        modal.style.display = 'none';
    });
}

document.querySelector('.create-btn').addEventListener('click', function () {
    document.getElementById('createModal').style.display = 'block';
})

document.querySelectorAll('.update-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        const id = this.dataset.id;
        const name = this.dataset.name;
        const price = this.dataset.price;
        const imageUrl = this.dataset.imageUrl;

        openUpdateModal(id, name, price, imageUrl);
    });
});

document.querySelectorAll('.delete-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        const id = this.dataset.id;
        document.getElementById('delete-id').value = id;
        document.getElementById('deleteModal').style.display = 'block';
    })
})

document.querySelectorAll('.cancel-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        closeModal();
    });
});
