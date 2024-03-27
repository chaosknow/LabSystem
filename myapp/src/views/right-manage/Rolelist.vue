<template>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <!-- <el-table-column prop="roleName" label="角色名称" width="180" /> -->
    <el-table-column label="角色名称">
      <template #default="scope">
        <el-popover
          placement="right"
          title="权限"
          :width="200"
          trigger="hover"
          @before-enter="handleHover(scope.row)"
        >
          <template #reference>
            <el-button class="m-2">{{ scope.row.roleName }}</el-button>
          </template>
          <template #default>
            <el-tree
              :data="rigthList"
              :props="defaultProps"
              @node-click="handleNodeClick"
              :default-expand-all="true"
              :render-content="renderContent"
            />
          </template> </el-popover
      ></template>
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
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="updateForm.roleName" />
      </el-form-item>
      <el-form-item label="角色权限" prop="rights">
        <el-tree
          :data="rigthList"
          :props="defaultProps"
          show-checkbox
          node-key="path"
          ref="treeRef"
          :check-strictly="true"
        />
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
import { onMounted, ref, reactive, nextTick } from "vue";
const rigthList = ref([]);
const tableData = ref([]);
onMounted(() => {
  getList();
  getRightList();
});

const getList = async () => {
  const res = await axios.get("/adminapi/roles");
  tableData.value = res.data;
  console.log(res.data);
};

const getRightList = async () => {
  const res = await axios.get("/adminapi/rights");
  rigthList.value = res.data;
};

const defaultProps = {
  children: "children",
  label: "title",
};

const RightPath = ref([]);
const handleHover = (item) => {
  console.log(item);
  RightPath.value = item.rights;
};
//自定义tree结点函数
const renderContent = (h, { node, data, store }) => {
  return h(
    "span",
    {
      class: `custom-tree-node ${
        RightPath.value.includes(data.path) ? "active" : ""
      }`,
    },
    h("span", null, node.label)
  );
};

//更新
const dialogVisible = ref(false);
const updateFormRef = ref();
const currentItem = ref();
const updateForm = reactive({
  roleName: "",
});
const rules = reactive({
  roleName: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
});
const update = (item) => {
  dialogVisible.value = true;
  console.log(item);
  updateForm.roleName = item.roleName;
  currentItem.value = item;

  //设置rigths给tree
  //nextTick等待dom执行完在执行nextTick
  nextTick(() => {
    treeRef.value.setCheckedKeys(JSON.parse(item.rights));
  });
};

//提交更新
const handleConfirm = async () => {
  // console.log(updateForm.roleName, treeRef.value.getCheckedKeys());
  await axios.put(`/adminapi/roles/${currentItem.value.id}`, {
    roleName: updateForm.roleName,
    rights: JSON.stringify(treeRef.value.getCheckedKeys()),
  });

  getList();

  dialogVisible.value = false;
};

const treeRef = ref();

//删除
const handledelete = async ({ id }) => {
  console.log(id);
  await axios.delete(`/adminapi/roles/${id}`);
  await getList();
};
</script>

<style lang="scss" scoped >
:deep(.active) {
  color: #f56c6c;
}
</style>