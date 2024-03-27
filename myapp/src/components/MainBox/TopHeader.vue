<template>
  <el-header>
    <div>智慧实验室管理平台</div>
    <div>
      <span style="line-height: 40px">欢迎{{ user.username }}登录</span>
      <el-dropdown>
        <el-avatar :size="40" :src="circleUrl" />
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>{{ user.role.roleName }}</el-dropdown-item>
            <el-dropdown-item
              v-if="user.username === 'admin'"
              @click="handleUpdate()"
              >更新学校平面图</el-dropdown-item
            >
            <el-dropdown-item @click="handleExit">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
  <el-dialog
    v-model="centerDialogVisible"
    title="更新学校平面图"
    width="50%"
    align-center
  >
    <el-upload
      class="upload-demo"
      drag
      action="/adminapi/upload"
      :data="data"
      :on-success="handleSuccess"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">将文件放到这里或 <em>点击上传</em></div>
      <template #tip>
        <div class="el-upload__tip">
          jpg/png 文件大小小于500kb的Jpg /png文件
        </div>
      </template>
    </el-upload>
  </el-dialog>
</template>

<script setup>
import { useUserStore } from "../../store/useUserStore";
import { useRouter } from "vue-router";
import { useRouterStore } from "../../store/useRouterStore";
import { ref } from "vue";
import { UploadFilled } from "@element-plus/icons-vue";
const { changeUser, user } = useUserStore();
const router = useRouter();
const { changeRouter } = useRouterStore();

const circleUrl = "/a.png";

const handleExit = () => {
  changeUser({});
  changeRouter(false);
  router.push("/login");
};

//更新学校平面图
const centerDialogVisible = ref(false);
const handleUpdate = () => {
  centerDialogVisible.value = true;
};

//更新学校平面图附带token过去
const data = {
  token: localStorage.getItem("token"),
};

const handleSuccess = () => {
  centerDialogVisible.value = false;
  window.location.reload();
};
</script>

<style lang="scss" scoped>
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #7b68ee;
  color: white;
}
</style>