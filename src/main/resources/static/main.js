$(document).ready(function() {

    /********** Bootstrap Datepicker **************/
    // Initialize the datepickers and set validation rules
    function initializeDatepickers() {
        $("#datepicker_first").datepicker({
            autoclose: true,
            startDate: '-0m',
            format: 'dd-mm-yyyy',
            todayHighlight: true
        }).on('changeDate', function(ev) {
            validateDates();
        });

        $("#datepicker_last").datepicker({
            autoclose: true,
            startDate: '-0m',
            format: 'dd-mm-yyyy',
            todayHighlight: true
        }).on('changeDate', function(ev) {
            validateDates();
        });
    }

    function validateDates() {
        var startDate = $("#datepicker_first").datepicker('getDate');
        var endDate = $("#datepicker_last").datepicker('getDate');

        if (startDate && endDate) {
            if (endDate <= startDate) {
                alert("Last date should be greater than the first date.");
                $("#datepicker_last").val('');
                return;
            }

            var minEndDate = new Date(startDate);
            minEndDate.setMonth(minEndDate.getMonth() + 1);
            var maxEndDate = new Date(startDate);
            maxEndDate.setFullYear(maxEndDate.getFullYear() + 40);

            if (endDate < minEndDate || endDate > maxEndDate) {
                alert("The gap between the first date and the last date should be at least one month and at most 40 years.");
                $("#datepicker_last").val('');
                return;
            }
        }
    }

    initializeDatepickers();
    /********** End Bootstrap Datepicker **************/

    /********** Code for Select Card **************/
    $('.region .card').on('click', function() {
        $(".region .card").removeClass("active");
        $(this).addClass("active");
    });
    /********** End Code for Select Card **************/

    /********** Custom Dropdowns **************/
    function toggleDropdown(element, className) {
        element.click(function(e) {
            e.preventDefault();
            e.stopPropagation();
            $(this).toggleClass('expanded');
            $('#' + $(e.target).attr('for')).prop('checked', true);
        });
    }

    function closeDropdownOnClickOutside(className) {
        $(document).click(function() {
            $(className).removeClass('expanded');
        });
    }

    toggleDropdown($('.dropdown-bank'), '.dropdown-bank');
    closeDropdownOnClickOutside('.dropdown-bank');
    
    toggleDropdown($('.dropdown-auth'), '.dropdown-auth');
    closeDropdownOnClickOutside('.dropdown-auth');

    toggleDropdown($('.dropdown_acc_type'), '.dropdown_acc_type');
    closeDropdownOnClickOutside('.dropdown_acc_type');

    toggleDropdown($('.dropdown_collection_type'), '.dropdown_collection_type');
    closeDropdownOnClickOutside('.dropdown_collection_type');

    toggleDropdown($('.dropdown_collection_freq'), '.dropdown_collection_freq');
    closeDropdownOnClickOutside('.dropdown_collection_freq');

    toggleDropdown($('.dropdown_create_mandate'), '.dropdown_create_mandate');
    closeDropdownOnClickOutside('.dropdown_create_mandate');

    $('.payment-select').on('click', function() {
        $(this).toggleClass('active');
    });

    $('.payment-select ul li').on('click', function() {
        var v = $(this).text();
        $('.payment-select ul li').not($(this)).removeClass('active');
        $(this).addClass('active');
        $('.payment-select label button').text(v);
    });

    $('.bank-select').on('click', function() {
        $(this).toggleClass('active');
    });

    $('.bank-select ul li').on('click', function() {
        var v = $(this).text();
        $('.bank-select ul li').not($(this)).removeClass('active');
        $(this).addClass('active');
        $('.bank-select label button').text(v);
    });

    $('.ammount-type').on('click', function() {
        $(this).toggleClass('active');
    });

    $('.ammount-type ul li').on('click', function() {
        var v = $(this).text();
        $('.ammount-type ul li').not($(this)).removeClass('active');
        $(this).addClass('active');
        $('.ammount-type label button').text(v);
    });

    $('.collection-type').on('click', function() {
        $(this).toggleClass('active');
    });

    $('.collection-type ul li').on('click', function() {
        var v = $(this).text();
        $('.collection-type ul li').not($(this)).removeClass('active');
        $(this).addClass('active');
        $('.collection-type label button').text(v);
    });

    $('.collection-freq').on('click', function() {
        $(this).toggleClass('active');
    });

    $('.collection-freq ul li').on('click', function() {
        var v = $(this).text();
        $('.collection-freq ul li').not($(this)).removeClass('active');
        $(this).addClass('active');
        $('.collection-freq label button').text(v);
    });

    /********** End Custom Dropdowns **************/

    /********** Input Box Empty Check **************/
    $("#search_mob_no").focus(function() {
        $("#search_acc_no").val(null);
    });

    $("#search_acc_no").focus(function() {
        $("#search_mob_no").val(null);
    });
    /********** End Input Box Empty Check **************/

    /********** Search Functionality **************/
    $('#search').on('click', function() {
        var mobileInputVal = $("#search_mob_no").val();
        var accInputVal = $("#search_acc_no").val();

        if (mobileInputVal) {
            $('#result_mob_text_value').text(mobileInputVal);
            $(".tab-pane").removeClass("active");
            $("input[type='radio']").prop("checked", false);
            $(".radio_for_mob").show();
            $(".radio_for_acc").hide();
        }

        if (accInputVal) {
            $('#result_acc_text_value').text(accInputVal);
            $(".tab-pane").removeClass("active");
            $("#accont1").addClass("active");
            $("#test1").prop("checked", true);
            $(".radio_for_acc").show();
            $(".radio_for_mob").hide();
        }
    });
    /********** End Search Functionality **************/

    /********** Radio Button Selection **************/
    $("#test1").change(function() {
        $(".tab-pane").removeClass("active");
        $("#accont1").addClass("active");
    });

    $("#m_test1").change(function() {
        $(".tab-pane").removeClass("active");
        $("#m_accont1").addClass("active");
    });

    $("#m_test2").change(function() {
        $(".tab-pane").removeClass("active");
        $("#m_accont2").addClass("active");
    });

    $("#m_test3").change(function() {
        $(".tab-pane").removeClass("active");
        $("#m_accont3").addClass("active");
    });
    /********** End Radio Button Selection **************/

    /********** Back Button Functionality **************/
    $('.back-btn').on('click', function() {
        $(".tab-pane").removeClass("active");
        $("input[type='radio']").prop("checked", false);
        $("#search_mob_no").val(null);
        $("#search_acc_no").val(null);
        $(".radio_for_mob").hide();
        $(".radio_for_acc").hide();
    });
    /********** End Back Button Functionality **************/

    /********** Check Passport Functionality **************/
    $("#chkPassport").click(function() {
        if ($(this).is(":checked")) {
            $(".cta").addClass('activate');
        } else {
            $(".cta").removeClass('activate');
        }
    });
    /********** End Check Passport Functionality **************/

    /********** Additional Dropdown Functionality **************/
    function addDropdownFunctionality(dropdownClass) {
        $(dropdownClass).on('click', function() {
            $(this).toggleClass('active');
        });

        $(dropdownClass + ' ul li').on('click', function() {
            var v = $(this).text();
            $(dropdownClass + ' ul li').not($(this)).removeClass('active');
            $(this).addClass('active');
            $(dropdownClass + ' label button').text(v);
        });
    }

    addDropdownFunctionality('.payment-select');
    addDropdownFunctionality('.accountType-select');
    /********** End Additional Dropdown Functionality **************/
});
