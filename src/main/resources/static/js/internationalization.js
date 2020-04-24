$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption) {
            window.location.replace('?lang=' + selectedOption);
            localStorage.setItem("locales", selectedOption);
        }
    });
});
