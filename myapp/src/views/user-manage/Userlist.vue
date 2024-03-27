<template>
  <el-button type="primary" @click="dialogVisible = true">添加用户</el-button>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
    <el-table-column label="角色名称">
      <template #default="scope">
        {{ scope.row.role.roleName }}
      </template>
    </el-table-column>
    <el-table-column label="用户名">
      <template #default="scope">
        {{ scope.row.username }}
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button
          type="primary"
          round
          @click="update(scope.row)"
          :disabled="scope.row.is_default ? true : false"
          >更新</el-button
        >
        <el-popconfirm
          title="确定要删除吗?"
          @confirm="handledelete(scope.row)"
          confirm-button-text="确定"
          cancel-button-text="取消"
        >
          <template #reference>
            <el-button
              type="danger"
              round
              :disabled="scope.row.is_default ? true : false"
              >删除</el-button
            >
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <!-- 添加用户对话框 -->
  <el-dialog v-model="dialogVisible" title="添加用户">
    <el-form
      ref="addFormRef"
      :model="addForm"
      :rules="rules"
      label-width="80px"
      class="addForm"
      status-icon
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="addForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="addForm.password" type="password" />
      </el-form-item>
      <el-form-item label="角色" prop="roleId">
        <el-select
          v-model="addForm.roleId"
          class="m-2"
          placeholder="请选择角色"
        >
          <el-option
            v-for="item in roleListData"
            :key="item.id"
            :label="item.roleName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm()"> 提交 </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 更新用户对话框 -->
  <el-dialog v-model="dialogUpdateVisible" title="更新用户">
    <el-form
      ref="UpdateFormRef"
      :model="UpdateForm"
      :rules="rules"
      label-width="80px"
      class="addForm"
      status-icon
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="UpdateForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="UpdateForm.password" type="password" />
      </el-form-item>
      <el-form-item label="角色" prop="roleId">
        <el-select
          v-model="UpdateForm.roleId"
          class="m-2"
          placeholder="请选择角色"
        >
          <el-option
            v-for="item in roleListData"
            :key="item.id"
            :label="item.roleName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogUpdateVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUpdateConfirm()">
          更新
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import axios from "axios";
import { onMounted, ref, reactive } from "vue";
const tableData = ref([]);
const roleListData = ref([]);
onMounted(() => {
  getList();
  roleList();
});

const getList = async () => {
  const res = await axios.get("/adminapi/users");
  tableData.value = res.data;
  // console.log(res.data);
};

const roleList = async () => {
  const res = await axios.get("/adminapi/roles");
  roleListData.value = res.data;
  // console.log(res.data);
};
// 添加用户对话框
const dialogVisible = ref(false);
const addFormRef = ref();
const addForm = reactive({
  username: "",
  password: "",
  roleId: "",
});
const rules = reactive({
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
  roleId: [{ required: true, message: "请选择角色", trigger: "blur" }],
});

const handleConfirm = () => {
  addFormRef.value.validate(async (valid) => {
    if (valid) {
      // console.log(addForm);
      dialogVisible.value = false;
      await axios.post("/adminapi/users", addForm);
      await getList();
      addForm.username = "";
      addForm.password = "";
      addForm.roleId = "";
    } else {
      console.log(valid);
    }
  });
};

//更新用户
const dialogUpdateVisible = ref(false);
const UpdateFormRef = ref();
const currentUpdate = ref({});
const UpdateForm = reactive({
  username: "",
  password: "",
  roleId: "",
});

const update = (item) => {
  dialogUpdateVisible.value = true;
  UpdateForm.username = item.username;
  UpdateForm.password = item.password;
  UpdateForm.roleId = item.roleId;
  currentUpdate.value = item;
};

const handleUpdateConfirm = async () => {
  dialogUpdateVisible.value = false;
  await axios.put(`/adminapi/users/${currentUpdate.value.id}`, UpdateForm);
  await getList();
};
//删除
const handledelete = async ({ id }) => {
  await axios.delete(`/adminapi/users/${id}`);
  await getList();
};
</script>