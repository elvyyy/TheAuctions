;$(function () {
    var init = function () {
        $('#edit-lot-form').submit(submitFormValidation);
        $('#edit-lot-form').change(submitFormValidation);
    };

    var submitFormValidation = function () {
        var strBid = $('#validationNumeric').val();
        var bid = parseFloat(strBid.replace(',', '.'));
        if (bid < 0.10) {
            $('#validationNumeric').addClass('is-invalid');
            return false;
        } else {
            $('#validationNumeric').removeClass('is-invalid');
        }
        return true;
    };

    init();
});