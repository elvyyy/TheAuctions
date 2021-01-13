function changeLanguage(lang) {
    $.ajax({
        url: '/ajax/html/change/lang',
        type: "GET",
        dataType: "html",
        data: { lang: lang },
    }).done(() => location.reload());
};