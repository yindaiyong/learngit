import { MessageBox } from 'element-ui';

export default {
    alert: (message = '', title = '', options = {}) => {
        if (title == '') { title = "提示" }
        options = { showClose: false }
        return MessageBox.alert(message, title, options);
    },
    confirm: (message = '', title = '', options = {}) => {
        if (title == '') { title = "提示" }
        if (message == '') { message = "确定执行此操作吗？" }
        options = { showClose: false, closeOnClickModal: false, type: 'warning' }
        return MessageBox.confirm(message, title, options);
    }
}