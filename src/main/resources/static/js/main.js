function showAndHide(show, hidden) {
    $(`#${show}`).removeClass('d-none');
    $(`#${hidden}`).addClass('d-none');
}
const apiResource = 'http://localhost:80/api/';
