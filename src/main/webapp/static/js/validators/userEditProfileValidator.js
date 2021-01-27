//const {XRegExp} = require('/node_modules/xregexp');

function validate() {
    let result = validateUsername("#username", usernamePattern);
    result = validateField("#first_name", namePattern) && result;
    result = validateField("#last_name", namePattern) && result;
    result = validateField("#middle_name", middleNamePattern) && result;
    return result;
}

let namePattern = /^[a-zа-я ,.\'-]{1,25}$/i;
let middleNamePattern = /^[a-zа-я ,.\'-]{0,25}$/i;
let usernamePattern = /^[a-zA-Z0-9_]{1,16}$/;

$(document).ready(function () {
    $("#first_name").keyup(() => validateField("#first_name", namePattern));
    $("#last_name").keyup(() => validateField("#last_name", namePattern));
    $("#middle_name").keyup(() => validateField("#middle_name", middleNamePattern));
    $("#username").keyup(() => validateUsername("#username", usernamePattern));
});

function validateField(id, pattern) {
    let element = $(id);
    let str = element.val();
    if (str == null || str.trim() == "") {
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

var validateUsername = (id, pattern) => {
    $('.invalid-username-match').removeClass("d-none");
    $('.username-in-use').addClass("d-none");
    let element = $(id);
    let str = element.val();
    let result = pattern.test(str);
    if (result) {
        element.addClass('is-valid');
        element.removeClass('is-invalid');
    } else {
        element.removeClass('is-valid');
        element.addClass('is-invalid');
    }
    return result;
};