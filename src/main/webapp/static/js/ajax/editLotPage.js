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
        } else {
            $('#validationNumeric').removeClass('is-invalid');
        }

        var lotId = parseInt($('#lotId').val());

        $.ajax({
            url: '/feed',
            method: 'POST',
            dataType: 'html',
            data: {command: 'update-lot', lotId: lotId},
            success: function (response) {
                if (response.result == 'ok') {
                    location.reload();
                }
            }
        });

        return false;
    };

    init();
});