$(document).ready(function () {
    // set create button handler
    $('.create-btn').click(() => {
        createEditItem();
    });

    // set create button handler
    $('#searchInput').on('input', ($evt) => renderTable(
        searchItems(
            $evt.target.value,
            $('#items-list').data('list')
        )
    ));

    showLoader();

    loadList().then(() => hideLoader());
});
