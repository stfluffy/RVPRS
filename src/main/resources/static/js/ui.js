function callDeleteItem(data) {
    const fragmentClone = $('#delete-modal-tpl')[0].content.cloneNode(true);

    document.body.appendChild(fragmentClone);

    const modalJQ = $('#delete-modal');

    modalJQ.modal({
        onHidden: () => modalJQ.remove(),
        onApprove: () => {
            showLoader();

            deleteItem(data.id)
                .then(() => loadList())
                .then(() => hideLoader());
        },
    }).modal('show');
}

function createEditItem(data) {
    const fragmentClone = $('#create-edit-modal-tpl')[0].content.cloneNode(true);

    document.body.appendChild(fragmentClone);

    const modalJQ = $('#create-edit-modal');

    data && setFormValue(modalJQ[0], data);

    modalJQ.modal({
        onHidden: () => modalJQ.remove(),
        onApprove: () => {
            showLoader();

            (data
                    ? updateItem({
                        id: data.id,
                        ...getFormValue($('#create-edit-modal')[0])
                    })
                    : createItem(
                        getFormValue($('#create-edit-modal')[0])
                    )
            )
                .then(() => loadList())
                .then(() => hideLoader());
        },
    }).modal('show');
}

function loadList() {
    return getList().then(response => response.json()).then(list => {
        $('#items-list').data('list', list);

        renderTable(list);
    });
}

function getFormValue(form) {
    const formValue = {};
    [...form.elements].map(input => formValue[input.name] = input.value);
    return formValue;
}

function setFormValue(form, data) {
    [...form.elements].map(input => input.value = data[input.name])
}

function showLoader() {
    $('#main-loader').show();
}

function hideLoader() {
    $('#main-loader').hide();
}

function renderTable(list) {
    const tBodyJQ = $('#items-list tbody');
    // clear table body;
    tBodyJQ[0].innerHTML = '';

    list.forEach((item) => {
        const clone = $('#productrow')[0].content.cloneNode(true);
        const td = $(clone).find("td");

        td[0].textContent = item.id;
        td[1].textContent = item.name;
        td[2].textContent = item.price;
        td[3].textContent = item.quantity;
        td[4].textContent = item.shelf;

        initItemActions(td[5], item);

        tBodyJQ[0].appendChild(clone);
    })
}

function initItemActions(td, item) {
    // set delete button handler
    $(td).find(".delete-btn").click(() => {
        callDeleteItem(item);
    });

    // set edit button handler
    $(td).find(".edit-btn").click(() => {
        createEditItem(item);
    });
}
