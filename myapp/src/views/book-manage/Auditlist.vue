<template>
  <div>
    <el-table :data="filterTableData" style="width: 100%" row-key="id">
      <el-table-column label="实验室名称">
        <template #default="scope">
          <el-link type="primary" @click="handleOpenDialog(scope.row)">{{
            scope.row.lab.title
          }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="预约人">
        <template #header>
          <el-input v-model="search" size="small" placeholder="请搜索" />
        </template>
        <template #default="scope">
          <div>{{ scope.row.book_username }}</div>
        </template>
      </el-table-column>
      <el-table-column label="预约时间">
        <template #default="scope">
          <el-tag style="height: 40px; line-height: 20px">
            {{ moment(scope.row.book_time).format("YYYY-MM-DD") }}
            <br />
            {{ class_method(scope.row.book_class) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="预约时间">
        <template #default="scope">
          <el-tag :type="book_state_type[scope.row.book_state]">
            {{ book_state_text[scope.row.book_state] }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-popconfirm
            title="确定要批准吗?"
            @confirm="handleUpdate(scope.row, APPROVE)"
            confirm-button-text="确定"
            cancel-button-text="取消"
          >
            <template #reference>
              <el-button
                type="primary"
                round
                :disabled="scope.row.is_default ? true : false"
                >批准</el-button
              >
            </template>
          </el-popconfirm>

          <el-popconfirm
            title="确定要驳回吗?"
            @confirm="handleUpdate(scope.row, REJECT)"
            confirm-button-text="确定"
            cancel-button-text="取消"
          >
            <template #reference>
              <el-button
                type="danger"
                round
                :disabled="scope.row.is_default ? true : false"
                >驳回</el-button
              >
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 预览实验室对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="实验室位置"
      :fullscreen="true"
      v-if="dialogVisible"
    >
      <LabMap
        :x="currentItem.x"
        :y="currentItem.y"
        :t="currentItem.title"
      ></LabMap>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import {
  ClassType,
  book_state_text,
  book_state_type,
  AUDIT,
  APPROVE,
  REJECT,
} from "../../util/type";
// import LabMap from "../../components/lab-manage/LabMap.vue";
import { useUserStore } from "../../store/useUserStore";
import moment from "moment";

const tableData = ref([]);
const { user } = useUserStore();
onMounted(() => {
  getList();
});
console.log(user);
const getList = async () => {
  var res = await axios.get(`/adminapi/books?book_state=${AUDIT}`);
  console.log(res.data);
  tableData.value = res.data;
};

const class_method = (value) => {
  return ClassType.find((item) => item.value === value).label;
};

const dialogVisible = ref(false);
const currentItem = ref({});
const handleOpenDialog = (item) => {
  dialogVisible.value = true;
  currentItem.value = item.lab;
};

//搜索框
const search = ref("");
const filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      !search.value ||
      data.book_username.toLowerCase().includes(search.value.toLowerCase())
  )
);

//操作
const handleUpdate = async ({ id }, state) => {
  await axios.put(`/adminapi/books/${id}`, {
    book_state: state,
  });
  await getList();
};
</script>