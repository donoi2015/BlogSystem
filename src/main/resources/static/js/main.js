
$(document).ready(function(e) {
    // your code here
    $('#navbar').find('li a').click(function () {
        $('#navbar').find('li').removeClass();
        $($(this).attr('href')).addClass('active');
    });
});

function goToURL(x) {
    bootbox.confirm("Are you sure?", function(result) {
        if(result) {
            location.href = '/post/delete/' + x;
        }
    });
}