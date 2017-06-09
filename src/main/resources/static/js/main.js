$(function() {
    $('#navbar').find('li a').click(function() {
        $('#navbar').find('li').removeClass();
        $($(this).attr('href')).addClass('active');
    });
});
