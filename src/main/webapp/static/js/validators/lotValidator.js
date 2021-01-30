$(document).ready(function (event) {
    $('#validationNumeric').keypress(function (event) {
        const str = $('validationNumeric').val();
        if (str != null || str != undefined) {
            if (str.length > 5) {
                const slice = str.slice(0, 6);
                $('#validationNumeric').val(slice);
            }
        }
    });
});

function validate() {
    if ($("#validationNumeric").val().trim().empty()) {
        return false;
    }
    return true;
}