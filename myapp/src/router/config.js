import Home from "../views/home/Home.vue";
//用户管理
import Userlist from "../views/user-manage/Userlist.vue";
//权限管理
import Rightlist from "../views/right-manage/Rightlist.vue";
import Rolelist from "../views/right-manage/Rolelist.vue";
//实验室管理
import Lablist from "../views/lab-manage/Lablist.vue";
import Addlab from "../views/lab-manage/Addlab.vue";
//预约管理
import Auditlist from "../views/book-manage/Auditlist.vue";
import Booklist from "../views/book-manage/Booklist.vue";
import Addbook from "../views/book-manage/Addbook.vue";

const routes = [
  {
    path: "/index",
    component: Home,
  },
  {
    path: "/user-manage/list",
    component: Userlist,
  },
  {
    path: "/right-manage/rightlist",
    component: Rightlist,
  },
  {
    path: "/right-manage/rolelist",
    component: Rolelist,
  },
  {
    path: "/lab-manage/lablist",
    component: Lablist,
  },
  {
    path: "/lab-manage/addlab",
    component: Addlab,
  },
  {
    path: "/book-manage/auditlist",
    component: Auditlist,
  },
  {
    path: "/book-manage/booklist",
    component: Booklist,
  },
  {
    path: "/book-manage/addbook",
    component: Addbook,
  },
];
export default routes;
