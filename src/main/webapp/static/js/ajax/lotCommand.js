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

function confirm(obj) {
    var id = parseInt(obj.getAttribute('lotId'));

    let $target = $("#lot" + id);

    $.ajax({
        url: '/feed',
        method: 'post',
        dataType: 'json',
        data: {command: 'confirm-lot', lotId: id},
        success: function (response) {
            if (response.result == 'ok') {
                $target.fadeTo(300, 0.01, function(){
                    $(this).slideUp(150, function() {
                        $(this).remove();
                    });
                });
            } else if (response.result == 'fail') {
                alert("Error! Operation cannot be done now.");
            }
        },
        error: function (resp) {
            console.log('error');
        },
    })
}