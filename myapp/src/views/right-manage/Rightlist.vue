<template>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <el-table-column prop="title" label="标题" width="180" />
    <el-table-column label="图标" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <el-icon size="40px">
            <component :is="mapItem[scope.row.icon]"></component>
          </el-icon>
        </div>
      </template>
    </el-table-column>

    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="primary" round @click="update(scope.row)"
          >更新</el-button
        >
        <el-popconfirm
          title="确定要删除吗?"
          @confirm="handledelete(scope.row)"
          confirm-button-text="确定"
          cancel-button-text="取消"
        >
          <template #reference>
            <el-button type="danger" round>删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="dialogVisible" title="更新权限">
    <el-form
      ref="updateFormRef"
      :model="updateForm"
      :rules="rules"
      label-width="80px"
      class="ruleForm"
      status-icon
    >
      <el-form-item label="权限名称" prop="title">
        <el-input v-model="updateForm.title" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm()"> 提交 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import axios from "axios";
import { onMounted, ref, reactive } from "vue";
import {
  User,
  HomeFilled,
  List,
  Key,
  OfficeBuilding,
  UploadFilled,
} from "@element-plus/icons-vue";
//图标映射
const mapItem = {
  User,
  HomeFilled,
  List,
  Key,
  OfficeBuilding,
  UploadFilled,
};

const tableData = ref([]);
onMounted(() => {
  getList();
});

const getList = async () => {
  const res = await axios.get("/adminapi/rights");
  tableData.value = res.data;
};

//更新
const dialogVisible = ref(false);
const updateFormRef = ref();
const currentItem = ref();
const updateForm = reactive({
  title: "",
});
const rules = reactive({
  title: [{ required: true, message: "请输入权限名称", trigger: "blur" }],
});
const update = (item) => {
  dialogVisible.value = true;
  console.log(item);
  updateForm.title = item.title;
  currentItem.value = item;
};

//提交更新
const handleConfirm = () => {
  updateFormRef.value.validate(async (valid, fields) => {
    if (valid) {
      await axios.put(`/adminapi/rights/${currentItem.value.id}`, updateForm);
      dialogVisible.value = false;
      //再次请求刷新页面
      await getList();
    } else {
      console.log("更新失败");
    }
  });
};

//删除
const handledelete = async ({ id }) => {
  console.log(id);
  await axios.delete(`/adminapi/rights/${id}`);
  await getList();
};
</script>