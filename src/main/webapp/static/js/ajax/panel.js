;$(function () {
    var init = function () {
        $('#change_status_form').submit(changeUserStatus);
        $('#change_role_form').submit(changeUserRole);
    }

    var showInvalidChangeStatusModal = function () {
        $('.failure-change-status').removeClass("d-none");
        $('.success-change-status').addClass("d-none");
        var myModal = new bootstrap.Modal(document.getElementById('change_status_modal'), {})
        myModal.show();
    };

    var showInvalidChangeRoleModal = function () {
        $('.failure-change-role').removeClass("d-none");
        $('.success-change-role').addClass("d-none");
        var myModal = new bootstrap.Modal(document.getElementById('change_role_modal'), {})
        myModal.show();
    }

    var changeUserStatus = function () {
        var username = $('#change_status_username').val();
        var status = $('#change_status_status').val();

        if (username.trim() === "") {
            showInvalidChangeStatusModal();
            return false;
        }

        $.ajax({
            url: '/feed',
            method: 'POST',
            dataType: 'json',
            data: {command: 'update-user-status', username: username, status: status},
            success: function (response) {
                if (response.result == "ok") {
                    $('.success-change-status').removeClass('.d-none');
                    $('.failure-change-status').addClass('.d-none');
                    var myModal = new bootstrap.Modal(document.getElementById('change_status_modal'), {})
                    myModal.show();
                } else {
                    $('.failure-change-status').removeClass('d-none');
                    $('.success-change-status').addClass('d-none');
                    var myModal = new bootstrap.Modal(document.getElementById('change_status_modal'), {})
                    myModal.show();
                }
            },
            error: showInvalidChangeStatusModal,
        });
        return false;
    };

    var changeUserRole = function () {
        var username = $('#change_role_username').val();
        var role = $('#change_role_role').val();

        if (username.trim() === "") {
            showInvalidChangeStatusModal();
            return false;
        }

        $.ajax({
            url: '/feed',
            method: 'POST',
            dataType: 'json',
            data: {command: 'update-user-role', username: username, role: role},
            success: function (response) {
                if (response.result == "ok") {
                    $('.success-change-role').removeClass('.d-none');
                    $('.failure-change-role').addClass('.d-none');
                    var myModal = new bootstrap.Modal(document.getElementById('change_role_modal'), {})
                    myModal.show();
                } else {
                    $('.failure-change-role').removeClass('d-none');
                    $('.success-change-role').addClass('d-none');
                    var myModal = new bootstrap.Modal(document.getElementById('change_role_modal'), {})
                    myModal.show();
                }
            },
            error: showInvalidChangeRoleModal,
        });
        return false;
    };

    init();
});