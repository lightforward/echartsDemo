{

    xAxis: {
        type: 'category',
        data: ${categories},
        axisLabel: {
            interval:0,
            rotate:20,
            textStyle: {
                fontSize : 10
            }
        }
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: ${values},
        type: 'bar',
        itemStyle: {
            normal: {
                label: {
                    show: true,
                    position: 'top',
                    textStyle: {
                        color: 'black',
                        fontSize: 10
                    }
<#--                    ,-->
<#--                    interval:0,-->
<#--                    rotate:50,-->
<#--                    textStyle: {-->
<#--                        fontSize : 15-->
<#--                    }-->
                }
            }
        }
    }]
}