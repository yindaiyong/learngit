import messagebox from './messagebox'
import func from './func'

const install = Vue => {
    if (install.installed)
        return;

        install.installed = true;

    Object.defineProperties(Vue.prototype, {
        // 注意，此处挂载在 Vue 原型的 $commsgbox 对象上
        $commsgbox: {
            get() {
                return messagebox
            }
        },
        $comfunc: {
            get() {
                return func
            }
        }
    })
}

export default install