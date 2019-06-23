function showAndHide(show, hidden) {
   if (show !== null) $(`#${show}`).removeClass('d-none');
    if (hidden !== null)  $(`#${hidden}`).addClass('d-none');
}
const apiResource = 'http://localhost:80/api/';
