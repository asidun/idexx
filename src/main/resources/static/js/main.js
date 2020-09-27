var url = '/v1/search/';

$(document).ready(function() {
    $('.results-fields').hide();
    $('.search-button').click(function(){
        var searchTerm = $('.form-control').val();
        $('.table-body').html('');
        if(searchTerm != ''){
            $.ajax({
                        url: url+searchTerm,
                        type: "GET",
                        dataType: "json",
                        success: function(data) {
                                                 $('.table-body').html('');
                                                 var tbl_body = document.createElement("tbody");
                                                 tbl_body.className = 'table-body'
                                                 var odd_even = false;
                                                 $.each(data, function() {
                                                     var tbl_row = tbl_body.insertRow();
                                                     tbl_row.className = odd_even ? "odd" : "even";
                                                     $.each(this, function(k , v) {
                                                         var cell = tbl_row.insertCell();
                                                         cell.appendChild(document.createTextNode(v.toString()));
                                                     });
                                                     odd_even = !odd_even;
                                                 });
                                                 $('.table-body').replaceWith(tbl_body);
                                                 $('.results-fields').show();
                                                },
                        error : function(xhr, exception) {
                                                        $('.results-fields').hide();
                                                        if (xhr.status === 0) {
                                                                        alert('Not connect.\n Verify Network.');
                                                                    } else if (xhr.status == 404) {
                                                                        alert('Search term not found. [404]');
                                                                    } else if (xhr.status == 500) {
                                                                        alert('Internal Server Error [500].');
                                                                    } else if (exception === 'parsererror') {
                                                                        alert('Requested JSON parse failed.');
                                                                    } else if (exception === 'timeout') {
                                                                        alert('Time out error.');
                                                                    } else if (exception === 'abort') {
                                                                        alert('Ajax request aborted.');
                                                                    } else {
                                                                        alert('Uncaught Error.\n' + xhr.responseText);
                                                                    }
                                                    }
            });
        }
    });
});

