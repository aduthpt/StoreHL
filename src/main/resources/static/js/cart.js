$(document).ready(function() {

    $(".change-product-amount").change(function () {
        dataCartProduct = {};
        dataCartProduct.id = $(this).data("id");
        dataCartProduct.amount = $(this).val();


        NProgress.start();

        var linkPost = "/api/cart-product/update";

        axios.post(linkPost, dataCartProduct).then(function(res){
            NProgress.done();
            if(res.data.success) {
                location.reload();
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.reload();
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });
    $(".delete-cart-product").on("click",function(){
        var pdInfo = $(this).data("id");

        NProgress.start();
        var linkGet = "/api/cart-product/delete/"+pdInfo;
        axios.get(linkGet).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    })
    var x=parseFloat(document.getElementById("2").innerHTML);
    var y= x*0.1;
    var ship;
    if (x>=50){
        ship=0;
    } else ship=2
    document.getElementById("5").innerHTML=ship;
    document.getElementById("3").innerHTML=Math.round(y);
    document.getElementById("4").innerHTML=x+y+ship;


});