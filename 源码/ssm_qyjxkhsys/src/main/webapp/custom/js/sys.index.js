/**
 * Created by admin on 2021/7/14.
 */



jQuery(function ($) {

    $('[name="begin"],[name="end"]').datepicker({
        startView: 'months',
        maxViewMode:'years',
        minViewMode:'months',
        format: 'yyyy-mm',
        language: 'zh-CN'
    });


    ace.settings.is("sidebar", "fixed");
    ace.settings.is("navbar", "fixed");

    initChart();

})

var initChart = function(){

    var ctx = $('#piechart-placeholder').css({'width': '90%', 'min-height': '250px'});

    var begin = $('[name="begin"]').val();
    var end = $('[name="end"]').val();
    begin = begin != ''?begin +"-01":null;
    end = end != ''?end +"-01":null;

    //利润统计
    $.post("/ssm_qyjxkhsys/info/profitStatistics",{begin:begin,end:end}, function (result) {
        if (result.status == STATUS.SUCCESS) {
            var labels = []
            var items = []
            _.each(result.data, function (item, index) {
                labels.push(moment(item.date).format('YYYY-MM'));
                items.push(item.profit == null ? 0 : item.profit);
            })

            var data = {
                labels: labels,
                datasets: [
                    {
                        label: "证书挂靠利润统计（元）",
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1,
                        data:items
                    }
                ]
            };

            var myBarChart = new Chart(ctx, {
                type: 'bar',
                data: data,
                options: {}
            });

        } else {
            $.error(result.message);
        }
    })
}