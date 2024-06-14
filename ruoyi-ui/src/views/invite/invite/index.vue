<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="代理商电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入代理商电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户数量" prop="usedCount">
        <el-input
          v-model="queryParams.usedCount"
          placeholder="请输入用户数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总收益" prop="moneys">
        <el-input
          v-model="queryParams.moneys"
          placeholder="请输入总收益"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月收益" prop="monthMoneys">
        <el-input
          v-model="queryParams.monthMoneys"
          placeholder="请输入月收益"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未支付金额" prop="collectMoneys">
        <el-input
          v-model="queryParams.collectMoneys"
          placeholder="请输入未支付金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付比例" prop="ratio">
        <el-input
          v-model="queryParams.ratio"
          placeholder="请输入支付比例"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['invite:invite:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['invite:invite:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['invite:invite:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['invite:invite:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inviteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="代理商编号" align="center" prop="inviteCode" />
      <el-table-column label="代理商电话" align="center" prop="phone" />
      <el-table-column label="用户数量" align="center" prop="usedCount" />
      <el-table-column label="总收益" align="center" prop="moneys" />
      <el-table-column label="月收益" align="center" prop="monthMoneys" />
      <el-table-column label="未支付金额" align="center" prop="collectMoneys" />
      <el-table-column label="支付比例" align="center" prop="ratio" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['invite:invite:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['invite:invite:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改代理商信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="代理商电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入代理商电话" />
        </el-form-item>
        <el-form-item label="用户数量" prop="usedCount">
          <el-input v-model="form.usedCount" placeholder="请输入用户数量" />
        </el-form-item>
        <el-form-item label="总收益" prop="moneys">
          <el-input v-model="form.moneys" placeholder="请输入总收益" />
        </el-form-item>
        <el-form-item label="月收益" prop="monthMoneys">
          <el-input v-model="form.monthMoneys" placeholder="请输入月收益" />
        </el-form-item>
        <el-form-item label="未支付金额" prop="collectMoneys">
          <el-input v-model="form.collectMoneys" placeholder="请输入未支付金额" />
        </el-form-item>
        <el-form-item label="支付比例" prop="ratio">
          <el-input v-model="form.ratio" placeholder="请输入支付比例" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInvite, getInvite, delInvite, addInvite, updateInvite } from "@/api/invite/invite";

export default {
  name: "Invite",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 代理商信息表格数据
      inviteList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        phone: null,
        usedCount: null,
        moneys: null,
        monthMoneys: null,
        collectMoneys: null,
        ratio: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        phone: [
          { required: true, message: "代理商电话不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        usedCount: [
          { required: true, message: "用户数量不能为空", trigger: "blur" }
        ],
        moneys: [
          { required: true, message: "总收益不能为空", trigger: "blur" }
        ],
        monthMoneys: [
          { required: true, message: "月收益不能为空", trigger: "blur" }
        ],
        collectMoneys: [
          { required: true, message: "未支付金额不能为空", trigger: "blur" }
        ],
        ratio: [
          { required: true, message: "支付比例不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询代理商信息列表 */
    getList() {
      this.loading = true;
      listInvite(this.queryParams).then(response => {
        this.inviteList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        inviteCode: null,
        phone: null,
        createTime: null,
        usedCount: null,
        moneys: null,
        monthMoneys: null,
        collectMoneys: null,
        ratio: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.inviteCode)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加代理商信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inviteCode = row.inviteCode || this.ids
      getInvite(inviteCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改代理商信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.inviteCode != null) {
            updateInvite(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInvite(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const inviteCodes = row.inviteCode || this.ids;
      this.$modal.confirm('是否确认删除代理商信息编号为"' + inviteCodes + '"的数据项？').then(function() {
        return delInvite(inviteCodes);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('invite/invite/export', {
        ...this.queryParams
      }, `invite_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
