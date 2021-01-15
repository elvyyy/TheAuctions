function changeLanguage(lang) {
    $.ajax({
        url: '/join',
        type: "GET",
        dataType: "html",
        data: { command: 'set-locale', lang: lang},
    }).done(() => location.reload());
};