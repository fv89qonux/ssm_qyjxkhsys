(function ($) {

    $.parser = {
        auto: true,
        parse: function (_2) {
            $("table.datagrid:not(.unload)", _2)["datagrid"]();
        },
        parseOptions: function (target) {

            var table = $(target);
            var options = {};
            var s = $.trim(table.attr("data-options"));
            if (s) {
                if (s.substring(0, 1) != "{") {
                    s = "{" + s + "}";
                }
                options = (new Function("return " + s))();
            }

            //各项数据配置初始化
            var columns = new Array();

            //获取column的参数
            $("th", $(target)).each(function () {
                columns.push({
                    field: $(this).attr("field"),
                    checkbox: $(this).attr("checkbox"),
                    number: $(this).attr("number"),
                    align: $(this).attr("align"),
                    sort: $(this).attr("sort"),
                    format: $(this).attr("format")
                });
            })

            options.columns = columns;

            return options;
        }
    };


    $(function () {
        if ($.parser.auto) {
            $.parser.parse();
        }
    });

    /**
     * 创建分页部分
     * @param {Object} target
     */
    function createPage(target) {
        var page = '<div class="row page hr-4">' +
            '<div class="col-md-8 ">' +
            '<select class="form-control input-xsmall input-sm input-inline page-size">' +
            '<option value="10">10</option>' +
            '<option value="20">20</option>' +
            '<option value="50">50</option>' +
            '</select> ' +
            '<a href="#" class="btn btn-sm default first"><i class="icon-double-angle-left"></i></a>' +
            '<a href="#" class="btn btn-sm default prev"><i class="icon-angle-left"></i></a>' +
            '<span> 第 ' +
            '<input  class="pagination-panel-input form-control input-mini input-inline input-sm text-center text-page" maxlenght="5" >' +
            ' 页，共 <span class="total-page"></span> 页 ' +
            '</span>' +
            '<a href="#" class="btn btn-sm default next"><i class="icon-angle-right"></i></a>' +
            '<a href="#" class="btn btn-sm default last"><i class="icon-double-angle-right"></i></a>' +
            '<a href="#" class="btn btn-sm default refresh"><i class="icon-refresh"></i></a>' +
            '</div>' +
            '<div class="col-md-4  text-right">' +
            '<div class="dataTables_info">' +
            '第 <span class="current-page"></span> 页， 显示 ' +
            '<span class="current-begin"></span> - ' +
            '<span class="current-end"></span> 条，共 <span class="total-count"></span> 条</div>' +
            '</div>' +
            '</div>';

        var _page = $(page);

        $(target).parent().append(_page);

        $.data(target, "datagrid").page = $(_page);

    }


    /**
     * 创建一个空内容的表格
     */
    function createTableWithoutTbody(target, columns) {
        var opts = $.data(target, 'datagrid').options;

        //把table放入指定的位置，并且加上效果
        $(target).addClass("table table-striped table-bordered table-hover no-margin-bottom no-border-top")
        $("tr", target).addClass("heading");


        //判断列是否需要排序，如果需要排序，那么加上效果
        //如果某一列需要checkbox，那么填入checkbox
        //对head的align进行处理，默认align
        $.each(columns, function (i) {
            var th = $("th", target).eq(i);
            if (this.sort)
                th.addClass("sorting");

            if (this.checkbox && !opts.single) {
                //此处对checkbox进行处理，设置为bs风格的checkbox
                var checkbox = ' <label>' +
                    '<input type="checkbox" class="ace" name="ids"  value="">' +
                    ' <span class="lbl"></span> </label>';
                th.append(checkbox);
            }

            if (this.number) {
                th.append("序号");
            }

            if (this.align)
                th.addClass("text-" + this.align);

        })
    }

    /**
     * 设置一些参数，比如clickrow
     */
    function setProperties(target) {
        var d = $.data(target, 'datagrid')
        var grid = d.grid;
        var page = d.page;
        var opts = d.options;
        var totalPage = d.data.totalPage;
        var data;

        if (opts.pagination) {
            data = d.data.data;
        } else {
            data = d.data;
        }

        $('tbody tr:not(".data-none")', grid).click(function () {
            var index = $(this).attr('row-index');
            if (opts.onClickRow) {
                opts.onClickRow(data[index], this);
            }
        }).dblclick(function () {
            var index = $(this).attr('row-index');
            if (opts.onDblClickRow) {
                opts.onDblClickRow(data[index], this);
            }
        });

        function onHeaderCellClick() {
            var field = $(this).attr('sortName') ? $(this).attr('sortName') : $(this).attr('field');
            var sort = $(this).attr("sort");
            if (!sort) return;

            opts.sortName = field;
            opts.sortOrder = 'asc';

            var c = 'sort-asc';
            if ($(this).hasClass('sort-asc')) {
                c = 'sort-desc';
                opts.sortOrder = 'desc';
            }
            $(this).removeClass('sort-asc');
            $(this).removeClass('sort-desc');
            $(this).addClass(c);

            request(target);
        }

        function onHeaderCheckboxClick() {

            if ($(this).prop("checked")) {
                $('tbody tr:not(".blank")', grid).each(function () {
                    //全部选中checkbox
                    var checkbox = $("input[type='checkbox'][name='ids']", $(this));
                    checkbox.prop('checked', true);
                    $(this).addClass("success")
                });
            } else {
                $('tbody tr:not(".blank")', grid).each(function () {
                    //全部选中checkbox
                    var checkbox = $("input[type='checkbox'][name='ids']", $(this));
                    checkbox.prop('checked', false);
                    $(this).removeClass("success")
                });
            }
        }

        function onRowClick() {
            var checkbox = $("input[type=checkbox][name=ids]", this);
            var checked = checkbox.prop("checked");
            $(this).toggleClass("success")
            if (checked) {
                checkbox.prop('checked', false);
            } else {
                //如果是单选，那么清除其他已选中的
                if (opts.single) {
                    $('tbody tr:not(".blank")', grid).each(function () {
                        //全部选中checkbox
                        var checkbox = $("input[type='checkbox'][name='ids']", $(this));
                        checkbox.prop('checked', false);
                        $(this).removeClass("success")
                    });
                }
                $(this).addClass("success")
                checkbox.prop('checked', true);
            }
        }

        function onCheckboxClick(e) {
            e.stopPropagation();
            $(this).prop("checked", !$(this).prop("checked"));
            $(this).parent().parent().trigger("click");
        }


        $("tbody tr:not('.data-none')", grid).on("click", onRowClick);

        $('thead th', grid).off().on('click', onHeaderCellClick);

        $('thead th input[type=checkbox][name=ids]', grid).off().on("click", onHeaderCheckboxClick);

        $('tbody td input[type=checkbox][name=ids]', grid).off().on("click", onCheckboxClick);

        function onPageSizeChange() {
            var pageSize = $(this).val();
            opts.pageSize = pageSize;
            request(target);
        }

        function onFirstPageClick() {
            opts.pageNo = 1;
            request(target);
            return false;
        }

        function onPrevPageClick() {
            opts.pageNo--;
            request(target);
            return false;
        }

        function onNextPageClick() {
            opts.pageNo++;
            request(target);
            return false;
        }

        function onLastPageClick() {
            opts.pageNo = totalPage;
            request(target);
            return false;
        }

        function onRefreshClick() {
            request(target);
            return false;
        }

        function onPageInput(e) {
            var input = $(this);
            var value = input.val();
            //只能输入数字
            if (/[^0-9]/g.test(value) || value < 1) {
                input.val("");
                return;
            }

            //输入的数字不能大于当前页数
            if (value > data.totalPage)
                input.val(data.totalPage);

            if (e.keyCode == 13) {
                input.trigger("blur");
            }
            return false;
        }

        function onPageBlur() {

            var pageNo = $(this).val();
            if (pageNo) {
                opts.pageNo = pageNo;
                request(target);
            }
            return false;
        }

        $("select.page-size", page).off().on("change", onPageSizeChange);
        $("a.first", page).off().on("click", onFirstPageClick);
        $("a.prev", page).off().on("click", onPrevPageClick);
        $("a.next", page).off().on("click", onNextPageClick);
        $("a.last", page).off().on("click", onLastPageClick);
        $("a.refresh", page).off().on("click", onRefreshClick);

        $("input.text-page").off().on("blur", onPageBlur).on("keyup", onPageInput);

    }

    /**
     * 处理树形结构的数据
     * @param data 数据
     * @returns {*}
     */
    function handleTreeData(data) {

        var idFiled = "id";
        var parentField = "pid";

        var i,
            l,
            treeData = [],
            tmpMap = [];

        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }

        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                treeData.push(data[i]);
            }
        }

        var currentData = [];

        treeDataSn(currentData, treeData);

        return currentData;
    }

    /**
     * 将树形结构的数据通过递归调用组成新的数组
     * @param data
     */
    function treeDataSn(currentData, data) {
        for (var i = 0; i < data.length; i++) {
            currentData.push(data[i]);
            if (data[i].children && data[i].children.length > 0) {
                treeDataSn(currentData, data[i].children);
            }
        }
    }

    /**
     * 加载grid的数据
     */
    function loadData(target) {
        var grid = $.data(target, 'datagrid').grid;
        var opts = $.data(target, 'datagrid').options;
        var data = $.data(target, 'datagrid').data;
        var list;

        if (opts.pagination) {
            list = data.data;
        } else {
            list = data;
        }

        function getTBody(target, columns) {
            var opts = $.data(target, 'datagrid').options;

            var tbody = ['<tbody>'];
            //如果没有数据，那么显示没有数据
            if (list == null || list.length == 0) {
                tbody.push("<tr class='blank  data-none'>");
                tbody.push("<td class='text-center' colspan='");
                tbody.push(columns.length);
                tbody.push("'>暂无数据</td></tr>");

            } else {
                for (var i = 0; i < list.length; i++) {
                    var row = list[i];

                    tbody.push('<tr data-id="' + row[opts.idField] + '" row-index="' + i + '">');

                    for (var j = 0; j < columns.length; j++) {
                        var column = columns[j];

                        tbody.push('<td');

                        if (column.align) {
                            tbody.push(" class='text-");
                            tbody.push(column.align);
                            tbody.push("' ");
                        }

                        tbody.push(">");

                        if (column.checkbox) {
                            //此处对checkbox进行处理，设置为bs风格的checkbox
                            var checkbox = ' <label>' +
                                '<input type="checkbox" class="ace" name="ids"  row-index="' + i + '" value="' + row[column.field] + '">' +
                                ' <span class="lbl"></span> </label>';
                            tbody.push(checkbox);

                        } else if (column.number) {
                            tbody.push((opts.pageNo - 1) * 10 + i + 1);
                        } else if (column.format) {
                            tbody.push(eval(column.format)(row[column.field], row));
                        } else {
                            tbody.push(row[column.field]);
                        }
                        tbody.push('</td>');
                    }

                    tbody.push('</tr>');
                }
            }

            tbody.push('</tbody>');
            return tbody.join('');
        }

        //去除旧数据
        $("tbody", grid).remove();

        grid.append(getTBody(target, opts.columns));

        setProperties(target);
    }

    function loadPage(target) {
        var page = $.data(target, 'datagrid').page;
        var opts = $.data(target, 'datagrid').options;
        var data = $.data(target, 'datagrid').data;

        //设置选择每页数量的值
        $("select.page-size option[value='" + opts.pageSize + "']", page).prop("selected", true);
        //设置返回第一页是否可用
        if (data.firstPage)
            $("a.first,a.prev", page).addClass("disabled");
        else
            $("a.first,a.prev", page).removeClass("disabled");

        if (data.lastPage)
            $("a.last,a.next", page).addClass("disabled");
        else
            $("a.last,a.next", page).removeClass("disabled");

        //设置总页数
        $("span.total-page", page).html(data.totalPage);
        //设置当前页数
        $("span.current-page", page).html(opts.pageNo);
        //设置当前条数起始和结束
        $("span.current-begin", page).html(((opts.pageNo - 1) * opts.pageSize) + 1);
        $("span.current-end", page).html(((opts.pageNo) * opts.pageSize));
        //设置总的条数
        $("span.total-count", page).html(data.total);
    }

    /**
     * 发送请求到服务器端获取数据
     */
    function request(target) {
        var opts = $.data(target, 'datagrid').options;

        var param = $.extend({}, opts.params);

        if (opts.pagination) {
            //此处将pageNo,pageSize转换成limit和offset
            $.extend(param, {
                limit: opts.pageSize,
                offset: opts.pageSize * (opts.pageNo - 1)
            });

            param.pageSize = opts.pageSize;
            param.pageNo = opts.pageNo;
        }

        if (opts.sortName) {
            $.extend(param, {
                sort: opts.sortName,
                order: opts.sortOrder
            });
        }

        $.ajax({
            type: "POST",
            url: opts.url,
            data: param,
            dataType: 'json',
            success: function (result) {
                //此处需要对数据进行特殊处理
                if (result.status) {

                    var data = result.data;


                    if (opts.pagination) {
                        //此处进行部分数据初始化，比如是否为第一页等
                        data.totalPage = data.total % opts.pageSize == 0 ? parseInt(data.total / opts.pageSize) : parseInt(data.total / opts.pageSize) + 1;

                        data.firstPage = opts.pageNo == 1;
                        data.lastPage = opts.pageNo == data.totalPage;
                    }

                    if (!opts.pagination && opts.tree) {
                        data = handleTreeData(data);
                    }

                    $.data(target, "datagrid").data = data;

                    loadData(target);

                    if (opts.pagination) {
                        loadPage(target);
                    }


                    if (opts.onLoadSuccess) {
                        opts.onLoadSuccess.apply(this, arguments);
                    }
                } else {
                    $.toast(result.message);
                }
            },
            error: function () {
                if (opts.onLoadError) {
                    opts.onLoadError.apply(this, arguments);
                } else {
                    $.toast("数据加载失败，请稍后再试");
                }
            }
        });
    }

    $.fn.datagrid = function (options, param) {

        if (typeof options == "string") {
            return $.fn.datagrid.methods[options](this, param);
        }
        options = options || {};

        return this.each(function () {

            var state = $.data(this, "datagrid"); //获取grid的各种参数数据，包含options和grid
            var opts;
            if (state) {
                opts = $.extend(state.options, options);
                state.options = opts;
            } else {
                opts = $.extend({}, $.extend({}, $.fn.datagrid.defaults, {
                    params: {}
                }), $.fn.datagrid.parseOptions(this), options);

                opts.columns = $.extend(true, [], opts.columns);

                $.data(this, "datagrid", {
                    options: opts,
                    grid: $(this),
                    data: {
                        total: 0,
                        rows: []
                    }
                });

                //绘制表格的部分代码，除去tbody之外的所有代码
                createTableWithoutTbody(this, opts.columns);

                if (opts.pagination) {
                    //绘制分页的代码
                    createPage(this);
                }


                if (opts.url) {
                    request(this);
                } else {
                    if (!opts.url) throw new Error("请为datagrid配置URL");
                }

                //setProperties(this);

            }
        });

    };

    $.fn.datagrid.parseOptions = function (target) {
        return $.extend({}, $.parser.parseOptions(target));
    };

    $.fn.datagrid.methods = {
        options: function (grids) {
            return $.data(grids[0], "datagrid").options;
        },
        /**
         * 增加获取当前所有行数据的方法。
         * @param {Object} target
         */
        getData: function (grids) {
            var target = grids[0];
            var data = $.data(target, 'datagrid').data;
            return data;
        },
        /**
         * 获取指定ID对应的行
         * @param grids
         */
        getRow: function (grids, id) {
            var grid = grids.eq(0);
            var data = grid.datagrid("getData");
            var opts = grid.datagrid("options");

            if (opts.pagination) {
                data = data.data;
            } else {
                data = data;
            }

            for (var i in data) {
                var row = data[i];
                if (row.id == id) {
                    return data[i];
                }
            }
        },
        /**
         * 获取选中的所有行，如果没有则返回[]
         * @param {Object} target
         */
        getSelectedRow: function (grids) {
            var target = grids[0];
            var data = $.data(target, 'datagrid').data;
            var opts = $.data(target, 'datagrid').options;

            if (opts.pagination) {
                data = data.data;
            } else {
                data = data;
            }

            var list = [];

            $('tbody td input[type=checkbox][name=ids]:checked', target).each(function () {
                var index = parseInt($(this).attr('row-index'));
                if (data[index]) {
                    list.push(data[index]);
                }
            });

            return list;
        },

        /**
         * 获取选中的所有行
         * @param {Object} target
         */
        getSelected: function (grids) {
            var target = grids[0];
            var data = $.data(target, 'datagrid').data;

            var opts = $.data(target, 'datagrid').options;

            if (opts.pagination) {
                data = data.data;
            } else {
                data = data;
            }


            var list = [];

            $('tbody td input[type=checkbox][name=ids]:checked', target).each(function () {
                var index = parseInt($(this).attr('row-index'));
                if (data[index]) {
                    list.push(data[index]);
                }
            });

            if (list.length == 0) {
                $.error("请选择至少一条数据");
                return;
            }

            return list;
        },

        /**
         * 获取选中的行的ids
         * @param {Object} target
         */
        getSelectedIds: function (grids) {

            var list = [];
            $('tbody td input[type=checkbox][name=ids]:checked', grids[0]).each(function () {
                list.push($(this).val());
            });

            if (list.length == 0) {
                $.error("请选择至少一条数据");
                return;
            }

            return list;

        },
        getSelectedOne: function (grids) {
            var grid = grids.eq(0);
            var row = grid.datagrid("getSelected");
            if (!row)
                return;
            if (row.length > 1) {
                $.error("只能选择一条数据");
                return;
            }
            return row[0];
        },
        getSelectedOneId: function (grids) {
            var grid = grids.eq(0);
            var row = grid.datagrid("getSelectedOne");
            if (row) {
                return row.id;
            }
        },

        /**
         * 清除选中的行
         */
        clearSelections: function (grids) {
            var grid = grids.eq(0);
            $("thead th input[type=checkbox][name=ids]", grid).prop("checked", true).trigger("click");
            return grid;

        },
        selectAll: function (grids) {
            var grid = grids.eq(0);
            $("thead th input[type=checkbox][name=ids]", grid).prop("checked", false).trigger("click");
            return grid;
        },
        load: function (grids, param) {
            return grids.each(function () {
                var opts = $(this).datagrid("options");
                if (typeof param == "string") {
                    opts.url = param;
                    param = null;
                }
                opts.pageNo = 1;

                opts.params = $.extend(opts.params, param);

                request(this);
            });
        },
        refresh: function (grids) {
            return grids.each(function () {
                var opts = $(this).datagrid("options");

                opts.pageNo = 1;

                request(this);
            });
        }
    };

    $.fn.datagrid.defaults = {
        pageNo: 1,
        pageSize: 10,
        method: 'post',
        idField: 'id',
        pagination: true,
        tree: false,
        single: false,
        onLoadSuccess: function () {
        },
        onLoadError: function () {
        },
        onClickRow: function () {
        },
        onDblClickRow: function () {
        }
    };

})(jQuery);

var DataGrid = {
    init: function () {
        $.parser.parse();
    }
}