var app = new Vue({
    el: "#box",
    data: {
        shopcarList: [],
        bid: "",
        total: "",
    },
    mounted() {
        this.getShopcarList();
    },
    methods: {
        buy: function () {
            axios.post("/settlement").then(
                function (response) {
                    alert(response.data.mes);
                }
            )
        },
        getShopcarList: function () {
            var that = this;
            axios.get("/getShopcarList").then(
                function (response) {
                    console.log(response);
                    that.shopcarList = response.data.shopcarsinfo;
                    console.log(that.shopcarList);
                    that.total = response.data.shopcarsinfo.total;
                }
            )
        },
        deleteShopcarOfBid: function (bid) {
            var that = this;
            axios.post("/deleteShopcarOfBid", {
                bid: that.bid,
            }).then(function (response) {
                console.log(response);
            })
        }
    }
})
// 刷新列表
app.getShopcarList();

var ul = document.querySelector("#cart_list").querySelector("ul");
var dels = document.querySelectorAll("#del");
var decrease = document.querySelectorAll("#decrease");
var add = document.querySelectorAll("#add");
var book_num = document.querySelectorAll("#book_num");
for (var i = 0; i < dels.length; i++) {
    dels[i].onclick = function () {
        ul.removeChild(this.parentNode);
    }
}
for (var i = 0; i < decrease.length; i++) {
    decrease[i].onclick = function () {
        alert(1)
        var num = parseInt(this.nextElementSibling.innerHTML);
        num--;
        this.nextElementSibling.innerHTML = num;
        console.log(parseInt(this.nextElementSibling.innerHTML));
        if (num < 1) {
            this.nextElementSibling.innerHTML = 0;
        }
    }
}
for (var i = 0; i < add.length; i++) {
    add[i].onclick = function () {
        var num = parseInt(this.previousElementSibling.innerHTML);
        num++;
        this.previousElementSibling.innerHTML = num;
        console.log(parseInt(this.previousElementSibling.innerHTML));
        if (num >= 100) {
            alert("请联系客服");
        }
    }
}

