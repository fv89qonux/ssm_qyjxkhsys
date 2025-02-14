if (! ("ace" in window)) {
    window.ace = {}
}
jQuery(function(a) {
    window.ace.click_event = a.fn.tap ? "tap": "click"
});
jQuery(function(a) {
    ace.handle_side_menu(jQuery);
    ace.enable_search_ahead(jQuery);
    ace.general_things(jQuery);
    ace.widget_boxes(jQuery);
    ace.widget_reload_handler(jQuery)
});
ace.handle_side_menu = function(a) {
    a("#menu-toggler").on(ace.click_event,
    function() {
        a("#sidebar").toggleClass("display");
        a(this).toggleClass("display");
        return false
    });
    var c = a("#sidebar").hasClass("menu-min");
    a("#sidebar-collapse").on(ace.click_event,
    function() {
        c = a("#sidebar").hasClass("menu-min");
        ace.settings.sidebar_collapsed(!c)
    });
    var b = navigator.userAgent.match(/OS (5|6|7)(_\d)+ like Mac OS X/i);
    a(".nav-list").on(ace.click_event,
    function(h) {
        var g = a(h.target).closest("a");
        if (!g || g.length == 0) {
            return
        }
        c = a("#sidebar").hasClass("menu-min");
        if (!g.hasClass("dropdown-toggle")) {
            if (c && ace.click_event == "tap" && g.get(0).parentNode.parentNode == this) {
                var i = g.find(".menu-text").get(0);
                if (h.target != i && !a.contains(i, h.target)) {
                    return false
                }
            }
            if (b) {
                document.location = g.attr("href");
                return false
            }
            return
        }
        var f = g.next().get(0);
        if (!a(f).is(":visible")) {
            var d = a(f.parentNode).closest("ul");
            if (c && d.hasClass("nav-list")) {
                return
            }
            d.find("> .open > .submenu").each(function() {
                if (this != f && !a(this.parentNode).hasClass("active")) {
                    a(this).slideUp(200).parent().removeClass("open")
                }
            })
        } else {}
        if (c && a(f.parentNode.parentNode).hasClass("nav-list")) {
            return false
        }
        a(f).slideToggle(200).parent().toggleClass("open");
        return false
    })
};
ace.general_things = function(a) {
    a('.ace-nav [class*="icon-animated-"]').closest("a").on("click",
    function() {
        var d = a(this).find('[class*="icon-animated-"]').eq(0);
        var c = d.attr("class").match(/icon\-animated\-([\d\w]+)/);
        d.removeClass(c[0]);
        a(this).off("click")
    });
    a(".nav-list .badge[title],.nav-list .label[title]").tooltip({
        placement: "right"
    });
    a("#ace-settings-btn").on(ace.click_event,
    function() {
        a(this).toggleClass("open");
        a("#ace-settings-box").toggleClass("open")
    });
    a("#ace-settings-navbar").on("click",
    function() {
        ace.settings.navbar_fixed(this.checked)
    }).each(function() {
        this.checked = ace.settings.is("navbar", "fixed")
    });
    a("#ace-settings-sidebar").on("click",
    function() {
        ace.settings.sidebar_fixed(this.checked)
    }).each(function() {
        this.checked = ace.settings.is("sidebar", "fixed")
    });
    a("#ace-settings-breadcrumbs").on("click",
    function() {
        ace.settings.breadcrumbs_fixed(this.checked)
    }).each(function() {
        this.checked = ace.settings.is("breadcrumbs", "fixed")
    });
    a("#ace-settings-add-container").on("click",
    function() {
        ace.settings.main_container_fixed(this.checked)
    }).each(function() {
        this.checked = ace.settings.is("main-container", "fixed")
    });
    a("#ace-settings-rtl").removeAttr("checked").on("click",
    function() {
        ace.switch_direction(jQuery)
    });
    a("#btn-scroll-up").on(ace.click_event,
    function() {
        var c = Math.min(400, Math.max(100, parseInt(a("html").scrollTop() / 3)));
        a("html,body").animate({
            scrollTop: 0
        },
        c);
        return false
    });
    try {
        a("#skin-colorpicker").ace_colorpicker()
    } catch(b) {}
    a("#skin-colorpicker").on("change",
    function() {
        var d = a(this).find("option:selected").data("skin");
        var c = a(document.body);
        c.removeClass("skin-1 skin-2 skin-3");
        if (d != "default") {
            c.addClass(d)
        }
        if (d == "skin-1") {
            a(".ace-nav > li.grey").addClass("dark")
        } else {
            a(".ace-nav > li.grey").removeClass("dark")
        }
        if (d == "skin-2") {
            a(".ace-nav > li").addClass("no-border margin-1");
            a(".ace-nav > li:not(:last-child)").addClass("light-pink").find('> a > [class*="icon-"]').addClass("pink").end().eq(0).find(".badge").addClass("badge-warning")
        } else {
            a(".ace-nav > li").removeClass("no-border margin-1");
            a(".ace-nav > li:not(:last-child)").removeClass("light-pink").find('> a > [class*="icon-"]').removeClass("pink").end().eq(0).find(".badge").removeClass("badge-warning")
        }
        if (d == "skin-3") {
            a(".ace-nav > li.grey").addClass("red").find(".badge").addClass("badge-yellow")
        } else {
            a(".ace-nav > li.grey").removeClass("red").find(".badge").removeClass("badge-yellow")
        }
    })
};
ace.widget_boxes = function(a) {
    a(document).on("hide.bs.collapse show.bs.collapse",
    function(c) {
        var b = c.target.getAttribute("id");
        a('[href*="#' + b + '"]').find('[class*="icon-"]').each(function() {
            var e = a(this);
            var d;
            var f = null;
            var g = null;
            if ((f = e.attr("data-icon-show"))) {
                g = e.attr("data-icon-hide")
            } else {
                if (d = e.attr("class").match(/icon\-(.*)\-(up|down)/)) {
                    f = "icon-" + d[1] + "-down";
                    g = "icon-" + d[1] + "-up"
                }
            }
            if (f) {
                if (c.type == "show") {
                    e.removeClass(f).addClass(g)
                } else {
                    e.removeClass(g).addClass(f)
                }
                return false
            }
        })
    });
    a(document).on("click.ace.widget", "[data-action]",
    function(o) {
        o.preventDefault();
        var n = a(this);
        var p = n.data("action");
        var b = n.closest(".widget-box");
        if (b.hasClass("ui-sortable-helper")) {
            return
        }
        if (p == "collapse") {
            var j = b.hasClass("collapsed") ? "show": "hide";
            var f = j == "show" ? "shown": "hidden";
            var c;
            b.trigger(c = a.Event(j + ".ace.widget"));
            if (c.isDefaultPrevented()) {
                return
            }
            var g = b.find(".widget-body");
            var m = n.find("[class*=icon-]").eq(0);
            var h = m.attr("class").match(/icon\-(.*)\-(up|down)/);
            var d = "icon-" + h[1] + "-down";
            var i = "icon-" + h[1] + "-up";
            var l = g.find(".widget-body-inner");
            if (l.length == 0) {
                g = g.wrapInner('<div class="widget-body-inner"></div>').find(":first-child").eq(0)
            } else {
                g = l.eq(0)
            }
            var e = 300;
            var k = 200;
            if (j == "show") {
                if (m) {
                    m.addClass(i).removeClass(d)
                }
                b.removeClass("collapsed");
                g.slideUp(0,
                function() {
                    g.slideDown(e,
                    function() {
                        b.trigger(c = a.Event(f + ".ace.widget"))
                    })
                })
            } else {
                if (m) {
                    m.addClass(d).removeClass(i)
                }
                g.slideUp(k,
                function() {
                    b.addClass("collapsed");
                    b.trigger(c = a.Event(f + ".ace.widget"))
                })
            }
        } else {
            if (p == "close") {
                var c;
                b.trigger(c = a.Event("close.ace.widget"));
                if (c.isDefaultPrevented()) {
                    return
                }
                var r = parseInt(n.data("close-speed")) || 300;
                b.hide(r,
                function() {
                    b.trigger(c = a.Event("closed.ace.widget"));
                    b.remove()
                })
            } else {
                if (p == "reload") {
                    var c;
                    b.trigger(c = a.Event("reload.ace.widget"));
                    if (c.isDefaultPrevented()) {
                        return
                    }
                    n.blur();
                    var q = false;
                    if (b.css("position") == "static") {
                        q = true;
                        b.addClass("position-relative")
                    }
                    b.append('<div class="widget-box-overlay"><i class="icon-spinner icon-spin icon-2x white"></i></div>');
                    b.one("reloaded.ace.widget",
                    function() {
                        b.find(".widget-box-overlay").remove();
                        if (q) {
                            b.removeClass("position-relative")
                        }
                    })
                } else {
                    if (p == "settings") {
                        var c = a.Event("settings.ace.widget");
                        b.trigger(c)
                    }
                }
            }
        }
    })
};
ace.widget_reload_handler = function(a) {
    a(document).on("reload.ace.widget", ".widget-box",
    function(b) {
        var c = a(this);
        setTimeout(function() {
            c.trigger("reloaded.ace.widget")
        },
        parseInt(Math.random() * 1000 + 1000))
    })
};
ace.enable_search_ahead = function(a) {
    ace.variable_US_STATES = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"];
    try {
        a("#nav-search-input").typeahead({
            source: ace.variable_US_STATES,
            updater: function(c) {
                a("#nav-search-input").focus();
                return c
            }
        })
    } catch(b) {}
};
ace.switch_direction = function(d) {
    var c = d(document.body);
    c.toggleClass("rtl").find(".dropdown-menu:not(.datepicker-dropdown,.colorpicker)").toggleClass("pull-right").end().find(".pull-right:not(.dropdown-menu,blockquote,.profile-skills .pull-right)").removeClass("pull-right").addClass("tmp-rtl-pull-right").end().find(".pull-left:not(.dropdown-submenu,.profile-skills .pull-left)").removeClass("pull-left").addClass("pull-right").end().find(".tmp-rtl-pull-right").removeClass("tmp-rtl-pull-right").addClass("pull-left").end().find(".chosen-container").toggleClass("chosen-rtl").end();
    function a(h, g) {
        c.find("." + h).removeClass(h).addClass("tmp-rtl-" + h).end().find("." + g).removeClass(g).addClass(h).end().find(".tmp-rtl-" + h).removeClass("tmp-rtl-" + h).addClass(g)
    }
    function b(h, g, i) {
        i.each(function() {
            var k = d(this);
            var j = k.css(g);
            k.css(g, k.css(h));
            k.css(h, j)
        })
    }
    a("align-left", "align-right");
    a("no-padding-left", "no-padding-right");
    a("arrowed", "arrowed-right");
    a("arrowed-in", "arrowed-in-right");
    a("messagebar-item-left", "messagebar-item-right");
    var e = d("#piechart-placeholder");
    if (e.size() > 0) {
        var f = d(document.body).hasClass("rtl") ? "nw": "ne";
        e.data("draw").call(e.get(0), e, e.data("chart"), f)
    }
};