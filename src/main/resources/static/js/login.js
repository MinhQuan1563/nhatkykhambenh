const togglePassword = document.querySelector("#togglePassword");
const password = document.querySelector("#passwordForm");

togglePassword.addEventListener("click", function () {
    const type = password.getAttribute("type") === "password" ? "text" : "password";
    password.setAttribute("type", type);

    this.classList.toggle('fa-eye');
    this.classList.toggle('fa-eye-slash');
});

window.onload = function () {
    var input = document.getElementById('usernameForm');
    input.addEventListener('input', function (e) {
        // Loại bỏ tất cả các ký tự không phải là số từ giá trị nhập
        this.value = this.value.replace(/\D/g, '');
    });
};

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');
    const password = document.getElementById('passwordForm');
    const rePassword = document.getElementById('rePasswordForm');

    form.addEventListener('submit', function (event) {
        if (password.value !== rePassword.value) {
            event.preventDefault(); // Ngăn chặn gửi form
            alert("Mật khẩu và mật khẩu nhập lại không khớp. Vui lòng kiểm tra lại.");
            rePassword.focus(); // Đưa con trỏ về trường nhập lại mật khẩu
        }
    });
});