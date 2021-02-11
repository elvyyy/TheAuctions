;$(function () {
    var init = function () {
        $('#load-more').click(loadMoreLots);
    };

    var convertLoaderToButton = function (btn, btnClass, actionClick) {
        btn.removeClass('load-indicator');
        btn.addClass('btn');
        btn.addClass(btnClass);
        btn.text(btn.attr('data-btn-text'));
        btn.removeAttr('data-btn-text');
        btn.click(actionClick);
    };

    var convertButtonToLoader = function (btn, btnClass) {
        btn.removeClass(btnClass);
        btn.removeClass('btn');
        btn.addClass('load-indicator');
        var text = btn.text();
        btn.text('');
        btn.attr('data-btn-text', text);
        btn.off('click');
    };

    var loadMoreLots = function () {
        var btn = $('#load-more');
        convertButtonToLoader(btn, 'btn-success');
        var pageNumber = parseInt($('#lot-list').attr('data-page-number'));
        var url = location.pathname + '?command=load-more&status=3&page=' + (pageNumber + 1) + '&' + location.search.substring(1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#lot-list tbody').append(html);
                pageNumber++;
                var pageCount = parseInt($('#lot-list').attr('data-page-count'));
                $('#lot-list').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-success', loadMoreLots);
                } else {
                    btn.remove();
                }
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-success', loadMoreLots);
            },
        });
    }

    init();
});
