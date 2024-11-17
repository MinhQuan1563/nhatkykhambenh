$('.delete').click(function() {
    const id = $(this).data('id');
    if (id) {
        $('#maXetNghiem').val(id);
        $('#deleteModal').modal('show');
    } else {
        console.error('ID không hợp lệ');
    }
});

document.getElementById('selectAll').addEventListener('change', function() {
    const checkboxes = document.querySelectorAll('input[name="selectedIds"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = this.checked;
    });
});

$('.delete-all').click(function() {
    const selectedIds = [];
    $('input[name="selectedIds"]:checked').each(function() {
        selectedIds.push($(this).val());
    });
    $('#selectedIds').val(selectedIds.join(','));
    if (selectedIds.length === 0) {
        alert('Vui lòng chọn ít nhất một mục để xóa.');
        return false;
    }
    $('#deleteAllModal').modal('show');
});

$("#searchButton").click(function () {
    performSearch();
});

$("#searchInput").keypress(function (event) {
    if (event.which === 13) {
        event.preventDefault();
        performSearch();
    }
});

function performSearch() {
    var query = $("#searchInput").val();
    var maChiTietKhamBenh = $("#maChiTietKhamBenh").val();
    window.location.href = "/admin/khambenh/chitiet/xetnghiem?maChiTietKhamBenh= " + maChiTietKhamBenh +  "&query=" + query;
}