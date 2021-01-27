const cancelIdLabel = "cancelButton";

function cancel(obj) {
    const cancelId = obj.id;
    let lotID = cancelId.slice(cancelIdLabel.length);
    let $target = $("#lot" + lotID);
    $.ajax({
        url: '/feed',
        method: 'GET',
        dataType: 'json',
        data: { command: 'cancel-lot', lotId: lotID },
    }).done(function(response) {
        if (response.result == true) {
            $target.fadeTo(300, 0.01, function(){
                $(this).slideUp(150, function() {
                    $(this).remove();
                });
            });
        }
    });
}