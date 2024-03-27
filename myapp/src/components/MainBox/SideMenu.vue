<template>
  <el-aside width="200px">
    <el-scrollbar>
      <el-menu
        :default-active="route.fullPath"
        class="el-menu-vertical-demo"
        style="height: 100vh"
        :router="true"
      >
        <template v-for="data in datalist" :key="data.path">
          <el-sub-menu
            :index="data.path"
            v-if="data.children.length && checkAuto(data.path)"
          >
            <template #title>
              <el-icon>
                <component :is="mapItem[data.icon]"></component>
              </el-icon>
              <span>{{ data.title }}</span>
            </template>
            <template v-for="item in data.children" :key="item.path">
              <el-menu-item :index="item.path" v-if="checkAuto(item.path)">
                <el-icon>
                  <component :is="mapItem[item.icon]"></component>
                </el-icon>
                {{ item.title }}</el-menu-item
              >
            </template>
          </el-sub-menu>

          <el-menu-item :index="data.path" v-else-if="checkAuto(data.path)">
            <el-icon><component :is="mapItem[data.icon]"></component></el-icon>
            <span>{{ data.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<script setup>
import {
  User,
  HomeFilled,
  List,
  Key,
  OfficeBuilding,
  UploadFilled,
} from "@element-plus/icons-vue";
import axios from "axios";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { useUserStore } from "../../store/useUserStore";

const route = useRoute();
const { user } = useUserStore();
const datalist = ref([]);
onMounted(() => {
  getList();
});

const getList = async () => {
  const res = await axios.get("/adminapi/rights");
  console.log(res.data);
  datalist.value = res.data;
};

//图标映射
const mapItem = {
  User,
  HomeFilled,
  List,
  Key,
  OfficeBuilding,
  UploadFilled,
};

//检查用户权限
const checkAuto = (path) => {
  return user.role.rights.includes(path);
};
</script>