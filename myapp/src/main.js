import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { createPinia } from "pinia";
//配置持久化
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
// 引入ElementPlus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import zhCn from "element-plus/dist/locale/zh-cn.mjs";
//粒子库
import Particles from "vue3-particles";
//导入axios配置
import "./util/axios.config";

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

createApp(App)
  .use(router)
  .use(pinia)
  .use(ElementPlus, {
    locale: zhCn,
  })
  .use(Particles)
  .mount("#app");
