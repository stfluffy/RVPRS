function searchItems(query, list) {
    return list.filter(item => item.name
        .toLowerCase()
        .includes(
            query.trim().toLowerCase()
        )
    );
}
