//const {XRegExp} = require('/node_modules/xregexp');

function validate() {
    let result = validateEmail("#email", emailPattern);
    result = validateField("#password", passwordPattern) && result;
    result = validateUsername("#username", usernamePattern) && result;
    result = validateField("#first_name", namePattern) && result;
    result = validateField("#last_name", namePattern) && result;
    result = validateField("#middle_name", middleNamePattern) && result;
    result = confirm("#password", "#confirm_password") && result;
    return result;
}

let namePattern = /^[a-zа-я ,.\'-]{1,25}$/i;
let middleNamePattern = /^[a-zа-я ,.\'-]{0,25}$/i;
let usernamePattern = /^[a-zA-Z0-9_]{1,16}$/;
let emailPattern = /^([a-z0-9_\.-]+)@([a-z0-9_\\.-]+)\.([a-z\\.]{2,6})$/;
let passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,36}$/;

$(document).ready(function () {
    $("#first_name").change(() => validateField("#first_name", namePattern));
    $("#last_name").change(() => validateField("#last_name", namePattern));
    $("#middle_name").change(() => validateField("#middle_name", middleNamePattern));
    $("#username").change(() => validateUsername("#username", usernamePattern));
    $("#email").change(() => validateEmail("#email", emailPattern));
    $("#password").change(() => validateField("#password", passwordPattern));
    $("#confirm_password").change(() => confirm("#password", "#confirm_password"));
});

function validateField(id, pattern) {
    let element = $(id);
    let str = element.val();
    if (str == null || str == "") {
        element.removeClass('is-valid');
        element.addClass('is-invalid');
        return false;
    }
    let result = pattern.test(str);
    if (result) {
        element.removeClass('is-invalid');
        element.addClass('is-valid');
    } else {
        element.removeClass('is-valid');
        element.addClass('is-invalid');
    }
    return result;
}

function validateUsername(id, pattern) {
    $('.invalid-username-match').removeClass("d-none");
    $('.username-in-use').addClass("d-none");
    let element = $(id);
    let str = element.val();
    let result = pattern.test(str);
    if (result) {
        let username = str;
        var responseData;

        $.ajax({
            url: '/join',
            type: 'GET',
            dataType: 'json',
            async: false,
            data: {command: 'exists', username: username},
        }).done(function (response) {
            console.log(response);
            responseData = response;
            console.log("good");
        }).fail(function (response) {
            console.log(response);
            responseData = response;
            console.log("fail");
        });

        let obj = responseData;
        if (obj.numberOfUsername == 0) {
            // element.removeClass('is-invalid');
            element.addClass('is-valid');
            element.removeClass('is-invalid');
            return true;
        } else {
            element.addClass('is-invalid');
            element.removeClass('is-valid');
            $('.invalid-username-match').addClass("d-none");
            $('.username-in-use').removeClass("d-none");
            return false;
        }


    } else {
        element.removeClass('is-valid');
        element.addClass('is-invalid');
        return false;
    }
}

function validateEmail(id, pattern) {
    $('.invalid-email-match').removeClass("d-none");
    $('.email-in-use').addClass("d-none");
    let element = $(id);
    let str = element.val();
    let result = pattern.test(str);
    if (result) {
        var responseData;
        $.ajax({
            url: '/join',
            type: 'GET',
            dataType: 'json',
            async: false,
            data: {command: 'exists', email: str},
        }).done(function (response) {
            console.log(response);
            responseData = response;
            console.log("good");
        }).fail(function (response) {
            console.log(response);
            responseData = response;
            console.log("fail");
        });
        let obj = responseData;
        if (obj.numberOfEmail == 0) {
            // element.removeClass('is-invalid');
            element.addClass('is-valid');
            element.removeClass('is-invalid');
            return true;
        } else {
            element.addClass('is-invalid');
            element.removeClass('is-valid');
            $('.invalid-email-match').addClass("d-none");
            $('.email-in-use').removeClass("d-none");
            return false;
        }
    } else {
        element.removeClass('is-valid');
        element.addClass('is-invalid');
        return false;
    }
}

function confirm(idElem, idConfirmElem) {
    let elem = $(idElem);
    let confirmElem = $(idConfirmElem);
    if (elem.val() === confirmElem.val()) {
        confirmElem.removeClass("is-invalid");
        confirmElem.addClass("is-valid");
        return true;
    } else {
        confirmElem.removeClass("is-valid");
        confirmElem.addClass("is-invalid");
        return false;
    }
}