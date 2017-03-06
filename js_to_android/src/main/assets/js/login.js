
// 验证输入不为空的脚本代码
function checkForm(form) {
        if(form.username.value == "") {
           alert("用户名不能为空!");
           form.username.focus();
           return false;
        }
        if(form.password.value == "") {
           alert("密码不能为空!");
           form.password.focus();
           return false;
        }
        javascript:control.login(form.username.value,form.password.value);
        return true;
}
