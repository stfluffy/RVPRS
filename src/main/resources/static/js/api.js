function getList() {
    return fetch('http://localhost:8080/api/products', {
        method: 'GET'
    })
}

function createItem(data) {
    return fetch('http://localhost:8080/api/products', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        }
    })
}

function updateItem(data) {
    return fetch(`http://localhost:8080/api/products/${data.id}`, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        }
    })
}

function deleteItem(id) {
    return fetch(`http://localhost:8080/api/products/${id}`, {
        method: 'DELETE'
    })
}

