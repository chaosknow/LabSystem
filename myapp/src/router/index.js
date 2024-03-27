import { createRouter, createWebHashHistory } from "vue-router";
import Login from "../views/Login.vue";
import MainBox from "../views/MainBox.vue";
import RouterConfig from "../router/config";
import { useRouterStore } from "../store/useRouterStore";
import { useUserStore } from "../store/useUserStore";
import NotFound from "../views/notfound/NotFound.vue";
import NProgress from "nprogress";
import "nprogress/nprogress.css";
const routes = [
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/mainbox",
    name: "mainbox",
    component: MainBox,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

// 路由拦截
router.beforeEach((to, from, next) => {
  //显示进度条
  NProgress.start();
  const { isGetterRouter } = useRouterStore();
  const { user } = useUserStore();
  //判断是否已经登录
  if (to.name === "login") {
    next();
  } else {
    if (!user.role) {
      next({
        path: "/login",
      });
    } else {
      if (!isGetterRouter) {
        //注销mainbox路由
        //防止上一次登录没有刷新界面导致路由权限叠加，使得没权访问的路由也有权访问
        router.removeRoute("mainbox");
        ConfigRouter(); //动态添加路由
        next({
          path: to.fullPath, //再走一遍新的路由配置，防止第一次跳转无反应
        });
      } else {
        next();
      }
    }
  }
});

router.afterEach(() => {
  //关闭进度条
  NProgress.done();
});
//动态添加路由
const ConfigRouter = () => {
  router.addRoute("mainbox", {
    path: "/mainbox",
    name: "mainbox",
    component: MainBox,
  });
  RouterConfig.forEach((item) => {
    checkPermission(item.path) && router.addRoute("mainbox", item);
  });

  //重定向
  router.addRoute("mainbox", {
    path: "/",
    redirect: "/index",
  });

  //404
  router.addRoute("mainbox", {
    path: "/:pathMatch(.*)*",
    component: NotFound,
  });
  //查看有多少路由
  console.log(router.getRoutes());

  const { changeRouter } = useRouterStore();
  changeRouter(true); //防止路由数量不断叠加
};

//检查用户权限
const checkPermission = (path) => {
  const { user } = useUserStore();
  return user.role.rights.includes(path);
};
export default router;
