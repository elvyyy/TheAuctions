;$(function () {
    var init = function () {
        document.querySelectorAll('.make-bid-btn').forEach(item => {
            item.addEventListener('click', event => {
                var bid = parseInt(event.target.getAttribute('bid'));
                var lotId = parseInt($('#lotId').val());
                $.ajax({
                    url: '/feed',
                    method: 'post',
                    dataType: 'json',
                    data: {command: 'make-bid', l: lotId, bid: bid},
                }).done(response => {
                    if (response.result == 'ok') {
                    } else {
                        alert("Ставки не принимаются");
                    }
                })
            })
        });
        lotUnit.initUnit();
        var myModal = new bootstrap.Modal(document.getElementById('change_status_modal'), {})
        myModal.show();
    };


    var lotUnit = {
        initUnit() {
            this.lotId = parseInt($('#lotId').val());
            this.openSocket();
        },

        openSocket() {
            this.webSocket = new WebSocket("ws://localhost:8081/lots/" + this.lotId);
            this.webSocket.onopen = () => this.onOpenSock();
            this.webSocket.onmessage = (ev) => this.onMessage(JSON.parse(ev.data));
            this.webSocket.onclose = ev => this.onClose();
        },

        onOpenSock() {

        },

        onMessage(msg) {
            var bid = parseFloat(msg.currentBid).toFixed(2);
            var createdBy = msg.createdBy;
            document.getElementById('currentBid').innerText = bid;
            document.getElementById('currentBidCreatedBy').innerText = createdBy;
        },

        onClose() {

        }
    }


    init();
});