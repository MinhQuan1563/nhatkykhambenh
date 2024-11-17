// Handle single delete
$('.delete').click(function() {
    const id = $(this).data('id');
    if (id) {
        $('#maXetNghiem').val(id);
        $('#deleteModal').modal('show');
    } else {
        console.error('ID không hợp lệ');
    }
});

// Handle select all checkbox
document.getElementById('selectAll').addEventListener('change', function() {
    const checkboxes = document.querySelectorAll('input[name="selectedIds"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = this.checked;
    });
});

// Handle multiple delete
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

// Handle search functionality
$("#searchButton").click(function() {
    performSearch();
});

$("#searchInput").keypress(function(event) {
    if (event.which === 13) {
        event.preventDefault();
        performSearch();
    }
});

function performSearch() {
    const query = $("#searchInput").val().trim();
    const maChiTietKhamBenh = $("#maChiTietKhamBenh").val();
    window.location.href = "/admin/khambenh/chitiet/xetnghiem?maChiTietKhamBenh=" + maChiTietKhamBenh + "&query=" + encodeURIComponent(query);
}