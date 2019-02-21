/**
 * report页面的下拉事件
 */
$(function () {
    $("#selectCityId").change(function () {
        var cityId = $("#selectCityId").val();
        var url = '/report/cityId/' + cityId;
        console.log("url->"+url);
        window.location.href = url;
    })
});
