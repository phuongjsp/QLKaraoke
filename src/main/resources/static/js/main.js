function showAndHide(show, hidden) {
   if (show !== null ) $(`#${show}`).removeClass('d-none');
    if (hidden !== null)  $(`#${hidden}`).addClass('d-none');
}
const apiResource = 'http://localhost/api/';

function validOnlyNumber(button, ...input) {
    let i = 0;
    for (const value of input) {
        i++;
        $(`#${value}`).on('keypress keyup keydown change', (e) =>{
            if (!/^\d+$/.test(e.target.value)) {
                $(`#${button}`).attr('disabled', true);
                return;
            }
            if (i === input.length) $(`#${button}`).attr('disabled', false);
        });
    }
}
